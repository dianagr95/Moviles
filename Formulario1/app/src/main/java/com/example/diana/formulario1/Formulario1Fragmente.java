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

       /* import com.example.diana.formulario1.MainActivity.*R;
        import com.example.diana.formulario1.lawyersapp.addeditlawyer.AddEditLawyerActivity;
        import com.herprogramacion.lawyersapp.data.LawyersDbHelper;
        import com.herprogramacion.lawyersapp.lawyerdetail.LawyerDetailActivity;

        import static com.herprogramacion.lawyersapp.data.LawyersContract.LawyerEntry;*/


/**
 * Vista para la lista de abogados del gabinete
 */
public class LawyersFragment extends Fragment {
    public static final int REQUEST_UPDATE_DELETE_FORMULARIO = 2;

    private FormularioDbHelper FormularioDbHelper;

    private ListView mFormularioList;
    private LawyersCursorAdapter mLawyersAdapter;
    private FloatingActionButton mAddButton;


    public LawyersFragment() {
        // Required empty public constructor
    }

    public static LawyersFragment newInstance() {
        return new LawyersFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_formulario1_fragmente, container, false);

        // Referencias UI
        mFormularioList = (ListView) root.findViewById(R.id.formulario_list);
        mLawyersAdapter = new LawyersCursorAdapter(getActivity(), null);
        mAddButton = (FloatingActionButton) getActivity().findViewById(R.id.fab);

        // Setup
        mFormularioList.setAdapter(mLawyersAdapter);

        // Eventos
        mFormularioList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor currentItem = (Cursor) mLawyersAdapter.getItem(i);
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
        mFormularioDbHelper = new FormularioDbHelper(getActivity());

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
        new LawyersLoadTask().execute();
    }

    private void showSuccessfullSavedMessage() {
        Toast.makeText(getActivity(),
                "fORMULARIO guardado correctamente", Toast.LENGTH_SHORT).show();
    }

    private void showAddScreen() {
        Intent intent = new Intent(getActivity(), AddEditLawyerActivity.class);
        startActivityForResult(intent, AddEditLawyerActivity.REQUEST_ADD_LAWYER);
    }

    private void showDetailScreen(String lawyerId) {
        Intent intent = new Intent(getActivity(), LawyerDetailActivity.class);
        intent.putExtra(Formulario1Activity.EXTRA_FORMULARIO_ID, lawyerId);
        startActivityForResult(intent, REQUEST_UPDATE_DELETE_FORMULARIO);
    }

    private class LawyersLoadTask extends AsyncTask<Void, Void, Cursor> {

        @Override
        protected Cursor doInBackground(Void... voids) {
            return mFormularioDbHelper.getAllLawyers();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            if (cursor != null && cursor.getCount() > 0) {
                mLawyersAdapter.swapCursor(cursor);
            } else {
                // Mostrar empty state
            }
        }
    }

}
