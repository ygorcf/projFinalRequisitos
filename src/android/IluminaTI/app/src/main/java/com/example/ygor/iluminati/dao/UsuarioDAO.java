package com.example.ygor.iluminati.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ygor.iluminati.model.Usuario;

import java.util.List;

/**
 * Created by Ygor on 14/11/2017.
 */

public class UsuarioDAO extends GenericDAO<Usuario> implements GenericDAO.TransformCursorListener<Usuario> {

    private static final String COL_ID = "_id";
    private static final String COL_MATRICULA = "matricula";
    private static final String COL_SENHA = "senha";
    private static final String COL_SERVIDOR = "servidor";

    public UsuarioDAO(SQLiteOpenHelper helper) {
        super(helper, "usuario");
    }

    @Override
    public long salvar(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MATRICULA, usuario.getMatricula());
        contentValues.put(COL_SENHA, usuario.getSenha());
        contentValues.put(COL_SERVIDOR, usuario.getServidor());
        return salvar(contentValues);
    }

    @Override
    public long atualizar(Usuario usuario) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_MATRICULA, usuario.getMatricula());
        contentValues.put(COL_SENHA, usuario.getSenha());
        contentValues.put(COL_SERVIDOR, usuario.getServidor());
        return atualizar(COL_ID + "=?", new String[]{String.valueOf(usuario.getId())}, contentValues);
    }

    @Override
    public long deletar(long id) {
        return deletar(COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public long deletarTodos() {
        return deletar(COL_ID, new String[]{""});
    }

    @Override
    public Usuario pesquisar(long id) {
        return pesquisar(COL_ID + "=?", new String[]{String.valueOf(id)}, this);
    }

    @Override
    public List<Usuario> listar() {
        return listar(this);
    }

    @Override
    public Usuario getFromCursor(Cursor cursor) {
        Usuario usuario = new Usuario();
        usuario.setId(cursor.getLong(cursor.getColumnIndex(COL_ID)));
        usuario.setMatricula(cursor.getString(cursor.getColumnIndex(COL_MATRICULA)));
        usuario.setSenha(cursor.getString(cursor.getColumnIndex(COL_SENHA)));
        usuario.setServidor(cursor.getString(cursor.getColumnIndex(COL_SERVIDOR)));
        return usuario;
    }
}
