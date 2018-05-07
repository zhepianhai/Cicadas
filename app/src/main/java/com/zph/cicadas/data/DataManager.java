package com.zph.cicadas.data;


import com.zph.cicadas.data.db.DbHelper;
import com.zph.cicadas.data.network.ApiHelper;
import com.zph.cicadas.data.network.prefs.PreferencesHelper;

/**
 *
 * @author zph
 * @date 2018/4/19
 */

public interface DataManager  extends ApiHelper,PreferencesHelper,DbHelper {

}
