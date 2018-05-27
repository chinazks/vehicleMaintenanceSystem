package com.xforceplus.data.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 发放记录
 * Created by Administrator on 2018/5/27 0027.
 */
@Entity
@Table(name = "release_record")
public class ReleaseRecord implements Serializable {
    private static final long serialVersionUID = -8580380657563100559L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    /**
     * 发料单位
     */
    @Column(name = "material_issuing_unit",columnDefinition = "varchar(50)")
    private String materialIssuingUnit;
    /**
     * 收料单位
     */
    @Column(name = "material_issuing_unit",columnDefinition = "varchar(50)")
    private String materialReceiveUnit;
    /**
     * 出库类别
     */
    @Column(name = "material_issuing_unit",columnDefinition = "varchar(50)")
    private String outboundCategory;
    /**
     * 器材编码 对应配件id
     */
    @Column(name = "material_issuing_unit",columnDefinition = "varchar(50)")
    private String accessoriesId;
    /**
     * 规格
     */
    private String specification;
    /**
     * 单位
     */
    private String units;
    /**
     * 原厂编号
     */
    private String orginalNumber;
    /**
     * 出库数
     */
    private int deliveryNumber;
    /**
     * 供应单价
     */
    private String price;
    /**
     * 车牌号
     */
    private String licensePlateNumber;
    /**
     * 出库日期
     */
    private String deliveryDate;
    /**
     * 总金额
     */
    private String sumMoney;
    /**
     * 经办人
     */
    private String reponsiableName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaterialIssuingUnit() {
        return materialIssuingUnit;
    }

    public void setMaterialIssuingUnit(String materialIssuingUnit) {
        this.materialIssuingUnit = materialIssuingUnit;
    }

    public String getMaterialReceiveUnit() {
        return materialReceiveUnit;
    }

    public void setMaterialReceiveUnit(String materialReceiveUnit) {
        this.materialReceiveUnit = materialReceiveUnit;
    }

    public String getOutboundCategory() {
        return outboundCategory;
    }

    public void setOutboundCategory(String outboundCategory) {
        this.outboundCategory = outboundCategory;
    }

    public String getAccessoriesId() {
        return accessoriesId;
    }

    public void setAccessoriesId(String accessoriesId) {
        this.accessoriesId = accessoriesId;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getOrginalNumber() {
        return orginalNumber;
    }

    public void setOrginalNumber(String orginalNumber) {
        this.orginalNumber = orginalNumber;
    }

    public int getDeliveryNumber() {
        return deliveryNumber;
    }

    public void setDeliveryNumber(int deliveryNumber) {
        this.deliveryNumber = deliveryNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(String sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getReponsiableName() {
        return reponsiableName;
    }

    public void setReponsiableName(String reponsiableName) {
        this.reponsiableName = reponsiableName;
    }
}
