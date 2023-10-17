package com.pbl4.server.sevices;
import com.pbl4.server.model.SnmpValueModel;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.springframework.stereotype.Service;

@Service
public class SnmpServices {


    public SnmpValueModel performSNMPV2cGet(String ipAddress, String oid, String community) {
        try {
            // Tạo địa chỉ để kết nối đến agent SNMP
            Address targetAddress = GenericAddress.parse("udp:" + ipAddress + "/161");

            // Tạo phiên SNMP
            TransportMapping<?> transport = new DefaultUdpTransportMapping();
            Snmp snmp = new Snmp(transport);
            transport.listen();

            // Tạo đối tượng CommunityTarget để xác định cộng đồng SNMP và đích đến
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(new OctetString(community));
            target.setAddress(targetAddress);
            target.setVersion(SnmpConstants.version2c);

            // Tạo đối tượng PDU cho yêu cầu GET
            PDU pdu = new PDU();
            pdu.setType(PDU.GET);
            pdu.add(new VariableBinding(new OID(oid)));

            // Gửi yêu cầu GET và nhận phản hồi
            ResponseEvent response = snmp.send(pdu, target);
            PDU responsePDU = response.getResponse();

            // Kiểm tra phản hồi
            if (responsePDU != null) {
                if (responsePDU.getErrorStatus() == PDU.noError) {
                    // Lấy giá trị từ phản hồi
                    VariableBinding vb = responsePDU.getVariableBindings().get(0);
                    return new SnmpValueModel(ipAddress, oid, community, vb.getVariable().toString(), true);
                } else {
                    return new SnmpValueModel(ipAddress, oid, community, "Error: " + responsePDU.getErrorStatusText(), false);
                }
            } else {
                return new SnmpValueModel(ipAddress, oid, community, "Error: Timeout exceeded", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new SnmpValueModel(ipAddress, oid, community, "Error: " + e.getMessage(), false);
        }
    }
}
