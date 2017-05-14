package com.cruse.life.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cruse.life.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by lulanqin on 2017/5/12.
 */
public class ChapterActivity extends Activity {

    private String chapter;
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_chapter);

        chapter = getIntent().getStringExtra("chapter");

        ImageView iv = (ImageView) findViewById(R.id.iv_back);
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        wv = (WebView) findViewById(R.id.wv_chapter);

        if (chapter != null) {
            WebSettings settings = wv.getSettings();
            String htmlData = getNewContent(chapter);
            wv.getSettings().setDefaultTextEncodingName("UTF-8");
            wv.loadData(htmlData, "text/html; charset=UTF-8", null);

            settings.setLoadWithOverviewMode(true);   //自适应屏幕
            settings.setUseWideViewPort(true);
            settings.setLoadsImagesAutomatically(true);
            settings.setDefaultFontSize(43);
            settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        }

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

