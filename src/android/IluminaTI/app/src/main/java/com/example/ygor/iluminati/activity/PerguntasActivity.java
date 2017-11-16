package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.ygor.iluminati.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerguntasActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        ButterKnife.bind(this);
    }

    private void responder() {
        finishPerguntas();
    }

    private void finishPerguntas() {
        setResult(100);
        finish();
    }

    @OnClick(R.id.btnResponder)
    public void onResponder() {
        responder();
    }

}
