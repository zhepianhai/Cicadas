package com.zph.cicadas.frag.home;


import com.zph.cicadas.define.Constants;
import com.zph.cicadas.frag.base.BaseMainFragment;

/**
 *
 * @author zph
 * @date 2018/3/22
 */
public class FragHome extends BaseMainFragment {
    public static FragHome getInstance() {
        return new FragHome();
    }

    @Override
    public int setFragMentType() {
        return Constants.HOME;
    }

}
