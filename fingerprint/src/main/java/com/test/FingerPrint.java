package com.test;

public class FingerPrint {
    public native int openDevice(String str);

    public native void closeDevice(int fd);

    public native int startCapturing(int fd);

    public native int stopCapturing(int fd);

    public native int getImg(byte[] bug, int fd);

    static {
        System.loadLibrary("newfp");
    }
}
