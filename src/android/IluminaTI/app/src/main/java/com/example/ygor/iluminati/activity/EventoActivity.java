package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.responses.RankingRespose;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventoActivity extends Activity {

    private Usuario usuario;
    private int idPalestra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        idPalestra = getIntent().getIntExtra("idPalestra", -1);

        if (idPalestra == -1) {
            Toast.makeText(this, "Id da palestra invalido.", Toast.LENGTH_LONG).show();
            finish();
        }

        ButterKnife.bind(this);
    }

    private void compartilharEvento() {
        Intent i = new Intent();
        i.setType("text/plain");
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, usuario.getMatricula() + " está compartilhando o evento do IluminaTI");
        startActivityForResult(i, 100);
    }

    private void openFeedback() {
        Intent i = new Intent(this, FeedbackActivity.class);
        i.putExtra("usuario", usuario);
        i.putExtra("idPalestra", idPalestra);
        startActivityForResult(i, 100);
    }

    private void openRanking() {
        Intent i = new Intent(this, RankingActivity.class);
        i.putExtra("usuario", usuario);
        i.putExtra("idPalestra", idPalestra);
        startActivityForResult(i, 100);
    }

    private void openJogar() {
        Intent i = new Intent(this, JogoActivity.class);
        i.putExtra("usuario", usuario);
        i.putExtra("idPalestra", idPalestra);
        startActivityForResult(i, 100);
    }

    @OnClick(R.id.btnCompartilhar)
    public void onCompartilhar() {
        compartilharEvento();
    }

    @OnClick(R.id.btnFeedback)
    public void onFeedback() {
        openFeedback();
    }

    @OnClick(R.id.btnJogar)
    public void onJogar() {
        openJogar();
    }

    @OnClick(R.id.btnRanking)
    public void onRanking() {
        openRanking();
    }

}
