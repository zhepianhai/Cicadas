package com.zph.cicadas.frag.home;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.zph.cicadas.R;
import com.zph.cicadas.adpter.AdpVirtualLayout;
import com.zph.cicadas.define.Constants;
import com.zph.cicadas.frag.base.BaseMainFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static com.zph.cicadas.define.Constants.DEFIND;

/**
 *
 * @author zph
 * @date 2018/3/22
 */
public class FragHome extends BaseMainFragment implements OnItemClickListener {
    private View mView;

    private RecyclerView mRecyclerView;
    private VirtualLayoutManager mManager;
    private AdpVirtualLayout mAdpVirtualLayout;
    private ArrayList<HashMap<String,String>> mArrayListData;

    public static FragHome getInstance() {
        return new FragHome();
    }

    @Override
    public int setFragMentType() {
        return DEFIND;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        mView=inflater.inflate(R.layout.fragment_home, container, false);
        return mView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.initVar();
        this.initView();
        this.initVirLayout();
    }

    private void initVar() {
        mArrayListData=new ArrayList<>();
    }

    private void initView() {
        mRecyclerView=mView.findViewById(R.id.frag_home_recyclerview);

    }
    private void initVirLayout() {
        mManager=new VirtualLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mManager);
        //设置线程池
        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
        mRecyclerView.setRecycledViewPool(viewPool);
        viewPool.setMaxRecycledViews(0, 10);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
        mAdpVirtualLayout=new AdpVirtualLayout(mManager,getActivity(),mArrayListData);
        mRecyclerView.setAdapter(mAdpVirtualLayout);
        mAdpVirtualLayout.setOnItemClickListener(this);

    }
    @Override
    public void onResume() {
        super.onResume();
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(getActivity(),"position:"+position,Toast.LENGTH_SHORT).show();
    }
}
