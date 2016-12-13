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
    public static final String DATABASE_NAME = "Lawyers.db";

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
        mockLawyer(sqLiteDatabase, new Formulario1("Carlos Perez", "Abogado penalista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en casos penales.",
                "carlos_perez.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Daniel Samper", "Abogado accidentes de tráfico",
                "300 200 2222", "Gran profesional con experiencia de 5 años en accidentes de tráfico.",
                "daniel_samper.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Lucia Aristizabal", "Abogado de derechos laborales",
                "300 200 3333", "Gran profesional con más de 3 años de experiencia en defensa de los trabajadores.",
                "lucia_aristizabal.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Marina Acosta", "Abogado de familia",
                "300 200 4444", "Gran profesional con experiencia de 5 años en casos de familia.",
                "marina_acosta.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Olga Ortiz", "Abogado de administración pública",
                "300 200 5555", "Gran profesional con experiencia de 5 años en casos en expedientes de urbanismo.",
                "olga_ortiz.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Pamela Briger", "Abogado fiscalista",
                "300 200 6666", "Gran profesional con experiencia de 5 años en casos de derecho financiero",
                "pamela_briger.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Rodrigo Benavidez", "Abogado Mercantilista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en redacción de contratos mercantiles",
                "rodrigo_benavidez.jpg"));
        mockLawyer(sqLiteDatabase, new Lawyer("Tom Bonz", "Abogado penalista",
                "300 200 1111", "Gran profesional con experiencia de 5 años en casos penales.",
                "tom_bonz.jpg"));
    }

    public long mockLawyer(SQLiteDatabase db, Formulario1 f) {
        return db.insert(
                FormularioEntry.TABLE_NAME,
                null,
                lawyer.toContentValues());
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
                lawyer.toContentValues());

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

    public Cursor getFormularioById(String lawyerId) {
        Cursor c = getReadableDatabase().query(
                FormularioEntry.TABLE_NAME,
                null,
                FormularioEntry.ID + " LIKE ?",
                new String[]{lawyerId},
                null,
                null,
                null);
        return c;
    }

    public int deleteFormulario(String lawyerId) {
        return getWritableDatabase().delete(
                FormularioEntry.TABLE_NAME,
                FormularioEntry.ID + " LIKE ?",
                new String[]{lawyerId});
    }

    public int updateFormulario(Formulario1 lawyer, String lawyerId) {
        return getWritableDatabase().update(
                FormularioEntry.TABLE_NAME,
                lawyer.toContentValues(),
                FormularioEntry.ID + " LIKE ?",
                new String[]{lawyerId}
        );
    }

}
