package kr.benefitplus.drawerexam.comm;

import android.content.Context;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class NetworkClient {

    private static final String BASE_URL = "http://stg.benefitplus.kr/api/";
    public static Retrofit retrofit;

    public static Retrofit getRetrofitClient(Context context) {
        if(retrofit == null) {
            // TODO : 데이터 통신의 로그를 Logcat에서 확인할 수 있다.
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

            OkHttpClient httpClient = new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(loggingInterceptor)
                    .build();

            Gson gson= new GsonBuilder()
                        .setLenient()
                        .create();

            retrofit = new Retrofit.Builder()
                                    .baseUrl(BASE_URL)
                                    .client(httpClient)
                                    .addConverterFactory(GsonConverterFactory.create())         // 데이터를 주고 받을 때 모델에 만든 클래스로 사용
                                    .build();
        }
        return retrofit;
    }

}
