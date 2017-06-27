package com.example.jyxmyt.view;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ImageZoomView extends View implements Observer {

    private Paint mPaint = new Paint(Paint.FILTER_BITMAP_FLAG);

    private Rect mRectSrc = new Rect();

    private Rect mRectDst = new Rect();

    private float mAspectQuotient;

 

    private Bitmap mBitmap;

    private ImageZoomState mZoomState;

 

    public ImageZoomView(Context context, AttributeSet attrs) {

       super(context, attrs);

    }

 

    @Override

    public void update(Observable observable, Object data) {

       this.invalidate();

    }

 

    @Override

    protected void onDraw(Canvas canvas) {

       if (mBitmap != null && mZoomState != null) {

           int viewWidth = this.getWidth();

           int viewHeight = this.getHeight();

           int bitmapWidth = mBitmap.getWidth();

           int bitmapHeight = mBitmap.getHeight();

 

           float panX = mZoomState.getmPanX();

           float panY = mZoomState.getmPanY();

           float zoomX = mZoomState.getZoomX(mAspectQuotient) * viewWidth

                  / bitmapWidth;// �൱��viewHeight/bitmapHeight*mZoom

           float zoomY = mZoomState.getZoomY(mAspectQuotient) * viewHeight

                  / bitmapHeight;// �൱��viewWidth/bitmapWidth*mZoom

 

           // Setup source and destination rectangles

           // ����ٶ�ͼƬ�ĸߺͿ�������ʾ����ĸߺͿ��������������������

           mRectSrc.left = (int) (panX * bitmapWidth - viewWidth / (zoomX * 2));

           mRectSrc.top = (int) (panY * bitmapHeight - viewHeight

                  / (zoomY * 2));

           mRectSrc.right = (int) (mRectSrc.left + viewWidth / zoomX);

           mRectSrc.bottom = (int) (mRectSrc.top + viewHeight / zoomY);

 

           mRectDst.left = this.getLeft();

           mRectDst.top = this.getTop();

           mRectDst.right = this.getRight();

           mRectDst.bottom = this.getBottom();

 

           // Adjust source rectangle so that it fits within the source image.

           // ���ͼƬ����С����ʾ������ߣ������С�����������ƶ�������������������������������������߽�

           if (mRectSrc.left < 0) {

              mRectDst.left += -mRectSrc.left * zoomX;

              mRectSrc.left = 0;

           }

           if (mRectSrc.right > bitmapWidth) {

              mRectDst.right -= (mRectSrc.right - bitmapWidth) * zoomX;

              mRectSrc.right = bitmapWidth;

           }

 

           if (mRectSrc.top < 0) {

              mRectDst.top += -mRectSrc.top * zoomY;

              mRectSrc.top = 0;

           }

           if (mRectSrc.bottom > bitmapHeight) {

              mRectDst.bottom -= (mRectSrc.bottom - bitmapHeight) * zoomY;

              mRectSrc.bottom = bitmapHeight;

           }

 

           // ��bitmap��һ����(����src�������Ĳ���)���Ƶ���ʾ����dstָ���ľ��δ�.�ؼ�����dst,��ȷ����bitmapҪ���Ĵ�С��λ��

           // ע�����������е�����λ��������ڸ��Ա���Ķ������������Ļ�ġ�

           canvas.drawBitmap(mBitmap, mRectSrc, mRectDst, mPaint);

       }

    }

 

    @Override

    protected void onLayout(boolean changed, int left, int top, int right,

           int bottom) {

       // TODO Auto-generated method stub

       super.onLayout(changed, left, top, right, bottom);

       this.calculateAspectQuotient();

    }

 

    public void setImageZoomState(ImageZoomState zoomState) {

       if (mZoomState != null) {

           mZoomState.deleteObserver(this);

       }

       mZoomState = zoomState;

       mZoomState.addObserver(this);

       invalidate();

    }

 

    public void setImage(Bitmap bitmap) {

       mBitmap = bitmap;

       this.calculateAspectQuotient();

       invalidate();

    }

    public void coliseBitMap() {
    	Log.i("as", "��ر���image");
        mBitmap.recycle();
        mBitmap = null;

     }

    private void calculateAspectQuotient() {

       if (mBitmap != null) {

           mAspectQuotient = ((float) mBitmap.getWidth() / mBitmap

                  .getHeight()) / ((float) this.getWidth() / this.getHeight());

       }

    }

}