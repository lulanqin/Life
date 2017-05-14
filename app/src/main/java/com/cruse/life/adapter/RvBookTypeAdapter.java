package com.cruse.life.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.cruse.life.R;
import com.cruse.life.activity.BookListActivity;
import com.cruse.life.entity.BookTypeBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lulanqin on 2017/5/12.
 */
public class RvBookTypeAdapter extends RecyclerView.Adapter<RvBookTypeAdapter.ViewHolder> {

    String content;
    private Context mContext;
    private List<BookTypeBean.TngouBean> dataList = new LinkedList<>();

    public void addAllData(BookTypeBean bean) {
        this.dataList.addAll(bean.getTngou());
        int size = dataList.size();
        notifyDataSetChanged();
    }

    public void clearData() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public RvBookTypeAdapter(Context context, BookTypeBean bean) {
        mContext = context;
        dataList.addAll(bean.getTngou());
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView iv;
        public RelativeLayout rl_bookType;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tv_bookTypeName);
            iv = (ImageView) itemView.findViewById(R.id.iv_bookIcon);
            rl_bookType = (RelativeLayout) itemView.findViewById(R.id.rl_bookType);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RvBookTypeAdapter.ViewHolder holder, final int position) {

        content = dataList.get(position).getName();
        holder.title.setText(content);

        if (content.contains("孕妇育儿")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.women));
        }

        if (content.contains("美容时尚")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.meirong));
        }

        if (content.contains("健康养生")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.jiangkang));
        }

        if (content.contains("两性生活")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.sex));
        }

        if (content.contains("美食烹饪")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.meishi));
        }

        if (content.contains("修养心里")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.xiuyang));
        }

        if (content.contains("家庭教育")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.edu));
        }

        if (content.contains("幽默笑话")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.joke));
        }

        if (content.contains("生活杂谈")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.travel));
            holder.title.setText("人在旅途");
        }

        // boolean flag = content.contentEquals("其他");
        if (content.equals("其它")) {
            holder.iv.setBackground(mContext.getResources().getDrawable(R.mipmap.other));
        }


        holder.rl_bookType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, BookListActivity.class);
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

