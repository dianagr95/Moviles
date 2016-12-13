package com.example.diana.formulario1;

import android.provider.BaseColumns;
/**
 * Esquema de la base de datos.
 */

public class EsquemaFormulario {
    public static abstract class FormularioEntry implements BaseColumns{
        public static final String TABLE_NAME ="lawyer";

        public static final String ID = "id";
        public static final String NAME = "nombre";
        public static final String COLOFAV = "colorfav";
        public static final String ANIMALFAV = "animalfav";
        public static final String CANCIONFAV = "cancionfav";
        public static final String EDAD = "edad";
    }
}
