package com.example.diana.formulario1;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bumptech.glide.Glide;
//import com.glide.request.target.BitmapImageViewTarget;
import com.example.diana.formulario1.R;
import com.example.diana.formulario1.EsquemaFormulario.FormularioEntry;

/**
 * Adaptador de formulario
 */
public class FormularioCursorAdapter extends CursorAdapter {
    public FormularioCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.list_item_formulario, viewGroup, false);
    }

    @Override
    public void bindView(View view, final Context context, Cursor cursor) {

        // Referencias UI.
        TextView nameText = (TextView) view.findViewById(R.id.tv_name);
        //final ImageView avatarImage = (ImageView) view.findViewById(R.id.iv_avatar);

        // Get valores.
        String name = cursor.getString(cursor.getColumnIndex(FormularioEntry.NAME));
        String cancionfav = cursor.getString(cursor.getColumnIndex(FormularioEntry.CANCIONFAV));

        // Setup.
        nameText.setText(name);
       /* Glide
                .with(context)
                .load(Uri.parse("file:///android_asset/" + cancionfav))
                .asBitmap()
                .error(R.drawable.ic_account_circle)
                .centerCrop()
                .into(new BitmapImageViewTarget(avatarImage) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable drawable
                                = RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        drawable.setCircular(true);
                        //avatarImage.setImageDrawable(drawable);
                    }
                });*/

    }

}

