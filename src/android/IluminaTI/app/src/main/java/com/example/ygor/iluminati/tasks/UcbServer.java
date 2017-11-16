package com.example.ygor.iluminati.tasks;

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

}
