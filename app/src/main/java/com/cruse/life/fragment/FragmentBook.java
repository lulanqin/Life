package com.cruse.life.fragment;


import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.cruse.life.R;
import com.cruse.life.adapter.RvBookTypeAdapter;
import com.cruse.life.entity.BookTypeBean;
import com.cruse.life.presenter.BookTypePresenter;
import com.cruse.life.view.BookTypeView;

/**
 * Created by lulanqin on 2017/5/10.
 */
public class FragmentBook extends Fragment {
    private View view;
    Context mContent;
    ListView lv;
    RecyclerView rv_book;
    //BookTypeAdapter adapter;
    RvBookTypeAdapter adapter;
    BookTypePresenter mPresenter;

    private BookTypeView mBookTypeView = new BookTypeView() {
        @Override
        public void onSuccess(BookTypeBean bean) {
            adapter = new RvBookTypeAdapter(getActivity(), bean);
            rv_book.setAdapter(adapter);
        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (view == null) {

            view = inflater.inflate(R.layout.act_book_type, container, false);
            mContent = getActivity();
            //lv = (ListView)view.findViewById(R.id.lv_book);

            rv_book = (RecyclerView) view.findViewById(R.id.rv_book);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
            rv_book.setLayoutManager(gridLayoutManager);
            rv_book.addItemDecoration(new GridSpacingItemDecoration(2, 30, true));

            mPresenter = new BookTypePresenter(mContent);
            mPresenter.onCreate();
            mPresenter.getDataFromNet();
            mPresenter.attachView(mBookTypeView);

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

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }


}

