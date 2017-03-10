package com.mahao.photoviewexample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.mahao.photoviewexample.adapter.ListviewAdapter;

import static com.mahao.photoviewexample.R.id.layout_viewpager_second;

public class SecondActivity extends AppCompatActivity {


    private ListView mListView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mListView = (ListView) findViewById(R.id.list_view);
        mViewPager = (ViewPager) findViewById(R.id.viewpager_second);

        View viewLayout = findViewById(R.id.layout_viewpager_second);

        ListviewAdapter adapter = new ListviewAdapter(this,mViewPager,viewLayout);
        mListView.setAdapter(adapter);
    }

    public void secondClick(View view) {

        mViewPager.setCurrentItem(4,false);
    }
}
