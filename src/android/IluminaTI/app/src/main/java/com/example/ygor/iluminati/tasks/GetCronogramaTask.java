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

public class GetCronogramaTask extends BaseTask<String, CronogramaResponse> {

    public GetCronogramaTask(Context context, CompleteListener<CronogramaResponse> completeListener) {
        super(context, completeListener, "Buscando dados do cronograma.");
    }

    @Override
    protected Response<CronogramaResponse> doInBackground(String... params) {
        try {
            UcbServer ucbServer = RetrofitHelper.getUcbServer();
            Call<CronogramaResponse> ret = ucbServer.getPalestras();
            return ret.execute();
        } catch (Exception e) {
            exception = e;
            return null;
        }
    }

}
