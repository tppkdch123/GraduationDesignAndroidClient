package com.example.huangshizhe.graduationdesignclient.vo;

/**
 * Created by huangshizhe on 2018/4/20.
 */
import java.io.Serializable;
import java.util.Date;

public class Room implements Serializable {
    private Long id;

    private Long providerId;

    private String title;

    private Integer defaultPrice;

    private Integer maxCapacity;

    private Integer usableArea;

    private String structure;

    private Date createTime;

    private Date updateTime;

    private Boolean realCheck;

    private Integer bedCount;

    private Integer commentNumber;

    private String hostMessage;

    private Float latitude;

    private Float longitude;

    private String street;

    private Boolean isDelete;

    private Boolean isOnsale;

    private Integer cityId;

    private Integer provinceId;

    private Long districtId;

    private String block;

    private Long areaId;

    private Integer moveUpCancelDays;

    private String earliestCheckInTime;

    private String lastestCheckInTime;

    private String lastestCheckOutTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(Integer defaultPrice) {
        this.defaultPrice = defaultPrice;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getUsableArea() {
        return usableArea;
    }

    public void setUsableArea(Integer usableArea) {
        this.usableArea = usableArea;
    }

    public String getStructure() {
        return structure;
    }

    public void setStructure(String structure) {
        this.structure = structure == null ? null : structure.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getRealCheck() {
        return realCheck;
    }

    public void setRealCheck(Boolean realCheck) {
        this.realCheck = realCheck;
    }

    public Integer getBedCount() {
        return bedCount;
    }

    public void setBedCount(Integer bedCount) {
        this.bedCount = bedCount;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }

    public String getHostMessage() {
        return hostMessage;
    }

    public void setHostMessage(String hostMessage) {
        this.hostMessage = hostMessage == null ? null : hostMessage.trim();
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Boolean getIsOnsale() {
        return isOnsale;
    }

    public void setIsOnsale(Boolean isOnsale) {
        this.isOnsale = isOnsale;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block == null ? null : block.trim();
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Integer getMoveUpCancelDays() {
        return moveUpCancelDays;
    }

    public void setMoveUpCancelDays(Integer moveUpCancelDays) {
        this.moveUpCancelDays = moveUpCancelDays;
    }

    public String getEarliestCheckInTime() {
        return earliestCheckInTime;
    }

    public void setEarliestCheckInTime(String earliestCheckInTime) {
        this.earliestCheckInTime = earliestCheckInTime == null ? null : earliestCheckInTime.trim();
    }

    public String getLastestCheckInTime() {
        return lastestCheckInTime;
    }

    public void setLastestCheckInTime(String lastestCheckInTime) {
        this.lastestCheckInTime = lastestCheckInTime == null ? null : lastestCheckInTime.trim();
    }

    public String getLastestCheckOutTime() {
        return lastestCheckOutTime;
    }

    public void setLastestCheckOutTime(String lastestCheckOutTime) {
        this.lastestCheckOutTime = lastestCheckOutTime == null ? null : lastestCheckOutTime.trim();
    }

}
