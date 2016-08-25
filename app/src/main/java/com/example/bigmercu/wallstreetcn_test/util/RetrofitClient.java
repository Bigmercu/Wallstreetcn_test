package com.example.bigmercu.wallstreetcn_test.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

/**
 * Created by bigmercu on 16/8/22.
 * Email:bigmercu@gmail.com
 */
public class RetrofitClient {

    private static String HOST_NAME = "https://bao.wallstreetcn.com/q/quote/v1/";

    private static final RetrofitClient instance = new RetrofitClient();

    public static RetrofitClient getInstance() {
        return instance;
    }

    private Retrofit retrofit;

    public RetrofitClient() {
        createRetrofit();
    }

    private void createRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HOST_NAME)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<?> clazz) {
        return (T) retrofit.create(clazz);
    }

}