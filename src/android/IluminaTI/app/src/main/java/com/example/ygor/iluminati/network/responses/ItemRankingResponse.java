package com.example.ygor.iluminati.network.responses;

import java.io.Serializable;

/**
 * Created by Ygor on 19/11/2017.
 */

public class ItemRankingResponse implements Serializable {

    private String matricula;
    private int pontuacao;

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

}
