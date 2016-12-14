package com.example.diana.formulario1;

import android.content.ContentValues;
import android.database.Cursor;

import  java.util.UUID;

import com.example.diana.formulario1.EsquemaFormulario.FormularioEntry;
/**
 * Entidad formulario
 */

public class Formulario1 {
    private String id;
    private String name;
    private String colorfav;
    private String animalfav;
    private String cancionfav;
    private String edad;


    public Formulario1(String name,
                  String colorfav, String animalfav,
                  String edad , String cancionfav) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.colorfav = colorfav;
        this.animalfav = animalfav;
        this.edad = edad;
        this.cancionfav = cancionfav;

    }

    public Formulario1(Cursor cursor) {
        id = cursor.getString(cursor.getColumnIndex(FormularioEntry.ID));
        name = cursor.getString(cursor.getColumnIndex(FormularioEntry.NAME));
        colorfav = cursor.getString(cursor.getColumnIndex(FormularioEntry.COLOFAV));
        animalfav = cursor.getString(cursor.getColumnIndex(FormularioEntry.ANIMALFAV));
        edad = cursor.getString(cursor.getColumnIndex(FormularioEntry.EDAD));
        cancionfav = cursor.getString(cursor.getColumnIndex(FormularioEntry.CANCIONFAV));
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(FormularioEntry.ID, id);
        values.put(FormularioEntry.NAME, name);
        values.put(FormularioEntry.COLOFAV, colorfav);
        values.put(FormularioEntry.ANIMALFAV, animalfav);
        values.put(FormularioEntry.EDAD, edad);
        values.put(FormularioEntry.CANCIONFAV, cancionfav);

        return values;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColorfav() {
        return colorfav;
    }

    public String getAnimalfav() {
        return animalfav;
    }

    public String getEdad() {
        return edad;
    }

    public String getCancionfav() {
        return cancionfav;
    }

}
