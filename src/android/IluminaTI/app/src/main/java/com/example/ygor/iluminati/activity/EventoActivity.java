package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.ygor.iluminati.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evento);
        ButterKnife.bind(this);
    }

    private void compartilharEvento() {
        Intent i = new Intent();
        i.setType("text/plain");
        i.setAction(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT, "Compartilhando evento do IluminaTI");
        startActivityForResult(i, 100);
    }

    private void openFeedback() {
        Intent i = new Intent(this, FeedbackActivity.class);
        startActivityForResult(i, 100);
    }

    private void openJogar() {
        Intent i = new Intent(this, JogoActivity.class);
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

}
