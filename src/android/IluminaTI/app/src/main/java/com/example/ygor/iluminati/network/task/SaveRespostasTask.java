package com.example.ygor.iluminati.network.task;

import android.content.Context;

import com.example.ygor.iluminati.network.responses.SaveRespostasResponse;
import com.example.ygor.iluminati.network.util.RetrofitHelper;
import com.example.ygor.iluminati.network.util.UcbServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Ygor on 19/11/2017.
 */

public class SaveRespostasTask extends BaseTask<SaveRespostasTask.SaveRespostasRequest, SaveRespostasResponse> {

    public SaveRespostasTask(Context context, CompleteListener<SaveRespostasResponse> completeListener) {
        super(context, completeListener, "Salvando respostas.");
    }

    @Override
    protected Response<SaveRespostasResponse> doTask(SaveRespostasRequest... params) throws Exception {
        UcbServer ucbServer = RetrofitHelper.getUcbServer();
        Call<SaveRespostasResponse> ret = ucbServer.saveRespostas(params[0].idPalestra, params[0].respostas);
        return ret.execute();
    }

    public static class SaveRespostasRequest {

        private SaveRespostasObjectRequest respostas;
        private int idPalestra;

        public SaveRespostasRequest(SaveRespostasObjectRequest respostas, int idPalestra) {
            this.respostas = respostas;
            this.idPalestra = idPalestra;
        }

        public SaveRespostasObjectRequest getRespostas() {
            return respostas;
        }

        public void setRespostas(SaveRespostasObjectRequest respostas) {
            this.respostas = respostas;
        }

        public int getIdPalestra() {
            return idPalestra;
        }

        public void setIdPalestra(int idPalestra) {
            this.idPalestra = idPalestra;
        }

    }

    public static class SaveRespostasObjectRequest {

        private List<Integer> respostas;
        private String matricula;

        public SaveRespostasObjectRequest(List<Integer> respostas, String matricula) {
            this.respostas = respostas;
            this.matricula = matricula;
        }

        public List<Integer> getRespostas() {
            return respostas;
        }

        public void setRespostas(List<Integer> respostas) {
            this.respostas = respostas;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

    }
}
