package com.xforceplus.data.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 维修记录
 * Created by Administrator on 2018/5/5 0005.
 */
@Entity
@Table(name="maintenance_record",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"unit_id", "license_plate_number "})})
public class MaintenanceRecord implements Serializable {
    private static final long serialVersionUID = 6748417636267487017L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    /**
     * 单位id
     */
    @Column(name = "unit_id",length = 3)
    private Integer unitId;
    /**
     * 车牌号
     */
    @Column(name = "license_plate_number",length = 10)
    private String licensePlateNumber;
    /**
     * 司机名称
     */
    @Column(name = "driver_name",length = 4)
    private String driverName;
    /**
     * 库房号
     */
    @Column(name = "store_room",length = 4)
    private Integer storeRoom;
    /**
     * 车辆类型
     */
    @Column(name = "vehicle_type",length = 10)
    private String vehicleType;

    /**
     * 配件id
     */
    @Column(name = "accessories_id",length = 10)
    private String accessoriesId;
    /**
     * 配件使用情况
     */
    @Column(name = "use_of_accessories",length = 100)
    private String useOfAccessories;
    /**
     * 配件缺少情况
     */
    @Column(name = "lack_of_accessories",length = 1)
    private Integer lackOfAccessories;
    /**
     * 维修价格
     */
    @Column(name = "maintenance_price")
    private double maintenancePrice;
    /**
     * 维修时间
     */
    @Column(name = "maintenance_time")
    private String maintenanceTime;
    /**
     * 备注
     */
    @Column(name = "remark",length = 500)
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getMaintenanceTime() {
        return maintenanceTime;
    }

    public void setMaintenanceTime(String maintenanceTime) {
        this.maintenanceTime = maintenanceTime;
    }

    public String getUseOfAccessories() {
        return useOfAccessories;
    }

    public void setUseOfAccessories(String useOfAccessories) {
        this.useOfAccessories = useOfAccessories;
    }

    public Integer getLackOfAccessories() {
        return lackOfAccessories;
    }

    public Integer getStoreRoom() {
        return storeRoom;
    }

    public void setStoreRoom(Integer storeRoom) {
        this.storeRoom = storeRoom;
    }

    public double getMaintenancePrice() {
        return maintenancePrice;
    }

    public void setMaintenancePrice(double maintenancePrice) {
        this.maintenancePrice = maintenancePrice;
    }

    public void setLackOfAccessories(Integer lackOfAccessories) {
        this.lackOfAccessories = lackOfAccessories;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getAccessoriesId() {
        return accessoriesId;
    }

    public void setAccessoriesId(String accessoriesId) {
        this.accessoriesId = accessoriesId;
    }

    public MaintenanceRecord() {
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public MaintenanceRecord(Integer unitId, String licensePlateNumber, String driverName, Integer storeRoom, String vehicleType, String accessoriesId, String useOfAccessories, Integer lackOfAccessories, double maintenancePrice, String maintenanceTime, String remark) {
        this.unitId = unitId;
        this.licensePlateNumber = licensePlateNumber;
        this.driverName = driverName;
        this.storeRoom = storeRoom;
        this.vehicleType = vehicleType;
        this.accessoriesId = accessoriesId;
        this.useOfAccessories = useOfAccessories;
        this.lackOfAccessories = lackOfAccessories;
        this.maintenancePrice = maintenancePrice;
        this.maintenanceTime = maintenanceTime;
        this.remark = remark;
    }

}
