package com.cruse.life.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cruse.life.R;
import com.cruse.life.activity.FoodDetailAtivity;
import com.cruse.life.entity.FoodTypeListBean;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by lulanqin on 2017/5/12.
 */
public class RvCurrFoodAdapter extends RecyclerView.Adapter<RvCurrFoodAdapter.ViewHolder> {

    private Context mContext;
    private List<FoodTypeListBean.TngouBean> dataList = new LinkedList<>();

    public void addAllData(FoodTypeListBean bean) {
        this.dataList.addAll(bean.getTngou());
        int size = dataList.size();
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public RvCurrFoodAdapter(Context context, FoodTypeListBean bean) {
        mContext = context;
        dataList.addAll(bean.getTngou());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        private ImageView iv;
        private TextView keyword;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_name);
            iv = (ImageView) itemView.findViewById(R.id.iv_food);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RvCurrFoodAdapter.ViewHolder holder, final int position) {
        holder.title.setText(dataList.get(position).getName());

        Glide.with(mContext).load("http://tnfs.tngou.net/img" + dataList.get(position).getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv);

        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoodDetailAtivity.class);
                intent.putExtra("id", dataList.get(position).getId() + "");
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
