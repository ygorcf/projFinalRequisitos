package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ygor.iluminati.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedbackActivity extends Activity {

    @BindView(R.id.campoComentario)
    EditText campoComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
    }

    private void sendComentario() {
        String comentario = campoComentario.getText().toString();
        Toast.makeText(this, comentario, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnEnviar)
    public void onEnviar() {
        sendComentario();
    }

}
