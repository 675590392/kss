package com.example.jyxmyt.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.Berth;

public class PWGridViewAdapter extends BaseAdapter {
	private Context context;
	private List<Berth> legalaidList;
	private Berth berth;

	public PWGridViewAdapter(Context context, List<Berth> legalaidList){
		this.context = context;
		this.legalaidList = legalaidList;
	}

	@Override
	public int getCount(){
		return legalaidList.size();
	}

	@Override
	public Object getItem(int position){
		return legalaidList.get(position);
	}

	@Override
	public long getItemId(int position){
		return position;
	}

	private class ViewHolder {
		public TextView puwei_hao; // 铺位号
		private TextView ry_fh; //人员番号
		private TextView ry_xm; //人员姓名
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.pwap_itme, null);
			holder.puwei_hao = (TextView) convertView.findViewById(R.id.puwei_hao);
			holder.ry_fh = (TextView) convertView.findViewById(R.id.ry_fh);
			holder.ry_xm = (TextView) convertView.findViewById(R.id.ry_xm);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		Berth detainees = legalaidList.get(position);
		holder.puwei_hao.setText(position+1 +"");
		holder.ry_fh.setText(detainees.getSnbh());
		holder.ry_xm.setText(detainees.getName());
		
		return convertView;
	}
	
	public void notifyRefresh(){
		notifyDataSetChanged();
	}
}
