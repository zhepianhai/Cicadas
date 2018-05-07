package com.zph.cicadas.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.devbrackets.android.exomedia.util.ResourceUtil;
import com.zph.cicadas.R;
import com.zph.cicadas.define.CONST_QUERY;
import com.zph.cicadas.frag.home.FragHome;
import com.zph.cicadas.frag.infomation.FragInfomation;
import com.zph.cicadas.frag.me.FragMe;
import com.zph.cicadas.frag.more.FragMore;
import com.zph.cicadas.ui.MvpActivity;
import com.zph.cicadas.utils.FragmentUtils;
import com.zph.cicadas.utils.SizeUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.ashokvarma.bottomnavigation.BottomNavigationBar.MODE_FIXED;

/**
 * @author zph
 * @date 2018/3/20
 */
public class MainActivity extends MvpActivity<MainView, MainPresenter> implements MainView {
    private final int TAB_HOME = 0;
    private final int TAB_INFO = 1;
    private final int TAB_MORE = 2;
    private final int TAB_ME = 3;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;
    @BindView(R.id.fab_search)
    FloatingActionButton mFabSearch;
    @BindView(R.id.content_main_framelay)
    FrameLayout mContent;
    Unbinder butter;
    @Inject
    MainPresenter mainPresenter;
    private Fragment mCurrentFragment;
    private FragHome mFragHome;
    private FragInfomation mFragInfomation;
    private FragMore mFragMore;
    private FragMe mFragMe;
    private FragmentManager mFragmentManager;
    private int mSelectIndex;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butter = ButterKnife.bind(this);

        mFragmentManager = getSupportFragmentManager();
        mSelectIndex = getIntent().getIntExtra(CONST_QUERY.KEY_SELECT_INDEX, 0);
        if (savedInstanceState != null) {
            mSelectIndex = savedInstanceState.getInt(CONST_QUERY.KEY_SELECT_INDEX);
        }
        initBottomNavigationBar(mSelectIndex);
        mFabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doOnFloatingActionButtonClick(mSelectIndex);

            }
        });
        doOnTabSelected(mSelectIndex);
    }

    @NonNull
    @Override
    public MainPresenter createPresenter() {
        getActivityComponent().inject(this);
        return mainPresenter;
    }

    @Override
    public void showLoading(boolean pullToRefresh) {

    }

    @Override
    public void showContent() {

    }

    @Override
    public void showMessage(String msg, int type) {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CONST_QUERY.KEY_SELECT_INDEX, mSelectIndex);
    }

    private void initBottomNavigationBar(@IntRange(from = 0, to = 3) int position) {
        mBottomNavigationBar.addItem(new BottomNavigationItem(ResourceUtil.getDrawable(this, R.drawable.ic_home_black_24dp), R.string.title_home));
        mBottomNavigationBar.addItem(new BottomNavigationItem(ResourceUtil.getDrawable(this, R.drawable.ic_infome_black_svg_24), R.string.title_information));
        mBottomNavigationBar.addItem(new BottomNavigationItem(ResourceUtil.getDrawable(this, R.drawable.ic_more_black_svg_24_1), R.string.title_find));
        mBottomNavigationBar.addItem(new BottomNavigationItem(ResourceUtil.getDrawable(this, R.drawable.ic_me_black_24dp), R.string.title_me));

        mBottomNavigationBar.setMode(MODE_FIXED);
        mBottomNavigationBar.setActiveColor(R.color.bottom_navigation_bar_active);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_DEFAULT);
//        mBottomNavigationBar.setMode(MODE_SHIFTING);
        mBottomNavigationBar.setFirstSelectedPosition(position);
        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                doOnTabSelected(position);
            }
        });
        mBottomNavigationBar.setBarBackgroundColor(R.color.bottom_navigation_bar_background);
        mBottomNavigationBar.setFab(mFabSearch);
        mBottomNavigationBar.initialise();
        SizeUtil.setBottomNavigationItem(mBottomNavigationBar, 4, 28, 12, this);
    }

    private void doOnTabSelected(@IntRange(from = 0, to = 3) int position) {
        switch (position) {
            case TAB_HOME:
                handlerFirstTabClickToShow(position, false);
                showFloatingActionButton(mFabSearch);
                setStatusBarColor(getResources().getColor(R.color.colorHome));
                break;
            case TAB_INFO:
                handlerFirstTabClickToShow(position, false);
                hideFloatingActionButton(mFabSearch);
                setStatusBarColor(getResources().getColor(R.color.colorHome));
                break;
            case TAB_MORE:
                handlerFirstTabClickToShow(position, false);
                hideFloatingActionButton(mFabSearch);
                setStatusBarColor(getResources().getColor(R.color.colorHome));
                break;
            case TAB_ME:
                handlerFirstTabClickToShow(position, false);
                hideFloatingActionButton(mFabSearch);
                setStatusBarColor(getResources().getColor(R.color.colorHome));
                break;

            default:
        }
        mSelectIndex = position;
    }

    private void showFloatingActionButton(final FloatingActionButton fabSearch) {
        fabSearch.show(new FloatingActionButton.OnVisibilityChangedListener() {
            @Override
            public void onShown(FloatingActionButton fab) {
                fabSearch.requestLayout();
                mBottomNavigationBar.setFab(fab);
            }
        });
    }

    private void handlerFirstTabClickToShow(int position, boolean isInnerReplace) {
        switch (position) {
            case TAB_HOME:
                if (mFragHome == null) {
                    mFragHome = FragHome.getInstance();
                }
                mCurrentFragment = FragmentUtils.switchContent(mFragmentManager, mCurrentFragment, mFragHome, mContent.getId(), position, false);
                break;
            case TAB_INFO:
                if (mFragInfomation == null) {
                    mFragInfomation = FragInfomation.getInstance();
                }
                mCurrentFragment = FragmentUtils.switchContent(mFragmentManager, mCurrentFragment, mFragInfomation, mContent.getId(), position, false);

                break;
            case TAB_MORE:
                if (mFragMore == null) {
                    mFragMore = FragMore.getInstance();
                }
                mCurrentFragment = FragmentUtils.switchContent(mFragmentManager, mCurrentFragment, mFragMore, mContent.getId(), position, false);
                break;

            case TAB_ME:
                if (mFragMe == null) {
                    mFragMe = FragMe.getInstance();
                }
                mCurrentFragment = FragmentUtils.switchContent(mFragmentManager, mCurrentFragment, mFragMe, mContent.getId(), position, false);

                break;
            default:
        }
    }


    private void hideFloatingActionButton(FloatingActionButton fabSearch) {
        ViewGroup.LayoutParams layoutParams = fabSearch.getLayoutParams();
        if (layoutParams != null && layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.LayoutParams coLayoutParams = (CoordinatorLayout.LayoutParams) layoutParams;
            FloatingActionButton.Behavior behavior = new FloatingActionButton.Behavior();
            coLayoutParams.setBehavior(behavior);
        }
        fabSearch.hide();
    }

    private void doOnFloatingActionButtonClick(@IntRange(from = 0, to = 2) int position) {
        switch (position) {
            case 0:
                showHomeBottomSheet();
                break;
            case 1:
                break;
            case 2:
                break;
            default:
        }
    }

    private void showHomeBottomSheet() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != butter) {
            butter.unbind();
        }
    }

}
