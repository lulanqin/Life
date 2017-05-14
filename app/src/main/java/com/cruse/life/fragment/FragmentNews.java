package com.cruse.life.fragment;


        import android.content.Context;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ListView;

        import com.cruse.life.R;
        import com.cruse.life.adapter.NewsTypeAdapter;
        import com.cruse.life.entity.NewsTypeBean;
        import com.cruse.life.presenter.NewsTypePresenter;
        import com.cruse.life.view.NewsTypeView;


/**
 * Created by lulanqin on 2017/5/10.
 */
public class FragmentNews extends Fragment {
    private View view;
    Context mContent;
    ListView lv;
    NewsTypeAdapter adapter;
    NewsTypePresenter mPresenter;

    private NewsTypeView mView = new NewsTypeView() {
        @Override
        public void onSuccess(NewsTypeBean bean) {
            adapter = new NewsTypeAdapter(getActivity(),bean.getTngou());
            lv.setAdapter(adapter);
        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.act_news_type, container, false);
            mContent = getActivity();
            lv = (ListView)view.findViewById(R.id.lv_news);
            mPresenter = new NewsTypePresenter(mContent);
            mPresenter.onCreate();
            mPresenter.getDataFromNet();
            mPresenter.attachView(mView);
        }

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.onStop();
    }


}

