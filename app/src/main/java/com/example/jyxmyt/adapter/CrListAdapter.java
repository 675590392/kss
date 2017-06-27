package com.example.jyxmyt.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.PersonalCr;


public class CrListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<PersonalCr> personalCrs;
	private PersonalCr cr;
	
	public CrListAdapter(Context context, ArrayList<PersonalCr> personalCrs){
		this.context = context;
		this.personalCrs = personalCrs;
	}

	@Override
	public int getCount(){
		return personalCrs.size();
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
		public TextView time; // 日期
		public TextView money; // 金额
		public TextView source; // 来源
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.cr_item, null);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.money = (TextView) convertView.findViewById(R.id.money);
			holder.source = (TextView) convertView.findViewById(R.id.source);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		cr = personalCrs.get(position);
		holder.time.setText(cr.getTime());
		holder.money.setText(cr.getMoney());
		holder.source.setText(cr.getSource());
		return convertView;
	}
}
