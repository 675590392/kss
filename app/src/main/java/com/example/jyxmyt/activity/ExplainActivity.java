package com.example.jyxmyt.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ZoomControls;

import com.example.jyxmyt.R;
import com.example.jyxmyt.view.ImageZoomState;
import com.example.jyxmyt.view.ImageZoomView;
import com.example.jyxmyt.view.SimpleImageZoomListener;

/**
 * @类名 FoodActivity.java
 * @包名 com.example.jyxmyt.activity
 * @作者
 * @时间 2014年5月6日 下午3:01:38
 * @Email ChunTian1314@vip.qq.com
 * @版本 V1.0
 * @功能 (使用说明)
 */
public class ExplainActivity extends BaseActivity implements OnClickListener {

	private int window_width, window_height;// 控件宽度
	private ImageView long_head; // 登陆者的头像
	private ImageView home_but; // 返回主页
	private ImageView return_but; // 返回上一步
	private ZoomControls zoomCtrl;// 系统自带的缩放控制组件

	private ImageZoomView zoomView;// 自定义的图片显示组件

	private ImageZoomState zoomState;// 图片缩放和移动状态类

	private SimpleImageZoomListener zoomListener;// 缩放事件监听器
	
	private Bitmap bitmap;// 要显示的图片位图

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		initView();
	}

	@Override
	public void initView() {
		setContentView(R.layout.explain_activity);
		long_head = (ImageView) findViewById(R.id.long_head);
		return_but = (ImageView) findViewById(R.id.return_but);
		return_but.setOnClickListener(this);
		home_but = (ImageView) findViewById(R.id.home_but);
		home_but.setOnClickListener(this);

		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sysm_back);

		zoomState = new ImageZoomState();

		zoomListener = new SimpleImageZoomListener();

		zoomListener.setZoomState(zoomState);

		zoomCtrl = (ZoomControls) findViewById(R.id.zoomCtrl);

		this.setImageController();

		zoomView = (ImageZoomView) findViewById(R.id.zoomView);

		zoomView.setImage(bitmap);

		zoomView.setImageZoomState(zoomState);

		zoomView.setOnTouchListener(zoomListener);

		zoomView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				setFullScreen();

			}

		});
	}

	private void setImageController() {

		zoomCtrl.setOnZoomInClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				float z = zoomState.getmZoom() + 0.25f;// 图像大小增加原来的0.25倍

				zoomState.setmZoom(z);

				zoomState.notifyObservers();

			}

		});

		zoomCtrl.setOnZoomOutClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				float z = zoomState.getmZoom() - 0.25f;// 图像大小减少原来的0.25倍

				zoomState.setmZoom(z);

				zoomState.notifyObservers();

			}

		});

	}

	/**
	 * 
	 * 隐藏处ImageZoomView外地其他组件，全屏显示
	 */

	private void setFullScreen() {

		if (zoomCtrl != null) {

			if (zoomCtrl.getVisibility() == View.VISIBLE) {

				// zoomCtrl.setVisibility(View.GONE);

				zoomCtrl.hide(); // 有过度效果

			} else if (zoomCtrl.getVisibility() == View.GONE) {

				// zoomCtrl.setVisibility(View.VISIBLE);

				zoomCtrl.show();// 有过渡效果

			}

		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		zoomView.coliseBitMap();
		bitmap.recycle();
		bitmap = null;
	}

	@Override
	public void onClick(View v) {
		if (v == home_but) {
			finish();
		} else if (v == return_but) {
			finish();
		}
	}

}
