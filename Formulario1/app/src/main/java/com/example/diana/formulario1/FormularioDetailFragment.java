package com.example.diana.formulario1;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.diana.formulario1.R;
import com.example.diana.formulario1.AddEditFormularioActivity;
import com.example.diana.formulario1.Formulario1;
import com.example.diana.formulario1.FormularioDbHelper;
import com.example.diana.formulario1.Formulario1Activity;
import com.example.diana.formulario1.Formulario1Fragment;

/**
 * Vista para el detalle del abogado
 */
public class FormularioDetailFragment extends Fragment {
    private static final String ARG_FORMULARIO_ID = "formularioId";

    private String mFormularioId;

    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mPhoneNumber;
    private TextView mSpecialty;
    private TextView mBio;

    private FormularioDbHelper mFormularioDbHelper;


    public FormularioDetailFragment() {
        // Required empty public constructor
    }

    public static FormularioDetailFragment newInstance(String formularioId) {
        FormularioDetailFragment fragment = new FormularioDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FORMULARIO_ID, formularioId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mFormularioId = getArguments().getString(ARG_FORMULARIO_ID);
        }

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_formulario_detail_fragmente, container, false);
        mCollapsingView = (CollapsingToolbarLayout) getActivity().findViewById(R.id.toolbar_layout);
       // mAvatar = (ImageView) getActivity().findViewById(R.id.iv_avatar);
        mPhoneNumber = (TextView) root.findViewById(R.id.nombre);
        mSpecialty = (TextView) root.findViewById(R.id.colorfav);
        mBio = (TextView) root.findViewById(R.id.edad);

        mFormularioDbHelper = new FormularioDbHelper(getActivity());

        loadLawyer();

        return root;
    }

    private void loadLawyer() {
        new GetFormularioByIdTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_edit:
                showEditScreen();
                break;
            case R.id.action_delete:
                new DeleteFormularioTask().execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Formulario1Fragment.REQUEST_UPDATE_DELETE_FORMULARIO) {
            if (resultCode == Activity.RESULT_OK) {
                getActivity().setResult(Activity.RESULT_OK);
                getActivity().finish();
            }
        }
    }

    private void showFormulario(Formulario1 f) {
        mCollapsingView.setTitle(f.getName());
        /*Glide.with(this)
                .load(Uri.parse("file:///android_asset/" + lawyer.getAvatarUri()))
                .centerCrop()
                .into(mAvatar);
        mPhoneNumber.setText(lawyer.getPhoneNumber());
        mSpecialty.setText(lawyer.getSpecialty());
        mBio.setText(lawyer.getBio());*/
    }

    private void showEditScreen() {
        Intent intent = new Intent(getActivity(), AddEditFormularioActivity.class);
        intent.putExtra(Formulario1Activity.EXTRA_FORMULARIO_ID, mFormularioId);
        startActivityForResult(intent, Formulario1Fragment.REQUEST_UPDATE_DELETE_FORMULARIO);
    }

    private void showFormularioScreen(boolean requery) {
        if (!requery) {
            showDeleteError();
        }
        getActivity().setResult(requery ? Activity.RESULT_OK : Activity.RESULT_CANCELED);
        getActivity().finish();
    }

    private void showLoadError() {
        Toast.makeText(getActivity(),
                "Error al cargar información", Toast.LENGTH_SHORT).show();
    }

    private void showDeleteError() {
        Toast.makeText(getActivity(),
                "Error al eliminar abogado", Toast.LENGTH_SHORT).show();
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
            }
        }

    }

    private class DeleteFormularioTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected Integer doInBackground(Void... voids) {
            return mFormularioDbHelper.deleteFormulario(mFormularioId);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            showFormularioScreen(integer > 0);
        }

    }

}
