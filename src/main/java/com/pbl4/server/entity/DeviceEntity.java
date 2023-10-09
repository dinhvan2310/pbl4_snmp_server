package com.pbl4.server.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

// @Data để nó tự tạo Getter, Setter
// @Document cho MongoDb, collection = "devices" đặt tên trong csdl
@Document(collection = "devices")
@Data
public class DeviceEntity {
    @Id
    private int Id;
    private DeviceSetting deviceSettings;
    private List<DeviceOID> deviceOIDS;


    // Nest Class
    @Data
    @Builder
    public static class DeviceSetting{
        private String deviceName;
        private String deviceType;
        private String deviceIpv4;
        private int priority;
        private int timeOut;
        private String snmpVersion;
        private String snmpCommunity;
        private int snmpPort;
    }

    @Data
    @Builder
    public static class DeviceOID{
        private List<DeviceOIDValue> deviceOIDValues;
        private DeviceOIDSettings deviceOIDSettings;
        private List<DeviceOID> childDeviceOID;
    }

    @Data
    @Builder
    public static class DeviceOIDValue{
        private LocalDateTime dateTime;
        private String value;
    }

    @Data
    @Builder
    public static class DeviceOIDSettings{
        private String sensorName;
        private String oidKey;
        private int priority;
        private String valueType;
        private String unit;
    }
}
