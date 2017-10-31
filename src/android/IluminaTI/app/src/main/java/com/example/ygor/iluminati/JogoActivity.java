package com.example.ygor.iluminati;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JogoActivity extends Activity {

    @BindView(R.id.tvContagem)
    TextView tvContagem;

    private CountDownTimer contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        ButterKnife.bind(this);
    }

    private void atualizaContagem(long segundosFaltando) {
        tvContagem.setText(String.valueOf(segundosFaltando / 1000));
    }

    private void terminarContagem() {
        Intent i = new Intent(this, PerguntasActivity.class);
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

    @OnClick(R.id.btnIniciar)
    public void onIniciar() {
        starCount();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

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
}
