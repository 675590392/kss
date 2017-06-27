package com.example.jyxmyt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;

public class SpinnerAdapter extends BaseAdapter {

	Context context;
	String set[];

	public SpinnerAdapter(Context context, String set[]){
		this.context = context;
		this.set = set;
	}

	@Override
	public int getCount(){
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
		public TextView text; // 
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.spinner_item, null);
			holder.text = (TextView)convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(set[position]);
		return convertView;
	}
}
