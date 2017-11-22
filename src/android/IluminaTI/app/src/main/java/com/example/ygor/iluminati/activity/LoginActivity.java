package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.dao.Database;
import com.example.ygor.iluminati.dao.UsuarioDAO;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.responses.LoginResponse;
import com.example.ygor.iluminati.network.task.BaseTask;
import com.example.ygor.iluminati.network.task.LoginTask;
import com.example.ygor.iluminati.network.util.RetrofitHelper;
import com.example.ygor.iluminati.util.Preferencias;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class LoginActivity extends Activity implements BaseTask.CompleteListener<LoginResponse> {

    @BindView(R.id.campoMatricula)
    EditText campoMatricula;

    @BindView(R.id.campoSenha)
    EditText campoSenha;

    @BindView(R.id.campoServidor)
    EditText campoServidor;

    @BindView(R.id.tvInfo)
    TextView tvInfo;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        if (checkLogin()) {
            sendLoginRequest();
        }
    }

    private boolean checkLogin() {
        tvInfo.setText("");
        Matcher matcherIp = Patterns.IP_ADDRESS.matcher(campoServidor.getText().toString());
        Matcher matcherDominio = Patterns.DOMAIN_NAME.matcher(campoServidor.getText().toString());

        if (campoMatricula.getText().length() <= 0) {
            tvInfo.setText("Matricula invalida.");
            return false;
        }
        if (campoSenha.getText().length() <= 0) {
            tvInfo.setText("Senha invalida.");
            return false;
        }
        if (!(matcherIp.matches() || matcherDominio.matches())) {
            tvInfo.setText("Ip do Servidor invalido.");
            return false;
        }

        usuario = new Usuario();
        usuario.setMatricula(campoMatricula.getText().toString());
        usuario.setSenha(campoSenha.getText().toString());
        usuario.setServidor("http://" + campoServidor.getText().toString() + ":3000/api/v1/");

        Preferencias preferencias = Preferencias.getPreferencias(this);
        if (!preferencias.setIpServidor(usuario.getServidor())) {
            tvInfo.setText("Falha ao salvar Ip do Servidor.");
            return false;
        }
        if (!preferencias.setMatricula(usuario.getMatricula())) {
            tvInfo.setText("Falha ao salvar Matricula.");
            return false;
        }
        if (!preferencias.setSenha(usuario.getSenha())) {
            tvInfo.setText("Falha ao salvar Ip do servidor.");
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

    private void sendLoginRequest() {
        LoginTask.LoginObjectRequest objReq = new LoginTask.LoginObjectRequest();
        objReq.setMatricula(usuario.getMatricula());
        objReq.setSenha(usuario.getSenha());
        LoginTask task = new LoginTask(this, this);
        task.execute(objReq);
    }

    @OnClick(R.id.btnLogin)
    public void onEntrar() {
        if (checkLogin()) {
            sendLoginRequest();
        }
    }

    @Override
    public void onComplete(LoginResponse result) {
        if (result.isSuccess() && result.getData().isLogged())
            openMain();
        else
            Toast.makeText(this, result.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(Exception e, Response<LoginResponse> result) {
        Toast.makeText(this, "Falha ao logar, tente novamente.", Toast.LENGTH_SHORT).show();
        try {
            Log.e(this.getClass().getName(), (e == null) ? (result == null) ? "Erro desconhecido" : result.errorBody().string() : e.getMessage());
        } catch (IOException e1) {
            Log.e(this.getClass().getName(), e1.getMessage());
        }
    }
}
