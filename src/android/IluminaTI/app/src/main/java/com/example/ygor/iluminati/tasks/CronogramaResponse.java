package com.example.ygor.iluminati.tasks;

import java.util.List;

/**
 * Created by Ygor on 12/11/2017.
 */

public class CronogramaResponse {

    private boolean success;
    private int status;
    private List<CronogramaPalestraResponse> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CronogramaPalestraResponse> getData() {
        return data;
    }

    public void setData(List<CronogramaPalestraResponse> data) {
        this.data = data;
    }

    public class CronogramaPalestraResponse {

        private String nome;
        private String horario;
        private Long dia;
        private List<String> alunosCheckin;

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
    }
}
