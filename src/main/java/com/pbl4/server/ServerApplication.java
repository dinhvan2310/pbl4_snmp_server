package com.pbl4.server;

import com.pbl4.server.entity.DeviceEntity;
import com.pbl4.server.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableMongoRepositories
public class ServerApplication implements CommandLineRunner {

	// Lấy Repository ra để thao tác với DB
	@Autowired
	private DeviceRepository deviceRepository;
	public ServerApplication(DeviceRepository deviceRepository)
	{
		this.deviceRepository = deviceRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}


	// Thêm Device mới vô DB
	@Override
	public void run(String... args) throws Exception {
		// Tạo Device
		DeviceEntity device = new DeviceEntity();
		// Set Id cho hén bằng hashCode của obj phía trên á
		device.setId(device.hashCode());
		// Set Settings cho device
		// C1: xài builder như bên dưới (nhờ cái annotation @Builder ở bên khai báo Entity)
		// C2: xài constructor (gà), nhớ viết constructor để xài, bên entity chưa có viết
		// device.setDeviceSettings(new DeviceEntity.DeviceSetting("R1", "Router", "192.168.137.1", ...))
		device.setDeviceSettings(DeviceEntity.DeviceSetting.builder()
				.deviceName("R1")
				.deviceType("Router")
				.deviceIpv4("192.168.147.1")
				.priority(5)
				.timeOut(30)
				.snmpVersion("v2c")
				.snmpCommunity("public")
				.snmpPort(161).build()
		);
		// Set tập OID cho Device, gán cho hén cái mảng rỗng trước, chút add pt vô sau (1)
		device.setDeviceOIDS(new ArrayList<DeviceEntity.DeviceOID>());

		// Tạo DeviceOID mới, xài builder hoặc constructor, để add vô cái mảng rỗng ngay phía trên (2)
		DeviceEntity.DeviceOID deviceOID = DeviceEntity.DeviceOID.builder()
				// Set Settings cho DeviceOID, có 2 cái settings: cái của Device, cái của DeviceOID
				.deviceOIDSettings(DeviceEntity.DeviceOIDSettings.builder()
						.sensorName("sysName")
						.oidKey(".1.3.6.1.2.1.1.5.0")
						.priority(5)
						.valueType("String")
						.unit("")
						.build())
				// Set mảng rỗng deviceOIDValues, ít bữa lưu value với thời gian quét vô đây
				.deviceOIDValues(new ArrayList<DeviceEntity.DeviceOIDValue>())
				.build();

		// (1)(2)
		device.getDeviceOIDS().add(deviceOID);

		// Save vô CSDL,
		deviceRepository.save(device);
	}
}
