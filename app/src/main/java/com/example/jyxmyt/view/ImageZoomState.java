package com.example.jyxmyt.view;

import java.util.Observable;

public class ImageZoomState extends Observable {

    private float mZoom = 1.0f;// ����ͼƬ���ŵı�������ʾ���ű�����ֵԽ��ͼ��Խ��

    private float mPanX = 0.5f;// ����ͼƬˮƽ�����ƶ��ı�����ֵԽ��ͼƬ�����������߽����ͼƬ��߽�ԽԶ��ͼ��Խ����ֵΪ0.5fʱ����

    private float mPanY = 0.5f;// ����ͼƬˮƽ�����ƶ��ı�����ֵԽ��ͼƬ����������ϱ߽����ͼƬ�ϱ߽�ԽԶ��ͼ��Խ���ϣ�ֵΪ0.5fʱ����

 

    public float getmZoom() {

       return mZoom;

    }

 

    public void setmZoom(float mZoom) {

       if (this.mZoom != mZoom) {

           this.mZoom = mZoom < 1.0f ? 1.0f : mZoom;// ��֤ͼƬ��СΪԭʼ״̬

           if (this.mZoom == 1.0f) {// ���س�ʼ��Сʱ��ʹ��λ��Ҳ�ָ�ԭʼλ��

              this.mPanX = 0.5f;

              this.mPanY = 0.5f;

           }

           this.setChanged();

       }

    }

 

    public float getmPanX() {

       return mPanX;

    }

 

    public void setmPanX(float mPanX) {

       if (mZoom == 1.0f) {// ʹͼΪԭʼ��Сʱ�����ƶ�

           return;

       }

       if (this.mPanX != mPanX) {

           this.mPanX = mPanX;

           this.setChanged();

       }

    }

 

    public float getmPanY() {

       return mPanY;

    }

 

    public void setmPanY(float mPanY) {

       if (mZoom == 1.0f) {// ʹͼΪԭʼ��Сʱ�����ƶ�

           return;

       }

       if (this.mPanY != mPanY) {

           this.mPanY = mPanY;

           this.setChanged();

       }

    }

 

    public float getZoomX(float aspectQuotient) {

       return Math.min(mZoom, mZoom * aspectQuotient);

    }

 

    public float getZoomY(float aspectQuotient) {

       return Math.min(mZoom, mZoom / aspectQuotient);

    }

}