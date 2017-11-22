package com.example.ygor.iluminati.network.task;

import android.content.Context;

import com.example.ygor.iluminati.network.responses.CronogramaResponse;
import com.example.ygor.iluminati.network.util.RetrofitHelper;
import com.example.ygor.iluminati.network.util.UcbServer;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ygor on 07/11/2017.
 */

public class GetCronogramaTask extends BaseTask<String, CronogramaResponse> {

    public GetCronogramaTask(Context context, CompleteListener<CronogramaResponse> completeListener) {
        super(context, completeListener, "Buscando dados do cronograma.");
    }

    @Override
    protected Response<CronogramaResponse> doTask(String... params) throws Exception {
        UcbServer ucbServer = RetrofitHelper.getUcbServer();
        Call<CronogramaResponse> ret = ucbServer.getPalestras();
        return ret.execute();
    }

}
