package com.example.jyxmyt.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.Goods;
import com.example.jyxmyt.entity.Shopping;

public class DZGWGridViewAdapter extends BaseAdapter {

	Context context;
	private List<Shopping.RowsBean> rowsBeanList;

	public DZGWGridViewAdapter(Context context,List<Shopping.RowsBean> rowsBeanList){
		this.context = context;
		this.rowsBeanList=rowsBeanList;
	}

	@Override
	public int getCount(){
//		return goods.size() > 0 ? goods.size() : 0;
		return  10;
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
		public ImageView wp_image; // 物品图片
		public TextView wp_name; // 物品名称
		public TextView wp_price; // 物品价格
		public TextView wp_standard; // 规格
		public Button buy_but; // 购买按钮
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.gw_grid_item, null);
			holder.wp_image = (ImageView) convertView
					.findViewById(R.id.wp_image);
			holder.wp_name = (TextView) convertView.findViewById(R.id.wp_name);
			holder.wp_price = (TextView) convertView
					.findViewById(R.id.wp_price);
			holder.wp_standard = (TextView) convertView
					.findViewById(R.id.wp_standard);
			holder.buy_but = (Button) convertView.findViewById(R.id.buy_but);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}

//		Goods good = goods.get(position);
//		System.out.println("---------名称------------" + good.getGoodName());
//		holder.wp_name.setText("名称:\t" + good.getGoodName());
//		holder.wp_price.setText("单价:\t" + good.getGoodPrice());
//		holder.wp_standard.setText("规格:\t" + good.getGoodStandard());
//		if(position < 10){
//			good.setInteger(integer[position]);
//			holder.wp_image.setBackgroundResource(integer[position]);
//		}else {
//			good.setInteger(R.drawable.wp);
//			holder.wp_image.setBackgroundResource(R.drawable.wp);
//		}

//		holder.buy_but.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v){
//				buy(position);
//			}
//		});
		return convertView;
	}
}
