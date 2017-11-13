package com.example.ygor.iluminati.tasks;

import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by Ygor on 07/11/2017.
 */

public abstract class BaseTask<K, T> extends AsyncTask<K, Void, T> {

    protected static final String BASE_URL = "http://192.168.137.1:3000/api/v1/";

    public interface CompleteListener<T> {

        void onComplete(T result);
        T converter(String jsonString);

    }
}
