package com.pbl4.server.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class SnmpValueModel {
    private String ipAddress;
    private String oidKey;
    private String communityString;
    private String value;
    private boolean isUp;

    public LocalDateTime getDateTime() {
        return LocalDateTime.now();
    }

}
