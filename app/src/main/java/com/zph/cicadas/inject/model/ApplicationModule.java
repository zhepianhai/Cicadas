package com.zph.cicadas.inject.model;

import android.app.Application;
import android.content.Context;


import com.zph.cicadas.data.AppDataManager;
import com.zph.cicadas.data.DataManager;
import com.zph.cicadas.data.chache.CacheProviders;
import com.zph.cicadas.data.db.AppDbHelper;
import com.zph.cicadas.data.db.DbHelper;
import com.zph.cicadas.data.network.AddressHelper;
import com.zph.cicadas.data.network.ApiHelper;
import com.zph.cicadas.data.network.AppApiHelper;
import com.zph.cicadas.data.network.prefs.AppPreferencesHelper;
import com.zph.cicadas.data.network.prefs.PreferencesHelper;
import com.zph.cicadas.define.Constants;
import com.zph.cicadas.inject.ApplicationContext;
import com.zph.cicadas.inject.DatabaseInfo;
import com.zph.cicadas.inject.PreferenceInfo;
import com.zph.cicadas.utils.AppCacheUtils;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

/**
 * @author zph
 * @date 2018/3/21
 */
@Module
public class ApplicationModule {
    private final Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    public Application providesApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    public Context providesContext() {
        return mApplication;
    }


    @Provides
    @PreferenceInfo
    String providePreferenceName(@ApplicationContext Context context) {
        return context.getPackageName() + "_preferences";
    }

    @Provides
    @DatabaseInfo
    String providesDatabaseName() {
        return Constants.DB_NAME;
    }


    @Singleton
    @Provides
    CacheProviders providesCacheProviders(@ApplicationContext Context context) {
        File cacheDir = AppCacheUtils.getRxCacheDir(context);
        return new RxCache.Builder()
                .persistence(cacheDir, new GsonSpeaker())
                .using(CacheProviders.class);
    }

    @Singleton
    @Provides
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Singleton
    @Provides
    ApiHelper providesApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }





    @Singleton
    @Provides
    AddressHelper providesAddressHelper(PreferencesHelper preferencesHelper) {
        return new AddressHelper(preferencesHelper);
    }
    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }
}
