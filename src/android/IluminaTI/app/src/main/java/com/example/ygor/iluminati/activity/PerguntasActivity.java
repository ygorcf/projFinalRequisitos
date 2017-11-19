package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.activity.adapter.RespostasJogoAdapter;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.responses.PerguntasResponse;
import com.example.ygor.iluminati.network.task.BaseTask;
import com.example.ygor.iluminati.network.task.GetPerguntasJogoTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class PerguntasActivity extends Activity implements RespostasJogoAdapter.OnItemClickListener {

    @BindView(R.id.tvPergunta)
    TextView tvPergunta;
    @BindView(R.id.listaRespostas)
    ListView listaRespostas;

    private List<PerguntasResponse.PerguntaResponse> perguntas;
    private List<PerguntasResponse.RespostaResponse> respostas;
    private PerguntasResponse.RespostaResponse respostaEscolhida;
    private int idPalestra;
    private Usuario usuario;
    private int indexPergunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perguntas);
        ButterKnife.bind(this);
        perguntas = (List<PerguntasResponse.PerguntaResponse>) getIntent().getSerializableExtra("perguntas");
        respostas = (List<PerguntasResponse.RespostaResponse>) getIntent().getSerializableExtra("respostas");
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        idPalestra = getIntent().getIntExtra("idPalestra", -1);
        indexPergunta = getIntent().getIntExtra("indexPergunta", -1);

        if (perguntas == null) {
            Toast.makeText(this, "Perguntas invalidas.", Toast.LENGTH_LONG).show();
            finish();
        }

        if (respostas == null) {
            Toast.makeText(this, "Respostas invalidas.", Toast.LENGTH_LONG).show();
            finish();
        }

        if (idPalestra == -1) {
            Toast.makeText(this, "Id da palestra invalido.", Toast.LENGTH_LONG).show();
            finish();
        }
        if (indexPergunta == -1) {
            Toast.makeText(this, "Pergunta invalida.", Toast.LENGTH_LONG).show();
            finish();
        }

        PerguntasResponse.PerguntaResponse pergunta = perguntas.get(indexPergunta);
        List<String> respostas = new ArrayList<>();


        for (PerguntasResponse.RespostaResponse resposta : pergunta.getRespostas()) {
            respostas.add(resposta.getResposta());
        }

        RespostasJogoAdapter adapter = new RespostasJogoAdapter(this, pergunta.getRespostas(), this);
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, respostas);

        tvPergunta.setText(pergunta.getPergunta());
        listaRespostas.setAdapter(adapter);
    }

    private void responder() {
        if (indexPergunta + 1 >= perguntas.size()) {
            finishPerguntas();
        } else {
            proxPergunta();
        }
    }

    private void finishPerguntas() {
        setResult(100);
        finish();
    }

    private void proxPergunta() {
        Intent i = new Intent(this, PerguntasActivity.class);
        i.putExtra("usuario", usuario);
        i.putExtra("idPalestra", idPalestra);
        i.putExtra("perguntas", (ArrayList) perguntas);
        i.putExtra("respostas", (ArrayList) respostas);
        i.putExtra("indexPergunta", indexPergunta + 1);
        startActivityForResult(i, 100);
    }

    @OnClick(R.id.btnResponder)
    public void onResponder() {
        responder();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 100) {
            setResult(100);
            finish();
        }
    }

    @Override
    public void onItemClick(PerguntasResponse.RespostaResponse resposta) {
        respostaEscolhida = resposta;
    }
}
