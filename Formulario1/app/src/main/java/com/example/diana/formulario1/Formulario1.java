package com.example.diana.formulario1;

import android.content.ContentValues;

import com.example.diana.formulario1.EsquemaFormulario.FormularioEntry;
/**
 * Entidad formulario
 */

public class Formulario1 {
    private String id;
    private String nombre;
    private String colorfav;
    private String animalfav;
    private String cancionfav;
    private String   edad ;

    public Formulario1(String id, String nombre, String colorfav, String animalfav, String cancionfav, String edad){

        this.id = id;
        this.nombre = nombre;
        this.colorfav = colorfav;
        this.animalfav = animalfav;
        this.cancionfav = cancionfav;
        this.edad = edad;


    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(FormularioEntry.ID, id);
        values.put(FormularioEntry.NAME, nombre);
        values.put(FormularioEntry.CANCIONFAV, cancionfav);
        values.put(FormularioEntry.COLOFAV, colorfav);
        values.put(FormularioEntry.EDAD, edad);
        values.put(FormularioEntry.ANIMALFAV, animalfav);
        return values;
    }

    public String getId(){

        return id;
    }
    public  String getNombre(){

        return nombre;

    }
     public String getColorfav(){
         return  colorfav;

     }
    public String getAnimalfav(){

        return  animalfav;
    }
     public String getCancionfav(){

         return  cancionfav;
     }
    public String getEdad(){

        return edad;
    }


}
