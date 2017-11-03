package com.example.ygor.iluminati;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CronogramaActivity extends Activity implements CalendarPickerView.OnDateSelectedListener {

    @BindView(R.id.calendar)
    CalendarPickerView calendario;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronograma);
        ButterKnife.bind(this);

        Calendar proxAno = Calendar.getInstance();
        proxAno.add(Calendar.YEAR, 1);

        Date hoje = new Date();

        calendario.init(hoje, proxAno.getTime()).inMode(CalendarPickerView.SelectionMode.SINGLE);
        calendario.setOnDateSelectedListener(this);
    }

    @Override
    public void onDateSelected(Date date) {
        Toast.makeText(this, dateFormat.format(date), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDateUnselected(Date date) {
        Toast.makeText(this, dateFormat.format(date), Toast.LENGTH_SHORT).show();
    }
}
