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

import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.mahao.photoviewexample.R;
import com.mahao.photoviewexample.utils.ListGridView;
import com.mahao.photoviewexample.utils.MyViewPager;

import java.util.ArrayList;
import java.util.List;

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

    private boolean currentState = false;

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
        return 14;
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

                mViewPager.setCurrentItem(6,false);
            }
        });

        mAdapter = new GridViewAdapter(getListString(14-i),mContext);
        mHolder.mGridView.setAdapter(mAdapter);
        mHolder.mGridView.setTag(i);
        mHolder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(final AdapterView<?> adapterView, final View view, int i, long l) {

                layoutView.setVisibility(View.VISIBLE);

                 //只创建一次
                 //同一个item里面点击没问题，，不同的item点击需要重新穿件viewpager
                 currentPosition = (int) adapterView.getTag();   // 6   7
                 if(oldPosition == -1){  //初始化
                      currentState = true;
                      oldPosition = currentPosition;  //用oldposition记录当前位置； //
                      mPagerAdapter = new ViewPagerAdapter(getListString(adapterView.getAdapter().getCount()), mContext,(GridView)adapterView, layoutView,true);
                      mViewPager.setAdapter(mPagerAdapter);

                     //可以实现-----比较耗内存---第一次创建10个,---以后不再创建；
                     //该方法的意思是左边创建10个，右边创建10个；
                     // mViewPager.setOffscreenPageLimit(adapterView.getAdapter().getCount());
                 }else {

                     Log.i("mahao",oldPosition + "___________1________________" + currentPosition);
                     if(oldPosition != currentPosition){  // 不同的position ，需要创建adapter

                         currentState = true;
                         mPagerAdapter = new ViewPagerAdapter(getListString(adapterView.getAdapter().getCount()), mContext,(GridView)adapterView, layoutView,true);
                         mViewPager.setAdapter(mPagerAdapter);
                         oldPosition = currentPosition;  //继续保存这个position；
                      //   mViewPager.getAdapter().notifyDataSetChanged(); //重绘

                     }else {

                         currentState = false;
                     }
                     Log.i("mahao",oldPosition + "_________2__________________" + currentPosition);
                 }

                 //viewpgaer 当adptetr创建之后，再次set（adapter），会判断是否是第一次set,
                //如果是，创建 viewpager的顺序是 ： 例如点击第1个条目  1-0-2
                //如果不是，创建viewpager的顺序是 : 例如点击第一个条目   0 1 2； 每次都会创建 0 -1 ；
                //因此使用 mViewPager.getAdapter().notifyDataSetChanged(); 清除之前创建的 0 - 1

                mViewPager.setCurrentItem(i, false);

               //方法二
                mPagerAdapter.updateFlag(currentState);

                View childAt = adapterView.getChildAt(i);
                ImageView imageView = (ImageView) childAt.findViewById(R.id.img_grid_item);
                Info imageViewInfo = PhotoView.getImageViewInfo(imageView);
                View pagerView = mViewPager.findViewWithTag(i);

                Log.i("mahao","pager----" + pagerView+"___________"+imageViewInfo);
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
        return mList.subList(0,i);//alloc----87.09   free---13.04
    }

    public List<String> getListString(int i){

        List<String> list = new ArrayList<>();
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308136466&di=d3df0660b6cad8055ced40f081ff08ef&imgtype=0&src=http%3A%2F%2Fi5.3conline.com%2Fimages%2Fpiclib%2F201201%2F04%2Fbatch%2F1%2F123590%2F1325657824467t6txnk6u78.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308136462&di=7a3427a1a7e1f529c7d95da7d6f405ee&imgtype=0&src=http%3A%2F%2Fimage91.360doc.com%2FDownloadImg%2F2015%2F12%2F0813%2F62615249_1.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489308469766&di=8568983d815a5160cded71746fc7a9ca&imgtype=0&src=http%3A%2F%2Fimg.tupianzj.com%2Fuploads%2Fallimg%2F160327%2F16-16032G21928.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489309860698&di=dc7644f463453eaa0850b5ef0aa38b25&imgtype=0&src=http%3A%2F%2Fimage95.360doc.com%2FDownloadImg%2F2016%2F02%2F2622%2F66721351_2.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1489299944&di=951c3ae0db72084b0fbfb9d8c51eb241&src=http://kantongxiao.com/upload/aHR0cDovL2cxLnlraW1nLmNvbS8wNTExNkY0OTUzRDJCMTdGNjcwODRDNzBCMzI2MjhGOA==.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b10000_10000&sec=1489300090&di=736e965dbc8651b962dd87c0a717adb1&src=http://imgchr.com/images/343691.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310297792&di=634fe961ee23769e83937f0b48170772&imgtype=0&src=http%3A%2F%2Fimg.qqai.net%2Fuploads%2Fi_0_4035598123x1084105545_21.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310726121&di=6e965108e646e728c2b0cd42a3bcba72&imgtype=0&src=http%3A%2F%2Fi1.17173.itc.cn%2F2014%2Fuploads%2Fvfz01%2Fvlog%2Fimages%2Fvideo%2F20140415%2F12727166_0.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310802270&di=b6efe180acb9fbd6ac94c7f3d943241f&imgtype=0&src=http%3A%2F%2Fvi3.ku6img.com%2Fdata1%2Fp12%2Fku6video%2F2014%2F1%2F6%2F13%2F1394313850193_93233402_93233402%2F106.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844360&di=4fd247ce56a810672e8a8f49c8c53c00&imgtype=0&src=http%3A%2F%2Fimg.sc115.com%2Fhb%2Fyl2%2F19%2F881606270133806.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844359&di=abfc2266288fd3600d18bfd53536a99d&imgtype=0&src=http%3A%2F%2Fi.dimg.cc%2Fdc%2Fd8%2Fa3%2F56%2Ff8%2Fc9%2F66%2Fd7%2Fa0%2F8d%2F98%2Fdb%2F84%2F8d%2F71%2F26.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489310844357&di=5b232c6e28d488a493505e18e97613c3&imgtype=0&src=http%3A%2F%2Fimg0.pconline.com.cn%2Fpconline%2F1609%2F22%2F8394940_19_thumb.jpg");
        list.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489311045886&di=a4d52e143d533288a95221a0b972ab7b&imgtype=0&src=http%3A%2F%2Fdimg05.c-ctrip.com%2Fimages%2Ftg%2F076%2F253%2F885%2F59af24cce33e46748111ca3b682909ea.jpg");
        list.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=970337886,1355285319&fm=23&gp=0.jpg");

        return list.subList(0,i);
    }
}
