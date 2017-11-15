package com.example.ygor.iluminati.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ygor on 03/10/2017.
 */

public abstract class GenericDAO<T> {

    private SQLiteOpenHelper helper;
    private SQLiteDatabase database;
    private String nomeTabela;

    public GenericDAO(SQLiteOpenHelper helper, String nomeTabela) {
        this.nomeTabela = nomeTabela;
        this.helper = helper;
    }

    protected long salvar(ContentValues valores) {
        database = helper.getWritableDatabase();
        long ret = database.insert(nomeTabela, null, valores);
        database.close();
        return ret;
    }

    protected Cursor pesquisar(String where, String[] valores) {
        Cursor cursor;
        database = helper.getReadableDatabase();

        cursor = database.query(nomeTabela, null, where, valores, null, null, null);
        if (cursor != null) {
            cursor.moveToNext();
        }
        database.close();
        return cursor;
    }

    protected <K> K pesquisar(String where, String[] valores, TransformCursorListener<K> transformCursorListener) {
        Cursor cursor = pesquisar(where, valores);
        K item = null;
        while (!cursor.isAfterLast()) {
            item = transformCursorListener.getFromCursor(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return item;
    }

    protected long atualizar(String where, String[] valores, ContentValues contentValues) {
        database = helper.getWritableDatabase();
        long ret = database.update(nomeTabela, contentValues, where, valores);
        database.close();
        return ret;
    }

    public Cursor listarCursor() {
        Cursor cursor;
        database = helper.getReadableDatabase();

        cursor = database.query(nomeTabela, null, null, null, null, null, null);
        if (cursor != null)
            cursor.moveToNext();
        database.close();
        return cursor;
    }

    protected <K> List<K> listar(TransformCursorListener<K> transformCursorListener) {
        Cursor cursor = listarCursor();
        List<K> lista = new ArrayList<K>();
        K item = null;
        while (!cursor.isAfterLast()) {
            item = transformCursorListener.getFromCursor(cursor);
            cursor.moveToNext();
        }
        cursor.close();
        return lista;
    }

    protected long deletar(String where, String[] valores) {
        database = helper.getWritableDatabase();
        long ret = database.delete(nomeTabela, where, valores);
        database.close();
        return ret;
    }

    public abstract long salvar(T t);
    public abstract long atualizar(T t);
    public abstract long deletar(long id);
    public abstract T pesquisar(long id);
    public abstract List<T> listar();

    protected interface TransformCursorListener<T> {
        T getFromCursor(Cursor cursor);
    }

}
