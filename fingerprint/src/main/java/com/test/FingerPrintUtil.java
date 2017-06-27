package com.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.Toast;

import com.hongda.Fingerprint_id2;
import com.hongda.Quality;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by tanghao on 2017/3/3.
 */

public class FingerPrintUtil {

    private Context mContext;

    FingerPrint fp = new FingerPrint();
    byte[] feature1 = null;//提取指纹
    byte[] feature2 = null;//
    private byte[] bmpData = null;
    int fd, ret;
    byte[] buf = null;
    FileOutputStream fos = null;
    byte[] temp = null;
    int ex_flag = 0;
    byte[] pnScore = null;
    int a = 0;


    public FingerPrintUtil(Context mContext) {
        this.mContext = mContext;
    }

    //init
    public void init() {
        fd = fp.openDevice("/dev/video4");
        if (fd > 0) {
            feature1 = new byte[512];
            feature2 = new byte[512];
            buf = new byte[256 * 360];
            bmpData = new byte[184320];
            temp = new byte[256 * 360 * 2];
            pnScore = new byte[2];
        } else {
            Toast.makeText(mContext, "初始化失败！", Toast.LENGTH_SHORT).show();
        }
        fp.closeDevice(fd);
    }


    /**
     * 返回指纹
     *
     * @return
     */
    public byte[] extract() {
        ex_flag = 1;
        fd = fp.openDevice("/dev/video4");
        ret = fp.startCapturing(fd);
        ret = fp.getImg(buf, fd);
        Quality.GetQualityScore(buf, pnScore);
        if (pnScore[0] > 40) {
            System.arraycopy(buf, 0, temp, 0, 256 * 360);
            ret = Fingerprint_id2.FP_FeatureExtract((byte) 1, (byte) 2, buf, feature1);
            fp.stopCapturing(fd);
            fp.closeDevice(fd);
            if (ret == 1) {
//                Toast.makeText(mContext, "指纹提取成功", Toast.LENGTH_SHORT).show();
                return feature1;
            } else {
                Toast.makeText(mContext, "指纹提取失败", Toast.LENGTH_SHORT).show();
                return null;
            }
//            showImg();
        } else {
            Toast.makeText(mContext, "指纹提取失败", Toast.LENGTH_SHORT).show();
            fp.stopCapturing(fd);
            fp.closeDevice(fd);

            return null;
        }

    }

    //比对指纹
    public boolean match(byte[] data, byte[] data2) {
        // buf=new byte[256*360];
        //feature2 = new byte[512];
        //int res=Fingerprint_id2.FP_Begin();
        //ret=fp.startCapturing(fd);
        boolean flag = false;

//        fd = fp.openDevice("/dev/video4");
//        ret = fp.startCapturing(fd);
//        ret = fp.getImg(buf, fd);
//        ret = Fingerprint_id2.FP_FeatureExtract((byte) 1, (byte) 2, buf, feature2);
//        if (ret == 1) {
        float[] fout = new float[1];
        ret = Fingerprint_id2.FP_FeatureMatch(data, data2, fout);
        if (fout[0] > 0.6) {
//                Toast.makeText(mContext, "指纹匹配成功", Toast.LENGTH_SHORT).show();
            flag = true;
        } else {
//                Toast.makeText(mContext, "指纹比对失败", Toast.LENGTH_SHORT).show();
        }
//        } else {
//            Toast.makeText(mContext, "指纹比对失败", Toast.LENGTH_SHORT).show();
//        }
//        showImg();
//        ret = fp.stopCapturing(fd);
//        fp.closeDevice(fd);

        return flag;
    }

    public void onDestroy() {
        ret = fp.stopCapturing(fd);
        fp.closeDevice(fd);
//	  Fingerprint_id2.FP_End();
    }

    public void onStop() {
        ret = fp.stopCapturing(fd);
        fp.closeDevice(fd);
//	   Fingerprint_id2.FP_End();
    }


    public boolean onKeyDown(int keyCode, KeyEvent event, boolean flag) {
        if (keyCode == KeyEvent.KEYCODE_F12) {
            Toast.makeText(mContext, "F12事件已被触发", Toast.LENGTH_SHORT).show();
            a++;
            String tag = "main";
            Log.i(tag, a + "  successful");
            System.out.println(a);

            return true;
        }
        return flag;
    }


    //保存
    private void save() {
        if (ex_flag == 1) {
            String str1 = Environment.getExternalStorageDirectory().getPath();
            try {
                File dir = new File(str1 + "/fingerprint/");
                if (!dir.exists())
                    dir.mkdir();
            } catch (Exception e) {
                System.out.print("创建失败");
            }
            str1 = str1 + "/fingerprint/";
            Calendar rightNow = Calendar.getInstance();
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd-HHmmss");//格式大小写有区别
            String str2 = fmt.format(rightNow.getTime()) + ".raw";
            File file = new File(str1, str2);
            try {
                fos = new FileOutputStream(file);
                fos.write(temp);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Toast.makeText(mContext, "提取的指纹图片成功保存至/mnt/sdcard/fingerprint/下", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mContext, "指纹尚未提取，无法保存", Toast.LENGTH_SHORT).show();
        }
        ex_flag = 0;
    }

    /**
     * 显示
     */
//    public void showImg() {
//        Arrays.fill(bmpData, (byte) 80);
//        System.arraycopy(buf, 0, bmpData, 0, 256 * 360);
//        int rgb[] = convertYUV420_NV21toRGB8888(bmpData, 256, 360);
//        Bitmap bm = Bitmap.createBitmap(rgb, 256, 360, Bitmap.Config.ARGB_8888);
//        imageView.setImageBitmap(toGrayscale(bm));
//    }
    public Bitmap toGrayscale(Bitmap bmp) {
        int height = bmp.getHeight();
        int width = bmp.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmp, 0, 0, paint);
        return bmpGrayscale;
    }

    public static int[] convertYUV420_NV21toRGB8888(byte[] data, int width, int height) {
        int size = width * height;
        int offset = size;
        int[] pixels = new int[size];
        int u, v, y1, y2, y3, y4;

        // i percorre os Y and the final pixels
        // k percorre os pixles U e V
        for (int i = 0, k = 0; i < size; i += 2, k += 2) {
            y1 = data[i] & 0xff;
            y2 = data[i + 1] & 0xff;
            y3 = data[width + i] & 0xff;
            y4 = data[width + i + 1] & 0xff;

            u = data[offset + k] & 0xff;
            v = data[offset + k + 1] & 0xff;
            u = u - 128;
            v = v - 128;

            pixels[i] = convertYUVtoRGB(y1, u, v);
            pixels[i + 1] = convertYUVtoRGB(y2, u, v);
            pixels[width + i] = convertYUVtoRGB(y3, u, v);
            pixels[width + i + 1] = convertYUVtoRGB(y4, u, v);

            if (i != 0 && (i + 2) % width == 0)
                i += width;
        }

        return pixels;
    }


    private static int convertYUVtoRGB(int y, int u, int v) {
        int r, g, b;

        r = y + (int) 1.402f * v;
        g = y - (int) (0.344f * u + 0.714f * v);
        b = y + (int) 1.772f * u;
        r = r > 255 ? 255 : r < 0 ? 0 : r;
        g = g > 255 ? 255 : g < 0 ? 0 : g;
        b = b > 255 ? 255 : b < 0 ? 0 : b;
        return 0xff000000 | (b << 16) | (g << 8) | r;
    }

    public void getRoot() {
        try {
            /* Missing read/write permission, trying to chmod the file */
            Process su;
            su = Runtime.getRuntime().exec("/system/bin/su");
            String cmd = "chmod 777 /dev/video4" + "\n"
                    + "exit\n";
            su.getOutputStream().write(cmd.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SecurityException();
        }
    }
}


