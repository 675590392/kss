package com.example.jyxmyt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.PsychologicalTest;

import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 * 心理测评试卷适配器
 */

public class list_xlcp_Adapter  extends BaseAdapter{

    private Context context;
    private List<PsychologicalTest> list;

    public list_xlcp_Adapter(Context context, List<PsychologicalTest> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return  list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        list_xlcp_Adapter.ViewHolder holder;
        if (convertView == null) {
            holder = new list_xlcp_Adapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.noml_adaprer, null);
            holder.paperName = (TextView) convertView.findViewById(R.id.paperName);
            convertView.setTag(holder);
        } else {
            holder = (list_xlcp_Adapter.ViewHolder) convertView.getTag();
        }
        try {
            holder.paperName.setText(list.get(position).getPaperName());
        }catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }


    private class ViewHolder {
        private TextView paperName; // 诊断日期
    }
}
