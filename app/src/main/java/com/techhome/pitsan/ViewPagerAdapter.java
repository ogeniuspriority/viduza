package com.techhome.pitsan;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    private final List<Presenter> mPresenterList = new ArrayList<>();

    @Override

    public int getCount() {
        return mPresenterList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public void addView(Presenter presenter) {
        mPresenterList.add(presenter);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View currentView = mPresenterList.get(position).getView();
        return currentView;
    }
}
