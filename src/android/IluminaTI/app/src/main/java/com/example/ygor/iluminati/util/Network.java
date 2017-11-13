package com.example.ygor.iluminati.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Ygor on 07/11/2017.
 */

public class Network {

    public static String sendGet(String url) {
        String resultado = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL endPoint = new URL(url);
            httpURLConnection = (HttpURLConnection) endPoint.openConnection();
            httpURLConnection.setRequestMethod("get");
            httpURLConnection.setConnectTimeout(2000);
            httpURLConnection.setReadTimeout(2000);

            InputStream inputStream = httpURLConnection.getInputStream();
            resultado = streamToString(inputStream);
        } catch (Exception e) {
            resultado = streamToString(httpURLConnection.getErrorStream());
        }
        return resultado;
    }

    private static String streamToString(InputStream inputStream) {
        StringBuilder strb = new StringBuilder();
        BufferedReader buff = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String linha;
            while ((linha = buff.readLine()) != null) {
                strb.append(linha);
            }
        } catch (Exception e) {

        }
        return strb.toString();
    }

}
