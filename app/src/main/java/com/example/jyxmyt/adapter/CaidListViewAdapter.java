package com.example.jyxmyt.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.WeekRecipes;

import java.util.List;

@SuppressLint("ResourceAsColor")
public class CaidListViewAdapter extends BaseAdapter {

	Context context;
	String set[];

	public CaidListViewAdapter(Context context, String set[]){
		this.context = context;
		this.set = set;
	}

	@Override
	public int getCount(){
		// TODO Auto-generated method stub
		return set.length;
	}

	@Override
	public Object getItem(int position){
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position){
		// TODO Auto-generated method stub
		return position;
	}

	private class ViewHolder {
		public TextView date; // 日期
		public TextView week=null; // 星期
		public TextView breakfast; // 早餐
		public TextView chinese; // 中餐
		public TextView dinner; // 晚餐
		public TextView remarks; // 备注
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.caid_item, null);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.date = (TextView) convertView.findViewById(R.id.date);
		holder.week = (TextView) convertView.findViewById(R.id.week);
		holder.breakfast = (TextView) convertView.findViewById(R.id.breakfast);
		holder.chinese = (TextView) convertView.findViewById(R.id.chinese);
		holder.dinner = (TextView) convertView.findViewById(R.id.dinner);
		holder.remarks = (TextView) convertView.findViewById(R.id.qita);

	 	holder.date.setTextColor(context.getResources().getColor(R.color.text_color));
		holder.week.setTextColor(context.getResources().getColor(R.color.text_color));
		holder.breakfast.setTextColor(context.getResources().getColor(R.color.hui));
		holder.chinese.setTextColor(context.getResources().getColor(R.color.hui));
		holder.dinner.setTextColor(context.getResources().getColor(R.color.hui));
		holder.remarks.setTextColor(context.getResources().getColor(R.color.hui));

		String p[] = set[position].split("_");

		try {
			holder.date.setText(p[0]);
			holder.week.setText(p[1]);
			holder.breakfast.setText(p[2]);
			holder.chinese.setText(p[3]);
			holder.dinner.setText(p[4]);
			holder.remarks.setText(p[5]);
		}catch (Exception e){
			e.printStackTrace();
		}

		return convertView;
	}

}
