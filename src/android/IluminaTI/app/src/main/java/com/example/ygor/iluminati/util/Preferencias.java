package com.example.ygor.iluminati.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Ygor on 18/11/2017.
 */

public class Preferencias {

    private static final String NOME_ARQ_PREFERENCIAS = "com.example.ygor.iluminati";
    private static final String OPCAO_IPSERVIDOR = "ipservidor";
    private static final String OPCAO_MATRICULA = "matricula";
    private static final String OPCAO_SENHA = "senha";

    private static Preferencias preferenciasInstance = null;

    private Context context;
    private String ipServidor;
    private String matricula;
    private String senha;

    private Preferencias() {}

    private SharedPreferences getSharedPreferences() {
        return context.getSharedPreferences(NOME_ARQ_PREFERENCIAS, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getEditor() {
        return getSharedPreferences().edit();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getIpServidor() {
        return getSharedPreferences().getString(OPCAO_IPSERVIDOR, null);
    }

    public boolean setIpServidor(String ipServidor) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(OPCAO_IPSERVIDOR, ipServidor);
        boolean ret = editor.commit();
        if (ret)
            this.ipServidor = ipServidor;
        return ret;
    }

    public String getMatricula() {
        return getSharedPreferences().getString(OPCAO_MATRICULA, null);
    }

    public boolean setMatricula(String matricula) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(OPCAO_MATRICULA, matricula);
        boolean ret = editor.commit();
        if (ret)
            this.matricula = matricula;
        return ret;
    }

    public String getSenha() {
        return getSharedPreferences().getString(OPCAO_SENHA, null);
    }

    public boolean setSenha(String senha) {
        SharedPreferences.Editor editor = getEditor();
        editor.putString(OPCAO_SENHA, senha);
        boolean ret = editor.commit();
        if (ret)
            this.senha = senha;
        return ret;
    }

    public static Preferencias getPreferencias(Context context) {
        if (preferenciasInstance == null) {
            preferenciasInstance = new Preferencias();
        }
        preferenciasInstance.setContext(context);
        return preferenciasInstance;
    }

}
