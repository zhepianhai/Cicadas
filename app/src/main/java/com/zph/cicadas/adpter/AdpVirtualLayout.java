package com.zph.cicadas.adpter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.zph.cicadas.R;
import com.zph.cicadas.frag.home.FragHome;
import com.zph.cicadas.frag.home.OnItemClickListener;
import com.zph.cicadas.utils.SizeUtil;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author zph
 * @date 2018/5/8
 */

public class AdpVirtualLayout extends VirtualLayoutAdapter<AdpVirtualLayout.VH> {

    private VirtualLayoutManager mManager;
    private Context mContext;
    private ArrayList<HashMap<String, String>> mArrayListData;
    private LayoutInflater mInflater;
    private OnItemClickListener mOnItemClickListener;//声明接口

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }
    public AdpVirtualLayout(@NonNull VirtualLayoutManager layoutManager, Context mContext, ArrayList<HashMap<String, String>> mArrayListData) {
        super(layoutManager);
        this.mManager = layoutManager;
        this.mContext = mContext;
        this.mArrayListData = mArrayListData;
        this.mInflater = LayoutInflater.from(mContext);
        this.initLayoutHelped();
    }

    private void initLayoutHelped() {
        List<LayoutHelper> helpers = new LinkedList<>();

        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(5);
        gridLayoutHelper.setItemCount(10);
        gridLayoutHelper.setSpanCount(5);
        gridLayoutHelper.setMarginBottom(20);

        GridLayoutHelper gridLayoutHelper1 = new GridLayoutHelper(3);
        gridLayoutHelper1.setItemCount(6);
        gridLayoutHelper1.setSpanCount(3);
        gridLayoutHelper.setMarginBottom(20);


        ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
        columnLayoutHelper.setItemCount(2);
        columnLayoutHelper.setWeights(new float[]{66.6f,33.3f});

        ColumnLayoutHelper columnLayoutHelper1 = new ColumnLayoutHelper();
        columnLayoutHelper1.setItemCount(3);

        columnLayoutHelper1.setWeights(new float[]{50f,25f,25f});

        //设置线性布局
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setItemCount(2);
        linearLayoutHelper.setDividerHeight(10);

        OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(3);
        onePlusNLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比

        helpers.add(gridLayoutHelper);//0-9
        helpers.add(DefaultLayoutHelper.newHelper(1));//10
        helpers.add(gridLayoutHelper1);//11-16
        helpers.add(DefaultLayoutHelper.newHelper(1));//17
        helpers.add(columnLayoutHelper);//18 19
        helpers.add(columnLayoutHelper1);// 20 21 22
        helpers.add(linearLayoutHelper);// 23 24
        helpers.add(onePlusNLayoutHelper);// 25 26  27
        this.mManager.setLayoutHelpers(helpers);

    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VH(mInflater.inflate(R.layout.item_fraghome_vlayout, parent, false),viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull final VH holder, int position) {
        VirtualLayoutManager.LayoutParams layoutParams = new VirtualLayoutManager.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        if (position <= 9) {
            layoutParams.height = SizeUtil.dp2px(mContext, 60);
            layoutParams.setMargins(0, 10, 0, 10);
        }
        if (position == 10) {
            layoutParams.height = SizeUtil.dp2px(mContext, 60);
        }
        if (position == 17) {
            layoutParams.height = SizeUtil.dp2px(mContext, 60);
        }
        if (position > 10 && position < 17) {
            layoutParams.height = SizeUtil.dp2px(mContext, 120);
            layoutParams.setMargins(0, 10, 0, 10);
        }
        if (position == 17) {
            layoutParams.height = SizeUtil.dp2px(mContext, 60);
        }
        if (position > 17) {
            layoutParams.height = SizeUtil.dp2px(mContext, 120);
        }
        if(position==18 ||position==19){
            layoutParams.height = SizeUtil.dp2px(mContext, 120);
            layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
        }

        holder.itemView.setLayoutParams(layoutParams);

        holder.mTv.setText(Integer.toString(position));
        if(position==18 ||position==19){
            holder.itemView.setBackgroundColor(Color.parseColor("#DB7093"));
        } else
            holder.itemView.setBackgroundColor(Color.parseColor("#A9A9A9"));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getLayoutPosition();
                mOnItemClickListener.onItemClick(holder.itemView, position);
            }
        });

        holder.mImg.setImageResource(R.mipmap.ic_launcher_round);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        List<LayoutHelper> helpers = getLayoutHelpers();
        if (helpers == null) {
            return 0;
        }
        int count = 0;
        for (int i = 0, size = helpers.size(); i < size; i++) {
            count += helpers.get(i).getItemCount();
        }
        return count;
    }

    class VH extends RecyclerView.ViewHolder {
        TextView mTv;
        ImageView mImg;
        VH(View itemView,int viewType) {
            super(itemView);
            mTv = itemView.findViewById(R.id.item_fraghome_vlayout_tv);
            mImg=itemView.findViewById(R.id.item_fraghome_vlayout_img);
        }
    }
}
