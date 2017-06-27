package com.example.jyxmyt.http_url;

import java.io.File;
import java.io.IOException;

import android.os.Environment;
import android.util.Log;

/**
 * 图片缓存位置
 * @author 123
 *
 */
public class ImageCacheLocation {
	private static final String TAG = "FileUtil";

	public static File getCacheFile(String imageUri){
		File cacheFile = null;
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				File sdCardDir = Environment.getExternalStorageDirectory();
				String fileName = getFileName(imageUri);
				File dir = new File(sdCardDir.getCanonicalPath()+"/"
						+ AsynImageLoader.fold);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				cacheFile = new File(dir, fileName);
			}  
		} catch (IOException e) {
			e.printStackTrace();
			Log.e(TAG, "getCacheFileError:" + e.getMessage());
		}
		
		return cacheFile;
	}
	/**
	 * 截取图片名称
	 * @param path
	 * @return
	 */
	public static String getFileName(String path) {
		int index = path.lastIndexOf("/");
		return path.substring(index + 1);
	}
}

