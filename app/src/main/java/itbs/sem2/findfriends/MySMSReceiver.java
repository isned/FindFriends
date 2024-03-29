package itbs.sem2.findfriends;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MySMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        //lancer un toast

        String messageBody,phoneNumber;
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED"))
        {
            Bundle bundle =intent.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if (messages.length > -1) {
                    messageBody = messages[0].getMessageBody();
                    phoneNumber = messages[0].getDisplayOriginatingAddress();

                    Toast.makeText(context,
                                    "Message : "+messageBody+"Reçu de la part de;"+ phoneNumber,
                                    Toast.LENGTH_LONG )
                            .show();

                    if (messageBody.equalsIgnoreCase("FindMe:envoyer moi votre position")){
                        //recuperer la position
                        // GPS =>lancer un service =>activité sans interface

                        Intent i = new Intent(context,MyLocationService.class);
                        i.putExtra("PHONE",phoneNumber);
                        context.startService(i);

                    }
                    if (messageBody.contains("FindMe:ma position est")){
                        String [] t=messageBody.split("#");
                        String longitude = t[1];
                        String latitude =t[2];
                        //lancer une notification
                        

                    }

                }
            }
        }

    }
}