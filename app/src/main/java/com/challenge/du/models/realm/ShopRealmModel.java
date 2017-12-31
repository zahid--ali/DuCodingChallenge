package com.challenge.du.models.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by k.zahid on 12/28/17.
 */

public class ShopRealmModel extends RealmObject {

    @PrimaryKey
    private int locationId;
    private int locationSection;
    private String title;
    private String titleArabic;
    private String hourOperation;
    private String address;
    private String addressArabic;
    private double latitude;
    private double longitude;
    private int isActive;
    private long createdDate;
    private long updatedDate;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationSection() {
        return locationSection;
    }

    public void setLocationSection(int locationSection) {
        this.locationSection = locationSection;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleArabic() {
        return titleArabic;
    }

    public void setTitleArabic(String titleArabic) {
        this.titleArabic = titleArabic;
    }

    public String getHourOperation() {
        return hourOperation;
    }

    public void setHourOperation(String hourOperation) {
        this.hourOperation = hourOperation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressArabic() {
        return addressArabic;
    }

    public void setAddressArabic(String addressArabic) {
        this.addressArabic = addressArabic;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(long updatedDate) {
        this.updatedDate = updatedDate;
    }
}
