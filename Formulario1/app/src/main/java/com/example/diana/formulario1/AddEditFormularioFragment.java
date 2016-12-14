package com.example.diana.formulario1;

import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.diana.formulario1.R;
import com.example.diana.formulario1.Formulario1;
import com.example.diana.formulario1.FormularioDbHelper;

/**
 * Vista para creación/edición de un abogado
 */
public class AddEditFormularioFragment extends Fragment {
    private static final String ARG_LAWYER_ID = "arg_formulario_id";

    private String mFormularioId;

    private FormularioDbHelper mFormularioDbHelper;

    private FloatingActionButton mSaveButton;
    private TextInputEditText mNameField;
    private TextInputEditText mPhoneNumberField;
    private TextInputEditText mSpecialtyField;
    private TextInputEditText mBioField;
    private TextInputLayout mNameLabel;
    private TextInputLayout mPhoneNumberLabel;
    private TextInputLayout mSpecialtyLabel;
    private TextInputLayout mBioLabel;


    public AddEditFormularioFragment() {
        // Required empty public constructor
    }

    public static AddEditFormularioFragment newInstance(String formularioId) {
        AddEditFormularioFragment fragment = new AddEditFormularioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_LAWYER_ID, formularioId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFormularioId = getArguments().getString(ARG_LAWYER_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_add_edit_lawyer, container, false);

        // Referencias UI
        mSaveButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        mNameField = (TextInputEditText) root.findViewById(R.id.et_name);
        mPhoneNumberField = (TextInputEditText) root.findViewById(R.id.et_phone_number);
        mSpecialtyField = (TextInputEditText) root.findViewById(R.id.et_specialty);
        mBioField = (TextInputEditText) root.findViewById(R.id.et_bio);
        mNameLabel = (TextInputLayout) root.findViewById(R.id.til_name);
        mPhoneNumberLabel = (TextInputLayout) root.findViewById(R.id.til_phone_number);
        mSpecialtyLabel = (TextInputLayout) root.findViewById(R.id.til_specialty);
        mBioLabel = (TextInputLayout) root.findViewById(R.id.til_bio);

        // Eventos
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEditLawyer();
            }
        });

        mFormularioDbHelper = new FormularioDbHelper(getActivity());

        // Carga de datos
        if (mFormularioId != null) {
            loadLawyer();
        }

        return root;
    }

    private void loadLawyer() {
        new GetFormularioByIdTask().execute();
    }

    private void addEditLawyer() {
        boolean error = false;

        String name = mNameField.getText().toString();
        String colorfav = mPhoneNumberField.getText().toString();
        String animalfav = mSpecialtyField.getText().toString();
        String edad = mBioField.getText().toString();

        if (TextUtils.isEmpty(name)) {
            mNameLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(colorfav)) {
            mPhoneNumberLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (TextUtils.isEmpty(animalfav)) {
            mSpecialtyLabel.setError(getString(R.string.field_error));
            error = true;
        }


        if (TextUtils.isEmpty(edad)) {
            mBioLabel.setError(getString(R.string.field_error));
            error = true;
        }

        if (error) {
            return;
        }

        Formulario1 formulario = new Formulario1(name, colorfav, animalfav, edad, "");

        new AddEditFormularioTask().execute(formulario);

    }

    private void showFormularioScreen(Boolean requery) {
        if (!requery) {
            showAddEditError();
            getActivity().setResult(Activity.RESULT_CANCELED);
        } else {
            getActivity().setResult(Activity.RESULT_OK);
        }

        getActivity().finish();
    }

    private void showAddEditError() {
        Toast.makeText(getActivity(),
                "Error al agregar nueva información", Toast.LENGTH_SHORT).show();
    }

    private void showFormulario(Formulario1 formulario) {
        mNameField.setText(formulario.getName());
        mPhoneNumberField.setText(formulario.getColorfav());
        mSpecialtyField.setText(formulario.getAnimalfav());
        mBioField.setText(formulario.getEdad());
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al editar abogado", Toast.LENGTH_SHORT).show();
    }

    private class GetFormularioByIdTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mFormularioDbHelper.getFormularioById(mFormularioId);
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.moveToLast()) {
                showFormulario(new Formulario1(cursor));
            } else {
                showLoadError();
                getActivity().setResult(Activity.RESULT_CANCELED);
                getActivity().finish();
            }
        }

    }

    private class AddEditFormularioTask extends AsyncTask<Formulario1, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Formulario1... formulario) {
            if (mFormularioId != null) {
                return mFormularioDbHelper.updateFormulario(formulario[0], mFormularioId) > 0;

            } else {
                return mFormularioDbHelper.saveFormulario(formulario[0]) > 0;
            }

        }

        @Override
        protected void onPostExecute(Boolean result) {
            showFormularioScreen(result);
        }

    }

}

