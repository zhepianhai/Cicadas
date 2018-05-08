package com.zph.cicadas;

import android.app.Application;

import com.zph.cicadas.inject.component.ApplicationComponent;
import com.zph.cicadas.inject.component.DaggerApplicationComponent;
import com.zph.cicadas.inject.model.ApplicationModule;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;

/**
 *
 * @author zph
 * @date 2018/5/7
 */

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication myApplication;
    private ApplicationComponent applicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
        applicationComponent.inject(this);
        BGASwipeBackHelper.init(this, null);

    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


}
