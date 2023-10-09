package com.pbl4.server.controller;

import com.pbl4.server.entity.DeviceEntity;
import com.pbl4.server.model.snmpDTO;
import com.pbl4.server.repository.DeviceRepository;
import com.pbl4.server.sevices.SnmpServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/snmp/")
public class SnmpController {
    private final SnmpServices snmpServices;
    private final DeviceRepository deviceRepository;
    public SnmpController(SnmpServices snmpServices, DeviceRepository deviceRepository) {
        this.snmpServices = snmpServices;
        this.deviceRepository = deviceRepository;
    }

    @GetMapping("{ip}/{oid}/{community}")
//    @CrossOrigin(origins = "*")
    public ResponseEntity<snmpDTO> snmpGet(@PathVariable String ip, @PathVariable String oid, @PathVariable String community) {
        return ResponseEntity.ok(snmpServices.performSNMPGet(ip, oid, community));
    }


    // localhost/api/snmp/demo
    // deviceRepository.findALl() trả về toàn bộ device á
    // deviceRepository còn nhiều method khác nữa á nghiên cứu thử

    // vd update csdl nó là như này:
    // DeviceEntity device = deviceRepository.findAll().get(0);
    //        device.getDeviceSettings().setTimeOut(5);
    //        deviceRepository.save(device);
    @GetMapping("demo")
    public ResponseEntity<List<DeviceEntity>> demo()
    {
        return ResponseEntity.ok(deviceRepository.findAll());
    }


}
