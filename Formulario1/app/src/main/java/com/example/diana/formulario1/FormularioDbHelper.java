package com.example.diana.formulario1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.diana.formulario1.EsquemaFormulario.FormularioEntry;
/**
 * Manejador de la base de datos
 */

public class FormularioDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Formulario1.db";

    public FormularioDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + EsquemaFormulario.FormularioEntry.TABLE_NAME + " ("
                + EsquemaFormulario.FormularioEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + FormularioEntry.ID + " TEXT NOT NULL,"
                + FormularioEntry.NAME + " TEXT NOT NULL,"
                + FormularioEntry.CANCIONFAV + " TEXT NOT NULL,"
                + FormularioEntry.COLOFAV + " TEXT NOT NULL,"
                + FormularioEntry.EDAD + " TEXT NOT NULL,"
                + FormularioEntry.ANIMALFAV + " TEXT,"
                + "UNIQUE (" + FormularioEntry.ID + "))");



        // Insertar datos ficticios para prueba inicial
        mockData(db);

    }

    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockFormulario(sqLiteDatabase, new Formulario1("1", "ab", "ab", "ab.", "ab","3"));
        mockFormulario(sqLiteDatabase, new Formulario1("1", "ab", "ab", "ab.", "ab","3"));
        mockFormulario(sqLiteDatabase,new Formulario1("1", "ab", "ab", "ab.", "ab","3"));
        mockFormulario(sqLiteDatabase, new Formulario1("1", "ab", "ab", "ab.", "ab","3"));
        mockFormulario(sqLiteDatabase, new Formulario1("1", "ab", "ab", "ab.", "ab","3"));
        mockFormulario(sqLiteDatabase, new Formulario1("1", "ab", "ab", "ab.", "ab","3"));
        mockFormulario(sqLiteDatabase, new Formulario1("1", "ab", "ab", "ab.", "ab","3"));
        mockFormulario(sqLiteDatabase, new Formulario1("1", "ab", "ab", "ab.", "ab","3"));
    }

    public long mockFormulario(SQLiteDatabase db, Formulario1 f) {
        return db.insert(
                FormularioEntry.TABLE_NAME,
                null,
                f.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // No hay operaciones
    }

    public long saveFormulario(Formulario1 f) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                FormularioEntry.TABLE_NAME,
                null,
                f.toContentValues());

    }

    public Cursor getAllFormulario() {
        return getReadableDatabase()
                .query(
                        FormularioEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }

    public Cursor getFormularioById(String fId) {
        Cursor c = getReadableDatabase().query(
                FormularioEntry.TABLE_NAME,
                null,
                FormularioEntry.ID + " LIKE ?",
                new String[]{fId},
                null,
                null,
                null);
        return c;
    }

    public int deleteFormulario(String fId) {
        return getWritableDatabase().delete(
                FormularioEntry.TABLE_NAME,
                FormularioEntry.ID + " LIKE ?",
                new String[]{fId});
    }

    public int updateFormulario(Formulario1 f, String fId) {
        return getWritableDatabase().update(
                FormularioEntry.TABLE_NAME,
                f.toContentValues(),
                FormularioEntry.ID + " LIKE ?",
                new String[]{fId}
        );
    }

}
