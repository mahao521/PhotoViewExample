package com.mahao.photoviewexample.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
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
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Penghy on 2016/11/15.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<String> mList;
    private Context mContext;
    private View mCurrentView;
    private GridView mGridView;
    private Info mInfo;
    private View  layoutView;
    private boolean flag = true;
    private boolean currentFalg = false;

    public ViewPagerAdapter(List<String> list, Context context, GridView gridView
            ,View layoutView,boolean position){

        this.mList = list;
        this.mContext = context;
        this.mGridView = gridView;
        Log.i("mahao",mGridView.getChildCount()+".." + mGridView.getAdapter().getCount());
        this.layoutView = layoutView;
        currentFalg = position;
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


    //更显状态
    public void updateFlag (Boolean flag){

        this.currentFalg = flag;
        //if(currentFalg == true){

            notifyDataSetChanged();
       // }
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
       // photoView.setImageResource(mList.get(position));
        Picasso.with(mContext).load(mList.get(position)).fit()
               // .placeholder(R.mipmap.ic_launcher)
                 .into(photoView);
        photoView.enable();
        view.setTag(position);

        //只有每次更换adapter的时候，第一个点击的视图需要添加动画
        if(flag == currentFalg){

            View girdItem =  mGridView.getChildAt(position);
            Log.i("mahao",mGridView.getChildCount()+"..........."+girdItem);
            ImageView image = (ImageView) girdItem.findViewById(R.id.img_grid_item);
            mInfo = PhotoView.getImageViewInfo(image);
            Log.i("mahao","111111111111111111111111111111111111111111111");
            photoView.animaFrom(mInfo);
            currentFalg = false;
        }

        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View girdItem =  mGridView.getChildAt(position);
                ImageView image = (ImageView) girdItem.findViewById(R.id.img_grid_item);
                Info mInfo = PhotoView.getImageViewInfo(image);
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

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
