package com.example.jyxmyt.adapter;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.activity.InterRulesActivity;
import com.example.jyxmyt.activity.RightsObligationsActivity;
import com.example.jyxmyt.entity.InterRules;
import com.example.jyxmyt.entity.Right;

import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */

public class InterRulseAdapter extends BaseAdapter {

    private Context context;
    private List<InterRules> list;
    private InterRulseAdapter.ViewHolder holder;
    private int pos = -1;

    public InterRulseAdapter(Context context, List<InterRules> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
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
        public TextView wt_title; // 问题标题
        private TextView content_title; //内容
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new InterRulseAdapter.ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.flyz_item, null);
            holder.wt_title = (TextView) convertView
                    .findViewById(R.id.wt_title);
            holder.content_title = (TextView) convertView
                    .findViewById(R.id.content_title);
            convertView.setTag(holder);
        } else {
            holder = (InterRulseAdapter.ViewHolder) convertView.getTag();
        }

        if (pos == position) {
            holder.content_title.setVisibility(View.VISIBLE);
        } else {
            holder.content_title.setVisibility(View.GONE);
        }
        if (InterRulesActivity.bool==true&&InterRulesActivity.flag==false) {
            holder.wt_title.setText(list.get(position).getKssTitle());
            holder.content_title.setText(Html.fromHtml(list.get(position).getKssChinese()));
        } else if(InterRulesActivity.bool==false&&InterRulesActivity.flag==false) {
            holder.wt_title.setText(list.get(position).getKssTitleEn());
            holder.content_title.setText(Html.fromHtml(list.get(position).getKssEnglish()));
        }else {
            holder.wt_title.setText(list.get(position).getKssTitleWy());
            holder.content_title.setText(Html.fromHtml(list.get(position).getKssUighur()));
        }

        holder.wt_title.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (pos == position) {
                    pos = -1;
                } else {
                    pos = position;
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

}
