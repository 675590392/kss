package com.example.jyxmyt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.PersonThing;
import com.example.jyxmyt.entity.PublicQueryUserDuty;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */

public class PublicScheduleAapter extends BaseAdapter {
    private PublicQueryUserDuty schedule;
    private List<PublicQueryUserDuty> list;
    private Context context;

    public PublicScheduleAapter(Context context, List<PublicQueryUserDuty> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount(){
        return list.size();
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
        private TextView zrap_time; //时间
        private TextView zrap_name;  //姓名
        private TextView zrap_fh; //番号
        private TextView zrap_fl; //分类
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        PublicScheduleAapter.ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.public_schedu_list_item, null);
            holder = new PublicScheduleAapter.ViewHolder();
            holder.zrap_fh = (TextView)convertView.findViewById(R.id.zrap_fh);
            holder.zrap_time = (TextView)convertView.findViewById(R.id.zrap_time);
            holder.zrap_name = (TextView)convertView.findViewById(R.id.zrap_name);
            holder.zrap_fl = (TextView)convertView.findViewById(R.id.zrap_fl);
            convertView.setTag(holder);
        }
        else{
            holder = (PublicScheduleAapter.ViewHolder) convertView.getTag();
        }
        schedule = list.get(position);
        holder.zrap_time.setText(schedule.getKssDutyStartTimer()+"-"+schedule.getKssDutyEndTime());
        holder.zrap_name.setText(schedule.getKssPrisonerName());
        holder.zrap_fh.setText(schedule.getKssPrisonerNum());
        holder.zrap_fl.setText(schedule.getKssDutyContent());

        return convertView;
    }
}
