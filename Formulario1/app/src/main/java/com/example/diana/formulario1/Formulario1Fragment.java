package com.example.diana.formulario1;


import android.app.Activity;
        import android.content.Intent;
        import android.database.Cursor;
        import android.os.AsyncTask;
        import android.os.Bundle;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.AdapterView;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.example.diana.formulario1.R;
        import com.example.diana.formulario1.AddEditFormularioActivity;
        import com.example.diana.formulario1.FormularioDbHelper;
        import com.example.diana.formulario1.FormularioDetailActivity;
        import com.example.diana.formulario1.Formulario1Activity;

        import static com.example.diana.formulario1.EsquemaFormulario.FormularioEntry;


/**
 * Vista para la lista de abogados del gabinete
 */
public class Formulario1Fragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_FORMULARIO = 2;

    private FormularioDbHelper mFormulario1DbHelper;

    private ListView mFormularioList;
    private FormularioCursorAdapter mFormuarioAdapter;
    private FloatingActionButton mAddButton;


    public Formulario1Fragment() {
        // Required empty public constructor
    }

    public static Formulario1Fragment newInstance() {
        return new Formulario1Fragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_formulario1_fragmente, container, false);

        // Referencias UI
        mFormularioList = (ListView) root.findViewById(R.id.formulario_list);
        mFormuarioAdapter = new FormularioCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        // Setup
        mFormularioList.setAdapter(mFormuarioAdapter);

        // Eventos
        mFormularioList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mFormuarioAdapter.getItem(i);
                String currentLawyerId = currentItem.getString(
                        currentItem.getColumnIndex(EsquemaFormulario.FormularioEntry.ID));

                showDetailScreen(currentLawyerId);
            }
        });
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddScreen();
            }
        });


        getActivity().deleteDatabase(com.example.diana.formulario1.FormularioDbHelper.DATABASE_NAME);

        // Instancia de helper
        mFormulario1DbHelper = new FormularioDbHelper(getActivity());

        // Carga de datos
        loadLawyers();

        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (Activity.RESULT_OK == resultCode) {
            switch (requestCode) {
                case AddEditLawyerActivity.REQUEST_ADD_FORMULARIO:
                    showSuccessfullSavedMessage();
                    loadLawyers();
                    break;
                case REQUEST_UPDATE_DELETE_FORMULARIO:
                    loadLawyers();
                    break;
            }
        }
    }

    private void loadLawyers() {
        new FormularioLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "fORMULARIO guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditLawyerActivity.class);
        startActivityForResult(intent, AddEditLawyerActivity.REQUEST_ADD_LAWYER);
    }

    private void showDetailScreen(String formularioId) {
        Intent intent = new Intent(getActivity(), FormularioDetailActivity.class);
        intent.putExtra(Formulario1Activity.EXTRA_FORMULARIO_ID, formularioId);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_FORMULARIO);
    }

    private class FormularioLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mFormulario1DbHelper.getAllLawyers();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mFormuarioAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

}
