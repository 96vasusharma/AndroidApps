package com.example.vasusharma.recievebroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class RecieveBroadcast extends BroadcastReceiver {
    public RecieveBroadcast() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"yeah hey ,  Broadcast recieved !",Toast.LENGTH_LONG).show();
    }
}
