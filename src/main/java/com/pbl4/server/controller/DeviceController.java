package com.pbl4.server.controller;

import com.pbl4.server.entity.DeviceEntity;
import com.pbl4.server.model.DeviceModel;
import com.pbl4.server.repository.DeviceRepository;
import com.pbl4.server.sevices.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    private final DeviceService deviceService;

    public DeviceController(DeviceRepository deviceRepository, DeviceService deviceService){
        this.deviceService=deviceService;
    }

    @GetMapping("")
    public ResponseEntity<List<DeviceModel>> getAllDevices(){
        return ResponseEntity.ok().body(deviceService.getAllDevices());
    }

    @GetMapping("/{id}/settings")
    @CrossOrigin("*")
    public ResponseEntity<DeviceEntity.DeviceSettings> getDeviceSettingsById(@PathVariable String id){
        DeviceEntity.DeviceSettings settings = deviceService.getDeviceSettingsById(id);
        if(settings==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(settings);
    }

    @PostMapping("")
    @CrossOrigin("*")
    public ResponseEntity<DeviceEntity> addDevice(@RequestBody DeviceEntity device){
        return ResponseEntity.ok().body(deviceService.addDevice(device));
    }

    @PostMapping("/{id}/settings")
    @CrossOrigin("*")
    public ResponseEntity<DeviceEntity.DeviceSettings> updateDeviceSettings(@PathVariable String id, @RequestBody DeviceEntity.DeviceSettings settings){
        DeviceEntity.DeviceSettings updatedSettings = deviceService.updateDeviceSettingsById(id, settings);
        if(updatedSettings==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedSettings);
    }

    @PostMapping("/{id}/oids")
    @CrossOrigin("*")
    public ResponseEntity<DeviceEntity.DeviceOID> addDeviceOIDById(@PathVariable String id, @RequestBody DeviceEntity.DeviceOID deviceOID){
        DeviceEntity.DeviceOID updatedDeviceOID = deviceService.addDeviceOIDById(id, deviceOID);
        if(updatedDeviceOID==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(updatedDeviceOID);
    }

    @GetMapping("/{id}/oids")
    @CrossOrigin("*")
    public ResponseEntity<List<DeviceEntity.DeviceOID>> getAllDeviceOIDById(@PathVariable String id){
        List<DeviceEntity.DeviceOID> deviceOIDs = deviceService.getAllDeviceOIDById(id);
        return ResponseEntity.ok().body(deviceOIDs);
    }

//    @GetMapping("/{id}/oids/{oidId}")
//    @CrossOrigin("*")
//    public ResponseEntity<DeviceEntity.DeviceOIDValue> getDeviceOIDValueById(@PathVariable String id, @PathVariable int oidId){
//        DeviceEntity.DeviceOIDValue deviceOIDValue = deviceService.getDeviceOIDValueById(id, oidId);
//        if(deviceOIDValue==null){
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(deviceOIDValue);
//    }
}
