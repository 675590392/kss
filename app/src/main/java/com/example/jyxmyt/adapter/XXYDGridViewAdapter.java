package com.example.jyxmyt.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.book.ReadBookActivity;

public class XXYDGridViewAdapter extends BaseAdapter {

	Context context;
	 //Sacle动画 - 渐变尺寸缩放  
    private Animation scaleAnimation = null;  
	public XXYDGridViewAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount(){
		return 10;
	}

	@Override
	public Object getItem(int position){
		return position;
	}

	@Override
	public long getItemId(int position){
		return position;
	}

	private class ViewHolder {
		public ImageView wp_image; // 物品图片
		public TextView wp_name; // 物品名称
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		final ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.xxyd_grid_item, null);
			holder.wp_image = (ImageView) convertView
					.findViewById(R.id.wp_image);
			holder.wp_name = (TextView) convertView.findViewById(R.id.wp_name);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}

		holder.wp_name.setText("法律的概念");
		holder.wp_image.setBackgroundResource(R.drawable.xxyd_grid_image);
		holder.wp_image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				context.startActivity(new Intent(context, ReadBookActivity.class));
			}
		});
		return convertView;
	}
	
}
