package com.example.jyxmyt.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.PeopleNumOrder;
import com.example.jyxmyt.entity.PersonalCr;
import com.example.jyxmyt.entity.PersonalZc;


public class ZcListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<PeopleNumOrder.RowsBean> personalZcs;
	private PeopleNumOrder.RowsBean zc;
	
	public ZcListAdapter(Context context, ArrayList<PeopleNumOrder.RowsBean> personalZcs){
		this.context = context;
		this.personalZcs = personalZcs;
	}

	@Override
	public int getCount(){
		return personalZcs.size();
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
		public TextView name; // 名称
		public TextView size; // 数量
		public TextView retailRrice; // 零售价
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.zc_item, null);
			holder.time = (TextView) convertView.findViewById(R.id.time);
			holder.name = (TextView) convertView.findViewById(R.id.name);
			holder.size = (TextView) convertView.findViewById(R.id.size);
			holder.retailRrice = (TextView) convertView.findViewById(R.id.retailRrice);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		zc = personalZcs.get(position);
		holder.time.setText(zc.getCreateTime());
		holder.name.setText(zc.getShopName());
		holder.size.setText(zc.getLimitedNumber()+"");
		holder.retailRrice.setText(zc.getShopPrice()+"");
		return convertView;
	}
}
