package com.example.jyxmyt.manager;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseManager {
	
	static final String TAG = "BaseManager";
	public static ExecutorService getDateService = Executors
			.newCachedThreadPool(); // 固定线程 线程池

}
