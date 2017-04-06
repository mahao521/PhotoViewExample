package com.mahao.photoviewexample.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by Penghy on 2017/3/10.
 */

public class MyViewPager extends ViewPager {


    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
/*
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            Field mFirstLayout = ViewPager.class.getDeclaredField("mFirstLayout");
            mFirstLayout.setAccessible(true);
            mFirstLayout.set(this, false);
            getAdapter().notifyDataSetChanged();
            setCurrentItem(getCurrentItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

   /* @Override
    public void onRestoreInstanceState(Parcelable state) {

        try {
            Field mFirstLayout = ViewPager.class.getDeclaredField("mRestoredCurItem");
            mFirstLayout.setAccessible(true);
            mFirstLayout.set(this, -1);
            getAdapter().notifyDataSetChanged();
            setCurrentItem(getCurrentItem());
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onRestoreInstanceState(state);
    }*/
}
