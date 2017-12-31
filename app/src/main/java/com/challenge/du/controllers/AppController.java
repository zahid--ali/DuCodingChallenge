package com.challenge.du.controllers;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.challenge.du.utils.AppConstants;
import com.challenge.du.utils.LocaleHelper;
import com.challenge.du.utils.SharedPrefUtil;

import io.realm.Realm;

/**
 * Created by k.zahid on 12/31/17.
 */

public class AppController extends Application {

    public static double USER_LOCATION_LAT = 0.0;
    public static double USER_LOCATION_LONG = 0.0;
    private static RealmController realmInstance;

    private static AppController mInstance;


    public static AppController getInstance() {
        return mInstance;
    }

    public static RealmController getRealmInstance() {
        return realmInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Realm.init(this);
        if (Resources.getSystem().getConfiguration().locale.getDisplayLanguage().equals("Arabic") || Resources.getSystem().getConfiguration().locale.getDisplayLanguage().equals("العربية")) {
            LocaleHelper.setLocale(mInstance, "ar");
        } else {
            LocaleHelper.setLocale(mInstance, "en");
        }
        realmInstance = new RealmController();

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(LocaleHelper.onAttach(base, LocaleHelper.getLanguage(base)));
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    public static AppController context() {
        return mInstance;
    }


}
