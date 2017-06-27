package com.example.jyxmyt.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.common.B64;
import com.example.jyxmyt.common.CalendarUtil;
import com.example.jyxmyt.common.ImageDispose;
import com.example.jyxmyt.entity.Berth;

public class ZRRYGridViewAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Berth> legalaidList;
	private Berth berth;

	public ZRRYGridViewAdapter(Context context, ArrayList<Berth> legalaidList){
		super();
		this.context = context;
		this.legalaidList = legalaidList;
	}

	@Override
	public int getCount(){
		// TODO Auto-generated method stub
		return legalaidList.size();
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
		public ImageView ry_head; // 头像
		public TextView ry_name; // 名称
		public TextView ry_snbh; // 时间
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.zrap_ry_item, null);
			holder = new ViewHolder();

			holder.ry_head = (ImageView) convertView.findViewById(R.id.head);
			holder.ry_name = (TextView) convertView.findViewById(R.id.name);
			holder.ry_snbh = (TextView) convertView.findViewById(R.id.snbh);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}

		berth = legalaidList.get(position);

		holder.ry_name.setText("姓名: " + berth.getName());
		holder.ry_snbh.setText("番号: " + berth.getSnbh());
		ImageDispose.getInstance().showImageDispose(holder.ry_head,
				berth.getImage(), R.drawable.badges, berth.getSnbh());
		return convertView;
	}

}
