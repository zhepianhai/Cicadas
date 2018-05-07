package com.zph.cicadas.ui.main;


import com.zph.cicadas.inject.PerActivity;
import com.zph.cicadas.ui.MvpBasePresenter;

import javax.inject.Inject;

/**
 * @author zph
 * @date 2018/3/20
 */
@PerActivity
public class MainPresenter extends MvpBasePresenter<MainView> implements IMain {
    @Inject
    public MainPresenter() {
    }
}
