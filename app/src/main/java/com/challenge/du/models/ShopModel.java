package com.challenge.du.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by k.zahid on 12/28/17.
 */

public class ShopModel {

    @SerializedName("locationId")
    @Expose
    private int locationId;
    @SerializedName("locationSection")
    @Expose
    private int locationSection;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleArabic")
    @Expose
    private String titleArabic;
    @SerializedName("hourOperation")
    @Expose
    private String hourOperation;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("addressArabic")
    @Expose
    private String addressArabic;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("isActive")
    @Expose
    private int isActive;
    @SerializedName("createdDate")
    @Expose
    private long createdDate;
    @SerializedName("updatedDate")
    @Expose
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
