package com.hongda;

/*
 * 返回值定义
 * 1	正确
 * -1	参数错误
 * -2	内存分配失败，没有分配到足够的内存
 * -3	功能未实现
 * -9	其他错误
 */

public class Fingerprint_id2 {
    /*
     * 版本信息获取
     * 函数原型： int FP_GetVersion(byte[] code)。
     * 参数： byte[] code 版本信息格式为XXYY，XX为开发者代码，YY为版本号。例如
     * “1201”，则code的填写方式为：code[0]=‘1’，code[1]=‘2’， code[3]=‘0’, code[4]=
     * ‘1’。输出参数。
     * 返回值： 调用成功，返回1；否则返回错误代码，错误代码值应符合B.4的要求。
     * 说明： 获取版本信息。
     * */
    public native static int FP_GetVersion(byte[] code);

    /*
     * 初始化操作
     * 函数原型： int FP_Begin()。
     * 参数： 无。
     * 返回值： 调用成功，返回1；否则返回错误代码
     * 说明： 初始化操作。
     * */
    public native static int FP_Begin();

    public native static int FP_End();

    /*
     * 指纹图像特征提取
     * 函数原型： int FP_FeatureExtract(byte cScannerType, byte
     * cFingerCode,byte[] pFingerImgBuf,byte[] pFeatureData)。
     * 参数： byte cScannerType 指纹采集器代码。输入参数。
     * byte cFingerCode 指位代码。输入参数。
     * byte[] pFingerImgBuf 指纹图像数据指针，指纹图像为RAW格式。输入参数。
     * byte[] pFeatureData 指纹特征数据指针，存储生成的指纹特征数据，由调
     * 用者分配内存空间，指纹特征数据文件结构应符合本标准附录A要求。输出参数。
     * 返回值： 调用成功，返回1；否则返回错误代码
     * 说明： 对指纹图像数据进行特征提取，生成指纹特征数据。
     * */
    public native static int FP_FeatureExtract(byte cScannerType,
                                               byte cFingerCode,
                                               byte[] pFingerImgBuf,
                                               byte[] pFeatureData);

    /*
     * 指纹特征数据比对
     * 函数原型： int FP_FeatureMatch(byte[] pFeatureData1,byte[]
     * pFeatureData2,float[] pfSimilarity)。
     * 参数： byte[] pFeatureData1 指纹特征数据指针1。输入参数。
     * byte[] pFeatureData2 指纹特征数据指针2。输入参数。
     * float[] pfSimilarity 相似度，取值范围为0.00 ～ 1.00，值0.00表示不匹配，值1.00
     * 表示完全匹配。输出参数。
     * 返回值： 调用成功，返回1；否则返回错误代码
     * 说明： 对2个指纹特征数据进行比对，得到相似度值。
     * */
    public native static int FP_FeatureMatch(byte[] pFeatureData1,
                                             byte[] pFeatureData2,
                                             float[] pfSimilarity);

    static {
        System.loadLibrary("fingerprint_id2");
    }
}
