package com.example.jyxmyt.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.common.B64;
import com.example.jyxmyt.common.CalendarUtil;
import com.example.jyxmyt.entity.Berth;
import com.example.jyxmyt.view.ImageDispose;

@SuppressLint("ResourceAsColor")
public class RYGridViewAdapter extends BaseAdapter {
	private Context context;
	private List<Berth> legalaidList;

	public RYGridViewAdapter(Context context, List<Berth> legalaidList) {
		this.context = context;
		this.legalaidList = legalaidList;
	}

	@Override
	public int getCount() {
		return legalaidList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private class ViewHolder {
		TextView where_number;
		ImageView image; // 头像
		LinearLayout personnel01;
	}

	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.nw_pwap, null);
			holder = new ViewHolder();
			holder.where_number = (TextView) convertView
					.findViewById(R.id.where_number); // 编号
			holder.image = (ImageView) convertView.findViewById(R.id.image);
			holder.personnel01 = (LinearLayout) convertView.findViewById(R.id.personnel01);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		final Berth detainees = legalaidList.get(position);
		if(detainees.getBool()){
			holder.personnel01.setBackgroundResource(R.drawable.ryxx_an);
		}else{
			holder.personnel01.setBackgroundResource(R.drawable.changes_people_background);
		}
		holder.personnel01.setVisibility(View.VISIBLE);
		holder.where_number.setText(detainees.getSnbh());
		ImageDispose.getInstance()
					.showImageDispose(holder.image, detainees.getImage(),
							R.drawable.badges, detainees.getSnbh());
		return convertView;
	}
}
