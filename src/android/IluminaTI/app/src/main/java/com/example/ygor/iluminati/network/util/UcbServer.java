package com.example.ygor.iluminati.network.util;

import com.example.ygor.iluminati.network.responses.CheckInResponse;
import com.example.ygor.iluminati.network.responses.CronogramaResponse;
import com.example.ygor.iluminati.network.responses.FeedbackResponse;
import com.example.ygor.iluminati.network.responses.PerguntasResponse;
import com.example.ygor.iluminati.network.responses.RankingRespose;
import com.example.ygor.iluminati.network.responses.SaveRespostasResponse;
import com.example.ygor.iluminati.network.task.SaveRespostasTask;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Ygor on 12/11/2017.
 */

public interface UcbServer {

    @GET("palestra/")
    Call<CronogramaResponse> getPalestras();

    @GET("palestra/qrcode/{idPalestra}/{matricula}")
    Call<CheckInResponse> checkInQrCode(@Path("idPalestra") int idPalestra, @Path("matricula") String matricula);

    @POST("palestra/{idPalestra}/feedback")
    Call<FeedbackResponse> sendFeedback(@Path("idPalestra") int idPalestra, @Body FeedbackResponse.FeedbackObjectResponse feedbackObject);

    @GET("palestra/{idPalestra}/jogo")
    Call<PerguntasResponse> getPerguntasJogo(@Path("idPalestra") int idPalestra);

    @GET("palestra/{idPalestra}/jogo/ranking")
    Call<RankingRespose> getRanking(@Path("idPalestra") int idPalestra);

    @POST("palestra/{idPalestra}/jogo/responder")
    Call<SaveRespostasResponse> saveRespostas(@Path("idPalestra") int idPalestra, @Body SaveRespostasTask.SaveRespostasObjectRequest respostas);

}
