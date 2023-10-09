package com.pbl4.server.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class snmpDTO {
    private String ip;
    private String oid;
    private String community;
    private String value;
}
