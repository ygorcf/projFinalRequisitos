package com.example.ygor.iluminati.tasks;

import android.content.Context;
import android.os.AsyncTask;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ygor on 14/11/2017.
 */

public class CheckInPalestraTask extends BaseTask<CheckInPalestraTask.CheckinParams, CheckInResponse> {

    public CheckInPalestraTask(Context context, BaseTask.CompleteListener<CheckInResponse> completeListener) {
        super(context, completeListener, "Realizando check-in na palestra.");
    }

    @Override
    protected Response<CheckInResponse> doTask(CheckinParams... params) throws Exception {
        UcbServer ucbServer = RetrofitHelper.getUcbServer();
        Call<CheckInResponse> ret = ucbServer.checkInQrCode(params[0].idPalestra, params[0].matricula);
        return ret.execute();
    }

    public static CheckinParams getParams(int idPalestra, String matricula) {
        CheckinParams params = new CheckinParams();
        params.idPalestra = idPalestra;
        params.matricula = matricula;
        return params;
    }

    public static class CheckinParams {
        public int idPalestra;
        public String matricula;
    }

}
