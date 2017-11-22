package com.example.ygor.iluminati.network.responses;

import com.example.ygor.iluminati.network.task.SaveRespostasTask;

import java.util.List;

/**
 * Created by Ygor on 19/11/2017.
 */

public class SaveRespostasResponse extends BaseResponse<SaveRespostasResponse.PerguntasResponse> {

    public static class PerguntasResponse {

        private SaveRespostasTask.SaveRespostasObjectRequest respostas;
        private int pontuacao;

        public SaveRespostasTask.SaveRespostasObjectRequest getRespostas() {
            return respostas;
        }

        public void setRespostas(SaveRespostasTask.SaveRespostasObjectRequest respostas) {
            this.respostas = respostas;
        }

        public int getPontuacao() {
            return pontuacao;
        }

        public void setPontuacao(int pontuacao) {
            this.pontuacao = pontuacao;
        }

    }

}
