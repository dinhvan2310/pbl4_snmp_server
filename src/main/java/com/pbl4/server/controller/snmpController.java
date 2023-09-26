package com.pbl4.server.controller;

import com.pbl4.server.DTO.snmpDTO;
import com.pbl4.server.sevices.snmpServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class snmpController {
    private final snmpServices snmpServices;
    public snmpController(snmpServices snmpServices) {
        this.snmpServices = snmpServices;
    }

    @GetMapping("api/snmp/{ip}/{oid}/{community}")
//    @CrossOrigin(origins = "*")
    public ResponseEntity<snmpDTO> snmpGet(@PathVariable String ip, @PathVariable String oid, @PathVariable String community) {
        return ResponseEntity.ok(snmpServices.performSNMPGet(ip, oid, community));
    }
}
