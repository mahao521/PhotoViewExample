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
import com.mahao.photoviewexample.utils.ListGridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GridView mGridView;
    private ViewPager mViewPager;
    private List<String> mList;
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
        mPagerAdapter = new ViewPagerAdapter(mList, MainActivity.this,mGridView,mLayout,true);
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

                Log.i("mahao","first___");
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

    //总结gridview:只会创建一屏幕的数据，它的girdview.getchildCount()等于屏幕上显示的item个数
    //但是它的adapter个数是全部的；


    private void initData() {

        mList = new ArrayList<>();

       /* mList.add(R.mipmap.pager_one);
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
*/
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308136466&di=d3df0660b6cad8055ced40f081ff08ef&imgtype=0&src=http%3A%2F%2Fi5.3conline.com%2Fimages%2Fpiclib%2F201201%2F04%2Fbatch%2F1%2F123590%2F1325657824467t6txnk6u78.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308136462&di=7a3427a1a7e1f529c7d95da7d6f405ee&imgtype=0&src=http%3A%2F%2Fimage91.360doc.com%2FDownloadImg%2F2015%2F12%2F0813%2F62615249_1.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308469766&di=8568983d815a5160cded71746fc7a9ca&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160327%2F16-16032G21928.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489309860698&di=dc7644f463453eaa0850b5ef0aa38b25&imgtype=0&src=http%3A%2F%2Fimage95.360doc.com%2FDownloadImg%2F2016%2F02%2F2622%2F66721351_2.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1489299944&di=951c3ae0db72084b0fbfb9d8c51eb241&src=http://kantongxiao.com/upload/aHR0cDovL2cxLnlraW1nLmNvbS8wNTExNkY0OTUzRDJCMTdGNjcwODRDNzBCMzI2MjhGOA==.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1489300090&di=736e965dbc8651b962dd87c0a717adb1&src=http://imgchr.com/images/343691.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310297792&di=634fe961ee23769e83937f0b48170772&imgtype=0&src=http%3A%2F%2Fimg.qqai.net%2Fuploads%2Fi_0_4035598123x1084105545_21.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310726121&di=6e965108e646e728c2b0cd42a3bcba72&imgtype=0&src=http%3A%2F%2Fi1.17173.itc.cn%2F2014%2Fuploads%2Fvfz01%2Fvlog%2Fimages%2Fvideo%2F20140415%2F12727166_0.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310802270&di=b6efe180acb9fbd6ac94c7f3d943241f&imgtype=0&src=http%3A%2F%2Fvi3.ku6img.com%2Fdata1%2Fp12%2Fku6video%2F2014%2F1%2F6%2F13%2F1394313850193_93233402_93233402%2F106.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844360&di=4fd247ce56a810672e8a8f49c8c53c00&imgtype=0&src=http%3A%2F%2Fimg.sc115.com%2Fhb%2Fyl2%2F19%2F881606270133806.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844359&di=abfc2266288fd3600d18bfd53536a99d&imgtype=0&src=http%3A%2F%2Fi.dimg.cc%2Fdc%2Fd8%2Fa3%2F56%2Ff8%2Fc9%2F66%2Fd7%2Fa0%2F8d%2F98%2Fdb%2F84%2F8d%2F71%2F26.jpg");
        mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844357&di=5b232c6e28d488a493505e18e97613c3&imgtype=0&src=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F1609%2F22%2F8394940_19_thumb.jpg");
      //  mList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489311045886&di=a4d52e143d533288a95221a0b972ab7b&imgtype=0&src=http%3A%2F%2Fdimg05.c-ctrip.com%2Fimages%2Ftg%2F076%2F253%2F885%2F59af24cce33e46748111ca3b682909ea.jpg");
      //  mList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=970337886,1355285319&fm=23&gp=0.jpg");
    }


    public void initPhoto(int position,boolean flag){

        View currentView = mViewPager.findViewWithTag(position);
        Log.i("mahao",currentView+"11111111111111111111111");
        if(currentView != null) {

            final PhotoView mPhoto = (PhotoView) currentView.findViewById(R.id.photo_view);
            mPhoto.enable();
            View view = mGridView.getChildAt(position);
            Log.i("mahao","view==="+view+"...."+mGridView.getChildCount());
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
