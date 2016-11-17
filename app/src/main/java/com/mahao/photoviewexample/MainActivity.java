package com.mahao.photoviewexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.mahao.photoviewexample.adapter.GridViewAdapter;
import com.mahao.photoviewexample.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView mGridView;
    private ViewPager mViewPager;
    private List<Integer> mList;
    private ViewPagerAdapter mPagerAdapter;
    private GridViewAdapter mAdapter;
    private View mLayout;
    private TextView mCount;
    private Button btnSave, btnShre;
    private AlphaAnimation mIn;
    private AlphaAnimation mOut;
    private ImageView pagerBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 设置整体布局的背景色变化
        mIn = new AlphaAnimation(0, 1);
        mOut = new AlphaAnimation(1, 0);

        mGridView = (GridView) findViewById(R.id.gridview);
        initData();
        mAdapter = new GridViewAdapter(mList,this);
        mGridView.setAdapter(mAdapter);
        mLayout = findViewById(R.id.layout_viewpager);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerAdapter = new ViewPagerAdapter(mList, MainActivity.this,mGridView,mLayout);
        mViewPager.setAdapter(mPagerAdapter);

        pagerBack = (ImageView) findViewById(R.id.img_back);

        mCount = (TextView) this.findViewById(R.id.txt_count);
        btnSave = (Button) findViewById(R.id.btn_save);
        btnShre = (Button) findViewById(R.id.btn_share);
        btnSave.setOnClickListener(this);
        btnShre.setOnClickListener(this);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                pagerBack.startAnimation(mIn);
                mLayout.setVisibility(View.VISIBLE);
                mViewPager.setCurrentItem(i,false);
                mCount.setText((i+1)+"/"+mList.size());
                initPhoto(i,true);
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                Log.i("mahao","first");
                mCount.setText((position+1)+"/"+mList.size());
                initPhoto(position,false);
            }

        @Override
        public void onPageScrollStateChanged(int state) {

            if (state ==ViewPager.SCROLL_STATE_IDLE) {
                // 未拖动页面时执行此处
            } else if (state ==ViewPager.SCROLL_STATE_DRAGGING) {
                // 正在拖动页面时执行此处
            }else if(state == ViewPager.SCROLL_STATE_SETTLING){

                // 拖动完成
                Log.i("mahao",state+"");
            }
        }
    });

        mOut.setDuration(300);
        mIn.setDuration(300);
        mOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                pagerBack.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initData() {

        mList = new ArrayList<>();

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
    }


    public void initPhoto(int position,boolean flag){

        View currentView = mViewPager.findViewWithTag(position);
        if(currentView != null) {

            final PhotoView mPhoto = (PhotoView) currentView.findViewById(R.id.photo_view);
            mPhoto.enable();
            View view = mGridView.getChildAt(position);
            ImageView image = (ImageView) view.findViewById(R.id.img_grid_item);
            final Info  mInfo = PhotoView.getImageViewInfo(image);
            if(flag == true){

                mPhoto.animaFrom(mInfo);
            }
            mPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Toast.makeText(MainActivity.this, "view_pager--1", Toast.LENGTH_SHORT).show();
                    mPhoto.animaTo(mInfo, new Runnable() {
                        @Override
                        public void run() {

                            pagerBack.startAnimation(mOut);
                            mLayout.setVisibility(View.GONE);

                        }
                    });
                }
            });
        }
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id){

            case R.id.btn_save:

                Intent intent = new Intent(this,SecondActivity.class);
                startActivity(intent);
                Toast.makeText(this,"保存",Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_share:

                Toast.makeText(this,"分享",Toast.LENGTH_SHORT).show();
                break;
        }
    }


}
