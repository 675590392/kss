package com.example.jyxmyt.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * Created by tanghao on 2017/2/20.
 */

public class LogUtil {
    /**
     * 获取错误的信�?
     *
     * @param e
     * @return
     */
    public static String getErrorInfo(Throwable e) {
        Writer writer = new StringWriter();
        PrintWriter pw = new PrintWriter(writer);
        e.printStackTrace(pw);
        pw.close();
        String error = writer.toString();
        return error;
    }
}
