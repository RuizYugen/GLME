package com.example.ruiz.glmev1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mensajeria extends AppCompatActivity {
    TextView telefono;
    EditText texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensajeria);
        telefono =findViewById(R.id.lblNumero);
        texto=findViewById(R.id.txtTexto);
        Intent intent =getIntent();
        telefono.setText(intent.getStringExtra("numero"));

    }

    public void Enviar(View v){
        SmsManager smsManager=SmsManager.getDefault();
        smsManager.sendTextMessage(telefono.getText().toString(),null,texto.getText().toString(),null,null);
        Toast.makeText(this, "Enviado", Toast.LENGTH_SHORT).show();
    }
}
