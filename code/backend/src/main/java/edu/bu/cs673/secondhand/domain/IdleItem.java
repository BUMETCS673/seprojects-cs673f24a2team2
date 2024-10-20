package edu.bu.cs673.secondhand.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * A class represent item.
 */
public class IdleItem {
    private Long id;

    private String idleName;

    private String idleDetails;

    private String pictureList;

    private BigDecimal idlePrice;

    private String idlePlace;

    private Integer idleLabel;

    private Date releaseTime;

    private Byte idleStatus;

    private Long userId;

    // getter and setter methods
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdleName() {
        return idleName;
    }

    public void setIdleName(String idleName) {
        this.idleName = idleName == null ? null : idleName.trim();
    }

    public String getIdleDetails() {
        return idleDetails;
    }

    public void setIdleDetails(String idleDetails) {
        this.idleDetails = idleDetails == null ? null : idleDetails.trim();
    }

    public String getPictureList() {
        return pictureList;
    }

    public void setPictureList(String pictureList) {
        this.pictureList = pictureList == null ? null : pictureList.trim();
    }

    public BigDecimal getIdlePrice() {
        return idlePrice;
    }

    public void setIdlePrice(BigDecimal idlePrice) {
        this.idlePrice = idlePrice;
    }

    public String getIdlePlace() {
        return idlePlace;
    }

    public void setIdlePlace(String idlePlace) {
        this.idlePlace = idlePlace == null ? null : idlePlace.trim();
    }

    public Integer getIdleLabel() {
        return idleLabel;
    }

    public void setIdleLabel(Integer idleLabel) {
        this.idleLabel = idleLabel;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Byte getIdleStatus() {
        return idleStatus;
    }

    public void setIdleStatus(Byte idleStatus) {
        this.idleStatus = idleStatus;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        IdleItem item = (IdleItem) obj;
        return item.id == this.id;
    }

    @Override
    public int hashCode() {
        return 31 + this.id.hashCode();
    }

    @Override
    public String toString() {
        return "[item - id= " + this.id +
            ", name=" + this.idleName + ", detail=" + this.idleDetails + 
            ", pictureList=" + this.pictureList + ", price=" + this.idlePrice + 
            ", label=" + this.idleLabel + ", releaseTime=" + this.releaseTime + 
            ", status=" + this.idleStatus + ", place=" + this.idlePlace + ", userId=]";
    }

    public List<IdleItem> getIdleItemByStatus(int status, int i, int nums) {
        return List.of();
    }

    public void setUser(User user) {
    }

    public int countIdleItemByStatus(int status) {
        return status;
    }
}