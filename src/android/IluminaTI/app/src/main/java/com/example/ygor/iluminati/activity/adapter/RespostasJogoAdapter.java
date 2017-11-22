package com.example.ygor.iluminati.activity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.network.responses.PerguntasResponse;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ygor on 19/11/2017.
 */

public class RespostasJogoAdapter extends BaseAdapter {

    List<PerguntasResponse.RespostaResponse> respostas;
    LayoutInflater inflater;
    OnItemClickListener listener;
    private View viewClicked = null;

    public RespostasJogoAdapter(Context context, List<PerguntasResponse.RespostaResponse> respostas, OnItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        this.respostas = respostas;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return respostas.size();
    }

    @Override
    public Object getItem(int position) {
        return respostas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        RespostasJogoHolder holder = null;
        PerguntasResponse.RespostaResponse resposta = (PerguntasResponse.RespostaResponse) getItem(position);

        if (view == null) {
            view = inflater.inflate(R.layout.item_resposta_jogo, parent, false);

            holder = new RespostasJogoHolder(view, listener, resposta);

            view.setTag(holder);
        } else {
            holder = (RespostasJogoHolder) view.getTag();
        }

        holder.tvResposta.setText(resposta.getResposta());

        return view;
    }

    public class RespostasJogoHolder implements View.OnClickListener {

        @BindView(R.id.tvResposta)
        public TextView tvResposta;

        private OnItemClickListener listener;
        private PerguntasResponse.RespostaResponse resposta;


        public RespostasJogoHolder(View view, OnItemClickListener listener, PerguntasResponse.RespostaResponse resposta) {
            ButterKnife.bind(this, view);
            this.listener = listener;
            this.resposta = resposta;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (viewClicked != null)
                viewClicked.setBackgroundColor(Color.argb(0, 0, 0, 0));
            v.setBackgroundColor(ResourcesCompat.getColor(v.getResources(), R.color.respostaSelected, null));
            viewClicked = v;
            this.listener.onItemClick(resposta);
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(PerguntasResponse.RespostaResponse resposta);
    }

}
