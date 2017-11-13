package com.example.ygor.iluminati.tasks;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Ygor on 12/11/2017.
 */

public interface UcbServer {

    @GET("palestra/")
    Call<CronogramaResponse> getPalestras();

}
