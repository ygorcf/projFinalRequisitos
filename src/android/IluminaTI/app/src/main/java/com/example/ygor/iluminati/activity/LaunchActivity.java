package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.util.RetrofitHelper;
import com.example.ygor.iluminati.util.Preferencias;

import java.util.regex.Pattern;

public class LaunchActivity extends Activity {

    private Usuario usuario;

    private void openMain() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("usuario", usuario);
        startActivityForResult(i, 1);
    }

    private void openLogin() {
        Intent i = new Intent(this, LoginActivity.class);
        i.putExtra("usuario", usuario);
        startActivityForResult(i, 2);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Preferencias preferencias = Preferencias.getPreferencias(this);

        if (preferencias.getIpServidor() != null && Patterns.WEB_URL.matcher(preferencias.getIpServidor()).matches() &&
                preferencias.getMatricula() != null && preferencias.getMatricula().length() > 0 &&
                preferencias.getSenha() != null && preferencias.getSenha().length() > 0) {
            usuario = new Usuario();
            usuario.setServidor(preferencias.getIpServidor());
            usuario.setMatricula(preferencias.getMatricula());
            usuario.setSenha(preferencias.getSenha());

            RetrofitHelper.BASE_URL = usuario.getServidor();
            openMain();
        } else {
            openLogin();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2)
            finish();
        else
            openLogin();
    }
}
