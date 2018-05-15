package com.example.kaymo.resolveai;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by kaymo on 28/04/2018.
 */

class ReclamacaoDAO extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ReclamacaoDB";
    private static final String TABELA_RECLAMACAO = "reclamacoes";
    private static final String ID = "id";
    private static final String CATEGORIA = "categoria";
    private static final String DESCRICAO = "descricao";
    private static final String CURTIR = "curtir";
    private static final String NAOCURTIR = "naocurtir";
    private static final String[] COLUNAS = {ID, CATEGORIA, DESCRICAO, CURTIR, NAOCURTIR};

    public ReclamacaoDAO(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void addReclamacao(Reclamacao reclamacao) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATEGORIA, reclamacao.getCategoria());
        values.put(DESCRICAO, reclamacao.getDescricao());
        values.put(CURTIR, reclamacao.getCurtir());
        values.put(NAOCURTIR, reclamacao.getNaoCurtir());

        db.insert(TABELA_RECLAMACAO, null, values);
        db.close();
    }

    public Reclamacao getReclamacao(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_RECLAMACAO,
                COLUNAS,
                " id = ?",
                new String[] { String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Reclamacao reclamacao = cursorToReclamacao(cursor);
            return reclamacao;

        }
    }

    private static Reclamacao cursorToReclamacao(Cursor cursor) {
        Reclamacao reclamacao = new Reclamacao();
        reclamacao.setId(Integer.parseInt(cursor.getString(0)));
        reclamacao.setCategoria(cursor.getString(1));
        reclamacao.setDescricao(cursor.getString(2));
        reclamacao.setCurtir(Integer.parseInt(cursor.getString(3)));
        reclamacao.setNaoCurtir((Integer.parseInt(cursor.getString(4))));

        return reclamacao;
    }

    public ArrayList<Reclamacao> getAllReclamacao() {
        ArrayList<Reclamacao> listaReclamacao = new ArrayList<>();

        String query = "SELECT * FROM " + TABELA_RECLAMACAO + " ORDER BY " + CURTIR + " DESC, " + NAOCURTIR + " ASC ";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Reclamacao reclamacao = cursorToReclamacao(cursor);
                listaReclamacao.add(reclamacao);
            } while (cursor.moveToNext());
        }

        return listaReclamacao;
    }

    public int updateReclamacao(Reclamacao reclamacao) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CURTIR, reclamacao.getCurtir());
        values.put(NAOCURTIR, reclamacao.getNaoCurtir());

        int i = db.update(TABELA_RECLAMACAO,
                values,
                ID + " = ?",
                new String[] { String.valueOf(reclamacao.getId())});

        db.close();
        return i;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE reclamacoes (" +
                "id     INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "categoria  TEXT,"+
                "descricao  TEXT,"+
                "curtir     INTEGER,"+
                "naocurtir  INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS reclamacoes");
        this.onCreate(db);
    }
}
