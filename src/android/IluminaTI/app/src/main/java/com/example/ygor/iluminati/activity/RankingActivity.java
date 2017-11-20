package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.activity.adapter.RankingAdapter;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.responses.ItemRankingResponse;
import com.example.ygor.iluminati.network.responses.RankingRespose;
import com.example.ygor.iluminati.network.task.BaseTask;
import com.example.ygor.iluminati.network.task.GetRankingTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class RankingActivity extends Activity implements BaseTask.CompleteListener<RankingRespose> {

    @BindView(R.id.listaRanking)
    ListView listaRanking;

    private Usuario usuario;
    private int idPalestra;
    private List<ItemRankingResponse> ranking;
    private RankingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        ButterKnife.bind(this);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        idPalestra = getIntent().getIntExtra("idPalestra", -1);

        if (idPalestra == -1) {
            Toast.makeText(this, "Id da palestra invalido.", Toast.LENGTH_LONG).show();
            finish();
        }

        ranking = new ArrayList<>();
        adapter = new RankingAdapter(this, ranking);

        listaRanking.setAdapter(adapter);

        GetRankingTask task = new GetRankingTask(this, this);
        task.execute(idPalestra);
    }

    @Override
    public void onComplete(RankingRespose result) {
        ranking.addAll(result.getData());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(Exception e, Response<RankingRespose> result) {
        Toast.makeText(this, "Falha ao obter o ranking do jogo..", Toast.LENGTH_LONG).show();
        Log.e(this.getClass().getName(), (e != null) ? e.getMessage() : "Erro desconhecido.");
    }
}
