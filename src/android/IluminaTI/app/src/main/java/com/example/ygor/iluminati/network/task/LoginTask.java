package com.example.ygor.iluminati.network.task;

import android.content.Context;

import com.example.ygor.iluminati.network.responses.LoginResponse;
import com.example.ygor.iluminati.network.util.RetrofitHelper;
import com.example.ygor.iluminati.network.util.UcbServer;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by notoriun on 21/11/17.
 */

public class LoginTask extends BaseTask<LoginTask.LoginObjectRequest, LoginResponse> {

    public LoginTask(Context context, CompleteListener<LoginResponse> completeListener) {
        super(context, completeListener, "Logando ...");
    }

    @Override
    protected Response<LoginResponse> doTask(LoginObjectRequest... params) throws Exception {
        UcbServer ucbServer = RetrofitHelper.getUcbServer();
        Call<LoginResponse> ret = ucbServer.login(params[0]);
        return ret.execute();
    }

    public static class LoginObjectRequest {

        private String matricula;
        private String senha;

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            this.senha = senha;
        }

    }

}
