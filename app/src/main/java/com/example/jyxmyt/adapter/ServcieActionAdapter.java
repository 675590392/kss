package com.example.jyxmyt.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.PersonThing;
import com.example.jyxmyt.entity.ServcieAction;
import com.example.jyxmyt.entity.ServcieActionDetailed;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/7.
 */

public class ServcieActionAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<ServcieActionDetailed> list;

    private Map<Integer, ServcieAction> selectMap;//选择项

    public ServcieActionAdapter(Context context, List<ServcieActionDetailed> list) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        this.list = list;

        selectMap = new HashMap<>();

        for (ServcieActionDetailed servcieAction : list) {
            ServcieAction action = new ServcieAction();//赋值
            action.setKssProductName(servcieAction.getKssProductName());
            action.setKssProductUnit(servcieAction.getKssProductUnit());
            selectMap.put(servcieAction.getId(), action);
        }

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
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ServcieActionAdapter.ListItemView listItemView;
        if (convertView == null) {
            convertView = mInflater
                    .inflate(R.layout.servcie_action_list_item, null);
            listItemView = new ServcieActionAdapter.ListItemView();
            listItemView.text_bt = (TextView) convertView
                    .findViewById(R.id.text_bt);
            listItemView.sp_num = (Spinner) convertView
                    .findViewById(R.id.sp_num);
            listItemView.text_unit = (TextView) convertView
                    .findViewById(R.id.text_unit);
            convertView.setTag(listItemView);
        } else {
            listItemView = (ServcieActionAdapter.ListItemView) convertView.getTag();
        }
        ServcieActionDetailed roomNumPlaceDuty = list.get(position);
        //....
        try {

            if (null != roomNumPlaceDuty) {
                listItemView.text_bt.setText(roomNumPlaceDuty.getKssProductName());
//                listItemView.sp_num.setSelection(R.array.jjdnum);
                listItemView.text_unit.setText(roomNumPlaceDuty.getKssProductUnit());
                // 居住事由 //
                String[] ChreasonArray = context.getResources().getStringArray(R.array.jjdnum);
                ArrayAdapter<String> adapter_Chreason = new ArrayAdapter<String>(context,
                        R.layout.spinner_style_text, ChreasonArray);
                adapter_Chreason
                        .setDropDownViewResource(R.layout.spinner_style);
                listItemView.sp_num.setAdapter(adapter_Chreason);
                listItemView.sp_num
                        .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent,
                                                       View view, int p, long id) {
                                // 点击处理事件
                                try {
                                    int key = list.get(position).getId();
                                    String number = listItemView.sp_num.getSelectedItem().toString().trim();
                                    selectMap.get(key).setKssProductNum(Integer.parseInt(number));
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

            } else {
                listItemView.text_bt.setText("");
//                listItemView.sp_num.setSelection(R.array.jjdnum);
                listItemView.text_unit.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }

    @Override
    public void notifyDataSetChanged() {
        for (ServcieActionDetailed servcieAction : list) {

            ServcieAction action = new ServcieAction();//赋值
            action.setKssProductName(servcieAction.getKssProductName());
            action.setKssProductUnit(servcieAction.getKssProductUnit());
            selectMap.put(servcieAction.getId(), action);
        }

        super.notifyDataSetChanged();
    }

    public Map<Integer, ServcieAction> getSelectMap() {
        return selectMap;
    }

    public final class ListItemView {
        public TextView text_bt = null;
        public Spinner sp_num = null;
        public TextView text_unit = null;
    }
}
