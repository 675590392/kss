package com.example.jyxmyt.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.Right;
import com.example.jyxmyt.entity.WeekEdu;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/3.
 */

public class WeekEeducatListAapter extends BaseAdapter{
    private LayoutInflater mInflater;
    private Context context;
    private List<WeekEdu> list;
    public WeekEeducatListAapter(Context context, List<WeekEdu> list) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ListItemView listItemView;

        if (convertView == null) {
            convertView = mInflater
                    .inflate(R.layout.week_edu_item, null);
            listItemView = new ListItemView();
            listItemView.tdate1 = (TextView) convertView
                    .findViewById(R.id.tdate1);
            listItemView.time1 = (TextView) convertView
                    .findViewById(R.id.time1);
            listItemView.time2 = (TextView) convertView
                    .findViewById(R.id.time2);
            listItemView.time3 = (TextView) convertView
                    .findViewById(R.id.time3);
            listItemView.time4 = (TextView) convertView
                    .findViewById(R.id.time4);
            listItemView.time5 = (TextView) convertView
                    .findViewById(R.id.time5);
            listItemView.time6 = (TextView) convertView
                    .findViewById(R.id.time6);
            listItemView.time7 = (TextView) convertView
                    .findViewById(R.id.time7);
            listItemView.time8 = (TextView) convertView
                    .findViewById(R.id.time8);
            listItemView.time9 = (TextView) convertView
                    .findViewById(R.id.time9);
            listItemView.timea = (TextView) convertView
                    .findViewById(R.id.timea);
            listItemView.timeb = (TextView) convertView
                    .findViewById(R.id.timeb);

            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }
        WeekEdu weekEdu = list.get(position);
        try {
            if (null != weekEdu) {
                if (!TextUtils.isEmpty(weekEdu.getWeekDay())) {
                    listItemView.tdate1.setText(weekEdu.getWeekDay());
                } else {
                    listItemView.tdate1.setText("");
                }
                if (!TextUtils.isEmpty(weekEdu.getTimesOne())) {
                    listItemView.time1.setText(weekEdu.getTimesOne());
                } else {
                    listItemView.time1.setText("");
                }
                if (!TextUtils.isEmpty(weekEdu.getTimesTwo())) {
                    listItemView.time2.setText(weekEdu.getTimesTwo());
                } else {
                    listItemView.time2.setText("");
                }
                if (!TextUtils.isEmpty(weekEdu.getTimesThree())) {
                    listItemView.time3.setText(weekEdu.getTimesThree());
                } else {
                    listItemView.time3.setText("");
                } if (!TextUtils.isEmpty(weekEdu.getTimesFour())) {
                    listItemView.time4.setText(weekEdu.getTimesFour());
                } else {
                    listItemView.time4.setText("");
                } if (!TextUtils.isEmpty(weekEdu.getTimesFive())) {
                    listItemView.time5.setText(weekEdu.getTimesFive());
                } else {
                    listItemView.time5.setText("");
                } if (!TextUtils.isEmpty(weekEdu.getTimesSix())) {
                    listItemView.time6.setText(weekEdu.getTimesSix());
                } else {
                    listItemView.time6.setText("");
                }
                if (!TextUtils.isEmpty(weekEdu.getTimesSeven())) {
                    listItemView.time7.setText(weekEdu.getTimesSeven());
                } else {
                    listItemView.time7.setText("");
                } if (!TextUtils.isEmpty(weekEdu.getTimesEight())) {
                    listItemView.time8.setText(weekEdu.getTimesEight());
                } else {
                    listItemView.time8.setText("");
                }
                if (!TextUtils.isEmpty(weekEdu.getTimesNine())) {
                    listItemView.time9.setText(weekEdu.getTimesNine());
                } else {
                    listItemView.time9.setText("");
                }
                if (!TextUtils.isEmpty(weekEdu.getTimesTen())) {
                    listItemView.timea.setText(weekEdu.getTimesTen());
                } else {
                    listItemView.timea.setText("");
                }
                if (!TextUtils.isEmpty(weekEdu.getTimesEleven())) {
                    listItemView.timeb.setText(weekEdu.getTimesEleven());
                } else {
                    listItemView.timeb.setText("");
                }
            } else {
                listItemView.tdate1.setText("");
                listItemView.time1.setText("");
                listItemView.time2.setText("");
                listItemView.time3.setText("");
                listItemView.time4.setText("");
                listItemView.time5.setText("");
                listItemView.time6.setText("");
                listItemView.time7.setText("");
                listItemView.time8.setText("");
                listItemView.time9.setText("");
                listItemView.timea.setText("");
                listItemView.timeb.setText("");


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    public final class ListItemView {
        public TextView tdate1 = null;
        public TextView time1 = null;
        public TextView time2 = null;
        public TextView time3 = null;
        public TextView time4 = null;
        public TextView time5 = null;
        public TextView time6 = null;
        public TextView time7 = null;
        public TextView time8 = null;
        public TextView time9 = null;
        public TextView timea = null;
        public TextView timeb = null;


    }
}
