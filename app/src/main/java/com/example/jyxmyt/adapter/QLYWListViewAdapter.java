package com.example.jyxmyt.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.activity.RightsObligationsActivity;
import com.example.jyxmyt.entity.Right;

public class QLYWListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Right> list;
    private ViewHolder holder;
    private int pos = -1;

    public QLYWListViewAdapter(Context context, List<Right> list) {
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
        //        private WebView content_title; //内容
        private TextView content_title; //内容
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.flyz_item, null);
            holder.wt_title = (TextView) convertView
                    .findViewById(R.id.wt_title);
            holder.content_title = (TextView) convertView
                    .findViewById(R.id.content_title);
            // 设置启动JS脚本
//            holder.content_title.getSettings().setJavaScriptEnabled(true);
//            // 设置可以支持缩放
//            holder.content_title.getSettings().setSupportZoom(true);
//            // 设置默认缩放方式尺寸
//            // content_xs.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
//            // 设置出现缩放工具，鼠标左键拖动出现
//            holder.content_title.getSettings().setBuiltInZoomControls(true);
//            // 设置默认编码
//            holder.content_title.getSettings().setDefaultTextEncodingName("UTF-8");
//            holder.content_title.setBackgroundColor(0); // 设置背景色
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (pos == position) {
            // 调用JS静态网页路径
//			showHtml(position);
            holder.content_title.setVisibility(View.VISIBLE);
        } else {
            holder.content_title.setVisibility(View.GONE);
        }

        if (RightsObligationsActivity.bool && RightsObligationsActivity.flag==false) {
            holder.wt_title.setText(list.get(position).getKssTitle());
            holder.content_title.setText(Html.fromHtml(list.get(position).getKssChinese()));
        } else if(RightsObligationsActivity.bool==false && RightsObligationsActivity.flag==false){
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

//				Intent intent = new Intent(context, NBContentActivity.class);
//				intent.putExtra("title", legalaidList.get(position).getName());
//				intent.putExtra("address", legalaidList.get(position)
//						.getContent());
//				intent.putExtra("id", 6);
//				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				context.startActivity(intent);

            }
        });

        return convertView;
    }


}