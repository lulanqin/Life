package com.cruse.life.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cruse.life.R;
import com.cruse.life.activity.FocusUpdateDetailActivity;
import com.cruse.life.entity.FocusUpdateBean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by lulanqin on 2017/5/12.
 */
public class RvUpdateAdapter extends RecyclerView.Adapter<RvUpdateAdapter.ViewHolder> {

    private Context mContext;
    private List<FocusUpdateBean.TngouBean> dataList = new LinkedList<>();

    public void addAllData(FocusUpdateBean bean) {
        this.dataList.addAll(bean.getTngou());
        int size = dataList.size();
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public RvUpdateAdapter(Context context) {
        mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        private ImageView iv;
        private TextView keyword;
        private RelativeLayout rl;

        public ViewHolder(View itemView) {
            super(itemView);
            rl = (RelativeLayout) itemView.findViewById(R.id.rl_focus);
            title = (TextView) itemView.findViewById(R.id.tv_FoodList_name);
            keyword = (TextView) itemView.findViewById(R.id.tv_FoodList_keyword);
            iv = (ImageView) itemView.findViewById(R.id.iv_FoodList_img);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RvUpdateAdapter.ViewHolder holder, final int position) {
        holder.title.setText(dataList.get(position).getTitle());
        holder.keyword.setText(dataList.get(position).getKeywords());

        //String temp = dataList.get(position).getImg();
        // boolean flag = temp.contentEquals("其他");
        if (!(dataList.get(position).getImg()).contains("default.jpg")) {
            Glide.with(mContext).load("http://tnfs.tngou.net/img" + dataList.get(position).getImg())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.iv);
        } else {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.error));
        }


        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FocusUpdateDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", dataList.get(position));
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}

