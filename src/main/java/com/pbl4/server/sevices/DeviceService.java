package com.pbl4.server.sevices;

import com.pbl4.server.entity.DeviceEntity;
import com.pbl4.server.model.DeviceModel;
import com.pbl4.server.model.SnmpValueModel;
import com.pbl4.server.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final SnmpServices snmpServices;
    @Autowired
    public DeviceService(DeviceRepository deviceRepository, SnmpServices snmpServices){

        this.deviceRepository=deviceRepository;
        this.snmpServices = snmpServices;
    }

    public DeviceEntity addDevice(DeviceEntity device){
        return deviceRepository.save
                (
                    DeviceEntity.builder()
                        .deviceSettings(device.getDeviceSettings())
                        .deviceOIDS(Collections.emptyList())
                        .build()
                );
    }

    public List<DeviceModel> getAllDevices(){
        List<DeviceModel> deviceModels = deviceRepository.findAll().stream().map(device -> {
            return DeviceModel.builder()
                    .deviceName(device.getDeviceSettings().getDeviceName())
                    .deviceIcon(device.getDeviceSettings().getDeviceIcon())
                    .deviceId(device.get_id())
                    .build();
        }).toList();
        if(deviceModels.isEmpty()){
            return Collections.emptyList();
        }
        return deviceModels;
    }

    public DeviceEntity.DeviceSettings getDeviceSettingsById(String id){
        return deviceRepository.findById(id)
                .map(DeviceEntity::getDeviceSettings)
                .orElse(null);
    }

    public DeviceEntity.DeviceSettings updateDeviceSettingsById(String id, DeviceEntity.DeviceSettings settings){
        return deviceRepository.findById(id)
                .map(device -> {
                    device.setDeviceSettings(settings);
                    return deviceRepository.save(device).getDeviceSettings();
                })
                .orElse(null);
    }

    public DeviceEntity.DeviceOID addDeviceOIDById(String id, DeviceEntity.DeviceOID deviceOID){
        return deviceRepository.findById(id)
                .map(device -> {
                    if(device.getDeviceOIDS()==null){
                        device.setDeviceOIDS(Collections.emptyList());
                    }
                    deviceOID.setDeviceOIDId(device.getDeviceOIDS().size());
                    device.getDeviceOIDS().add(deviceOID);
                    return deviceRepository.save(device).getDeviceOIDS().get(device.getDeviceOIDS().size()-1);
                })
                .orElse(null);
    }

    public List<DeviceEntity.DeviceOID> getAllDeviceOIDById(String id){
        return deviceRepository.findById(id)
                .map(DeviceEntity::getDeviceOIDS)
                .orElse(Collections.emptyList());
    }

    public DeviceEntity.DeviceOID getDeviceOIDById(String id, int deviceOIDId){
        return deviceRepository.findById(id)
                .map(device -> {
                    if(device.getDeviceOIDS()==null){
                        device.setDeviceOIDS(Collections.emptyList());
                    }
                    return device.getDeviceOIDS().get(deviceOIDId);
                })
                .orElse(null);
    }

//    public DeviceEntity.DeviceOID getDeviceOIDValueById(String id, int deviceOIDId){
//        // tìm device theo id
//        return deviceRepository.findById(id)
//                .map(device -> {
//                    // check null deviceOIDS
//                    if(device.getDeviceOIDS()==null){
//                        // set deviceOIDS = emptyList nếu null, do không hiểu ren mà nó lại null
//                        device.setDeviceOIDS(Collections.emptyList());
//                    }
//                    DeviceEntity.DeviceOID deviceOID = device.getDeviceOIDS().get(deviceOIDId);
//
//                    // lấy thông tin cần thiết
//                    String ipv4 = device.getDeviceSettings().getDeviceIpv4();
//                    String oidKey = device.getDeviceOIDS().get(deviceOIDId).getDeviceOIDSettings().getOidKey();
//                    String community = device.getDeviceSettings().getSnmpCommunity();
//                    // lấy giá trị, có trường up để check up(true/false), up = false nếu không lấy được giá trị
//                    // và dateTime để check thời gian lấy giá trị và value
//                    SnmpValueModel snmpValue = snmpServices.performSNMPV2cGet(ipv4, oidKey, community);
//
//                    // check up
//                    if(!snmpValue.isUp()){
//                        device.getDeviceOIDS().get(deviceOIDId).getDeviceOIDSettings().setPause(true);
//                        deviceOID.getDeviceOIDSettings().setPause(true);
//                        return DeviceEntity.DeviceOID.builder()
//                                .deviceOIDSettings(deviceOID.getDeviceOIDSettings())
//                                .deviceOIDId(deviceOID.getDeviceOIDId())
//                                .childDeviceOID(deviceOID.getChildDeviceOID())
//                                .deviceOIDValues(Collections.singletonList(DeviceEntity.DeviceOIDValue.builder()
//                                        .dateTime(snmpValue.getDateTime())
//                                        .value("Unreachable")
//                                        .build()))
//                                .build();
//
//                    }
//
////                    // check pause
////                    if(!device.getDeviceSettings().isMonitorStatus()){
////                        device.getDeviceOIDS().forEach(deviceOID -> {
////                            deviceOID.getDeviceOIDSettings().setPause(true);
////                        });
////                        return DeviceEntity.DeviceOIDValue.builder()
////                                .dateTime(snmpValue.getDateTime())
////                                .value("Pause")
////                                .build();
////                    }
////                    if(device.getDeviceOIDS().get(deviceOIDId).getDeviceOIDSettings().isPause()){
////                         return DeviceEntity.DeviceOIDValue.builder()
////                                .dateTime(snmpValue.getDateTime())
////                                .value("Pause")
////                                .build();
////                    }
////                    // check value type
////                    if(device.getDeviceOIDS().get(deviceOIDId).getDeviceOIDSettings().getValueType().equals("String")){
////                        return DeviceEntity.DeviceOIDValue.builder()
////                                .dateTime(snmpValue.getDateTime())
////                                .value(snmpValue.getValue())
////                                .build();
////                    }
////
////                    // save value
////                    device.getDeviceOIDS().get(deviceOIDId).getDeviceOIDValues().add(0, DeviceEntity.DeviceOIDValue.builder()
////                            .dateTime(snmpValue.getDateTime())
////                            .value(snmpValue.getValue())
////                            .build());
////                    deviceRepository.save(device);
////                    return device.getDeviceOIDS().get(deviceOIDId).getDeviceOIDValues().get(0);
//                })
//                .orElse(null);
//    }


//    public DeviceEntity getDeviceById(int id){
//        DeviceEntity device = deviceRepository.findById(id);
//        if(device==null){
//            throw new DeviceNotFoundException("Device with ID " + id + " not found");
//        }
//        return device;
//
//    }
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//    /ublic DeviceEntity.DeviceSetting getDeviceSettingById(int id){
//        return Optional.ofNullable(getDeviceById(id))
//                .map(DeviceEntity::getDeviceSettings)
//                .orElse(null);

//    }
//    public List<DeviceEntity.DeviceOID> getDeviceOIDById(int id){
//        return Optional.ofNullable(getDeviceById(id))
//                .map(DeviceEntity::getDeviceOIDS)
//                .orElse(Collections.emptyList());

//    }
//    public DeviceEntity saveDevice(DeviceEntity device){
//        return deviceRepository.save(device);
//    }
//    public void deleteDevice(Integer id){
//        deviceRepository.deleteById(id);
//    }
//
//    public static class DeviceNotFoundException extends RuntimeException {
//        public DeviceNotFoundException(String message) {
//            super(message);
//        }
//    }
}
