package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.EditText;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.dao.Database;
import com.example.ygor.iluminati.dao.UsuarioDAO;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.util.RetrofitHelper;
import com.example.ygor.iluminati.util.Preferencias;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

    @BindView(R.id.campoMatricula)
    EditText campoMatricula;

    @BindView(R.id.campoSenha)
    EditText campoSenha;

    @BindView(R.id.campoServidor)
    EditText campoServidor;

    Database database;
    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new Database(this);
        ButterKnife.bind(this);
        if (checkLogin()) {
            openMain();
        }
    }

    private boolean checkLogin() {
        Matcher matcherIp = Patterns.IP_ADDRESS.matcher(campoServidor.getText().toString());
        Matcher matcherDominio = Patterns.DOMAIN_NAME.matcher(campoServidor.getText().toString());
        if (campoMatricula.getText().length() > 0 &&
                campoSenha.getText().length() > 0 &&
                (matcherIp.matches() || matcherDominio.matches())) {
            usuario = new Usuario();
            usuario.setMatricula(campoMatricula.getText().toString());
            usuario.setSenha(campoSenha.getText().toString());
            usuario.setServidor("http://" + campoServidor.getText().toString() + ":3000/api/v1/");

            Preferencias preferencias = Preferencias.getPreferencias(this);
            if (!preferencias.setIpServidor(usuario.getServidor()))
                return false;
            if (!preferencias.setMatricula(usuario.getMatricula()))
                return false;
            if (!preferencias.setSenha(usuario.getSenha()))
                return false;
        } else {
            return false;
        }

        RetrofitHelper.BASE_URL = usuario.getServidor();

        return true;
    }

    private void openMain() {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("usuario", usuario);
        startActivityForResult(i, 100);
    }

    @OnClick(R.id.btnLogin)
    public void onEntrar() {
        if (checkLogin()) {
            openMain();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            new UsuarioDAO(database).deletarTodos();
        }
    }
}
