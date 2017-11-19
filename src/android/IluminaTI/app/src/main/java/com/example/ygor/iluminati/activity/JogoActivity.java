package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.responses.PerguntasResponse;
import com.example.ygor.iluminati.network.task.BaseTask;
import com.example.ygor.iluminati.network.task.GetPerguntasJogoTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class JogoActivity extends Activity implements View.OnClickListener, BaseTask.CompleteListener<PerguntasResponse> {

    @BindView(R.id.tvContagem)
    TextView tvContagem;
    @BindView(R.id.btnIniciar)
    Button btnIniciar;

    private CountDownTimer contador;
    private List<PerguntasResponse.PerguntaResponse> perguntas;
    private int idPalestra;
    private Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        ButterKnife.bind(this);
        btnIniciar.setOnClickListener(this);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        idPalestra = getIntent().getIntExtra("idPalestra", -1);

        if (idPalestra == -1) {
            Toast.makeText(this, "Id da palestra invalido.", Toast.LENGTH_LONG).show();
            finish();
        }

        GetPerguntasJogoTask task = new GetPerguntasJogoTask(this, this);
        task.execute(idPalestra);
    }

    private void atualizaContagem(long segundosFaltando) {
        tvContagem.setText("Carregando ... " + String.valueOf(segundosFaltando / 1000));
    }

    private void terminarContagem() {
        tvContagem.setText("Iniciando ...");
        Intent i = new Intent(JogoActivity.this, PerguntasActivity.class);
        i.putExtra("usuario", usuario);
        i.putExtra("idPalestra", idPalestra);
        i.putExtra("perguntas", (ArrayList<PerguntasResponse.PerguntaResponse>) new ArrayList<PerguntasResponse.PerguntaResponse>(perguntas));
        i.putExtra("respostas", new ArrayList<PerguntasResponse.RespostaResponse>());
        i.putExtra("indexPergunta", 0);
        startActivityForResult(i, 100);
    }

    private void starCount() {
        contador = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                atualizaContagem(millisUntilFinished);
            }

            @Override
            public void onFinish() {
                terminarContagem();
            }
        }.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        btnIniciar.setOnClickListener(null);
        if (requestCode == 100) {
            switch (resultCode) {
                case 100:
                    setResult(100);
                    finish();
                    break;
                default:
                    finish();
            }
        }

    }

    @Override
    public void onClick(View v) {
        btnIniciar.setOnClickListener(null);
        starCount();
    }

    @Override
    public void onComplete(PerguntasResponse result) {
        perguntas = result.getData();
    }

    @Override
    public void onError(Exception e, Response<PerguntasResponse> result) {
        Toast.makeText(this, "Falha ao obter as perguntas do jogo..", Toast.LENGTH_LONG).show();
        Log.e(this.getClass().getName(), (e != null) ? e.getMessage() : "Erro desconhecido.");
    }
}
