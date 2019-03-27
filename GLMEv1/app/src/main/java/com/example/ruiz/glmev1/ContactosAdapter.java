package com.example.ruiz.glmev1;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Contacto> contactos;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre;
        Button enviarSMS;
        Button llamar;
        Button email;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = (TextView) itemView.findViewById(R.id.lblNombreItem);
            enviarSMS = (Button) itemView.findViewById(R.id.btnMensaje);
            llamar = (Button) itemView.findViewById(R.id.btnLlamar);
            email=(Button) itemView.findViewById(R.id.btnEmail);
        }
    }

    public ContactosAdapter(Context context) {
        this.context = context;
        contactos = traerContactos();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contacto_item, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        /*if(contactos!=null){
              if(contactos.moveToPosition(i)){
                  viewHolder.nombre.setText(contactos.getString(contactos.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));
              }else{
                viewHolder.nombre.setText("Mal");
              }
        }*/
        viewHolder.nombre.setText(contactos.get(i).getNombre());
        final int indice = i;
        viewHolder.enviarSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Mensajeria.class);
                intent.putExtra("numero", contactos.get(indice).getTelefono());
                ((Activity) context).startActivity(intent);

            }
        });
        viewHolder.llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + contactos.get(indice).getTelefono()));
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                ((Activity) context).startActivity(intent);

            }
        });
        viewHolder.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] TO = {contactos.get(indice).getEmail()};
                String[] CC = {""};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_CC, CC);
// Esto podrás modificarlo si quieres, el asunto y el cuerpo del mensaje
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Asunto");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Escribe aquí tu mensaje");

                try {
                    ((Activity) context).startActivity(Intent.createChooser(emailIntent, "Enviar email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context,
                            "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public ArrayList<Contacto> traerContactos(){
        String[] campos={ContactsContract.Data._ID,ContactsContract.Data.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.Data.CONTACT_ID
        };
        String clausula =ContactsContract.Data.MIMETYPE+"='"+
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE+"' AND "+
                ContactsContract.CommonDataKinds.Phone.NUMBER+" IS NOT NULL";
        String orden=ContactsContract.Data.DISPLAY_NAME+ " ASC";
        ArrayList<Contacto>lista=new ArrayList<Contacto>();
        Cursor cursor=context.getContentResolver().query(
                ContactsContract.Data.CONTENT_URI,
                campos,
                clausula,
                null,
                orden
        );
        Contacto obj;
        while(cursor.moveToNext()){
            obj=new Contacto();
            obj.setId(cursor.getString(cursor.getColumnIndex(ContactsContract.Data._ID)));
            obj.setNombre(cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)));
            obj.setTelefono(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
            String selectionArgs =
                    ContactsContract.CommonDataKinds.Email.CONTACT_ID + " =?";

            Cursor telefonoCursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                    new String[] {ContactsContract.CommonDataKinds.Email.ADDRESS},
                    selectionArgs,
                    new String[]{cursor.getString(cursor.getColumnIndex(ContactsContract.Data.CONTACT_ID))},
                    null
            );
            String correo="";
            if (telefonoCursor.moveToFirst()){
                correo = telefonoCursor.getString(0);
            }

            telefonoCursor.close();
            obj.setEmail(correo);
            if(lista.size()==0){
                lista.add(obj);
            }else{
                if(!lista.get(lista.size()-1).equals(obj)){
                    lista.add(obj);
                }
            }
        }
        return lista;
    }

    private String getCorreo(Uri uri) {
        String id = null;
        String correo = null;

        Cursor contactoCursor = context.getContentResolver().query(
                uri,
                new String[]{ContactsContract.Contacts._ID},
                null,
                null,
                null
        );

        if (contactoCursor.moveToFirst()){
            id = contactoCursor.getString(0);
        }

        contactoCursor.close();

        String selectionArgs =
                ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ? ";

        Cursor telefonoCursor = context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Email.CONTENT_URI,
                new String[] {ContactsContract.CommonDataKinds.Email.ADDRESS},
                selectionArgs,
                new String[]{id},
                null
        );
        if (telefonoCursor.moveToFirst()){
            correo = telefonoCursor.getString(0);
        }

        telefonoCursor.close();

        return correo;
    }

}
