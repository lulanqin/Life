package com.cruse.life.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.cruse.life.R;
import com.cruse.life.activity.NewsListActivity;
import com.cruse.life.entity.NewsTypeBean;

import java.util.List;

/**
 * Created by lulanqin on 2017/4/5.
 */
public class NewsTypeAdapter extends SimpleBaseAdapter<NewsTypeBean.TngouBean> {

    //String content;
    public NewsTypeAdapter(Context c, List<NewsTypeBean.TngouBean> datas) {
        super(c, datas);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        EntityHolder holder = null;
        if (convertView == null) {
            holder = new EntityHolder();
            convertView = layoutInflater.inflate(R.layout.item_news, null);

            convertView.setTag(holder);
        } else {
            holder = (EntityHolder) convertView.getTag();
        }

        holder.iv = (ImageView) convertView.findViewById(R.id.iv_News);

        holder.tv_title = (TextView) convertView.findViewById(R.id.tv_News_name);
        String content = datas.get(position).getName();
        holder.tv_title.setText(content);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, NewsListActivity.class);
                intent.putExtra("id", datas.get(position).getId() + "");
                intent.putExtra("name", datas.get(position).getName());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }
        });


        if (content.contains("减肥瘦身")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.thin));
        }

        if (content.contains("私密生活")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.simi));
        }

        if (content.contains("女性保养")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.baoyang));
        }

        if (content.contains("男性健康")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.man));
        }


        if (content.contentEquals("孕婴手册")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.baby));
        }

        if (content.contains("夫妻情感")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.emo));
        }

        if (content.contains("育儿宝典")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.yuer));
        }

        if (content.contains("健康饮食")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.yinshi));
        }

        if (content.contains("医疗护理")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.huli));
        }

        if (content.equals("老人健康")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.older));
        }

        if (content.contains("孩子健康")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.haizijiankang));
        }

        //boolean flag = content.contentEquals("四季养生");
        if (content.contentEquals("四季养生")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.season));
        }

        if (content.contentEquals("心里健康")) {
            holder.iv.setBackground(c.getResources().getDrawable(R.mipmap.xinlijiankang));
        }


        return convertView;
    }

    private class EntityHolder

    {
        private TextView tv_title;
        private ImageView iv;
    }
}

