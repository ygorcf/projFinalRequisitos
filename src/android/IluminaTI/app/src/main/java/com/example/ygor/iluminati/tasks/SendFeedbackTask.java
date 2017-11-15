package com.example.ygor.iluminati.tasks;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ygor on 15/11/2017.
 */

public class SendFeedbackTask extends BaseTask<SendFeedbackTask.FeedbackBodyRequest, FeedbackResponse> {

    public SendFeedbackTask(Context context, CompleteListener<FeedbackResponse> completeListener) {
        super(context, completeListener, "Enviando feedback.");
    }

    @Override
    protected Response<FeedbackResponse> doTask(FeedbackBodyRequest... params) throws Exception {
        UcbServer ucbServer = RetrofitHelper.getUcbServer();
        Call<FeedbackResponse> ret = ucbServer.sendFeedback(params[0].idPalestra, params[0].feedback);
        return ret.execute();
    }

    public static class FeedbackBodyRequest {

        public int idPalestra;
        public FeedbackResponse.FeedbackObjectResponse feedback;

    }

}
