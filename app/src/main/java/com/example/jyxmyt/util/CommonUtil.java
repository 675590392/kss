package com.example.jyxmyt.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.example.jyxmyt.activity.ShoppingActivity;
import com.example.jyxmyt.app.JYYTApplication;
import com.example.jyxmyt.entity.Finger;
import com.example.jyxmyt.entity.SynchFinger;
import com.example.jyxmyt.entity.db.FingerBean;
import com.test.FingerPrintUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by tanghao on 2017/2/24.
 */

public class CommonUtil {


    /**
     * 获取监区号
     *
     * @param mContext
     * @return
     */
    public static String getKssArea(Context mContext) {
        return SharedPreferencesUtil.getSetting(JYYTApplication.fileName, mContext, JYYTApplication.KSS_AREA);
    }

    public static String setKssArea(Context mContext, String kssArea) {
        return SharedPreferencesUtil.setSetting(JYYTApplication.fileName, mContext, JYYTApplication.KSS_AREA, kssArea);
    }

    /**
     * 获取监室号
     *
     * @param mContext
     * @return
     */
    public static String getkssRoomNum(Context mContext) {
        return SharedPreferencesUtil.getSetting(JYYTApplication.fileName, mContext, JYYTApplication.KSS_ROOMNUM);
    }

    public static String setkssRoomNum(Context mContext, String kssRoomNum) {
        return SharedPreferencesUtil.setSetting(JYYTApplication.fileName, mContext, JYYTApplication.KSS_ROOMNUM, kssRoomNum);
    }

    /**
     * 得到指纹信息g
     *
     * @param mContext
     */
    public static List<Finger> getFingerInfo(Context mContext) {
        List<FingerBean> fingerBeanList = JYYTApplication.getDaoInstant().getFingerBeanDao().loadAll();
        return db2FingerList(fingerBeanList);

//         SharedPreferencesUtil.getObject(JYYTApplication.fileName, mContext, JYYTApplication.FINGER_INFO, ArrayList.class);
    }

//    public static void setFingerInfo(Context mContext, List<Finger> fingers) {
//        SharedPreferencesUtil.setObject(JYYTApplication.fileName, mContext, JYYTApplication.FINGER_INFO, fingers);
//    }

//
//    public static FingerBean finger2DB(Finger finger) {
//        FingerBean fingerBean = new FingerBean();
//        fingerBean.setKssArea(finger.getKssArea());
//        fingerBean.setKssFeatureNumFirst(finger.getKssFeatureNumFirst());
//        fingerBean.setKssFeatureNumSecond(finger.getKssFeatureNumSecond());
//        fingerBean.setKssPrisonerName(finger.getKssPrisonerName());
//        fingerBean.setKssPrisonerNum(finger.getKssPrisonerNum());
//        fingerBean.setKssRoomNum(finger.getKssRoomNum());
//        fingerBean.setKssState(finger.getKssState());
//
//        return fingerBean;
//    }
//
//    public static List<FingerBean> fingers2DBList(List<Finger> fingers) {
//        List<FingerBean> fingerBeanList = new ArrayList<>();
//
//        for (Finger finger : fingers) {
//            fingerBeanList.add(finger2DB(finger));
//        }
//
//        return fingerBeanList;
//    }

    public static Finger db2Finger(FingerBean fingerBean) {
        Finger finger = new Finger();
        finger.setKssArea(fingerBean.getKssArea());
        finger.setKssFeatureNumFirst(fingerBean.getKssFeatureNumFirst());
        finger.setKssFeatureNumSecond(fingerBean.getKssFeatureNumSecond());
        finger.setKssPrisonerName(fingerBean.getKssPrisonerName());
        finger.setKssPrisonerNum(fingerBean.getKssPrisonerNum());
        finger.setKssRoomNum(fingerBean.getKssRoomNum());
        finger.setKssState(fingerBean.getKssState());

        return finger;
    }

    public static List<Finger> db2FingerList(List<FingerBean> fingerBeanList) {
        List<Finger> fingers = new ArrayList<>();

        for (FingerBean fingerBean : fingerBeanList) {
            fingers.add(db2Finger(fingerBean));
        }

        return fingers;
    }


    public static FingerBean synchFinger2DB(SynchFinger synchFinger) {
        FingerBean fingerBean = new FingerBean();
        fingerBean.setType(synchFinger.getType());
        fingerBean.setFingerSynoId(synchFinger.getFingerSynoId());

        fingerBean.setKssArea(synchFinger.getKssArea());
        fingerBean.setKssFeatureNumFirst(synchFinger.getKssFeatureNumFirst());
        fingerBean.setKssFeatureNumSecond(synchFinger.getKssFeatureNumSecond());
        fingerBean.setKssPrisonerName(synchFinger.getKssPrisonerName());
        fingerBean.setKssPrisonerNum(synchFinger.getKssPrisonerNum());
        fingerBean.setKssRoomNum(synchFinger.getKssRoomNum());
        fingerBean.setKssState(synchFinger.getKssState());

        return fingerBean;
    }

    public static List<FingerBean> synchFinger2DBList(List<SynchFinger> synchFingers) {
        List<FingerBean> fingerBeanList = new ArrayList<>();

        for (SynchFinger synchFinger : synchFingers) {
            fingerBeanList.add(synchFinger2DB(synchFinger));
        }

        return fingerBeanList;
    }

    /**
     * 验证邮箱
     *
     * @param email
     * @return
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getHostIP() {

        String hostIp = null;
        try {
            Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
            while (nis.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) nis.nextElement();
                Enumeration<InetAddress> ias = ni.getInetAddresses();
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement();
                    if (ia instanceof Inet6Address) {
                        continue;// skip ipv6
                    }
                    String ip = ia.getHostAddress();
                    if (!"127.0.0.1".equals(ip)) {
                        hostIp = ia.getHostAddress();
                        break;
                    }
                }
            }
        } catch (SocketException e) {
            Log.i("yao", "SocketException");
            e.printStackTrace();
        }
        return hostIp;

    }

    public static String arrayToString(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            buff.append(bytes[i] + ",");

        }
//            System.out.println(buff.toString());
        return buff.toString();
    }

    public static byte[] stringToArray(String data) {
        byte[] bytes = null;

        try {
            if (data != null && !data.equals("")) {
                String[] ss = data.split(",");
                bytes = new byte[ss.length];
                for (int i = 0; i < ss.length; i++) {
                    bytes[i] = Byte.parseByte(ss[i]);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        }

        return bytes;
    }

    /**
     * 对比指纹
     *
     * @param data
     * @return
     */
    public static Finger machFinger(byte[] data, Context mContext, FingerPrintUtil fingerPrintUtil) {


        if (data != null) {
            List<Finger> fingers = CommonUtil.getFingerInfo(mContext);
            for (Finger finger : fingers) {
                String first = finger.getKssFeatureNumFirst();
                String second = finger.getKssFeatureNumSecond();

                byte[] dataFirst = CommonUtil.stringToArray(first);
                if (dataFirst != null) {
                    boolean flag = fingerPrintUtil.match(data, dataFirst);
                    if (flag) {
                        return finger;
                    }
                }

                byte[] dataSecond = CommonUtil.stringToArray(second);
                if (dataSecond != null) {
                    boolean flag2 = fingerPrintUtil.match(data, dataSecond);
                    if (flag2) {
                        return finger;
                    }
                }
            }
        } else {
//            Toast.makeText(mContext, "提取指纹失败", Toast.LENGTH_LONG).show();
        }

        return null;
    }

    //打开APK程序代码

    public static void openFile(File file,Context mContext) {
        // TODO Auto-generated method stub
        Log.e("OpenFile", file.getName());
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file),
                "application/vnd.android.package-archive");
        mContext.startActivity(intent);
    }

//    public void install(String apkPath,String apkName,Context mContext) {
//        File file = new File(apkPath);
//        if( !file.exists())
//            return ;
//        Uri mPackageURI = Uri.fromFile(file);
//        int installFlags = 0;
//        PackageManager pm = mContext.getPackageManager();
//        PackageInfo info = pm.getPackageArchiveInfo(apkPath, PackageManager.GET_ACTIVITIES);
//        if(info != null){
//            try {
//                PackageInfo pi = pm.getPackageInfo(info.packageName,PackageManager.GET_UNINSTALLED_PACKAGES);
//                if( pi != null){
//                    installFlags |= PackageManager.INSTALL_REPLACE_EXISTING;
//                }
//            } catch (PackageManager.NameNotFoundException e) {
//            }
//            //把包名和apkName对应起来，后面需要使用
////            map.put(info.packageName, apkName);
//            IPackageInstallObserver observer = new PackageInstallObserver();
//            pm.installPackage(mPackageURI, observer, installFlags, info.packageName);
//        }
//    }

    /**
     * encodeBase64File:(将文件转成base64 字符串). <br/>
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     * @author guhaizhou@126.com
     * @since JDK 1.6
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] buffer = new byte[(int) file.length()];
        inputFile.read(buffer);
        inputFile.close();
        return Base64.encodeToString(buffer, Base64.DEFAULT);
    }

    /**
     * decoderBase64File:(将base64字符解码保存文件). <br/>
     *
     * @param base64Code 编码后的字串
     * @param savePath   文件保存路径
     * @throws Exception
     * @author guhaizhou@126.com
     * @since JDK 1.6
     */
    public static void decoderBase64File(String base64Code, String savePath) throws Exception {
//byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
        byte[] buffer = Base64.decode(base64Code, Base64.DEFAULT);
        FileOutputStream out = new FileOutputStream(savePath);
        out.write(buffer);
        out.close();
    }

    /**
     * 销毁activity
     */
    public static List<Activity> activities = new ArrayList<Activity>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public static void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }


}



}
