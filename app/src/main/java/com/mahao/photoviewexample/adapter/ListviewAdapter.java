package com.mahao.photoviewexample.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.mahao.photoviewexample.MainActivity;
import com.mahao.photoviewexample.R;
import com.mahao.photoviewexample.utils.ListGridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Penghy on 2016/11/17.
 */


public class ListviewAdapter extends BaseAdapter implements ViewPager.OnPageChangeListener {


    private List<Integer> mIntegerList;
    private Context mContext;
    private ViewPager mViewPager;
    private ViewHolder mHolder;
    private View layoutView;

    public ListviewAdapter ( Context context, ViewPager pager,View view){

        this.mViewPager = pager;
        this.mContext = context;
        this.layoutView = view;
    }

    @Override
    public int getCount() {

        int len = 0;
        if(mIntegerList != null){

            len = mIntegerList.size();
        }
        return 7;
    }

    @Override
    public Object getItem(int i) {
        return mIntegerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null ){

            view = LayoutInflater.from(mContext).inflate(R.layout.list_view,viewGroup,false);
            mHolder = new ViewHolder(view);
            view.setTag(mHolder);
        }else{
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.mTxtTitle.setText("        2016年11月17日，我给小露露买了一个手机，然而她并不喜欢我，4700的手机，我回家也许可以干很多事情.");
        mHolder.mTxtType.setText("美女控");

        GridViewAdapter adapter = new GridViewAdapter(getList(),mContext);
        mHolder.mGridView.setAdapter(adapter);
        mHolder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                layoutView.setVisibility(View.VISIBLE);
                mViewPager.setCurrentItem(i,false);
                initPhoto(i,true);
            }
        });

        ViewPagerAdapter  mPagerAdapter = new ViewPagerAdapter(getList(),mContext,mHolder.mGridView,layoutView);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        return view;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

        initPhoto(position,false);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ViewHolder{

        private TextView mTxtTitle;
        private TextView mTxtType;
        private ListGridView mGridView;

        public ViewHolder(View view){

            mGridView = (ListGridView) view.findViewById(R.id.list_grid_view);
            mTxtTitle = (TextView) view.findViewById(R.id.txt_title);
            mTxtType = (TextView) view.findViewById(R.id.btn_type);
        }
    }

    public List<Integer> getList(){

        List<Integer> mList = new ArrayList<>();
        mList.add(R.mipmap.pager_one);
        mList.add(R.mipmap.ic_launcher);
        mList.add(R.mipmap.ic_launcher);
        mList.add(R.mipmap.pager_two);
        mList.add(R.mipmap.pager_three);
        mList.add(R.mipmap.pager_four);
        mList.add(R.mipmap.pager_five);
        mList.add(R.mipmap.pager_six);
        mList.add(R.mipmap.pager_seven);
        mList.add(R.mipmap.pager_eight);
        mList.add(R.mipmap.ic_launcher);
        return mList;
    }


    public void initPhoto(int position,boolean flag) {

        View currentView = mViewPager.findViewWithTag(position);
        if (currentView != null) {

            final PhotoView mPhoto = (PhotoView) currentView.findViewById(R.id.photo_view);
            mPhoto.enable();
            View view = mHolder.mGridView.getChildAt(position);
            ImageView image = (ImageView) view.findViewById(R.id.img_grid_item);
            final Info mInfo = PhotoView.getImageViewInfo(image);
            if (flag == true) {

                mPhoto.animaFrom(mInfo);
            }
            mPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    Toast.makeText(mContext, "view_pager--1", Toast.LENGTH_SHORT).show();
                    mPhoto.animaTo(mInfo, new Runnable() {
                        @Override
                        public void run() {

                            layoutView.setVisibility(View.GONE);
                        }
                    });
                }
            });
        }
    }
}
