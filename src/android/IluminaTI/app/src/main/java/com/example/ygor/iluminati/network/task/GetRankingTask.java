package com.example.ygor.iluminati.network.task;

import android.content.Context;

import com.example.ygor.iluminati.network.responses.RankingRespose;
import com.example.ygor.iluminati.network.util.RetrofitHelper;
import com.example.ygor.iluminati.network.util.UcbServer;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ygor on 19/11/2017.
 */

public class GetRankingTask extends BaseTask<Integer, RankingRespose> {

    public GetRankingTask(Context context, CompleteListener<RankingRespose> completeListener) {
        super(context, completeListener, "Obtendo ranking...");
    }

    @Override
    protected Response<RankingRespose> doTask(Integer... params) throws Exception {
        UcbServer ucbServer = RetrofitHelper.getUcbServer();
        Call<RankingRespose> ret = ucbServer.getRanking(params[0]);
        return ret.execute();
    }

}
