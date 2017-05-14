package com.cruse.life.adapter;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cruse.life.R;
import com.cruse.life.activity.FocusUpdateDetailActivity;
import com.cruse.life.entity.FocusUpdateBean;


import java.util.LinkedList;
import java.util.List;

/**
 * Created by lulanqin on 2017/4/5.
 */
public class FocusUpdateAdapter extends SimpleBaseAdapter<FocusUpdateBean.TngouBean> {

    String name;
    List<FocusUpdateBean.TngouBean> datas = new LinkedList<>();

    public FocusUpdateAdapter(Context c, List<FocusUpdateBean.TngouBean> pdatas) {
        super(c, pdatas);
        this.datas = pdatas;
    }

    public void addData(FocusUpdateBean bean) {
        this.datas.addAll(bean.getTngou());
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        EntityHolder holder = null;
        if (convertView == null) {
            holder = new EntityHolder();
            convertView = layoutInflater.inflate(R.layout.item_list, null);

            convertView.setTag(holder);
        } else {
            holder = (EntityHolder) convertView.getTag();
        }

        holder.iv = (ImageView) convertView.findViewById(R.id.iv_FoodList_img);


        Glide.with(c).load("http://tnfs.tngou.net/img" + datas.get(position).getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv);


        holder.tv_title = (TextView) convertView.findViewById(R.id.tv_FoodList_name);
        holder.tv_title.setText(datas.get(position).getTitle());

        holder.tv_keyword = (TextView) convertView.findViewById(R.id.tv_FoodList_keyword);
        holder.tv_keyword.setText(datas.get(position).getKeywords());

        holder.tv_material = (TextView) convertView.findViewById(R.id.tv_FoodList_material);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, FocusUpdateDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("bean", datas.get(position));
                intent.putExtras(bundle);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }
        });


        return convertView;
    }

    private class EntityHolder {
        private ImageView iv;
        private TextView tv_title;
        private TextView tv_keyword;
        private TextView tv_material;
    }
}

