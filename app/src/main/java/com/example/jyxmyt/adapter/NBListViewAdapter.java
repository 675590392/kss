package com.example.jyxmyt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;

/**
 * 内部规定适配器
 * 
 * @author 123
 * 
 */
public abstract class NBListViewAdapter extends BaseAdapter {
	Context context;
	String set[];

	public NBListViewAdapter(Context context, String set[]){
		this.context = context;
		this.set = set;
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
		public TextView content; // 内容
		public TextView title; // 标题
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.nbgd_item, null);
			holder.content = (TextView) convertView.findViewById(R.id.content);
			holder.title = (TextView) convertView.findViewById(R.id.title);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText((position+1)+"\t\t"+set[position]);
		holder.title.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v){
				showContent(position);
			}
		});
		return convertView;
	}
	public abstract void showContent(int position);
}
