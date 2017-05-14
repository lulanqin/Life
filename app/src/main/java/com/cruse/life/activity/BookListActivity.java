package com.cruse.life.activity;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.ListView;
        import android.widget.TextView;

        import com.cruse.life.R;
        import com.cruse.life.adapter.BookListAdapter;
        import com.cruse.life.entity.BookListBean;
        import com.cruse.life.presenter.BookListPresenter;
        import com.cruse.life.view.BookListView;


/**
 * Created by lulanqin on 2017/5/10.
 */
public class BookListActivity extends Activity{
    private ListView lv;
    private ImageView iv_back;
    private BookListAdapter adapter;
    private BookListPresenter mPresenter;
    private String name;
    private String id;


    private BookListView mFoodTypeListView = new BookListView() {
        @Override
        public void onSuccess(BookListBean bean) {
            adapter = new BookListAdapter(getApplicationContext(),bean.getList());
            lv.setAdapter(adapter);

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_food_list);

        lv = (ListView)findViewById(R.id.lv_FoodTypeList);
        TextView tv_title = (TextView)findViewById(R.id.tv_FoodType_title);
        iv_back =  (ImageView)findViewById(R.id.iv_back);

        name = getIntent().getStringExtra("name");
        id = getIntent().getStringExtra("id");

        tv_title.setText(name);

        mPresenter = new BookListPresenter(this,id);
        mPresenter.onCreate();
        mPresenter.getDataFromNet();
        mPresenter.attachView(mFoodTypeListView);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onStop();
    }
}

