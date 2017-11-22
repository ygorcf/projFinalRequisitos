package com.example.ygor.iluminati.network.responses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ygor on 14/11/2017.
 */

public class PalestraResponse implements Serializable {

    private String nome;
    private String horario;
    private Long dia;
    private List<String> alunosCheckin;
    private List<FeedbackResponse.FeedbackObjectResponse> feedbacks;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Long getDia() {
        return dia;
    }

    public void setDia(Long dia) {
        this.dia = dia;
    }

    public List<String> getAlunosCheckin() {
        return alunosCheckin;
    }

    public void setAlunosCheckin(List<String> alunosCheckin) {
        this.alunosCheckin = alunosCheckin;
    }

    public List<FeedbackResponse.FeedbackObjectResponse> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<FeedbackResponse.FeedbackObjectResponse> feedbacks) {
        this.feedbacks = feedbacks;
    }
}
