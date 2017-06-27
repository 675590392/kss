package com.example.jyxmyt.adapter;

import java.util.LinkedList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.jyxmyt.R;
import com.example.jyxmyt.activity.ShoppingActivity;
import com.example.jyxmyt.entity.Goods;
import com.example.jyxmyt.entity.Shopping;

public abstract class DZGWListViewAdapter extends BaseAdapter {

    Context context;
    LinkedList<Goods> linkedList;
    Double price = 0.0;
    ShoppingActivity activity;
    private List<Shopping.RowsBean> rowsBeanList;

    public DZGWListViewAdapter(Context context, List<Shopping.RowsBean> rowsBeanList) {
        this.context = context;
        this.rowsBeanList = rowsBeanList;
        if (context instanceof ShoppingActivity) {
            activity = (ShoppingActivity) context;
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return linkedList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private class ViewHolder {
        public ImageView wp_iamge; // 物品图片
        public TextView wp_name; // 物品名称
        public TextView wp_price; // 物品单价
        public ImageView redudction_but; // 减少数量
        public ImageView add_but; // 加数量
        public EditText number_edit; // 显示的数量文本框
        public ImageView wp_cancel; // 删除
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.gw_list_item, null);
            holder.wp_iamge = (ImageView) convertView
                    .findViewById(R.id.wp_iamge);
            holder.wp_name = (TextView) convertView.findViewById(R.id.wp_name);
            holder.wp_price = (TextView) convertView
                    .findViewById(R.id.wp_price);
            holder.redudction_but = (ImageView) convertView
                    .findViewById(R.id.redudction_but);
            holder.add_but = (ImageView) convertView.findViewById(R.id.add_but);
            holder.number_edit = (EditText) convertView
                    .findViewById(R.id.number_edit);
            holder.wp_cancel = (ImageView) convertView
                    .findViewById(R.id.wp_cancel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Goods goods = linkedList.get(position);
        holder.wp_name.setText("名称:\t" + goods.getGoodName());
        holder.wp_price.setText("单价:\t" + goods.getGoodPrice());
        holder.number_edit.setText(goods.getBuyNumber());
        holder.wp_iamge.setBackgroundResource(goods.getInteger());

        holder.wp_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(position);
            }
        });
        holder.redudction_but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                reduce(position);
            }
        });
        holder.add_but.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                add(position);
            }
        });
        price = 0.0;
        for (int i = 0; i < linkedList.size(); i++) {
            price += Double.valueOf(linkedList.get(i).getGoodPrice())
                    .doubleValue()
                    * Integer.parseInt(linkedList.get(i).getBuyNumber());
        }
        System.out.println("--------->>>|=" + price);
//		alwaysRemember(price);
        return convertView;
    }

    /**
     * 删除
     *
     * @param position
     */
    public abstract void delete(int position);

    /**
     * 添加
     *
     * @param position
     */
    public abstract void add(int position);

    /**
     * 减少
     *
     * @param position
     */
    public abstract void reduce(int position);

    /**
     * 计算总价并传到handler在界面显示
     * @param price
     */
//	public void alwaysRemember(Double price){
//		Message message = new Message();
//		message.what = CommonSigns.GOOD_COUNT_PIRC;
//		message.obj = price;
//		activity.handler.sendMessage(message);
//	}
}
