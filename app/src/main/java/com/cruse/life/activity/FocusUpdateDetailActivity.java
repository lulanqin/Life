package com.cruse.life.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;


import com.cruse.life.R;
import com.cruse.life.entity.FocusUpdateBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by lulanqin on 2017/5/11.
 */
public class FocusUpdateDetailActivity extends Activity {

    ImageView iv;
    ImageView iv_back;
    TextView tv_FocusDetail_title;
    TextView tv_FocusDetail_from;
    TextView tv_FocusDetail_description;
    WebView wv_FocusDetail_message;
    FocusUpdateBean.TngouBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_focus_detail);
        initView();
        Intent intent = this.getIntent();
        bean = (FocusUpdateBean.TngouBean) intent.getSerializableExtra("bean");

        tv_FocusDetail_title.setText(bean.getTitle());
        tv_FocusDetail_from.setVisibility(View.GONE);

        if (bean.getMessage() != null) {
            WebSettings settings = wv_FocusDetail_message.getSettings();
            String htmlData = getNewContent(bean.getMessage());
            wv_FocusDetail_message.getSettings().setDefaultTextEncodingName("UTF-8");
            wv_FocusDetail_message.loadData(htmlData, "text/html; charset=UTF-8", null);

            settings.setLoadWithOverviewMode(true);   //自适应屏幕
            settings.setUseWideViewPort(true);
            settings.setLoadsImagesAutomatically(true);
            settings.setDefaultFontSize(43);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        }

        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void initView() {
        tv_FocusDetail_title = (TextView) findViewById(R.id.tv_FocusDetail_title);
        tv_FocusDetail_from = (TextView) findViewById(R.id.tv_FocusDetail_from);

        wv_FocusDetail_message = (WebView) findViewById(R.id.wv_FocusDetail_message);
        iv = (ImageView) findViewById(R.id.iv_FocusDetail_img);
        iv_back = (ImageView) findViewById(R.id.iv_back);
    }

    public static String getNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }

        return doc.toString();
    }

}
