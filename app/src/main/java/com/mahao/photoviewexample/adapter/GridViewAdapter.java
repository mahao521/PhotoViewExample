package com.mahao.photoviewexample.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mahao.photoviewexample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Penghy on 2016/11/15.
 */
public class GridViewAdapter extends BaseAdapter {

    private List<String> mIntegerList;
    private Context mContext;


    public GridViewAdapter(List<String> mlist,Context context){

        this.mIntegerList = mlist;
        this.mContext = context;
    }

    @Override
    public int getCount() {

        int len = 0;
        if(mIntegerList != null){
            len = mIntegerList.size();
        }
        Log.i("mahao","manactivity_len"+ len);
        return len;
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item,viewGroup,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(mContext).load(mIntegerList.get(i)).fit()
                //.placeholder(R.mipmap.ic_launcher)
                .into(holder.mImageView);

        return convertView;
    }

     class ViewHolder{

        private ImageView mImageView;

         public ViewHolder(View imageView){

              mImageView = (ImageView) imageView.findViewById(R.id.img_grid_item);
         }
    }
}
