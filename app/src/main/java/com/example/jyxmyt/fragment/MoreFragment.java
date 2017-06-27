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
import com.example.jyxmyt.activity.EvaluationOfHeartActivity;
import com.example.jyxmyt.activity.ExplainActivity;
import com.example.jyxmyt.activity.LearningGardenActivity;
import com.example.jyxmyt.activity.MedicalConsumptionActivity;
import com.example.jyxmyt.activity.PersonalConsumptionActivity;
import com.example.jyxmyt.activity.QuestionnaireActivity;
import com.example.jyxmyt.activity.TVActivity;

public class MoreFragment extends Fragment implements OnClickListener {

	private ViewHolder holder;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	class ViewHolder {

		private ImageView xxyd_image;// 学习园地
		private ImageView grxf_image;// 个人消费
		private ImageView jzcx_image;// 就诊消费
		private ImageView DS;// 电视
		private ImageView SYSM;// 使用说明
		private ImageView XLCP;// 心里评测
		private ImageView WJDC;// 问卷调查

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		holder = new ViewHolder();
		System.out.println("---------------onCreateView--------------");
		View view = inflater.inflate(R.layout.main_more_activity, null);
		holder.xxyd_image = (ImageView) view.findViewById(R.id.xxyd_image);
		holder.xxyd_image.setOnClickListener(this);
		holder.grxf_image = (ImageView) view.findViewById(R.id.grxf_image);
		holder.grxf_image.setOnClickListener(this);
		holder.jzcx_image = (ImageView) view.findViewById(R.id.jzcx_image);
		holder.jzcx_image.setOnClickListener(this);
		holder.DS = (ImageView) view.findViewById(R.id.DS);
		holder.DS.setOnClickListener(this);
		holder.SYSM = (ImageView) view.findViewById(R.id.SYSM);
		holder.SYSM.setOnClickListener(this);
		holder.XLCP = (ImageView) view.findViewById(R.id.XLCP);
		holder.XLCP.setOnClickListener(this);
		holder.WJDC = (ImageView) view.findViewById(R.id.WJDC);
		holder.WJDC.setOnClickListener(this);
		return view;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v){
		// TODO Auto-generated method stub
		if( v == holder.xxyd_image){//学习园地
			Intent intent = new Intent(getActivity(), LearningGardenActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "学习园地", 1).show();
		}else if( v == holder.grxf_image){// 个人消费
			Intent intent = new Intent(getActivity(), PersonalConsumptionActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "个人消费", 1).show();
		}else if( v == holder.jzcx_image){// 就诊消费
			Intent intent = new Intent(getActivity(), MedicalConsumptionActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "就诊消费", 1).show();
		}else if( v == holder.DS){// 电视
			Intent intent = new Intent(getActivity(),
					TVActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "电视", 1).show();
		}else if( v == holder.SYSM){// 使用说明
			Intent intent = new Intent(getActivity(),
					ExplainActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "使用说明", 1).show();
		}else if( v == holder.XLCP){   // 心里评测
			Intent intent = new Intent(getActivity(),
					EvaluationOfHeartActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "心里评测", 1).show();
		}else if( v == holder.WJDC){ // 问卷调查
			Intent intent = new Intent(getActivity(),
					QuestionnaireActivity.class);
			startActivity(intent);
			Toast.makeText(getActivity(), "问卷调查", 1).show();
		}
	}
}