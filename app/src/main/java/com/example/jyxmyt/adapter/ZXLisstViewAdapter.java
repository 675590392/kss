package com.example.jyxmyt.adapter;

import com.example.jyxmyt.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ZXLisstViewAdapter extends BaseAdapter{
	
	Context context;
	String set[];
	
	public ZXLisstViewAdapter(Context context,String set[]){
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
		private TextView zx_content; // 内容
		private TextView zx_time; // 时间
		private LinearLayout linearBack;
		private TextView 	gf_content;//规范内容
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(R.layout.zx_item, null);
			holder.zx_content = (TextView)convertView.findViewById(R.id.zx_content);
			holder.zx_time = (TextView)convertView.findViewById(R.id.zx_time);
			holder.gf_content = (TextView)convertView.findViewById(R.id.gf_content);
			holder.linearBack = (LinearLayout)convertView.findViewById(R.id.linearBack);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		String p[] = set[position].split("_");
//		String time = p[0].split("－")[0].split(":")[0];
//		Log.i("as", "========time=====>" + Integer.parseInt(time));
//		if(Integer.parseInt(time) < 12){
//			holder.zx_time.setBackgroundResource(jb2(Integer.parseInt(time)));
//			holder.linearBack.setBackgroundResource(jb2(Integer.parseInt(time)));
//		}else{
//			holder.zx_time.setBackgroundResource(jb(Integer.parseInt(time)-12));
//			holder.linearBack.setBackgroundResource(jb(Integer.parseInt(time)-12));
//		}
		holder.zx_time.setText(p[0]);
		holder.zx_content.setText(p[1]);
		holder.gf_content.setText("("+p[2]+")");
		return convertView;
	}

	private int jb(int i){
		int[] js = new int[]{R.color.jb1,R.color.jb2,R.color.jb3,R.color.jb4,R.color.jb5,R.color.jb6,R.color.jb7,R.color.jb8,R.color.jb9,R.color.jb10,R.color.jb11,R.color.jb12};
		return js[i];
		
	}
	private int jb2(int i){
		int[] js = new int[]{R.color.jb12,R.color.jb11,R.color.jb10,R.color.jb9,R.color.jb8,R.color.jb7,R.color.jb6,R.color.jb5,R.color.jb4,R.color.jb3,R.color.jb2,R.color.jb1};
		return js[i];
		
	}
	
}
