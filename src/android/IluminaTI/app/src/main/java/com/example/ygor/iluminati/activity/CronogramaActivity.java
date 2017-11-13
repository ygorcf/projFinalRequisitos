package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.model.Palestra;
import com.example.ygor.iluminati.tasks.BaseTask;
import com.example.ygor.iluminati.tasks.CompleteListener;
import com.example.ygor.iluminati.tasks.CronogramaResponse;
import com.example.ygor.iluminati.tasks.GetCronogramaTask;
import com.example.ygor.iluminati.tasks.UcbServer;
import com.example.ygor.iluminati.util.ISO8601DateParser;
import com.example.ygor.iluminati.util.Network;
import com.squareup.timessquare.CalendarPickerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CronogramaActivity extends Activity implements CalendarPickerView.OnDateSelectedListener, CompleteListener<CronogramaResponse> {

    @BindView(R.id.calendar)
    CalendarPickerView calendario;

    @BindView(R.id.tvTituloDia)
    TextView tituloDia;

    @BindView(R.id.tvDadosDia)
    TextView dadosDia;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    ArrayList<Palestra> palestras = new ArrayList<>();
    Palestra palestraSelecionada = null;
    CalendarPickerView.FluentInitializer calendarInitializer;
    boolean loaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma);

        GetCronogramaTask task = new GetCronogramaTask(this, this);
        task.execute("http://192.168.15.8:3000/api/v1/");
        ButterKnife.bind(this);

        Calendar hoje = Calendar.getInstance();

        Calendar maior = Calendar.getInstance();
        maior.add(Calendar.YEAR, 1);


        calendario.setOnDateSelectedListener(this);
        calendarInitializer = calendario.init(hoje.getTime(), maior.getTime());
        calendarInitializer.inMode(CalendarPickerView.SelectionMode.SINGLE);
        calendarInitializer.withSelectedDate(hoje.getTime());
    }

    @Override
    public void onDateSelected(Date date) {
        if (loaded) {
            tituloDia.setText(dateFormat.format(date));
            for (Palestra p : palestras) {
                if (dateFormat.format(p.getData()).equals(dateFormat.format(date))) {
                    palestraSelecionada = p;
                    String dados = "Palestra: " + p.getNome();
                    dadosDia.setText(dados);
                }
            }
        }
    }

    @Override
    public void onDateUnselected(Date date) {
        palestraSelecionada = null;
        dadosDia.setText("");
        tituloDia.setText("");
    }

    @Override
    public void onComplete(CronogramaResponse result) {
        if (result != null) {
            Palestra p;
            Date maiorData = null;
            try {
                for (CronogramaResponse.CronogramaPalestraResponse palestra : result.getData()) {
                    p = new Palestra();
                    p.setData(new Date(palestra.getDia()));
                    p.setNome(palestra.getNome());
                    p.setHorario(palestra.getHorario());
                    p.getAlunos().addAll(palestra.getAlunosCheckin());
                    palestras.add(p);

                    if (maiorData == null || p.getData().after(maiorData)) {
                        maiorData = p.getData();
                    }
                    calendarInitializer.withHighlightedDate(p.getData());
                }
                loaded = true;
            } catch (Exception e) {
                Toast.makeText(this, "Falha ao obter os dados do cronograma. Tente novamente.", Toast.LENGTH_SHORT).show();
                Log.e(this.getClass().getName(), e.getMessage());
                finish();
            }
        } else {
            Toast.makeText(this, "Falha ao obter os dados do cronograma. Tente novamente.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
