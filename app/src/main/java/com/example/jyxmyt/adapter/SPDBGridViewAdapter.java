package com.example.jyxmyt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jyxmyt.R;

public abstract class SPDBGridViewAdapter extends BaseAdapter {

	Context context;
	String set[];
	Integer set1[];

	public SPDBGridViewAdapter(Context context, String set[],Integer set1[]){
		this.context = context;
		this.set = set;
		this.set1 = set1;
	}

	@Override
	public int getCount(){

		return set.length;
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
		public ImageView sp_image; // 视频封面图
		public TextView sp_name; // 视频名称
		public TextView sp_founder; // 创建人
		public TextView sp_time; // 创建时间
		public TextView sp_kd; // 看点
		public TextView sp_length; // 片长
		public TextView sp_describe; // 视频描述
		public ImageView play_but; //播放按钮
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.spdb_item, null);
			holder.sp_image = (ImageView) convertView
					.findViewById(R.id.sp_image);
			holder.sp_name = (TextView) convertView.findViewById(R.id.sp_name);
			holder.sp_founder = (TextView) convertView
					.findViewById(R.id.sp_founder);
			holder.sp_time = (TextView) convertView.findViewById(R.id.sp_time);
			holder.sp_kd = (TextView) convertView.findViewById(R.id.sp_kd);
			holder.sp_length = (TextView) convertView
					.findViewById(R.id.sp_length);
			holder.sp_describe = (TextView) convertView
					.findViewById(R.id.sp_describe);
			holder.play_but = (ImageView) convertView
					.findViewById(R.id.play_but);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		String p[] = set[position].split("_");
		holder.sp_name.setText("名称: " + p[0]);
		holder.sp_founder.setText("创建人: " + p[1]);
		holder.sp_time.setText("创建时间: " + p[2]);
		holder.sp_kd.setText("看点: " + p[3]);
		holder.sp_length.setText("片长: " + p[4]);
		holder.sp_describe.setText(p[5]);
		holder.sp_image.setImageResource(set1[position]);
		
		holder.play_but.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v){
				OnDemand(position);
			}
		});
		
		return convertView;
	}

	public abstract void OnDemand(int position);
}
