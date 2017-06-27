package com.example.jyxmyt.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.PersonThing;
import com.example.jyxmyt.entity.RoomNumPlaceDuty;
import com.example.jyxmyt.entity.WeekEdu;

import java.util.List;

/**
 * Created by Administrator on 2017/3/4.
 *
 */

public class BunkAgrementAapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<PersonThing> list;
    public BunkAgrementAapter(Context context, List<PersonThing> list){
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
                     .inflate(R.layout.zbb_item, null);
            listItemView = new ListItemView();
            listItemView.text_peo_num = (TextView) convertView
                    .findViewById(R.id.text_peo_num);
            listItemView.text_peo_name = (TextView) convertView
                    .findViewById(R.id.text_peo_name);
            listItemView.text_peo_pwh = (TextView) convertView
                    .findViewById(R.id.text_peo_pwh);
            listItemView.text_peo_yj = (TextView) convertView
                    .findViewById(R.id.text_peo_yj);
            listItemView.text_peo_dz = (TextView) convertView
                    .findViewById(R.id.text_peo_dz);
            listItemView.text_duty_content = (TextView) convertView
                    .findViewById(R.id.text_duty_content);

            convertView.setTag(listItemView);
        } else {
            listItemView = (ListItemView) convertView.getTag();
        }
        PersonThing roomNumPlaceDuty = list.get(position);
        try {
            if (null != roomNumPlaceDuty) {
                 if (!TextUtils.isEmpty(roomNumPlaceDuty.getFh())) {
                    listItemView.text_peo_num.setText(roomNumPlaceDuty.getFh());
                } else {
                    listItemView.text_peo_num.setText("");
                }
                if (!TextUtils.isEmpty(roomNumPlaceDuty.getXm())) {
                    listItemView.text_peo_name.setText(roomNumPlaceDuty.getXm());
                } else {
                    listItemView.text_peo_name.setText("");
                } if (!TextUtils.isEmpty(roomNumPlaceDuty.getPw())) {
                    listItemView.text_peo_pwh.setText(roomNumPlaceDuty.getPw());
                } else {
                    listItemView.text_peo_pwh.setText("");
                }
                if (!TextUtils.isEmpty(roomNumPlaceDuty.getYj())) {
                    listItemView.text_peo_yj.setText(roomNumPlaceDuty.getYj());
                } else {
                    listItemView.text_peo_yj.setText("");
                }
                if (!TextUtils.isEmpty(roomNumPlaceDuty.getDz())) {
                    listItemView.text_peo_dz.setText(roomNumPlaceDuty.getDz());
                } else {
                    listItemView.text_peo_dz.setText("");
                }
                if (!TextUtils.isEmpty(roomNumPlaceDuty.getZr())) {
                    listItemView.text_duty_content.setText(roomNumPlaceDuty.getZr());
                } else {
                    listItemView.text_duty_content.setText("");
                }
            } else {
                listItemView.text_peo_num.setText("");
                listItemView.text_peo_name.setText("");
                listItemView.text_peo_pwh.setText("");
                listItemView.text_peo_yj.setText("");
                listItemView.text_peo_dz.setText("");
                listItemView.text_duty_content.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    public final class ListItemView {
        public TextView text_peo_num = null;
        public TextView text_peo_name = null;
        public TextView text_peo_pwh = null;
        public TextView text_peo_yj = null;
        public TextView text_peo_dz = null;
        public TextView text_duty_content = null;
    }
}
