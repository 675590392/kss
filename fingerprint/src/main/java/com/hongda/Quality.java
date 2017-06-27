package com.hongda;

public class Quality {
    /*
	 * 指纹图像质量值获取
	 * 函数原型： int GetQualityScore(byte[] pFingerImgBuf,byte[]
	 * pnScore)。
	 * 参数： byte[] pFingerImgBuf 指纹图像数据指针，指纹图像为RAW格式。输入参数。
	 * byte[] pnScore 指纹图像质量值指针，指纹图像质量值取值范围为00H ～
	 * 64H，值01H表示最低质量，值64H表示最高质量，值00H表示未知。
	 * 返回值： 调用成功，返回1；否则返回错误代码
	 * 说明： 获取指纹图像的质量值。
	 * */
    public native static int GetQualityScore(byte[] pFingerImgBuf, byte[] pnScore);

    static {
        System.loadLibrary("quality");
    }
}
