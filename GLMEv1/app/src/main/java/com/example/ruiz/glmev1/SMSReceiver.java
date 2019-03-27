package com.example.ruiz.glmev1;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle inteBundle=intent.getExtras();
        if(inteBundle!=null){
            Object[] sms=(Object[])inteBundle.get("pdus");

            for (int i=0; i<sms.length; i++){
                SmsMessage smsMessage=SmsMessage.createFromPdu((byte[])sms[i]);
                String phone=smsMessage.getOriginatingAddress();
                String message=smsMessage.getMessageBody().toString();
                Toast.makeText(context,phone+": "+message,Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(context, MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent= PendingIntent.getActivity(context,0,intent1,0);
                NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(context,"ficha")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("De: "+phone)
                        .setContentText(message)
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);
                int consecutivo=(int)System.currentTimeMillis()/1000;
                notificationManagerCompat.notify(1001,mBuilder.build());
            }

        }
    }
}
