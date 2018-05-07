package com.zph.cicadas.data;

import android.graphics.Bitmap;
import android.hardware.Camera;


import com.zph.cicadas.data.db.DbHelper;
import com.zph.cicadas.data.network.ApiHelper;
import com.zph.cicadas.data.network.prefs.PreferencesHelper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 *
 * @author zph
 * @date 2018/4/19
 */
@Singleton
public class AppDataManager implements DataManager {
    private ApiHelper mApiHelper;
    private PreferencesHelper mPreferencesHelper;
    private DbHelper mDbHelper;

    @Inject
    public  AppDataManager(ApiHelper mApiHelper,PreferencesHelper  mPreferencesHelper,DbHelper mDbHelper) {
        this.mApiHelper=mApiHelper;
        this.mPreferencesHelper=mPreferencesHelper;
        this.mDbHelper=mDbHelper;
    }

    @Override
    public Observable<List<Object>> getMorePicEffect(String category, boolean pullToRefresh) {
        return mApiHelper.getMorePicEffect(category,pullToRefresh);
    }

    @Override
    public Observable<String> getMorePictexture(String string) {
        return mApiHelper.getMorePictexture(string);
    }


    @Override
    public void setPicMoreEffectAddress(String address) {
        mPreferencesHelper.setPicMoreEffectAddress(address);
    }

    @Override
    public String getPicMoreEffectAddress() {
        return mPreferencesHelper.getPicMoreEffectAddress();
    }

    @Override
    public void setPicBaseAddress(String address) {
        mPreferencesHelper.setPicBaseAddress(address);
    }

    @Override
    public String getPicBaseAddress() {
        return mPreferencesHelper.getPicBaseAddress();
    }

    @Override
    public void initCategory(int type, String[] value, String[] name) {

    }

}
