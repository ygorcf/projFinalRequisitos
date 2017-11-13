package com.example.ygor.iluminati.model;

import com.example.ygor.iluminati.util.ISO8601DateParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ygor on 07/11/2017.
 */

public class Palestra {

    private String nome;
    private String horario;
    private Date data;
    private ArrayList<String> alunos;

    public Palestra() {
        this.setAlunos(new ArrayList<String>());
    }

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public ArrayList<String> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<String> alunos) {
        this.alunos = alunos;
    }
}
