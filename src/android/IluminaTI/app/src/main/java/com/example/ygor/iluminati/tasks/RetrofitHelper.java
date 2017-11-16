package com.example.ygor.iluminati.tasks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ygor on 14/11/2017.
 */

public class RetrofitHelper {

    public static String BASE_URL = null;

    private static Retrofit retrofitInstance = null;
    private static UcbServer ucbServerInstance = null;

    public static Retrofit getRetrofit() {
        if (retrofitInstance == null) {
            retrofitInstance = createRetrofit();
        }
        return retrofitInstance;
    }

    public static UcbServer getUcbServer() {
        if (ucbServerInstance == null) {
            ucbServerInstance = createUcbServer();
        }
        return ucbServerInstance;
    }

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static UcbServer createUcbServer() {
        return getRetrofit().create(UcbServer.class);
    }

}
