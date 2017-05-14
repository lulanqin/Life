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
import com.cruse.life.activity.BookDetailAtivity;
import com.cruse.life.entity.BookListBean;


import java.util.List;

/**
 * Created by lulanqin on 2017/4/5.
 */
public class BookListAdapter extends SimpleBaseAdapter<BookListBean.ListBean> {

    String name;

    public BookListAdapter(Context c, List<BookListBean.ListBean> datas) {
        super(c, datas);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        EntityHolder holder = null;
        if (convertView == null) {
            holder = new EntityHolder();
            convertView = layoutInflater.inflate(R.layout.item_booklist, null);

            convertView.setTag(holder);
        } else {
            holder = (EntityHolder) convertView.getTag();
        }

        holder.iv = (ImageView) convertView.findViewById(R.id.iv_BookList_img);

        Glide.with(c).load("http://tnfs.tngou.net/img" + datas.get(position).getImg())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.iv);

        holder.tv_name = (TextView) convertView.findViewById(R.id.tv_BookList_name);
        holder.tv_name.setText(datas.get(position).getName());

        holder.tv_author = (TextView) convertView.findViewById(R.id.tv_BookList_author);
        holder.tv_author.setText(datas.get(position).getAuthor());

        holder.tv_summary = (TextView) convertView.findViewById(R.id.tv_BookList_summary);
        holder.tv_summary.setText(datas.get(position).getSummary());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, BookDetailAtivity.class);
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
        private TextView tv_name;
        private TextView tv_author;
        private TextView tv_summary;
    }
}
