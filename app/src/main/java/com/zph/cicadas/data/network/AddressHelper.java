package com.zph.cicadas.data.network;


import com.zph.cicadas.data.network.prefs.PreferencesHelper;

/**
 * @author zph
 * @date 2018/4/27
 */

public class AddressHelper {

    private PreferencesHelper preferencesHelper;

    /**
     * 无需手动初始化,已在inject中全局单例
     *
     */
    public AddressHelper(PreferencesHelper preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    public String getPicBaseAddress() {
        return preferencesHelper.getPicBaseAddress();
    }
    public String getPicMoreEffectAddress(){return  preferencesHelper.getPicMoreEffectAddress();}


}
