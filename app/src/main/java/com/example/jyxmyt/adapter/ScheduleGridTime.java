package com.example.jyxmyt.adapter;

import java.util.Calendar;
import java.util.TimeZone;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.common.CalendarUtil;

public abstract class ScheduleGridTime extends BaseAdapter {
	Context context;
	String setTime[] = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
	int upPosition;
	public ScheduleGridTime(Context context,int position){
		this.context = context;
		this.upPosition = position;
	}

	@Override
	public int getCount(){
		return setTime.length;
	}

	@Override
	public Object getItem(int position){
		return null;
	}

	@Override
	public long getItemId(int position){
		return 0;
	}
	
	private class ViewHolder {
		private TextView zrap_time_button;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.zrap_time_list, null);
			holder = new ViewHolder();
			holder.zrap_time_button = (TextView)convertView.findViewById(R.id.zrap_time_button);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.zrap_time_button.setText(setTime[position]);
		Calendar c = Calendar.getInstance();
		c.setTimeZone(TimeZone.getTimeZone("GMT+8:00")); 
		String mWay = String.valueOf(c.get(Calendar.DAY_OF_WEEK)); 
		if(Integer.parseInt(mWay) != 1 || Integer.parseInt(mWay) != 7){
			if((Integer.parseInt(mWay) - 2 ) > position){
				holder.zrap_time_button.setBackgroundResource(R.drawable.zrap_rq);
//				if(upPosition == position){
//					holder.zrap_time_button.setBackgroundResource(R.drawable.zrap_button);
//				}else {
////					holder.zrap_time_button.setBackgroundResource(R.drawable.zrap_time_lv);
//				}
			}else{
				if(upPosition == position){
					holder.zrap_time_button.setBackgroundResource(R.drawable.zrap_button);
				}else {
					holder.zrap_time_button.setBackgroundResource(R.drawable.zrap_time_lv);
				}
			}
		}
		
		if(position == 5 || position == 6){
			holder.zrap_time_button.setBackgroundResource(R.drawable.zrap_time_red);
//			if(upPosition == position){
//					holder.zrap_time_button.setBackgroundResource(R.drawable.zrap_button);
//			}else {
////				holder.zrap_time_button.setBackgroundResource(R.drawable.zrap_time_lv);
//			}
		}
		
//		holder.zrap_time_button.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v){
//				time(CalendarUtil.getMondayOFWeek(position),position);
//			}
//		});
		return convertView;
	}
	
	
//	public abstract void time(String time,int position);
	
	public void upBackground(int position){
		this.upPosition = position;
		notifyDataSetChanged();
	}
}
