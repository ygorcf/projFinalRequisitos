package com.example.ygor.iluminati.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ygor on 03/10/2017.
 */

public class Database extends SQLiteOpenHelper {

    public static final String NOME_DATABASE = "iluminati.dn";
    public static final int VERSAO = 1;

    public Database(Context context) {
        super(context, NOME_DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE usuario(_id INTEGER PRIMARY KEY AUTOINCREMENT, matricula TEXT, senha TEXT, servidor TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS usuario;";
        db.execSQL(sql);
    }
}
