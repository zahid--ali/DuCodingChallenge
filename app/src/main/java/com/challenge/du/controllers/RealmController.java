package com.challenge.du.controllers;

import com.challenge.du.BuildConfig;
import com.challenge.du.models.ShopModel;
import com.challenge.du.models.SectionModel;
import com.challenge.du.models.realm.ShopRealmModel;
import com.challenge.du.models.realm.SectionRealmModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by k.zahid on 12/31/17.
 */

public class RealmController {
    private RealmConfiguration mRealmConfiguration;


    public RealmController() {
        mRealmConfiguration = new RealmConfiguration
                .Builder()
                .name(BuildConfig.REALM_FILE)
                .schemaVersion(BuildConfig.REALM_VERSION)
                .build();
    }


    private SectionModel getSectionModel(SectionRealmModel sectionRealmModel) {
        SectionModel sectionModel = new SectionModel();
        sectionModel.setCreatedDate(sectionRealmModel.getCreatedDate());
        sectionModel.setExternalLink(sectionRealmModel.getExternalLink());
        sectionModel.setExternalLinkArabic(sectionRealmModel.getExternalLinkArabic());
        sectionModel.setImagePath(sectionRealmModel.getImagePath());
        sectionModel.setIsActive(sectionRealmModel.getIsActive());
        sectionModel.setIsCmsApi(sectionRealmModel.getIsCmsApi());
        sectionModel.setIsCmsSection(sectionRealmModel.getIsCmsSection());
        sectionModel.setIsDeletable(sectionRealmModel.getIsDeletable());
        sectionModel.setIsLink(sectionRealmModel.getIsLink());
        sectionModel.setIsTileView(sectionRealmModel.getIsTileView());
        sectionModel.setIsVisible(sectionRealmModel.getIsVisible());
        sectionModel.setPosition(sectionRealmModel.getPosition());
        sectionModel.setSectionId(sectionRealmModel.getSectionId());
        sectionModel.setShortDesc(sectionRealmModel.getShortDesc());
        sectionModel.setShortDescArabic(sectionRealmModel.getShortDescArabic());
        sectionModel.setShortDescColor(sectionRealmModel.getShortDescColor());
        sectionModel.setTitle(sectionRealmModel.getTitle());
        sectionModel.setTitleArabic(sectionRealmModel.getTitleArabic());
        sectionModel.setTitleColor(sectionRealmModel.getTitleColor());
        sectionModel.setUpdatedDate(sectionRealmModel.getUpdatedDate());
        return sectionModel;

    }

    private SectionRealmModel getSectionRealmModel(SectionModel sectionModel) {
        SectionRealmModel sectionRealmModel = new SectionRealmModel();
        sectionRealmModel.setCreatedDate(sectionModel.getCreatedDate());
        sectionRealmModel.setExternalLink(sectionModel.getExternalLink());
        sectionRealmModel.setExternalLinkArabic(sectionModel.getExternalLinkArabic());
        sectionRealmModel.setImagePath(sectionModel.getImagePath());
        sectionRealmModel.setIsActive(sectionModel.getIsActive());
        sectionRealmModel.setIsCmsApi(sectionModel.getIsCmsApi());
        sectionRealmModel.setIsCmsSection(sectionModel.getIsCmsSection());
        sectionRealmModel.setIsDeletable(sectionModel.getIsDeletable());
        sectionRealmModel.setIsLink(sectionModel.getIsLink());
        sectionRealmModel.setIsTileView(sectionModel.getIsTileView());
        sectionRealmModel.setIsVisible(sectionModel.getIsVisible());
        sectionRealmModel.setPosition(sectionModel.getPosition());
        sectionRealmModel.setSectionId(sectionModel.getSectionId());
        sectionRealmModel.setShortDesc(sectionModel.getShortDesc());
        sectionRealmModel.setShortDescArabic(sectionModel.getShortDescArabic());
        sectionRealmModel.setShortDescColor(sectionModel.getShortDescColor());
        sectionRealmModel.setTitle(sectionModel.getTitle());
        sectionRealmModel.setTitleArabic(sectionModel.getTitleArabic());
        sectionRealmModel.setTitleColor(sectionModel.getTitleColor());
        sectionRealmModel.setUpdatedDate(sectionModel.getUpdatedDate());
        return sectionRealmModel;

    }


    private ShopModel getShopModel(ShopRealmModel shopRealmModel) {
        ShopModel shopModel = new ShopModel();
        shopModel.setAddress(shopRealmModel.getAddress());
        shopModel.setAddressArabic(shopRealmModel.getAddressArabic());
        shopModel.setCreatedDate(shopRealmModel.getCreatedDate());
        shopModel.setHourOperation(shopRealmModel.getHourOperation());
        shopModel.setIsActive(shopRealmModel.getIsActive());
        shopModel.setLatitude(shopRealmModel.getLatitude());
        shopModel.setLatitude(shopRealmModel.getLongitude());
        shopModel.setLocationId(shopRealmModel.getLocationId());
        shopModel.setLocationSection(shopRealmModel.getLocationSection());
        shopModel.setTitle(shopRealmModel.getTitle());
        shopModel.setTitleArabic(shopRealmModel.getTitleArabic());
        return shopModel;
    }

    private ShopRealmModel getShopRealmModel(ShopModel shopModel) {
        ShopRealmModel shopRealmModel = new ShopRealmModel();
        shopRealmModel.setAddress(shopModel.getAddress());
        shopRealmModel.setAddressArabic(shopModel.getAddressArabic());
        shopRealmModel.setCreatedDate(shopModel.getCreatedDate());
        shopRealmModel.setHourOperation(shopModel.getHourOperation());
        shopRealmModel.setIsActive(shopModel.getIsActive());
        shopRealmModel.setLatitude(shopModel.getLatitude());
        shopRealmModel.setLatitude(shopModel.getLongitude());
        shopRealmModel.setLocationId(shopModel.getLocationId());
        shopRealmModel.setLocationSection(shopModel.getLocationSection());
        shopRealmModel.setTitle(shopModel.getTitle());
        shopRealmModel.setTitleArabic(shopModel.getTitleArabic());
        return shopRealmModel;
    }


    public void saveShops(List<ShopModel> branches) {
        Realm realm = Realm.getDefaultInstance();
        for (final ShopModel branch : branches) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(getShopRealmModel(branch));
                }
            });
        }
    }

    public void saveShops(final ShopModel branch) {
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(getShopRealmModel(branch));
            }
        });

    }

    public List<ShopModel> getShops() {
        ArrayList<ShopModel> branches = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<ShopRealmModel> results = realm.where(ShopRealmModel.class).findAll();
        for (ShopRealmModel shopRealmModel : results)
            branches.add(getShopModel(shopRealmModel));
        return branches;
    }

    public void saveSections(List<SectionModel> sections) {
        Realm realm = Realm.getDefaultInstance();
        for (final SectionModel section : sections) {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(getSectionRealmModel(section));
                }
            });
        }
    }

    public void saveSection(final SectionModel section) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(getSectionRealmModel(section));
            }
        });

    }


    public List<SectionModel> getSections() {
        List<SectionModel> sectionModels = new ArrayList<>();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<SectionRealmModel> results = realm.where(SectionRealmModel.class).findAll();
        for (SectionRealmModel sectionRealmModel : results)
            sectionModels.add(getSectionModel(sectionRealmModel));
        return sectionModels;
    }


}
