package com.mahao.photoviewexample.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.mahao.photoviewexample.R;
import com.mahao.photoviewexample.utils.ListGridView;

import java.security.PolicySpi;
import java.util.ArrayList;
import java.util.List;

import static android.R.attr.positiveButtonText;
import static android.R.attr.x;

/**
 * Created by Penghy on 2016/11/17.
 */


public class ListviewAdapter extends BaseAdapter{

    private List<Integer> mIntegerList;
    private Context mContext;
    private ViewPager mViewPager;
    private ViewHolder mHolder;
    private View layoutView;
    private GridViewAdapter mAdapter;
    private ViewPagerAdapter mPagerAdapter;

    //记录当前的viewPager
    private GridView mCurrentGridView;

    //记录当前的位置
    private int currentPosition = -1;

    //记录上一次的位置
    private int oldPosition = -1;

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
        return 9;
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
        mHolder.mTxtType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("mahao","onclick");
                mViewPager.setCurrentItem(6,false);
            }
        });

        mAdapter = new GridViewAdapter(getList(11-i),mContext);
        mHolder.mGridView.setAdapter(mAdapter);
        mHolder.mGridView.setTag(i);

        mCurrentGridView = mHolder.mGridView;

/*       mPagerAdapter = new ViewPagerAdapter(getList(mHolder.mGridView.getAdapter().getCount()), mContext,mHolder.mGridView, layoutView,i);
         mViewPager.setAdapter(mPagerAdapter);*/

      //  mHolder.mGridView.setOnItemClickListener(this);

        mHolder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {

                layoutView.setVisibility(View.VISIBLE);

                 //只创建一次
                 //同一个item里面点击没问题，，不同的item点击需要重新穿件viewpager
                 currentPosition = (int) adapterView.getTag();   // 6   7
                 if(oldPosition == -1){  //初始化

                     oldPosition = currentPosition;  //用oldposition记录当前位置； //6
                     mPagerAdapter = new ViewPagerAdapter(getList(adapterView.getAdapter().getCount()), mContext,(GridView)adapterView, layoutView,i);
                     mViewPager.setAdapter(mPagerAdapter);
                 }
                Log.i("mahao",oldPosition + "___________1________________" + currentPosition);
                if(oldPosition != currentPosition){  // 不同的position ，需要创建adapter

                    mPagerAdapter = new ViewPagerAdapter(getList(adapterView.getAdapter().getCount()), mContext,(GridView)adapterView, layoutView,i);
                    mViewPager.setAdapter(mPagerAdapter);

                    oldPosition = currentPosition;  //继续保存这个position；

                }
                Log.i("mahao",oldPosition + "_________2__________________" + currentPosition);

                //mViewPage
                mViewPager.setCurrentItem(i, false);
                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                      // mViewPager.setTag(position);
                      //View currentView = mViewPager.findViewWithTag(position);
                      //Log.i("mahao",currentView+"...............");
                      // initPhoto((GridView)adapterView,i,true);
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });
               // initPhoto((GridView)adapterView,i,true);
                ImageView imageView = (ImageView) view.findViewById(R.id.img_grid_item);
                Info imageViewInfo = PhotoView.getImageViewInfo(imageView);
                View pagerView = mViewPager.findViewWithTag(i);
                if(pagerView != null){

                    PhotoView mPhoto = (PhotoView) pagerView.findViewById(R.id.photo_view);
                    mPhoto.enable();
                    mPhoto.animaFrom(imageViewInfo);
                }
            }
        });
        return view;
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

    public List<Integer> getList(int i){

        List<Integer> mList = new ArrayList<>();
        mList.add(R.mipmap.pager_one);
        mList.add(R.mipmap.pager_one);
        mList.add(R.mipmap.pager_eight);
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
        return mList.subList(0,i);
    }

    //获取gridview的每个子条目
    public void initPhoto(GridView gridView,int position, boolean flag) {

        View currentView = mViewPager.findViewWithTag(position);
       // View currentView = mViewPager.getChildAt(position);
        Log.i("mahao",currentView +"////[[[[[[[[[[[[[[[[[" + flag + "............." + position);
        View viewIn = LayoutInflater.from(mContext).inflate(R.layout.pager_item,null);
       // if (currentView != null) {

            final PhotoView mPhoto = (PhotoView) viewIn.findViewById(R.id.photo_view);
            mPhoto.enable();
            View view = gridView.getChildAt(position);

            ImageView image = (ImageView) view.findViewById(R.id.img_grid_item);
            Info mInfo = PhotoView.getImageViewInfo(image);

            if (flag == true) {

                mPhoto.animaFrom(mInfo);
            }
          /*  mPhoto.setOnClickListener(new View.OnClickListener() {
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
            });*/
      //  }
    }
}
