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
import com.cruse.life.activity.FoodListActivity;
import com.cruse.life.entity.FoodBean;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by lulanqin on 2017/5/12.
 */
public class RvFoodTypeAdapter extends RecyclerView.Adapter<RvFoodTypeAdapter.ViewHolder> {

    private Context mContext;
    private List<FoodBean.TngouBean> dataList = new LinkedList<>();

    public void addAllData(FoodBean bean) {
        this.dataList.addAll(bean.getTngou());
        int size = dataList.size();
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public RvFoodTypeAdapter(Context context, FoodBean bean) {
        mContext = context;
        dataList.addAll(bean.getTngou());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_tag);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_tags, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RvFoodTypeAdapter.ViewHolder holder, final int position) {
        holder.title.setText(dataList.get(position).getName());

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FoodListActivity.class);
                intent.putExtra("id", dataList.get(position).getId() + "");
                intent.putExtra("name", dataList.get(position).getName());
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

