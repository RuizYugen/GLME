package com.example.ruiz.glmev1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private final int PERMISSIONS_REQUEST_READ_CONTACT = 100;
    private final int PERMISSIONS_REQUEST_SEND_SMS=99;
    private final int PERMISSIONS_REQUEST_RECEIVE_SMS=98;
    private final int PERMISSIONS_REQUEST_READ_SMS=97;
    private final int PERMISSIONS_REQUEST_READ_STATE_PHONE=96;
    private final int PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS=95;
    private final int PERMISSIONS_REQUEST_CALL_PHONE=94;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new
                            String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACT
            );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new
                            String[]{Manifest.permission.SEND_SMS},
                    PERMISSIONS_REQUEST_SEND_SMS
            );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new
                            String[]{Manifest.permission.RECEIVE_SMS},
                    PERMISSIONS_REQUEST_RECEIVE_SMS
            );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_SMS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new
                            String[]{Manifest.permission.READ_SMS},
                    PERMISSIONS_REQUEST_READ_SMS
            );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new
                            String[]{Manifest.permission.READ_PHONE_STATE},
                    PERMISSIONS_REQUEST_READ_STATE_PHONE
            );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.PROCESS_OUTGOING_CALLS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new
                            String[]{Manifest.permission.PROCESS_OUTGOING_CALLS},
                    PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS
            );
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new
                            String[]{Manifest.permission.CALL_PHONE},
                    PERMISSIONS_REQUEST_CALL_PHONE
            );
        }
        recyclerView=(RecyclerView)findViewById(R.id.rclrContactos);
        ContactosAdapter adapter=new ContactosAdapter(this);
        LinearLayoutManager  linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_READ_CONTACT:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    return;
                } else {
                    finish();
                }
                return;
            case PERMISSIONS_REQUEST_SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    return;
                } else {
                    finish();
                }
                return;
            case PERMISSIONS_REQUEST_RECEIVE_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    return;
                } else {
                    finish();
                }
                return;
            case PERMISSIONS_REQUEST_READ_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    return;
                } else {
                    finish();
                }
                return;
            case PERMISSIONS_REQUEST_READ_STATE_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    return;
                } else {
                    finish();
                }
                return;
            case PERMISSIONS_REQUEST_PROCESS_OUTGOING_CALLS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    return;
                } else {
                    finish();
                }
                return;
            case PERMISSIONS_REQUEST_CALL_PHONE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    return;
                } else {
                    finish();
                }
                return;
        }
    }
}
