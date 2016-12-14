package com.example.diana.formulario1;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.diana.formulario1.R;
import com.example.diana.formulario1.Formulario1Activity;

public class AddEditFormularioActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_FORMULARIO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_formulario);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String lawyerId = getIntent().getStringExtra(Formulario1Activity.EXTRA_FORMULARIO_ID);

        setTitle(lawyerId == null ? "Añadir formulario" : "Editar formulario");

        AddEditFormularioFragment addEditFormularioFragment = (AddEditFormularioFragment)
                getSupportFragmentManager().findFragmentById(R.id.add_edit_formulario_container);
        if (addEditFormularioFragment == null) {
            addEditFormularioFragment = AddEditFormularioFragment.newInstance(lawyerId);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.add_edit_formulario_container, addEditFormularioFragment)
                    .commit();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
