package com.zph.cicadas.inject.component;

import android.content.Context;


import com.zph.cicadas.MyApplication;
import com.zph.cicadas.data.DataManager;
import com.zph.cicadas.data.network.AddressHelper;
import com.zph.cicadas.inject.ApplicationContext;
import com.zph.cicadas.inject.model.ApiServiceModule;
import com.zph.cicadas.inject.model.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 * @author zph
 * @date 2018/3/21
 */
@Singleton
@Component(modules = {ApplicationModule.class, ApiServiceModule.class})
public interface ApplicationComponent{
    void inject(MyApplication myApplication);

    @ApplicationContext
    Context getContext();
    AddressHelper getAddressHelper();
    DataManager getDataManager();
}
