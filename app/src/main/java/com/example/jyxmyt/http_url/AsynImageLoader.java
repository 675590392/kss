package com.example.jyxmyt.http_url;

import java.io.File;
import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

/**
 * 图片加载
 * 
 * @author 123
 * 
 */
public class AsynImageLoader {
	private static final String TAG = "AsynImageLoader";
	// 缓存下载过的图片的Map
	private static Map<String, SoftReference<Bitmap>> caches;
	public static String fold="wang01"; // 缓存SD路径名
	// 任务队列
	private List<Task> taskQueue;
	private boolean isRunning = false;
	private static AsynImageLoader asynImageLoader = new AsynImageLoader();

	private AsynImageLoader(){
		// 初始化变量
		caches = new HashMap<String, SoftReference<Bitmap>>();
		taskQueue = new ArrayList<AsynImageLoader.Task>();
		// 启动图片下载线程
		isRunning = true;
		new Thread(runnable).start();
	}

	public static AsynImageLoader getInstance(){
		return asynImageLoader;
	}

	/**
	 * 控件显示图片
	 * 
	 * @param imageView 显示控件
	 * @param url 图片的URL地址
	 * @param resId 图片加载过程中显示的图片资源（默认图片）
	 * @param path 缓存路径
	 */
	public void showImageAsyn(ImageView imageView, String url, int resId,
			String path){
		fold = path;
		if(url == null){
			imageView.setImageResource(resId);
			return;
		}
		imageView.setTag(url);
		Bitmap bitmap = loadImageAsyn(url, getImageCallback(imageView, resId),
				true);

		if(bitmap == null){
			imageView.setImageResource(resId);
		}
		else{
			imageView.setImageBitmap(bitmap);
		}
	}

	/**
	 * 该方法暂时不需要
	 * 
	 * @param imageView
	 * @param url
	 * @param resId
	 * @param b
	 * @param context
	 */
	public void showImageAsyn(ImageView imageView, String url, int resId,
			boolean b, Context context){
		if(url == null){
			Bitmap defaultB = loadImageByID(resId, context);
			if(defaultB != null){
				if(b){
					imageView.setImageBitmap(defaultB);
				}
				else{
				
				}
			}
			else{
				imageView.setImageResource(resId);
			}
			return;
		}
		imageView.setTag(url);
		Bitmap bitm = loadImageAsyn(url, getImageCallback(imageView, resId), b);

		if(bitm == null){
			Bitmap defaultB = loadImageByID(resId, context);
			if(defaultB != null){
				if(b){
					imageView.setImageBitmap(defaultB);
				}
				else{
			
				}
			}
			else{
				imageView.setImageResource(resId);
			}

		}
		else{
			if(b){
				imageView.setImageBitmap(bitm);
			}
			else{
			}
		}
		System.gc();
	}

	public Bitmap loadImageByID(int id, Context context){
		String path = id + "";
		if(caches.containsKey(path)){
			// 取出软引用
			SoftReference<Bitmap> rf = caches.get(path);
			// 通过软引用，获取图片
			Bitmap bitmap = rf.get();
			// 如果该图片已经被释放，则将该path对应的键从Map中移除掉
			if(bitmap == null){
				caches.remove(path);
			}
			else{
				// 如果图片未被释放，直接返回该图片
				Log.i(TAG, "return image in cache" + path);
				return bitmap;
			}
		}
		else{
			Bitmap bitmap = BitmapFactory.decodeResource(
					context.getResources(), id);
			caches.put(id + "", new SoftReference<Bitmap>(bitmap));
			return bitmap;
		}
		return null;
	}

	public Bitmap loadImageAsyn(String path, ImageCallback callback, boolean b){
		// 成本地SD卡拿取图片
		File cacheFile = ImageCacheLocation.getCacheFile(path); // 查看本地
		// 从本地加载图片
		Bitmap bitmapBd = null;
		try{
			bitmapBd = BitmapFactory.decodeFile(cacheFile.getCanonicalPath());
		}
		catch(IOException e){
			e.printStackTrace();
		}
		// 判断缓存中是否已经存在该图片
		if(caches.containsKey(path) && bitmapBd != null){
			// 取出软引用
			SoftReference<Bitmap> rf = caches.get(path);
			// 通过软引用，获取图片
			Bitmap bitmap = rf.get();
			// 如果该图片已经被释放，则将该path对应的键从Map中移除掉
			if(bitmap == null){
				caches.remove(path); // 释放map
				if(bitmapBd == null){ // 判断SD卡是否有图片
				}
				else{
					return bitmapBd;
				}
			}
			else{
				// 如果图片未被释放，直接返回该图片
				Log.i(TAG, "return image in cache" + path);
				if(b){
					return bitmap;
				}
				else{
				}
			}
		}
		else{
			// 如果缓存中不常在该图片，则创建图片下载任务
			Task task = new Task();
			task.path = path;
			task.callback = callback;
			task.b = b;
			Log.i(TAG, "new Task ," + path);
			if(!taskQueue.contains(task)){
				taskQueue.add(task);
				// 唤醒任务下载队列
				synchronized(runnable){
					runnable.notify();
				}
			}
		}

		// 缓存中没有图片则返回null
		return null;
	}

	/**
	 * 
	 * @param imageView
	 * @param resId
	 *            图片加载完成前显示的图片资源ID
	 * @return
	 */
	private ImageCallback getImageCallback(final ImageView imageView,
			final int resId){
		return new ImageCallback() {

			@Override
			public void loadImage(String path, Bitmap bitmap){
				if(path.equals(imageView.getTag().toString())){
					imageView.setImageBitmap(bitmap);
				}
				else{
					imageView.setImageResource(resId);
				}
			}
		};
	}

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg){
			// 子线程中返回的下载完成的任务
			Task task = (Task) msg.obj;
			// 调用callback对象的loadImage方法，并将图片路径和图片回传给adapter
			task.callback.loadImage(task.path, task.bitmap);
		}

	};

	private Runnable runnable = new Runnable() {

		@Override
		public void run(){
			while(isRunning){
				// 当队列中还有未处理的任务时，执行下载任务
				while(taskQueue.size() > 0){
					// 获取第一个任务，并将之从任务队列中删除
					Task task = taskQueue.remove(0);
					// 将下载的图片添加到缓存
					try{
						Bitmap bitmap = UrlAccessImages
								.getbitmapAndwrite(task.path); // 下载图片
																// 同时写道本地缓存文件中
						if(bitmap == null){
							if(task.times < 4){
								task.times = task.times + 1;
								taskQueue.add(task);
							}
							continue;
						}
						if(task.b){
							task.bitmap = bitmap;
						}
						else{
						}

					}
					catch(Exception e){
						// TODO Auto-generated catch block
						if(task.times < 4){
							task.times = task.times + 1;
							taskQueue.add(task);
						}
						continue;
					}
					caches.put(task.path,
							new SoftReference<Bitmap>(task.bitmap));
					if(handler != null){
						// 创建消息对象，并将完成的任务添加到消息对象中
						Message msg = handler.obtainMessage();
						msg.obj = task;
						// 发送消息回主线程
						handler.sendMessage(msg);
					}
				}

				// 如果队列为空,则令线程等待
				synchronized(this){
					try{
						this.wait();
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		}
	};

	// 回调接口
	public interface ImageCallback {
		void loadImage(String path, Bitmap bitmap);
	}

	class Task {
		// 下载任务的下载路径
		String path;
		// 下载的图片
		Bitmap bitmap;
		// 回调对象
		ImageCallback callback;

		int times = 1;
		boolean b = true;

		@Override
		public boolean equals(Object o){
			Task task = (Task) o;
			try{
				return task.path.equals(path);
			}
			catch(Exception e){
				return false;
			}
		}
	}
}
