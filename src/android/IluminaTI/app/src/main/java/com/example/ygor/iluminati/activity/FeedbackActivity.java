package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.task.BaseTask;
import com.example.ygor.iluminati.network.responses.FeedbackResponse;
import com.example.ygor.iluminati.network.task.SendFeedbackTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class FeedbackActivity extends Activity implements BaseTask.CompleteListener<FeedbackResponse> {

    private Usuario usuario;
    private int idPalestra;

    @BindView(R.id.campoComentario)
    EditText campoComentario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        idPalestra = getIntent().getIntExtra("idPalestra", -1);

        if (idPalestra == -1) {
            Toast.makeText(this, "Id da palestra invalido.", Toast.LENGTH_LONG).show();
            finish();
        }
        ButterKnife.bind(this);
    }

    private void sendComentario() {
        String comentario = campoComentario.getText().toString();
        SendFeedbackTask.FeedbackBodyRequest body = new SendFeedbackTask.FeedbackBodyRequest();
        body.feedback = new FeedbackResponse.FeedbackObjectResponse();
        body.feedback.setComentario(comentario);
        body.feedback.setMatricula(usuario.getMatricula());
        body.idPalestra = idPalestra;
        SendFeedbackTask task = new SendFeedbackTask(this, this);
        task.execute(body);
    }

    @OnClick(R.id.btnEnviar)
    public void onEnviar() {
        sendComentario();
    }

    @Override
    public void onComplete(FeedbackResponse result) {
        if (result.getStatus() == 200) {
            Toast.makeText(this, "Comentario enviado com sucesso.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ocorreu uma falha ao enviar o comentario", Toast.LENGTH_LONG).show();
            Log.e(this.getClass().getName(), result.getMessage());
        }
    }

    @Override
    public void onError(Exception e, Response<FeedbackResponse> result) {
        Toast.makeText(this, "Ocorreu uma falha ao enviar o comentario", Toast.LENGTH_LONG).show();
        Log.e(this.getClass().getName(), e.getMessage());
    }
}
