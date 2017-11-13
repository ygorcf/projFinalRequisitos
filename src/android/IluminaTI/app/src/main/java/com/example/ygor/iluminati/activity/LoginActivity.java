package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.ygor.iluminati.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    private boolean checkLogin() {
        return true;
    }

    private void openMain() {
        Intent i = new Intent(this, MainActivity.class);
        startActivityForResult(i, 100);
    }

    @OnClick(R.id.btnLogin)
    public void onEntrar() {
        if (checkLogin()) {
            openMain();
        }
    }
}
