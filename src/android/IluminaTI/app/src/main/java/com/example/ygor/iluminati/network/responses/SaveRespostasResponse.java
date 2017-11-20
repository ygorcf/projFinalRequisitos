package com.example.ygor.iluminati.network.responses;

import java.util.List;

/**
 * Created by Ygor on 19/11/2017.
 */

public class SaveRespostasResponse extends BaseResponse<SaveRespostasResponse.PerguntasResponse> {

    public static class PerguntasResponse {

        private List<Integer> respostas;
        private int pontuacao;

        public List<Integer> getRespostas() {
            return respostas;
        }

        public void setRespostas(List<Integer> respostas) {
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
