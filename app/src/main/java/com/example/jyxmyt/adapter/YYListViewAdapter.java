package com.example.jyxmyt.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.jyxmyt.R;
import com.example.jyxmyt.view.SaituDialog;

public abstract class YYListViewAdapter extends BaseAdapter {

	Context context;
	String set[];
	private int CurrendPosition = -1;
	private int yunyue[];

	public YYListViewAdapter(Context context, String set[], int yunyue[]){
		this.context = context;
		this.set = set;
		this.yunyue = yunyue;
	}

	@Override
	public int getCount(){
		return set.length;
	}

	@Override
	public Object getItem(int position){
		return position;
	}

	@Override
	public long getItemId(int position){
		return position;
	}

	private class ViewHolder {
		public TextView yy_name; // 预约名称
		public LinearLayout yy_info; // 该项目信息
		public RadioButton yy_but; // 预约按钮
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent){
		ViewHolder holder;
		if(convertView == null){
			convertView = LayoutInflater.from(context).inflate(
					R.layout.yy_list_item, null);
			holder = new ViewHolder();
			holder.yy_name = (TextView) convertView.findViewById(R.id.yy_name);
			holder.yy_info = (LinearLayout) convertView.findViewById(R.id.yy_info);
			holder.yy_but = (RadioButton) convertView.findViewById(R.id.yy_but);
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		holder.yy_name.setText(set[position]);
		if(yunyue[position] == 0){
			holder.yy_but.setText("审核中");
		}
		else if(yunyue[position] == 1){
			holder.yy_but.setText("审核成功");
		}
		else if(yunyue[position] == 2){
			holder.yy_but.setText("审核失败");
		}
		else if(yunyue[position] == 3){
			holder.yy_but.setText("预约");
		}

		holder.yy_info.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v){
				// TODO Auto-generated method stub
				View view = LayoutInflater.from(context).inflate(
						R.layout.dialog, null);
				Button yy_name = (Button) view.findViewById(R.id.yy_name);
				ImageView yy_sx = (ImageView) view.findViewById(R.id.yy_sx);
				Button yy_z = (Button) view.findViewById(R.id.yy_z);
				ImageView yy_xx = (ImageView) view.findViewById(R.id.yy_xx);
				Button yy_jg = (Button) view.findViewById(R.id.yy_jg);
				ImageView yy_fh = (ImageView) view.findViewById(R.id.yy_fh);
				switch(yunyue[position]){
				case 0:
					yy_name.setBackgroundResource(R.drawable.yy_dia_shen);
					yy_sx.setBackgroundResource(R.drawable.yy_dia_jian_shen);
					yy_z.setBackgroundResource(R.drawable.yy_dia_zhong);
					yy_xx.setBackgroundResource(R.drawable.yy_dia_jian_shen);
					yy_jg.setBackgroundResource(R.drawable.yy_dia_shen);
					yy_fh.setBackgroundResource(R.drawable.yy_dia_fanhui);
					yy_z.setText(set[position]+"审核中");
					yy_z.setTextSize(30);
					break;
				case 1:
					yy_name.setBackgroundResource(R.drawable.yy_dia_qiann);
					yy_sx.setBackgroundResource(R.drawable.yy_dia_jian);
					yy_z.setBackgroundResource(R.drawable.yy_dia_zhong_qian);
					yy_xx.setBackgroundResource(R.drawable.yy_dia_jian_shen);
					yy_jg.setBackgroundResource(R.drawable.yy_dia_shen);
					yy_fh.setBackgroundResource(R.drawable.yy_dia_fanhui);
					yy_z.setText(set[position]+"审核成功");
					yy_z.setTextSize(30);
					break;
				case 2:
					yy_name.setBackgroundResource(R.drawable.yy_dia_qiann);
					yy_sx.setBackgroundResource(R.drawable.yy_dia_jian);
					yy_z.setBackgroundResource(R.drawable.yy_dia_zhong_qian);
					yy_xx.setBackgroundResource(R.drawable.yy_dia_jian);
					yy_jg.setBackgroundResource(R.drawable.yy_dia_qiann);
					yy_fh.setBackgroundResource(R.drawable.yy_dia_fanhui);
					yy_z.setText(set[position]+ "失败");
					yy_z.setTextSize(30);
					break;
				case 3:
					yy_name.setBackgroundResource(R.drawable.yy_dia_qiann);
					yy_sx.setBackgroundResource(R.drawable.yy_dia_jian);
					yy_z.setBackgroundResource(R.drawable.yy_dia_zhong_qian);
					yy_xx.setBackgroundResource(R.drawable.yy_dia_jian_shen);
					yy_jg.setBackgroundResource(R.drawable.yy_dia_shen);
					yy_fh.setBackgroundResource(R.drawable.yy_dia_qian);
					yy_z.setText(set[position] );
					yy_z.setTextSize(25);
					break;

				default:
					break;
				}

				SaituDialog.Builder builder = new SaituDialog.Builder(context);
				builder.setContentView(view).setTitle(set[position])
						.setCanceleable(false).setPositiveButton("确定", null);
				builder.create().show();
			}
		});

		holder.yy_but.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v){
				bookingButton(position, yunyue[position]);
			}
		});
		if(CurrendPosition == position){
			holder.yy_but.setChecked(true);
		}
		else{
			holder.yy_but.setChecked(false);
		}
		return convertView;
	}

	/**
	 * 预约按钮事件
	 * 
	 * @param position
	 */
	public abstract void bookingButton(int position, int vales);

	public void SetPostion(int P){
		CurrendPosition = P;
		notifyDataSetChanged();
	}
}
