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
import com.example.jyxmyt.entity.PublicIpBunkInfo;

import java.util.List;

/**
 * Created by Administrator on 2017/3/8.
 */

public class PublicBunkAgrementAapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<PublicIpBunkInfo.RowBean> list;
    public PublicBunkAgrementAapter(Context context, List<PublicIpBunkInfo.RowBean> list){
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
        PublicBunkAgrementAapter.ListItemView listItemView;
        if (convertView == null) {
            convertView = mInflater
                    .inflate(R.layout.public_bunk_list_item, null);
            listItemView = new PublicBunkAgrementAapter.ListItemView();
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
            convertView.setTag(listItemView);
        } else {
            listItemView = (PublicBunkAgrementAapter.ListItemView) convertView.getTag();
        }
        PublicIpBunkInfo.RowBean roomNumPlaceDuty = list.get(position);
        try {
            if (null != roomNumPlaceDuty) {
                    if (!TextUtils.isEmpty(roomNumPlaceDuty.getKssPrisonerNum())) {
                        listItemView.text_peo_num.setText(roomNumPlaceDuty.getKssPrisonerNum());
                    } else {
                        listItemView.text_peo_num.setText("");
                    }
                    if (!TextUtils.isEmpty(roomNumPlaceDuty.getKssPrisonerName())) {
                        listItemView.text_peo_name.setText(roomNumPlaceDuty.getKssPrisonerName());
                    } else {
                        listItemView.text_peo_name.setText("");
                    }
                    if (roomNumPlaceDuty.getKssBedNum()>=0) {
                        listItemView.text_peo_pwh.setText(String.valueOf(roomNumPlaceDuty.getKssBedNum()));
                    } else {
                        listItemView.text_peo_pwh.setText("");
                    }
                    if (!TextUtils.isEmpty(roomNumPlaceDuty.getKssGlass())) {
                        listItemView.text_peo_yj.setText(roomNumPlaceDuty.getKssGlass());
                    } else {
                        listItemView.text_peo_yj.setText("");
                    }
                    if (!TextUtils.isEmpty(roomNumPlaceDuty.getKssBench())) {
                        listItemView.text_peo_dz.setText(roomNumPlaceDuty.getKssBench());
                    } else {
                        listItemView.text_peo_dz.setText("");
                    }
            } else {
                listItemView.text_peo_num.setText("");
                listItemView.text_peo_name.setText("");
                listItemView.text_peo_pwh.setText("");
                listItemView.text_peo_yj.setText("");
                listItemView.text_peo_dz.setText("");
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
    }
}
