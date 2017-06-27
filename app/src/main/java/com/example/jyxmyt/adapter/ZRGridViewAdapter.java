package com.example.jyxmyt.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.PersonThing;
import com.example.jyxmyt.entity.Schedule;

public class ZRGridViewAdapter extends BaseAdapter {

	private PersonThing schedule;
	private List<PersonThing> list;
	private Context context;

	public ZRGridViewAdapter(Context context, List<PersonThing> list){
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount(){
		return list.size();
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
		private TextView zrap_time; //时间
		private TextView zrap_name;  //姓名
		private TextView zrap_fh; //番号
		private TextView zrap_fl; //分类
		private TextView zrap_bz;  //备注
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.zrap_item, null);
			holder = new ViewHolder();
			holder.zrap_fh = (TextView)convertView.findViewById(R.id.zrap_fh);
			holder.zrap_time = (TextView)convertView.findViewById(R.id.zrap_time);
			holder.zrap_name = (TextView)convertView.findViewById(R.id.zrap_name);
			holder.zrap_fl = (TextView)convertView.findViewById(R.id.zrap_fl);
			holder.zrap_bz = (TextView)convertView.findViewById(R.id.zrap_bz);
			
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		schedule = list.get(position);
		holder.zrap_time.setText(schedule.getTime());
		holder.zrap_name.setText(schedule.getXm());
		holder.zrap_fh.setText(schedule.getFh());
		holder.zrap_fl.setText(schedule.getZr());
		holder.zrap_bz.setText(schedule.getBz());
		return convertView;
	}
}
