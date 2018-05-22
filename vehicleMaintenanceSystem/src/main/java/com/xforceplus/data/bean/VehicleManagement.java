package com.xforceplus.data.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 车辆管理
 * Created by Administrator on 2018/5/5 0005.
 */
@Entity
@Table(name="vehicle_management")
public class VehicleManagement implements Serializable {
    private static final long serialVersionUID = -332939265018015024L;
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    /**
     * 装备名称
     */
    @Column(name = "equipment_Name",length = 50)
    private String equipmentName;
    /**
     * 装备型号
     */
    @Column(name = "equipment_model",length = 50)
    private String equipmentModel;
    /**
     * 车牌号
     */
    @Column(name = "license_plate_number",length = 10)
    private String licensePlateNumber;
    /**
     * 车辆类型
     */
    @Column(name = "vehicle_type",length = 10)
    private String vehicleType;
    /**
     * 司机名称
     */
    @Column(name = "driver_name",length = 4)
    private String driverName;
    /**
     * 单位id
     */
    @Column(name = "unit_id",length = 3)
    private Integer unitId;
    /**
     * 备注
     */
    @Column(name = "remarke",length = 200)
    private String remarke;
    /**
     *备用字段1
     */
    @Column(name = "ext1",length = 255)
    private String ext1;
    /**
     * 备用字段2
     */
    @Column(name = "ext2",length = 255)
    private String ext2;
    /**
     * 备用字段3
     */
    @Column(name = "ext3",length = 255)
    private String ext3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    public String getRemarke() {
        return remarke;
    }

    public void setRemarke(String remarke) {
        this.remarke = remarke;
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1;
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2;
    }

    public String getExt3() {
        return ext3;
    }

    public void setExt3(String ext3) {
        this.ext3 = ext3;
    }

    public VehicleManagement() {
    }

    public VehicleManagement(String equipmentName, String equipmentModel, String licensePlateNumber, String vehicleType, String driverName, Integer unitId, String remarke) {
        this.equipmentName = equipmentName;
        this.equipmentModel = equipmentModel;
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
        this.driverName = driverName;
        this.unitId = unitId;
        this.remarke = remarke;
    }

    public VehicleManagement(String equipmentName, String equipmentModel, String licensePlateNumber, String vehicleType, String driverName, Integer unitId, String remarke, String ext1, String ext2, String ext3) {
        this.equipmentName = equipmentName;
        this.equipmentModel = equipmentModel;
        this.licensePlateNumber = licensePlateNumber;
        this.vehicleType = vehicleType;
        this.driverName = driverName;
        this.unitId = unitId;
        this.remarke = remarke;
        this.ext1 = ext1;
        this.ext2 = ext2;
        this.ext3 = ext3;
    }
}
