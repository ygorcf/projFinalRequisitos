package com.example.ygor.iluminati.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.BaseAdapter;

import com.example.ygor.iluminati.activity.CronogramaActivity;
import com.example.ygor.iluminati.util.Network;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ygor on 07/11/2017.
 */

public class GetCronogramaTask extends AsyncTask<String, Void, Response<CronogramaResponse>> {

    private Context context;
    private ProgressDialog progressDialog;
    private Exception exception;
    private CompleteListener<CronogramaResponse> completeListener;

    public GetCronogramaTask(Context context, CompleteListener<CronogramaResponse> completeListener) {
        this.context = context;
        this.completeListener = completeListener;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "Aguarde", "Buscando dados.");
    }

    @Override
    protected Response<CronogramaResponse> doInBackground(String... params) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(params[0])
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            UcbServer ucbServer = retrofit.create(UcbServer.class);
            Call<CronogramaResponse> ret = ucbServer.getPalestras();
            return ret.execute();
        } catch (Exception e) {
            exception = e;
            return null;
        }
    }

    @Override
    protected void onPostExecute(Response<CronogramaResponse> result) {
        completeListener.onComplete((result != null) ? result.body() : null);
        progressDialog.dismiss();
    }

}
