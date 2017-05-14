package com.cruse.life.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import com.cruse.life.R;
import com.cruse.life.entity.NewsDetailBean;
import com.cruse.life.presenter.NewsDetailPresenter;
import com.cruse.life.view.NewsDetailView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by lulanqin on 2017/5/10.
 */
public class NewsDetailAtivity extends Activity {

    ImageView iv;
    TextView tv_NewsDetail_title;
    TextView tv_NewsDetail_from;
    WebView wv_NewsDetail_message;
    ImageView iv_back;

    String id;
    NewsDetailPresenter mPresenter;


    private NewsDetailView mView = new NewsDetailView() {
        @Override
        public void onSuccess(NewsDetailBean bean) {

            tv_NewsDetail_title.setText(bean.getTitle());
            tv_NewsDetail_from.setText(bean.getKeywords());

            if (bean.getMessage() != null) {
                WebSettings settings = wv_NewsDetail_message.getSettings();
                String htmlData = getNewContent(bean.getMessage());
                wv_NewsDetail_message.getSettings().setDefaultTextEncodingName("UTF-8");
                wv_NewsDetail_message.loadData(htmlData, "text/html; charset=UTF-8", null);

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

        @Override
        public void onError(String result) {

        }
    };

    public static String getNewContent(String htmltext) {

        Document doc = Jsoup.parse(htmltext);
        Elements elements = doc.getElementsByTag("img");
        for (Element element : elements) {
            element.attr("width", "100%").attr("height", "auto");
        }

        return doc.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_focus_detail);

        initView();
        id = getIntent().getStringExtra("id");

        mPresenter = new NewsDetailPresenter(this, id);
        mPresenter.onCreate();
        mPresenter.getDataFromNet();
        mPresenter.attachView(mView);

    }

    private void initView() {
        tv_NewsDetail_title = (TextView) findViewById(R.id.tv_FocusDetail_title);
        tv_NewsDetail_from = (TextView) findViewById(R.id.tv_FocusDetail_from);
        //tv_FocusDetail_description = (TextView) findViewById(R.id.tv_FocusDetail_description);
        wv_NewsDetail_message = (WebView) findViewById(R.id.wv_FocusDetail_message);
        iv = (ImageView) findViewById(R.id.iv_FocusDetail_img);
        iv_back = (ImageView) findViewById(R.id.iv_back);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }
}

