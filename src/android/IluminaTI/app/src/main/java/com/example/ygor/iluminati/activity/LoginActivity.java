package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.dao.Database;
import com.example.ygor.iluminati.dao.UsuarioDAO;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.tasks.RetrofitHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;

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
        UsuarioDAO uBd = new UsuarioDAO(database);
        List<Usuario> usuarios = uBd.listar();
        if (usuarios.size() > 1)
            uBd.deletarTodos();

        usuarios = uBd.listar();
        if (usuarios.size() == 0) {
            if (campoMatricula.getText().length() > 0 &&
                    campoSenha.getText().length() > 0 &&
                    campoServidor.getText().length() > 0) {
                usuario = new Usuario();
                usuario.setMatricula(campoMatricula.getText().toString());
                usuario.setSenha(campoSenha.getText().toString());
                usuario.setServidor(campoServidor.getText().toString());
                uBd.salvar(usuario);
            } else {
                return false;
            }
        } else {
            usuario = usuarios.get(0);
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
