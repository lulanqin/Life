package com.cruse.life.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.cruse.life.R;
import com.cruse.life.activity.FocusListActivity;
import com.cruse.life.entity.FocusTypeBean;

import java.util.List;

/**
 * Created by lulanqin on 2017/4/5.
 */
public class FocusTypeAdapter extends SimpleBaseAdapter<FocusTypeBean.TngouBean> {

    String name;

    public FocusTypeAdapter(Context c, List<FocusTypeBean.TngouBean> datas) {
        super(c, datas);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        EntityHolder holder = null;
        if (convertView == null) {
            holder = new EntityHolder();
            convertView = layoutInflater.inflate(R.layout.item_tags, null);

            convertView.setTag(holder);
        } else {
            holder = (EntityHolder) convertView.getTag();
        }

        holder.tv_title = (TextView) convertView.findViewById(R.id.tv_tag);
        name = datas.get(position).getName();
        holder.tv_title.setText(name);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, FocusListActivity.class);
                intent.putExtra("id", datas.get(position).getId() + "");
                intent.putExtra("name", datas.get(position).getName());
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

