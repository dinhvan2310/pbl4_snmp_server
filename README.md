# Table of Contents

1. [Tai liệu về OID](#tailieuveoid)
2. [OID Repository](#oidrepository)
3. [Cấu hình SNMP trên router/switch](#cauhinhsnmp)

## Tài liệu về OID <a name="tailieuveoid"></a>

- [Tài liệu về OID](./Aplication_Guide_SNMP%20OID.pdf)
  > Đại khái là OID (Object Identifier) là một chuỗi mã duy nhất để xác định một object. Object là dạng dữ liệu đơn giản (text,...) chứa 1 thông tin nào đó (vd: Tên thiết bị). 
  
  > Vd: OID .1.3.6.1.2.1.1.5.0 là sysName (Tên của thiết bị).


  > Mỗi thiết bị mạng thì hén được cấu hình sẵn một số mã oid cho nó rồi, tuỳ các thiết bị khác nhau mà nó sẽ có tập mã oid khác nhau. Khi mà cái đứa quản lý gởi request snmp có mã oid tới thiết bị mạng, nếu mà thiết bị đó có cấu hình mã oid nớ thì nó sẽ trả response có thông tin về cho đứa quản lý, còn không thì nó sẽ báo lỗi về.

  > ![Alt text](./src/image/image.png).

  > Mà thường sẽ luôn có dãy mã oid từ 1.3.6.1.2 (mgmt - management) trở xuống (chắc là quy ước chung), có khi rõ trong tài liệu á. 1.3.6.1.4 (private) là dãy mã dành riêng cho mấy công ty tạo ra thiết bị mạng đó tự định nghĩa.

  ![Alt text](./src/image/image-1.png)



## OID Repository <a name="oidrepository"></a>

- [OID Repository](http://www.oid-info.com/cgi-bin/display?tree=)

   > Cái ni để tra thông tin toàn bộ mã OID, nhiều lắm, éo biết nữa, cuối kì làm báo cáo rồi tính

  ![Tree Display](./src/image/Tree%20display.png)


## Cấu hình SNMP trên router/switch <a name="cauhinhsnmp"></a>

- [Cấu hình SNMP trên router/switch](https://sss.net.vn/docs/cau-hinh-snmp-tren-router-switch-dua-tren-ios-cua-cisco)
