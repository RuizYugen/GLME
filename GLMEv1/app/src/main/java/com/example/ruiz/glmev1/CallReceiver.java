package com.example.ruiz.glmev1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras=intent.getExtras();
        if(extras !=null){
            if(extras.getString("state").equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String phoneNumber = extras.getString("incoming_number");
                Toast.makeText(context,"Te esta llamando "+phoneNumber,Toast.LENGTH_SHORT).show();

            }else if(extras.getString("state").equals(TelephonyManager.EXTRA_STATE_IDLE)){
                Toast.makeText(context,"Colgo ",Toast.LENGTH_SHORT).show();

            }else if(extras.getString("state").equals(TelephonyManager.EXTRA_STATE_OFFHOOK)){
                Toast.makeText(context,"Contesto ",Toast.LENGTH_LONG).show();

            }
        }
    }
}
