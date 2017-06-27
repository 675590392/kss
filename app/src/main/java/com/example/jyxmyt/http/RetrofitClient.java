package com.example.jyxmyt.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by tanghao on 2017/1/27.
 */
public class RetrofitClient {
    private static CommonApi commonApi;
    private static final String baseUrl = "http://192.168.31.155:8088/";
    private static final String basePictureUrl = "http://192.31.155:8088";

    public static String getBasePictureUrl() {
        return basePictureUrl;
    }

    public static String getBaseUrl() {
        return baseUrl;
    }

    /**
     * @return
     */
    public static CommonApi getCommonApi() {
        if (commonApi != null)
            return commonApi;

        synchronized (RetrofitClient.class) {
            if (commonApi == null)
                commonApi = configRetrofit()
                        .create(CommonApi.class);
        }
        return commonApi;
    }

    private static Retrofit configRetrofit() {

        return new Retrofit.Builder()
                .client(getOkHttpClient())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
//                .connectTimeout(DEFAULT_TIME OUT, TimeUnit.SECONDS)
                .build();
    }
}
