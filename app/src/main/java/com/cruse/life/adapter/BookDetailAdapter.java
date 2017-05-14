package com.cruse.life.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cruse.life.R;
import com.cruse.life.activity.ChapterActivity;
import com.cruse.life.entity.BookDetailBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lulanqin on 2017/4/5.
 */
public class BookDetailAdapter extends SimpleBaseAdapter<BookDetailBean.ListBean> {

    String name;
    List<BookDetailBean.ListBean> datas = new ArrayList<>();

    public BookDetailAdapter(Context c, List<BookDetailBean.ListBean> pdatas) {
        super(c, pdatas);
        int size = pdatas.size();
        for (int i = size; i > 0; i--)
            datas.add(pdatas.get(i - 1));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        EntityHolder holder = null;
        if (convertView == null) {
            holder = new EntityHolder();
            convertView = layoutInflater.inflate(R.layout.item_detail_chapter, null);

            convertView.setTag(holder);
        } else {
            holder = (EntityHolder) convertView.getTag();
        }

        holder.tv_title = (TextView) convertView.findViewById(R.id.tv_chapter);
        name = datas.get(position).getTitle();
        holder.tv_title.setText(name);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, ChapterActivity.class);
                intent.putExtra("chapter", datas.get(position).getMessage() + "");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            }
        });


        return convertView;
    }

    private class EntityHolder

    {
        private TextView tv_title;
    }
}
