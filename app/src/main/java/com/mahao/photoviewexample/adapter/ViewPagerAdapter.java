package com.mahao.photoviewexample.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.mahao.photoviewexample.R;

import java.util.List;

/**
 * Created by Penghy on 2016/11/15.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<Integer> mList;
    private Context mContext;
    private View mCurrentView;
    private GridView mGridView;
    private Info mInfo;
    private View  layoutView;
    private int count = 1;
    private int currentPosition = 0;

    public ViewPagerAdapter(List<Integer> list, Context context, GridView gridView
    ,View layoutView,int position){

        this.mList = list;
        this.mContext = context;
        this.mGridView = gridView;
        this.layoutView = layoutView;
        currentPosition = position;
    }



    @Override
    public int getCount() {

        int len = 0;
        if(mList != null){
            len = mList.size();
        }
        return len;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentView = (View)object;
    }

    public View getPrimaryItem() {
        return mCurrentView;
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position) {

        Log.i("mahao",position+"...00");
        View view  = LayoutInflater.from(mContext).inflate(R.layout.pager_item,container,false);
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        final PhotoView photoView = (PhotoView) view.findViewById(R.id.photo_view);
        photoView.setImageResource(mList.get(position));
        photoView.enable();

        View girdItem =  mGridView.getChildAt(position);
        ImageView image = (ImageView) girdItem.findViewById(R.id.img_grid_item);
        mInfo = PhotoView.getImageViewInfo(image);
        view.setTag(position);

        if(currentPosition != -1){


        }

        //item 只执行一次

         /* if(currentPosition != -1 ){

                Log.i("mahao","cur-----------" + currentPosition);

                View girdItem =  mGridView.getChildAt(position);
                //  Log.i("mahao",position + ".."+ girdItem);
                ImageView image = (ImageView) girdItem.findViewById(R.id.img_grid_item);
                mInfo = PhotoView.getImageViewInfo(image);
                photoView.animaFrom(mInfo);
            }*/

       // Log.i("mahao",position + ".."+ mGridView);

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View girdItem =  mGridView.getChildAt(position);
                ImageView image = (ImageView) girdItem.findViewById(R.id.img_grid_item);
                mInfo = PhotoView.getImageViewInfo(image);
                Toast.makeText(mContext, "view_pager--2..." + position, Toast.LENGTH_SHORT).show();
                photoView.animaTo(mInfo, new Runnable() {
                    @Override
                    public void run() {

                        layoutView.setVisibility(View.GONE);
                    }
                });
            }
        });
        container.addView(view);
        return view;
    }
}
