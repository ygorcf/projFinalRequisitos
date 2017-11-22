package com.example.ygor.iluminati.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.model.Palestra;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by notoriun on 21/11/17.
 */

public class PalestrasAdapter extends BaseAdapter {

    private List<Palestra> palestras;
    private LayoutInflater inflater;

    public PalestrasAdapter(Context context, List<Palestra> palestras) {
        this.palestras = palestras;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return palestras.size();
    }

    @Override
    public Object getItem(int i) {
        return palestras.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        View view = convertView;
        Palestra palestra = (Palestra) getItem(i);
        PalestraViewHolder holder = null;

        if (view == null) {
            view = inflater.inflate(R.layout.item_palestra, viewGroup, false);

            holder = new PalestraViewHolder(view);

            view.setTag(holder);
        } else {
            holder = (PalestraViewHolder) view.getTag();
        }

        holder.tvTituloPalestra.setText(palestra.getNome());
        holder.tvHorarioPalestra.setText(palestra.getHorario());

        return view;
    }

    public class PalestraViewHolder {

        @BindView(R.id.tvTituloPalestra)
        public TextView tvTituloPalestra;

        @BindView(R.id.tvHorarioPalestra)
        public TextView tvHorarioPalestra;

        public PalestraViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
