package com.mahao.photoviewexample.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by Penghy on 2016/11/17.
 */


public class ListGridView extends GridView {

    public ListGridView(Context context) {
        super(context);
    }

    public ListGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

         int expandspec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
         super.onMeasure(widthMeasureSpec, expandspec);
    }
}
