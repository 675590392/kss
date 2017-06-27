package com.example.jyxmyt.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.activity.NBContentActivity;
import com.example.jyxmyt.entity.LegalAid;

public class FLYZListViewAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<LegalAid> legalaidList;
	private LegalAid legalaid;
	private PopupWindow popWindow;

	public FLYZListViewAdapter(Context context, ArrayList<LegalAid> legalaidList){
		this.context = context;
		this.legalaidList = legalaidList;
	}

	@Override
	public int getCount(){
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
		private TextView wt_title; // 问题标题
		private TextView content_title; //内容
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		final ViewHolder holder;
		if(convertView == null){
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.flyz_item, null);
			holder.wt_title = (TextView) convertView
					.findViewById(R.id.wt_title);
			holder.content_title = (TextView) convertView
					.findViewById(R.id.content_title);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.content_title.setVisibility(View.GONE);
		legalaid = legalaidList.get(position);
		holder.wt_title.setText(legalaid.getName());
		holder.wt_title.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v){

				Intent intent = new Intent(context, NBContentActivity.class);
				intent.putExtra("title", legalaidList.get(position).getName());
				intent.putExtra("address", legalaidList.get(position)
						.getContent());
				intent.putExtra("id", 6);
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				context.startActivity(intent);

			}
		});

		return convertView;
	}
}
