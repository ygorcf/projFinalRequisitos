package com.example.ygor.iluminati.model;

import java.io.Serializable;

/**
 * Created by Ygor on 14/11/2017.
 */

public class Usuario implements Serializable {

    private long id;
    private String servidor;
    private String matricula;
    private String senha;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

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
