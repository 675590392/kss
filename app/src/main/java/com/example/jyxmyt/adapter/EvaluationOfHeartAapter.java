package com.example.jyxmyt.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.entity.MeasurementofHeart;
import com.example.jyxmyt.entity.MedicalInfo;
import com.example.jyxmyt.entity.Psychometrics;
import com.example.jyxmyt.entity.PublicQueryUserDuty;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2017/3/10.
 */

public class EvaluationOfHeartAapter extends BaseAdapter {
    private List<MeasurementofHeart> list;
    private Context context;
    private HashMap<Integer, String> hashMap;
    //
    private HashMap<Integer, Boolean> checkBoxMap1 = new HashMap<>();
    private HashMap<Integer, Boolean> checkBoxMap2 = new HashMap<>();
    private HashMap<Integer, Boolean> checkBoxMap3 = new HashMap<>();
    private HashMap<Integer, Boolean> checkBoxMap4 = new HashMap<>();
    private HashMap<Integer, Boolean> checkBoxMap5 = new HashMap<>();

    private HashMap<Integer, Integer> radioGroupMap = new HashMap<>();


    public HashMap<Integer, String> getHashMap() {
        return hashMap;
    }


    public EvaluationOfHeartAapter(Context context, List<MeasurementofHeart> list) {
        this.context = context;
        this.list = list;
        hashMap = new HashMap<>();
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
        private TextView text_isselect_all;//单多选
        private TextView text_title; //标题
        private RadioGroup radioGroup;
        private RadioButton radioButton1;
        private RadioButton radioButton2;
        private RadioButton radioButton3;
        private RadioButton radioButton4;
        private RadioButton radioButton5;

        private LinearLayout lin_a;  //第一个
        private CheckBox chebox_a; //A
        private TextView text_a; //A
        private LinearLayout lin_b;  //第二个
        private CheckBox chebox_b; //B
        private TextView text_b; //B
        private LinearLayout lin_c;  //第三个
        private CheckBox chebox_c; //C
        private TextView text_c; //C
        private LinearLayout lin_d;  //第四个
        private CheckBox chebox_d; //D
        private TextView text_d; //D
        private LinearLayout lin_e;  //第五个
        private CheckBox chebox_e; //E
        private TextView text_e; //E

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final EvaluationOfHeartAapter.ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.evaluation_heart_list_item, null);
            holder = new EvaluationOfHeartAapter.ViewHolder();
            holder.text_isselect_all = (TextView) convertView.findViewById(R.id.text_isselect_all); //单多选
            holder.text_title = (TextView) convertView.findViewById(R.id.text_title); //标题
            holder.lin_a = (LinearLayout) convertView.findViewById(R.id.lin_a);  //第一个
            holder.chebox_a = (CheckBox) convertView.findViewById(R.id.chebox_a); //A
            holder.chebox_b = (CheckBox) convertView.findViewById(R.id.chebox_b); //B
            holder.chebox_c = (CheckBox) convertView.findViewById(R.id.chebox_c); //C
            holder.chebox_d = (CheckBox) convertView.findViewById(R.id.chebox_d); //D
            holder.chebox_e = (CheckBox) convertView.findViewById(R.id.chebox_e); //E

            holder.radioGroup = (RadioGroup) convertView.findViewById(R.id.radioGroup);
            holder.radioButton1 = (RadioButton) convertView.findViewById(R.id.radioButton1);
            holder.radioButton2 = (RadioButton) convertView.findViewById(R.id.radioButton2);
            holder.radioButton3 = (RadioButton) convertView.findViewById(R.id.radioButton3);
            holder.radioButton4 = (RadioButton) convertView.findViewById(R.id.radioButton4);
            holder.radioButton5 = (RadioButton) convertView.findViewById(R.id.radioButton5);
            convertView.setTag(holder);
        } else {
            holder = (EvaluationOfHeartAapter.ViewHolder) convertView.getTag();
        }

        final MeasurementofHeart schedule = list.get(position);

        holder.radioGroup.setTag(position);
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

                int index = (int) radioGroup.getTag();
                radioGroupMap.put(index, checkedId);

                MeasurementofHeart schedule = list.get(index);

                switch (checkedId) {
                    case R.id.radioButton1:
                        hashMap.put(schedule.getId(), "A");
                        break;
                    case R.id.radioButton2:
                        hashMap.put(schedule.getId(), "B");
                        break;
                    case R.id.radioButton3:
                        hashMap.put(schedule.getId(), "C");
                        break;
                    case R.id.radioButton4:
                        hashMap.put(schedule.getId(), "D");
                        break;
                    case R.id.radioButton5:
                        hashMap.put(schedule.getId(), "E");
                        break;
                }

            }
        });
        holder.chebox_a.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    String value = hashMap.get(schedule.getId());
                    if (value != null) {
                        hashMap.put(schedule.getId(), value + ",A");
                    } else {
                        hashMap.put(schedule.getId(), "A");
                    }
                } else {
                    hashMap.remove(schedule.getId());
                }

                checkBoxMap1.put(position, isChecked);

            }
        });
        holder.chebox_b.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    String value = hashMap.get(schedule.getId());
                    if (value != null) {
                        hashMap.put(schedule.getId(), value + ",B");
                    } else {
                        hashMap.put(schedule.getId(), "B");
                    }

                } else {
                    hashMap.remove(schedule.getId());
                }

                checkBoxMap2.put(position, isChecked);
            }
        });
        holder.chebox_c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    String value = hashMap.get(schedule.getId());
                    if (value != null) {
                        hashMap.put(schedule.getId(), value + ",C");
                    } else {
                        hashMap.put(schedule.getId(), "C");
                    }
                } else {
                    hashMap.remove(schedule.getId());
                }

                checkBoxMap3.put(position, isChecked);
            }
        });
        holder.chebox_d.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    String value = hashMap.get(schedule.getId());
                    if (value != null) {
                        hashMap.put(schedule.getId(), value + ",D");
                    } else {
                        hashMap.put(schedule.getId(), "D");
                    }
                } else {
                    hashMap.remove(schedule.getId());
                }

                checkBoxMap4.put(position, isChecked);
            }
        });
        holder.chebox_e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked) {
                    String value = hashMap.get(schedule.getId());
                    if (value != null) {
                        hashMap.put(schedule.getId(), value + ",E");
                    } else {
                        hashMap.put(schedule.getId(), "E");
                    }
                } else {
                    hashMap.remove(schedule.getId());
                }

                checkBoxMap5.put(position, isChecked);
            }
        });


        holder.text_title.setText((position + 1) + "、" + schedule.getKssContext());//标题
        if (schedule.getIsMultiselect() == 1) {
            holder.text_isselect_all.setText("(多选)");
            holder.radioGroup.setVisibility(View.GONE);
            holder.lin_a.setVisibility(View.VISIBLE);
            if (TextUtils.isEmpty(schedule.getKssAnswerA())) {
                holder.chebox_a.setVisibility(View.GONE);
            } else {
                holder.chebox_a.setText("A." + schedule.getKssAnswerA()); //A
            }
            if (TextUtils.isEmpty(schedule.getKssAnswerB())) {
                holder.chebox_b.setVisibility(View.GONE);
            } else {
                holder.chebox_b.setText("B." + schedule.getKssAnswerB()); //B
            }
            if (TextUtils.isEmpty(schedule.getKssAnswerC())) {
                holder.chebox_c.setVisibility(View.GONE);
            } else {
                holder.chebox_c.setText("C." + schedule.getKssAnswerC()); //C
            }
            if (TextUtils.isEmpty(schedule.getKssAnswerD())) {
                holder.chebox_d.setVisibility(View.GONE);
            } else {
                holder.chebox_d.setText("D." + schedule.getKssAnswerD()); //D
            }
            if (TextUtils.isEmpty(schedule.getKssAnswerE())) {
                holder.chebox_e.setVisibility(View.GONE);
            } else {
                holder.chebox_e.setText("E." + schedule.getKssAnswerE()); //E
            }
        } else if (schedule.getIsMultiselect() == 0) {
            holder.text_isselect_all.setText("(单选)");
            holder.lin_a.setVisibility(View.GONE);
            holder.radioGroup.setVisibility(View.VISIBLE);
            if (TextUtils.isEmpty(schedule.getKssAnswerA())) {
                holder.radioButton1.setVisibility(View.GONE);
            } else {
                holder.radioButton1.setText("A." + schedule.getKssAnswerA()); //A
            }
            if (TextUtils.isEmpty(schedule.getKssAnswerB())) {
                holder.radioButton2.setVisibility(View.GONE);
            } else {
                holder.radioButton2.setText("B." + schedule.getKssAnswerB()); //B
            }
            if (TextUtils.isEmpty(schedule.getKssAnswerC())) {
                holder.radioButton3.setVisibility(View.GONE);
            } else {
                holder.radioButton3.setText("C." + schedule.getKssAnswerC()); //C
            }
            if (TextUtils.isEmpty(schedule.getKssAnswerD())) {
                holder.radioButton4.setVisibility(View.GONE);
            } else {
                holder.radioButton4.setText("D." + schedule.getKssAnswerD()); //D
            }
            if (TextUtils.isEmpty(schedule.getKssAnswerE())) {
                holder.radioButton5.setVisibility(View.GONE);
            } else {
                holder.radioButton5.setText("E." + schedule.getKssAnswerE()); //E
            }
        }

        getCheckStatus(position, holder.chebox_a, checkBoxMap1);
        getCheckStatus(position, holder.chebox_b, checkBoxMap2);
        getCheckStatus(position, holder.chebox_c, checkBoxMap3);
        getCheckStatus(position, holder.chebox_d, checkBoxMap4);
        getCheckStatus(position, holder.chebox_e, checkBoxMap5);

        if(radioGroupMap.get(position)!=null){
            holder.radioGroup.check(radioGroupMap.get(position));
        }else {
            holder.radioGroup.clearCheck();
        }
        return convertView;
    }


    private void getCheckStatus(int position, CompoundButton compoundButton, Map<Integer, Boolean> hashMap) {
        if (hashMap.get(position) != null) {
            compoundButton.setChecked(hashMap.get(position));
        } else {
            compoundButton.setChecked(false);
        }
    }


    @Deprecated
    private void change(ViewHolder holder, Psychometrics schedule) {
        String o = hashMap.get(schedule.getId());
        if (o != null) {
            if (o.length() == 1) {//单选
                //abc
                if (o.equals("A")) {
                    holder.radioButton1.setChecked(true);
                } else if (o.equals("B")) {
                    holder.radioButton2.setChecked(true);
                } else if (o.equals("C")) {
                    holder.radioButton3.setChecked(true);
                } else if (o.equals("D")) {
                    holder.radioButton4.setChecked(true);
                } else if (o.equals("E")) {
                    holder.radioButton5.setChecked(true);
                }
            } else {//多選
                String[] s = o.split(",");
                for (int i = 0; i < s.length; i++) {
                    //ABC  s[i]
                    if (s[i].equals("A")) {
                        holder.chebox_a.setChecked(true); //A
                    } else if (s[i].equals("B")) {
                        holder.chebox_b.setChecked(true); //B
                    } else if (s[i].equals("C")) {
                        holder.chebox_c.setChecked(true); //B
                    } else if (s[i].equals("D")) {
                        holder.chebox_d.setChecked(true); //B
                    } else if (s[i].equals("E")) {
                        holder.chebox_e.setChecked(true); //B
                    }
                }
            }
        } else {//所有不勾選
            holder.chebox_a.setChecked(false); //A
            holder.chebox_b.setChecked(false); //B
            holder.chebox_c.setChecked(false); //C
            holder.chebox_d.setChecked(false); //D
            holder.chebox_e.setChecked(false);

            holder.radioButton1.setChecked(false);
            holder.radioButton2.setChecked(false);
            holder.radioButton3.setChecked(false);
            holder.radioButton4.setChecked(false);
            holder.radioButton5.setChecked(false);

        }
    }

    public int getUnselectPosition() {
        for (int i = 0; i < list.size(); i++) {
            int key = list.get(i).getId();
            Object o = hashMap.get(key);
            if (o == null) {
                return i;
            }
        }
        return -1;
    }
    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
