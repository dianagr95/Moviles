package com.example.diana.formulario1;

/**
 * Created by BIBLIO_REP on 12/12/2016.
 */

public class Formulario1 {
    private String id;
    private String nombre;
    private String colorfav;
    private String animalfav;
    private String cancionfav;
    private int   edad ;

    public Formulario1(String id, String nombre, String colorfav, String animalfav, String cancionfav, int edad){

        this.id = id;
        this.nombre = nombre;
        this.colorfav = colorfav;
        this.animalfav = animalfav;
        this.cancionfav = cancionfav;
        this.edad = edad;


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
    public int getEdad(){

        return edad;
    }


}
