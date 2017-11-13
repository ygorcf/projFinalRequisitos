package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.ygor.iluminati.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void openCronograma() {
        Intent i = new Intent(this, CronogramaActivity.class);
        startActivityForResult(i, 100);
    }

    private void openEvento() {
        Intent i = new Intent(this, EventoActivity.class);
        startActivityForResult(i, 100);
    }

    @OnClick(R.id.btnCheckin)
    public void onCheckin() {
        openEvento();
    }

    @OnClick(R.id.btnCronograma)
    public void onCronograma() {
        openCronograma();
    }

}
