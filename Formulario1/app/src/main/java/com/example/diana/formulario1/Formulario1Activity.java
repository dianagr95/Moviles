package com.example.diana.formulario1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Formulario1Activity extends AppCompatActivity {

    public static final String EXTRA_FORMULARIO_ID = "extra_formulario_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Formulario1Fragment fragment = (Formulario1Fragment)
                getSupportFragmentManager().findFragmentById(R.id.formulario_container);

        if (fragment == null) {
            fragment = Formulario1Fragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.formulario_container, fragment)
                    .commit();
        }
    }

}
