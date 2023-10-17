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
@Builder
public class DeviceEntity {
    @Id
    private String _id;
    private DeviceSettings deviceSettings;
    private List<DeviceOID> deviceOIDS;


    @Data
    @Builder
    public static class DeviceSettings{
        private String deviceName;
        private String deviceType;
        private String deviceIpv4;
        private int priority;
        private String deviceIcon;
        private boolean monitorStatus;
        private String snmpVersion;
        private String snmpCommunity;
        private int snmpPort;
    }

    @Data
    @Builder
    public static class DeviceOID{
        private int deviceOIDId;
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
        private String status;
        private String unit;
    }
}
