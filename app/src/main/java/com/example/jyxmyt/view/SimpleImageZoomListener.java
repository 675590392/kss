package com.example.jyxmyt.view;

import android.view.MotionEvent;
import android.view.View;

public class SimpleImageZoomListener implements View.OnTouchListener {

	private ImageZoomState mState;// ͼƬ���ź��ƶ�״̬

	private final float SENSIBILITY = 0.8f;// ͼƬ�ƶ�ʱ��������

	/**
	 * 
	 * �仯����ʼ������
	 */

	private float sX;

	private float sY;

	/**
	 * 
	 * �������ʼ�����꣬�����ж���ָ�Ƿ�������ƶ����Ӷ���UP�¼����ж��Ƿ�Ϊ����¼�
	 */

	private float sX01;

	private float sY01;

	/**
	 * 
	 * �����������������
	 */

	private float sDistance;

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		int action = event.getAction();

		int pointNum = event.getPointerCount();// ��ȡ��������

		if (pointNum == 1) {// ���㴥��,����ʵ��ͼ����ƶ�����Ӧ����¼�

			float mX = event.getX();// ��¼�����ƶ��Ĵ�����x����

			float mY = event.getY();// ��¼�����ƶ��Ĵ�����y����

			switch (action) {

			case MotionEvent.ACTION_DOWN:

				// ��¼��ʼ������

				sX01 = mX;

				sY01 = mY;

				sX = mX;

				sY = mY;

				return false;// ����return false ������Ӧ����¼�

			case MotionEvent.ACTION_MOVE:

				float dX = (mX - sX) / v.getWidth();

				float dY = (mY - sY) / v.getHeight();

				mState.setmPanX(mState.getmPanX() - dX * SENSIBILITY);

				mState.setmPanY(mState.getmPanY() - dY * SENSIBILITY);

				mState.notifyObservers();

				// ������ʼ������

				sX = mX;

				sY = mY;

				break;

			case MotionEvent.ACTION_UP:

				if (event.getX() == sX01 && event.getY() == sY01) {

					return false;// return false ִ�е���¼�

				}

				break;

			}

		}

		if (pointNum == 2) {// ��㴥��������ʵ��ͼ�������

			// ��¼�����ƶ���һ������������

			float mX0 = event.getX(event.getPointerId(0));

			float mY0 = event.getY(event.getPointerId(0));

			// ��¼�����ƶ�����һ������������

			float mX1 = event.getX(event.getPointerId(1));

			float mY1 = event.getY(event.getPointerId(1));

			float distance = this.getDistance(mX0, mY0, mX1, mY1);

			switch (action) {

			case MotionEvent.ACTION_POINTER_2_DOWN:

			case MotionEvent.ACTION_POINTER_1_DOWN:

				sDistance = distance;

				break;

			case MotionEvent.ACTION_POINTER_1_UP:

				// ע�⣺�ɿ���һ������������ָ�����ͱ�����Եڶ���������Ϊ��ʼ����ƶ�������Ҫ�Եڶ�������������ֵΪ��ʼ�����긳ֵ

				sX = mX1;

				sY = mY1;

				break;

			case MotionEvent.ACTION_POINTER_2_UP:

				// ע�⣺�ɿ��ڶ�������������ָ�����ͱ�����Եڶ���������Ϊ��ʼ����ƶ�������Ҫ�Ե�һ������������ֵΪ��ʼ�����긳ֵ

				sX = mX0;

				sY = mY0;

				break;

			case MotionEvent.ACTION_MOVE:

				// float dDistance = (distance - sDistance) / sDistance;

				// mState.setmZoom(mState.getmZoom()

				// * (float) Math.pow(5, dDistance));

				mState.setmZoom(mState.getmZoom() * distance / sDistance);

				mState.notifyObservers();

				sDistance = distance;

				break;

			}

		}

		return true;// ���뷵��true������false�Ļ��Ե���¼����߼�������Ӱ�졣�������ѯAndroid�¼����ػ��Ƶ��������

	}

	/**
	 * 
	 * //���أ� mX0, mY0���루�� mX1, mY1�������ľ���
	 * 
	 * 
	 * 
	 * @param mX0
	 * 
	 * @param mX1
	 * 
	 * @param mY0
	 * 
	 * @param mY1
	 * 
	 * @return
	 */

	private float getDistance(float mX0, float mY0, float mX1, float mY1) {

		double dX2 = Math.pow(mX0 - mX1, 2);// �����������ƽ��

		double dY2 = Math.pow(mY0 - mY1, 2);// ������������ƽ��

		return (float) Math.pow(dX2 + dY2, 0.5);

	}

	public void setZoomState(ImageZoomState state) {

		mState = state;

	}

}