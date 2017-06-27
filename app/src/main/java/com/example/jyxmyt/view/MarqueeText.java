package com.example.jyxmyt.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;
/**
 * �Զ���Text�ؼ�
 * ʹ�ؼ��ڵ����ֹ���
 * @author 123
 *
 */
public class MarqueeText extends TextView implements Runnable {
	public int currentScrollX;// ��ǰ������λ��
	private boolean isStop = false;
	private int textWidth;
	private boolean isMeasure = false;

	public MarqueeText(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public MarqueeText(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MarqueeText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (!isMeasure||textWidth==0) {//���ֿ��ֻ���ȡһ�ξͿ�����
			getTextWidth();
			isMeasure = true;
		}
	}

	/**
	 * ��ȡ���ֿ��
	 */
	private void getTextWidth() {
		Paint paint = this.getPaint();
		String str = this.getText().toString();
		textWidth = (int) paint.measureText(str);
	}

	@Override
	public void run() {
		currentScrollX += 1;// �����ٶ�
		scrollTo(currentScrollX, 0);
		if (isStop) {
			return;
		}
		if (getScrollX() >textWidth) {
			scrollTo(-getWidth(), 0);
			currentScrollX = -getWidth();
			// return;
		}
		if(currentScrollX == 0){
			
		}
		postDelayed(this, 12);
	}

	// ��ʼ����
	public void startScroll() {
		isStop = false;
		this.removeCallbacks(this);
		post(this);
	}

	// ֹͣ����
	public void stopScroll() {
		isStop = true;
	}

	// ��ͷ��ʼ����
	public void startFor0() {
		currentScrollX = 0;
		startScroll();
	}
}
