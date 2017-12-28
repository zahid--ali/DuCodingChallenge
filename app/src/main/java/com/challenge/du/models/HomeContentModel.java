package com.challenge.du.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by k.zahid on 12/28/17.
 */

public class HomeContentModel {
    @SerializedName("sectionId")
    @Expose
    private int sectionId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("titleArabic")
    @Expose
    private String titleArabic;
    @SerializedName("titleColor")
    @Expose
    private String titleColor;
    @SerializedName("shortDesc")
    @Expose
    private String shortDesc;
    @SerializedName("shortDescArabic")
    @Expose
    private String shortDescArabic;
    @SerializedName("shortDescColor")
    @Expose
    private String shortDescColor;
    @SerializedName("imagePath")
    @Expose
    private String imagePath;
    @SerializedName("position")
    @Expose
    private int position;
    @SerializedName("isActive")
    @Expose
    private int isActive;
    @SerializedName("isVisible")
    @Expose
    private int isVisible;
    @SerializedName("isCmsApi")
    @Expose
    private int isCmsApi;
    @SerializedName("isTileView")
    @Expose
    private int isTileView;
    @SerializedName("isCmsSection")
    @Expose
    private int isCmsSection;
    @SerializedName("isDeletable")
    @Expose
    private int isDeletable;
    @SerializedName("createdDate")
    @Expose
    private long createdDate;
    @SerializedName("updatedDate")
    @Expose
    private long updatedDate;
    @SerializedName("isLink")
    @Expose
    private int isLink;
    @SerializedName("externalLink")
    @Expose
    private String externalLink;
    @SerializedName("externalLinkArabic")
    @Expose
    private String externalLinkArabic;


    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
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

    public String getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(String titleColor) {
        this.titleColor = titleColor;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getShortDescArabic() {
        return shortDescArabic;
    }

    public void setShortDescArabic(String shortDescArabic) {
        this.shortDescArabic = shortDescArabic;
    }

    public String getShortDescColor() {
        return shortDescColor;
    }

    public void setShortDescColor(String shortDescColor) {
        this.shortDescColor = shortDescColor;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(int isVisible) {
        this.isVisible = isVisible;
    }

    public int getIsCmsApi() {
        return isCmsApi;
    }

    public void setIsCmsApi(int isCmsApi) {
        this.isCmsApi = isCmsApi;
    }

    public int getIsTileView() {
        return isTileView;
    }

    public void setIsTileView(int isTileView) {
        this.isTileView = isTileView;
    }

    public int getIsCmsSection() {
        return isCmsSection;
    }

    public void setIsCmsSection(int isCmsSection) {
        this.isCmsSection = isCmsSection;
    }

    public int getIsDeletable() {
        return isDeletable;
    }

    public void setIsDeletable(int isDeletable) {
        this.isDeletable = isDeletable;
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

    public int getIsLink() {
        return isLink;
    }

    public void setIsLink(int isLink) {
        this.isLink = isLink;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public String getExternalLinkArabic() {
        return externalLinkArabic;
    }

    public void setExternalLinkArabic(String externalLinkArabic) {
        this.externalLinkArabic = externalLinkArabic;
    }
}
