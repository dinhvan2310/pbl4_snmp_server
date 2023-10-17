package com.pbl4.server.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeviceModel {
    private String deviceId;
    private String deviceName;
    private String deviceIcon;
}
