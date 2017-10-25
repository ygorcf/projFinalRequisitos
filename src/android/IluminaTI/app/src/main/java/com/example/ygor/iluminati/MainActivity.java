package com.example.ygor.iluminati;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnCheckin)
    public void checkin() {
        Intent i = new Intent(this, EventoActivity.class);
        startActivityForResult(i, 100);
    }

}
