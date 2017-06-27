package com.example.jyxmyt.common;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class ImageDispose {
	HashMap<String, SoftReference<Bitmap>> hashMap;

	private static ImageDispose imageDispose = new ImageDispose();
	// 任务队列
	private List<Task> taskQueue;
	private boolean isRunning = false;

	private ImageDispose(){
		// 初始化变量
		hashMap = new HashMap<String, SoftReference<Bitmap>>();
		taskQueue = new ArrayList<ImageDispose.Task>();
		isRunning = true;
		new Thread(runnable).start();
	}

	public static ImageDispose getInstance(){
		return imageDispose;
	}

	/**
	 * 
	 * @param imageView
	 *            显示图片控件
	 * @param strImage
	 *            图片的16进制字符串
	 * @param resId
	 *            默认图片
	 * @param key
	 *            软引用key值
	 */
	@SuppressWarnings("unused")
	public void showImageDispose(ImageView imageView, String strImage,
			int resId, String key){
		if(null == strImage){
			imageView.setBackgroundResource(resId);
		}
		imageView.setTag(key);
		Bitmap bitmap = loadImageAsyn(strImage,
				getImageCallback(imageView, resId), key, true);

		if(bitmap == null){
			imageView.setImageResource(resId);
		}
		else{
			imageView.setImageBitmap(bitmap);
		}
	}

	/**
	 * 图片加载处理
	 * 
	 * @param strImage
	 * @param imageCallback
	 * @param key
	 * @param boo
	 * @return
	 */
	private Bitmap loadImageAsyn(String strImage, ImageCallback imageCallback,
			String key, Boolean boo){
		if(hashMap.containsKey(key)){
			// 取出软引用
			SoftReference<Bitmap> rf = hashMap.get(key);
			// 通过软引用，获取图片
			Bitmap bitmap = rf.get();
			if(null == bitmap){
				hashMap.remove(key); // 释放map
			}
			else{
				return bitmap;
			}
		}
		else{
			// 如果缓存中不常在该图片，则创建图片下载任务
			Task task = new Task();
			task.strImage = strImage;
			task.key = key;
			task.callback = imageCallback;
			task.b = boo;
			if(!taskQueue.contains(task)){
				taskQueue.add(task);
				// 唤醒任务下载队列
				synchronized(runnable){
					runnable.notify();
				}
			}
		}
		return null;
	}

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
						Bitmap bitmap = CalendarUtil.getBitmapFromByte(B64
								.hexStringToBytes(task.strImage));

						if(bitmap == null){
							if(task.times < 4){
								task.times = task.times + 1;
								taskQueue.add(task);
							}
							continue;
						}
						if(task.b){
							task.bitmap = CalendarUtil.comp(bitmap);
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
					hashMap.put(task.key,
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

	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg){
			// 子线程中返回的下载完成的任务
			Task task = (Task) msg.obj;
			// 调用callback对象的loadImage方法，并将图片路径和图片回传给adapter
			task.callback.loadImage(task.key, task.bitmap);
		}
	};

	private ImageCallback getImageCallback(final ImageView imageView,
			final int resId){
		return new ImageCallback() {

			@Override
			public void loadImage(String key, Bitmap bitmap){
				if(key.equals(imageView.getTag().toString())){
					imageView.setImageBitmap(bitmap);
				}
				else{
					imageView.setImageResource(resId);
				}
			}
		};
	}

	// 回调接口
	public interface ImageCallback {
		void loadImage(String key, Bitmap bitmap);
	}

	class Task {
		String strImage;
		// 下载的图片
		Bitmap bitmap;
		// 回调对象
		ImageCallback callback;

		String key;

		int times = 1;
		boolean b = true;

		@Override
		public boolean equals(Object o){
			Task task = (Task) o;
			try{
				return task.key.equals(key);
			}
			catch(Exception e){
				return false;
			}
		}
	}
}
