package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.activity.adapter.PalestrasAdapter;
import com.example.ygor.iluminati.model.Palestra;
import com.example.ygor.iluminati.network.task.BaseTask;
import com.example.ygor.iluminati.network.responses.CronogramaResponse;
import com.example.ygor.iluminati.network.task.GetCronogramaTask;
import com.example.ygor.iluminati.network.responses.PalestraResponse;
import com.example.ygor.iluminati.util.NotificationHelper;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class CronogramaActivity extends Activity implements CalendarPickerView.OnDateSelectedListener, BaseTask.CompleteListener<CronogramaResponse> {

    @BindView(R.id.calendar)
    CalendarPickerView calendario;

    @BindView(R.id.tvTituloDia)
    TextView tituloDia;

    @BindView(R.id.listaPalestras)
    ListView listaPalestras;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Map<String, List<Palestra>> palestras = null;
    Palestra palestraSelecionada = null;
    PalestrasAdapter adapter = null;
    List<Palestra> palestrasLista = null;
    CalendarPickerView.FluentInitializer calendarInitializer;
    boolean loaded = false;
    boolean notify = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma);
        notify = getIntent().getBooleanExtra("notify", true);
        palestras = new HashMap<>();

        GetCronogramaTask task = new GetCronogramaTask(this, this);
        task.execute("http://192.168.15.8:3000/api/v1/");
        ButterKnife.bind(this);

        Calendar hoje = Calendar.getInstance();

        Calendar maior = Calendar.getInstance();
        maior.add(Calendar.YEAR, 1);

        palestrasLista = new ArrayList<>();

        adapter = new PalestrasAdapter(this, palestrasLista);

        listaPalestras.setAdapter(adapter);

        calendario.setOnDateSelectedListener(this);
        calendarInitializer = calendario.init(hoje.getTime(), maior.getTime());
        calendarInitializer.inMode(CalendarPickerView.SelectionMode.SINGLE);
        calendarInitializer.withSelectedDate(hoje.getTime());
    }

    @Override
    public void onDateSelected(Date date) {
        if (loaded) {
            tituloDia.setText(dateFormat.format(date));
            List<Palestra> palestrasDia = palestras.get(dateFormat.format(date));

            palestrasLista.clear();
            palestrasLista.addAll(palestrasDia);
            adapter.notifyDataSetChanged();

        }
    }

    @Override
    public void onDateUnselected(Date date) {
        palestraSelecionada = null;
        tituloDia.setText("");
    }

    @Override
    public void onComplete(CronogramaResponse result) {
        if (result != null) {
            Palestra p;
            Date maiorData = null;
            String dia;
            StringBuilder strbNotificacao = new StringBuilder();
            try {
                for (PalestraResponse palestra : result.getData()) {
                    p = new Palestra();
                    p.setData(new Date(palestra.getDia()));
                    p.setNome(palestra.getNome());
                    p.setHorario(palestra.getHorario());
                    p.getAlunos().addAll(palestra.getAlunosCheckin());

                    dia = dateFormat.format(p.getData());

                    if (!palestras.containsKey(dia))
                        palestras.put(dia, new ArrayList<Palestra>());

                    palestras.get(dia).add(p);

                    strbNotificacao.append(p.getNome())
                            .append(" - ")
                            .append(dateFormat.format(p.getData()))
                            .append(" ")
                            .append(p.getHorario())
                            .append("\n");

                    if (maiorData == null || p.getData().after(maiorData)) {
                        maiorData = p.getData();
                    }
                    calendarInitializer.withHighlightedDate(p.getData());
                }
                String titulo = "IluminaTI - Evento";
                String msg = "IluminaTI - " + result.getData().size() + " evento";
                if (result.getData().size() > 1) {
                    titulo += "s";
                    msg += "s";
                }

                Calendar calendar = Calendar.getInstance();

                if (notify)
                    NotificationHelper.newNotification(this,
                                                        titulo,
                                                        titulo,
                                                        msg,
                                                        strbNotificacao,
                                                        calendar.getTimeInMillis(),
                                                        this.getClass());

                Calendar hoje = Calendar.getInstance();
                List<Palestra> palestrasDia = palestras.get(dateFormat.format(hoje.getTime()));

                palestrasLista.clear();
                palestrasLista.addAll(palestrasDia);
                adapter.notifyDataSetChanged();

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

    @Override
    public void onError(Exception e, Response<CronogramaResponse> result) {
        Toast.makeText(this, "Falha ao obter os dados do cronograma. Tente novamente.", Toast.LENGTH_SHORT).show();
        Log.e(this.getClass().getName(), e.getMessage());
        finish();
    }
}
