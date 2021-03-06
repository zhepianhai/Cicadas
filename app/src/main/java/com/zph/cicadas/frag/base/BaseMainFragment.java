package com.zph.cicadas.frag.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zph.cicadas.R;
import com.zph.cicadas.adpter.BaseMainFragmentAdapter;
import com.zph.cicadas.ui.MvpFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author zph
 */
public abstract class BaseMainFragment extends MvpFragment<BaseMainView, BaseMainPresenter> implements BaseMainView, View.OnClickListener{
//    @BindView(R.id.frag_base_tabLayout)
    TabLayout mTabLayout;
//    @BindView(R.id.frag_base_viewPager)
    ViewPager mViewPager;
//    Unbinder unbinder;
    private boolean mIsBackground = false;
    private int mCurrentSelectPosition = 0;
    @Inject
    BaseMainPresenter mBaseMainPresenter;


    protected View mView;
    private BaseMainFragmentAdapter mBaseMainFragmentAdapter;
    private FragmentManager mFragmentManager;
    private ArrayList<HashMap<String,String>> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mFragmentManager = getChildFragmentManager();
        list=new ArrayList<>();
        mBaseMainFragmentAdapter = new BaseMainFragmentAdapter(getActivity(),mFragmentManager, list, setFragMentType());
    }

    public BaseMainFragment() {
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        mView=inflater.inflate(R.layout.fragment_base_main, container, false);
//        unbinder = ButterKnife.bind(this,mView);
        return  mView;
    }


    @NonNull
    @Override
    public BaseMainPresenter createPresenter() {
        getActivityComponent().inject(this);
        return mBaseMainPresenter;
    }

    @Override
    public void onLoadAllFragMentViewFinish(List<HashMap<String, String>> categoryList) {
        list.clear();
        list.addAll(categoryList);
        mBaseMainFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabLayout=mView.findViewById(R.id.frag_base_tabLayout);
        mViewPager=mView.findViewById(R.id.frag_base_viewPager);

        mBaseMainFragmentAdapter.setDestroy(isNeedDestroy());
        mViewPager.setAdapter(mBaseMainFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentSelectPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        presenter.loadOneFragMentData(setFragMentType());

    }
    public abstract int setFragMentType();
    public boolean isNeedDestroy() {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mIsBackground = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        mIsBackground = true;
    }

    @Override
    protected void onLazyLoadOnce() {
        super.onLazyLoadOnce();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }






    @Override
    public void onClick(View v) {
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showMessage(String msg, int type) {
        super.showMessage(msg, type);
    }

    @Override
    public void showError(String message) {

    }




    @Override
    public void onDestroy() {
//        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

}
