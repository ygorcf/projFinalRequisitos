package com.example.ygor.iluminati.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ygor.iluminati.R;
import com.example.ygor.iluminati.model.Usuario;
import com.example.ygor.iluminati.network.responses.PalestraResponse;
import com.example.ygor.iluminati.network.task.BaseTask;
import com.example.ygor.iluminati.network.task.CheckInPalestraTask;
import com.example.ygor.iluminati.network.responses.CheckInResponse;
import com.example.ygor.iluminati.util.NotificationHelper;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class MainActivity extends Activity implements BaseTask.CompleteListener<CheckInResponse> {

    private Usuario usuario;
    private int idPalestra;

    @BindView(R.id.tvInfo)
    TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
        ButterKnife.bind(this);

        tvInfo.setText("Olá, " + usuario.getMatricula());
    }

    private void openCronograma() {
        Intent i = new Intent(this, CronogramaActivity.class);
        i.putExtra("usuario", usuario);
        startActivityForResult(i, 100);
    }

    private void openEvento(PalestraResponse palestra) {
        Intent i = new Intent(this, EventoActivity.class);
        i.putExtra("usuario", usuario);
        i.putExtra("idPalestra", idPalestra);
        i.putExtra("palestraResponse", palestra);
        startActivityForResult(i, 100);
    }

    private void readQrCode() {
        IntentIntegrator ii = new IntentIntegrator(MainActivity.this);
        ii.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        ii.setBeepEnabled(false);
        ii.setPrompt("Aproxime o codigo QR");
        ii.initiateScan(IntentIntegrator.QR_CODE_TYPES);
    }

    @OnClick(R.id.btnCheckin)
    public void onCheckin() {
        readQrCode();
    }

    @OnClick(R.id.btnCronograma)
    public void onCronograma() {
        openCronograma();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                try {
                    String urlQr = result.getContents();
                    String resultQr = (urlQr.contains("qrcode/")) ? urlQr.substring((((urlQr.indexOf(0) == '/') ? "/" : "") + "qrcode/").length() + 1) : null;
                    idPalestra = Integer.parseInt(resultQr);
                    CheckInPalestraTask task = new CheckInPalestraTask(this, this);
                    CheckInPalestraTask.CheckinParams params = CheckInPalestraTask.getParams(idPalestra, usuario.getMatricula());
                    task.execute(params);
                } catch (Exception e) {
                    Toast.makeText(this, "Ocorreu um erro ao realizar o checkin.", Toast.LENGTH_LONG).show();
                    Log.e(this.getClass().getName(), e.getMessage());
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onComplete(CheckInResponse result) {
        if (result.getData().isCheckin())
            openEvento(result.getData().getPalestra());
        else
            Toast.makeText(this, result.getData().getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Exception e, Response<CheckInResponse> result) {
        Toast.makeText(this, "Ocorreu um erro ao realizar o checkin.", Toast.LENGTH_LONG).show();
        try {
            Log.e(this.getClass().getName(), (e != null) ? e.getMessage() : result.errorBody().string());
        } catch (IOException e1) {
            Log.e(this.getClass().getName(), e1.getMessage());
        }
    }
}
