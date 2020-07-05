package com.assignment.shadiandroidtest.app;

import androidx.annotation.NonNull;

import com.assignment.shadiandroidtest.BuildConfig;
import com.assignment.shadiandroidtest.constants.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static final int TIMEOUT = 45;

    public static <T> T buildMainApiService(final Class<T> service) {
        return buildGeneric(service, Constants.BASE_URL);
    }

    private static <T> T buildGeneric(Class<T> service, String hostUrl) {
        return new Retrofit.Builder()
                .baseUrl(hostUrl)
                .client(getOkHttpClientInstance())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service);
    }

    @NonNull
    private static OkHttpClient getOkHttpClientInstance() {
        return new OkHttpClient.Builder()
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(getOkHttpLoggingInterceptor())
                .build();
    }

    private static long getTimeOutFor(String key) {
        return TIMEOUT;
    }

    private static HttpLoggingInterceptor getOkHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return httpLoggingInterceptor;
    }
}
