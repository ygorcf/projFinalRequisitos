package com.example.ygor.iluminati;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class JogoActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.tvContagem)
    TextView tvContagem;
    @BindView(R.id.btnIniciar)
    Button btnIniciar;

    private CountDownTimer contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);
        ButterKnife.bind(this);
        btnIniciar.setOnClickListener(this);
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
}
