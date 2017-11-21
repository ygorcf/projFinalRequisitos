package com.example.ygor.iluminati.network.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ygor on 18/11/2017.
 */

public class PerguntasResponse extends BaseResponse<List<PerguntasResponse.PerguntaResponse>> implements Serializable {

    public class PerguntaResponse implements Serializable {

        private String pergunta;
        private int valor;
        private List<RespostaResponse> respostas;
        private List<ItemRankingResponse> ranking;

        public String getPergunta() {
            return pergunta;
        }

        public void setPergunta(String pergunta) {
            this.pergunta = pergunta;
        }

        public int getValor() {
            return valor;
        }

        public void setValor(int valor) {
            this.valor = valor;
        }

        public List<RespostaResponse> getRespostas() {
            return respostas;
        }

        public void setRespostas(List<RespostaResponse> respostas) {
            this.respostas = respostas;
        }

        public List<ItemRankingResponse> getRanking() {
            return ranking;
        }

        public void setRanking(List<ItemRankingResponse> ranking) {
            this.ranking = ranking;
        }
    }

    public class RespostaResponse implements Serializable {

        private String resposta;
        private boolean correta;

        public String getResposta() {
            return resposta;
        }

        public void setResposta(String resposta) {
            this.resposta = resposta;
        }

        public boolean isCorreta() {
            return correta;
        }

        public void setCorreta(boolean correta) {
            this.correta = correta;
        }

    }

}
