package com.example.ygor.iluminati.network.task;

import android.content.Context;

import com.example.ygor.iluminati.network.responses.PerguntasResponse;
import com.example.ygor.iluminati.network.util.RetrofitHelper;
import com.example.ygor.iluminati.network.util.UcbServer;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ygor on 18/11/2017.
 */

public class GetPerguntasJogoTask extends BaseTask<Integer, PerguntasResponse> {

    public GetPerguntasJogoTask(Context context, CompleteListener<PerguntasResponse> completeListener) {
        super(context, completeListener, "Obtendo perguntas.");
    }

    @Override
    protected Response<PerguntasResponse> doTask(Integer... params) throws Exception {
        UcbServer ucbServer = RetrofitHelper.getUcbServer();
        Call<PerguntasResponse> ret = ucbServer.getPerguntasJogo(params[0]);
        return ret.execute();
    }

}
