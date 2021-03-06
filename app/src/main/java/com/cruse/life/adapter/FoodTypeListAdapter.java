package com.cruse.life.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cruse.life.R;
import com.cruse.life.activity.FoodDetailAtivity;
import com.cruse.life.entity.FoodTypeListBean;
import com.cruse.life.utils.GlideRoundTransform;


import java.util.List;

/**
 * Created by lulanqin on 2017/4/5.
 */
public class FoodTypeListAdapter extends SimpleBaseAdapter<FoodTypeListBean.TngouBean> {

    String name;
    GlideRoundTransform roundTransform;

    public FoodTypeListAdapter(Context c, List<FoodTypeListBean.TngouBean> datas) {
        super(c, datas);
        roundTransform = new GlideRoundTransform(c);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        EntityHolder holder = null;
        if (convertView == null) {
            holder = new EntityHolder();
            //convertView = layoutInflater.inflate(R.layout.item_foodlist, null);
            convertView = layoutInflater.inflate(R.layout.item_list, null);

            convertView.setTag(holder);
        } else {
            holder = (EntityHolder) convertView.getTag();
        }

        holder.iv = (ImageView) convertView.findViewById(R.id.iv_FoodList_img);

        Glide.with(c).load("http://tnfs.tngou.net/img" + datas.get(position).getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .transform(roundTransform)
                .into(holder.iv);

        holder.tv_title = (TextView) convertView.findViewById(R.id.tv_FoodList_name);
        holder.tv_title.setText(datas.get(position).getName());

        holder.tv_keyword = (TextView) convertView.findViewById(R.id.tv_FoodList_keyword);
        holder.tv_keyword.setText(datas.get(position).getKeywords());

        holder.tv_material = (TextView) convertView.findViewById(R.id.tv_FoodList_material);
        holder.tv_material.setText(datas.get(position).getFood());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, FoodDetailAtivity.class);
                intent.putExtra("id", datas.get(position).getId() + "");
//                intent.putExtra("name",datas.get(position).getName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }
        });


        return convertView;
    }

    private class EntityHolder

    {
        private ImageView iv;
        private TextView tv_title;
        private TextView tv_keyword;
        private TextView tv_material;
    }
}

