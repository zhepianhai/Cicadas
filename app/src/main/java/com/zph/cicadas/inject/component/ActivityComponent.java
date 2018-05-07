package com.zph.cicadas.inject.component;


import com.zph.cicadas.frag.base.BaseMainFragment;
import com.zph.cicadas.frag.home.FragHome;
import com.zph.cicadas.frag.infomation.FragInfomation;
import com.zph.cicadas.frag.more.FragMore;
import com.zph.cicadas.inject.PerActivity;
import com.zph.cicadas.inject.model.ActivityModule;
import com.zph.cicadas.ui.main.MainActivity;

import dagger.Component;

/**
 * @author zph
 * @date 2018/3/21
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(BaseMainFragment baseMainFragment);
    void inject(FragHome fragHome);
    void inject(FragInfomation fragInfomation);
    void inject(FragMore fragMore);

}
