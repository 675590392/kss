package com.example.jyxmyt.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.jyxmyt.R;
import com.example.jyxmyt.activity.BerthActivity;
import com.example.jyxmyt.activity.DayRestActivity;
import com.example.jyxmyt.activity.FoodActivity;
import com.example.jyxmyt.activity.InternalRulesActivity;
import com.example.jyxmyt.activity.LegalAidActivity;
import com.example.jyxmyt.activity.MoreCaidActivity;
import com.example.jyxmyt.activity.ResearchersReportActivity;
import com.example.jyxmyt.activity.RightsObligationsActivity;
import com.example.jyxmyt.activity.ScheduleActivity;
import com.example.jyxmyt.activity.ShoppingActivity;
import com.example.jyxmyt.activity.VideoActivity;

public class ViewFragment extends Fragment implements OnClickListener {
	
	private static final String KEY_CONTENT = "ViewFragment:Act";
	private ViewHolder holder;

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
	}

	class ViewHolder {
		private ImageView YY; // 预约
		private ImageView FLYZ; // 法律援助
		private ImageView QLYW; // 权利义务
		private ImageView SPDB; // 视屏点播
		private ImageView MRYC; // 每日一餐
		private ImageView PWAP; // 铺位安排
		private ImageView DZGW; // 大帐购物
		private ImageView YRZX; // 一日作息
		private ImageView NBGD; // 内部规定
		private ImageView ZRAP; // 值日安排
		private ImageView HSBZ; // 伙食标准
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState){
		holder = new ViewHolder();
		View view = inflater.inflate(R.layout.main_fragment, null);
		holder.YY = (ImageView) view.findViewById(R.id.YY);
		holder.YY.setOnClickListener(this);
		holder.MRYC = (ImageView) view.findViewById(R.id.MRYC);
		holder.MRYC.setOnClickListener(this);
		holder.QLYW = (ImageView) view.findViewById(R.id.QLYW);
		holder.QLYW.setOnClickListener(this);
		holder.SPDB = (ImageView) view.findViewById(R.id.SPDB);
		holder.SPDB.setOnClickListener(this);
		holder.PWAP = (ImageView) view.findViewById(R.id.PWAP);
		holder.PWAP.setOnClickListener(this);
		holder.DZGW = (ImageView) view.findViewById(R.id.DZGW);
		holder.DZGW.setOnClickListener(this);
		holder.YRZX = (ImageView) view.findViewById(R.id.YRZX);
		holder.YRZX.setOnClickListener(this);
		holder.NBGD = (ImageView) view.findViewById(R.id.NBGD);
		holder.NBGD.setOnClickListener(this);
		holder.ZRAP = (ImageView) view.findViewById(R.id.ZRAP);
		holder.ZRAP.setOnClickListener(this);
		holder.FLYZ = (ImageView) view.findViewById(R.id.FLYZ);
		holder.FLYZ.setOnClickListener(this);
		holder.HSBZ = (ImageView) view.findViewById(R.id.HSBZ);
		holder.HSBZ.setOnClickListener(this);
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState){
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v){
		if(v == holder.YY){ // 预约
			Intent intent = new Intent(getActivity(),
					ResearchersReportActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "预约", 1).show();
		}
		else if(v == holder.FLYZ){ // 法律援助
			Intent intent = new Intent(getActivity(),
					LegalAidActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "法律援助", 1).show();
		}
		else if(v == holder.QLYW){ // 权利义务
			Intent intent = new Intent(getActivity(),
					RightsObligationsActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "权利义务 ", 1).show();
		}
		else if(v == holder.SPDB){ // 视屏点播
			Intent intent = new Intent(getActivity(), VideoActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "视屏点播", 1).show();
		}
		else if(v == holder.MRYC){ //每周食谱
			// 每周食谱
			Intent intent = new Intent(getActivity(),
					MoreCaidActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "每周食谱", 1).show();
		}
		else if(v == holder.PWAP){ // 铺位安排
			Intent intent = new Intent(getActivity(), BerthActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "铺位安排", 1).show();
		}
		else if(v == holder.DZGW){ // 大帐购物
			Intent intent = new Intent(getActivity(),
					ShoppingActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "大帐购物", 1).show();
		}
		else if(v == holder.YRZX){ // 一日作息
			Intent intent = new Intent(getActivity(), DayRestActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "一日作息", 1).show();
		}
		else if(v == holder.NBGD){
			Intent intent = new Intent(getActivity(),
					InternalRulesActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "内部规定", 1).show();
		}
		else if(v == holder.ZRAP){ //值日安排
			Intent intent = new Intent(getActivity(),
					ScheduleActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "值日安排", 1).show();
		}else if(v == holder.HSBZ){ //伙食标准
			Intent intent = new Intent(getActivity(),
					FoodActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "伙食食物标准", 1).show();
		}
	}

}