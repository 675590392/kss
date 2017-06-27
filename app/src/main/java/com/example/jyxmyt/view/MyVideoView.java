package com.example.jyxmyt.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

public class MyVideoView extends VideoView {

	public MyVideoView(Context context, AttributeSet attrs, int defStyle){
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public MyVideoView(Context context, AttributeSet attrs){
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public MyVideoView(Context context){
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
		// TODO Auto-generated method stub
		int width = getDefaultSize(getWidth(), widthMeasureSpec);
		int height = getDefaultSize(getHeight(), heightMeasureSpec);
		setMeasuredDimension(width, height);
	}
}
