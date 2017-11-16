package com.example.ygor.iluminati.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ygor on 07/11/2017.
 */

public abstract class BaseTask<K, T> extends AsyncTask<K, Void, Response<T>> {

    protected Context context;
    protected ProgressDialog progressDialog;
    protected Exception exception;
    protected BaseTask.CompleteListener<T> completeListener;
    protected String mesageLoading;

    public BaseTask(Context context, BaseTask.CompleteListener<T> completeListener) {
        this.context = context;
        this.completeListener = completeListener;
        this.mesageLoading = "Carregando.";
    }

    public BaseTask(Context context, BaseTask.CompleteListener<T> completeListener, String mesageLoading) {
        this(context, completeListener);
        this.mesageLoading = "Carregando.";
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Aguarde", mesageLoading);
    }

    @Override
    protected void onPostExecute(Response<T> result) {
        if (exception == null && result != null && result.isSuccessful())
            completeListener.onComplete(result.body());
        else
            completeListener.onError(exception, result);
        progressDialog.dismiss();
    }

    @Override
    protected Response<T> doInBackground(K... params) {
        try {
            return doTask(params);
        } catch (Exception e) {
            exception = e;
            return null;
        }
    }

    protected abstract Response<T> doTask(K... params) throws Exception;

    public interface CompleteListener<T> {

        void onComplete(T result);
        void onError(Exception e, Response<T> result);

    }
}
