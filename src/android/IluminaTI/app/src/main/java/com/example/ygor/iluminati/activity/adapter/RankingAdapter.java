package com.example.ygor.iluminati.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.network.responses.ItemRankingResponse;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Ygor on 19/11/2017.
 */

public class RankingAdapter extends BaseAdapter {

    private List<ItemRankingResponse> ranking;
    private LayoutInflater inflater;

    public RankingAdapter(Context context, List<ItemRankingResponse> ranking) {
        this.inflater = LayoutInflater.from(context);
        this.ranking = ranking;
    }

    @Override
    public int getCount() {
        return ranking.size();
    }

    @Override
    public Object getItem(int position) {
        return ranking.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        RankingHolder holder = null;
        ItemRankingResponse item = (ItemRankingResponse) getItem(position);

        if (view == null) {
            view = inflater.inflate(R.layout.item_ranking, parent, false);

            holder = new RankingHolder(view);

            view.setTag(holder);
        } else
            holder = (RankingHolder) view.getTag();

        holder.tvMatricula.setText(item.getMatricula());
        holder.tvPontuacao.setText(String.valueOf(item.getPontuacao()));
        holder.tvPosicao.setText(String.valueOf(position + 1) + "º");

        return view;
    }

    public class RankingHolder {

        @BindView(R.id.tvMatricula)
        public TextView tvMatricula;

        @BindView(R.id.tvPontuacao)
        public TextView tvPontuacao;

        @BindView(R.id.tvPosicao)
        public TextView tvPosicao;

        public RankingHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
