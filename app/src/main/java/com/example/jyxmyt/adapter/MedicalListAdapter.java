package com.example.jyxmyt.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.Medical;
import com.example.jyxmyt.entity.MedicalInfo;
import com.example.jyxmyt.entity.PersonalCr;


public class MedicalListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MedicalInfo.RowsBean> medicals;
    private Map<Integer, MedicalInfo.RowsBean> checkItems;//记录选择项
    private boolean selectAll = false;


    public MedicalListAdapter(Context context, ArrayList<MedicalInfo.RowsBean> medicals) {
        this.context = context;
        this.medicals = medicals;
        checkItems = new HashMap<>();
    }

    @Override
    public int getCount() {
        return medicals.size();
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
        private TextView time; // 诊断日期
        private TextView name; // 疾病名称
        private TextView hospital; // 医院
        private TextView backTo; // 回所时间
        private CheckBox che_contrin;//是否确认
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.medical_item, null);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.hospital = (TextView) convertView.findViewById(R.id.hospital);
            holder.backTo = (TextView) convertView.findViewById(R.id.backTo);
            holder.che_contrin = (CheckBox) convertView.findViewById(R.id.che_contrin);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final MedicalInfo.RowsBean medical = medicals.get(position);

        try {
            holder.time.setText(medical.getKssDiagnosisDate());
            holder.name.setText(medical.getKssZd());
            holder.hospital.setText(medical.getKssYs());
            holder.backTo.setText(medical.getKssZs());
        }catch (Exception e){
            e.printStackTrace();
        }
        holder.che_contrin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkItems.put(medicals.get(position).getId(), medical);
                } else {
                    checkItems.remove(medicals.get(position).getId());
                }
            }
        });

        if (selectAll)
            holder.che_contrin.setChecked(true);
        return convertView;
    }


    /**
     * 得到选择项
     *
     * @return
     */
    public Map<Integer, MedicalInfo.RowsBean> getCheckItems() {
        return checkItems;
    }

    public void setSelectAll(boolean selectAll) {
        this.selectAll = selectAll;
    }
}
