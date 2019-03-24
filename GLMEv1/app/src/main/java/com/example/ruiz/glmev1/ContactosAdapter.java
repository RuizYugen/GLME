package com.example.ruiz.glmev1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Contacto> contactos;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView nombre;
        Button enviarSMS;
        public ViewHolder(View itemView) {
            super(itemView);
            nombre=(TextView) itemView.findViewById(R.id.lblNombreItem);
            enviarSMS=(Button)itemView.findViewById(R.id.btnMensaje);

        }
    }

    public ContactosAdapter(Context context){
        this.context=context;
        contactos=traerContactos();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contacto_item,viewGroup,false);

        ViewHolder viewHolder=new ViewHolder(itemView);
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
        final int indice=i;
        viewHolder.enviarSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Mensajeria.class);
                intent.putExtra("numero",contactos.get(indice).getTelefono());
                ((Activity)context).startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public ArrayList<Contacto> traerContactos(){
        String[] campos={ContactsContract.Data._ID,ContactsContract.Data.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Email.ADDRESS
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
            obj.setEmail(cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS)));
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

}
