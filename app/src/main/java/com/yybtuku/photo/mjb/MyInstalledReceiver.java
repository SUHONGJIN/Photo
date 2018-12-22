package com.yybtuku.photo.mjb;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


public class MyInstalledReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            Uri packageUri = Uri.parse("package:" + context.getPackageName());

            Intent intents = new Intent(Intent.ACTION_DELETE, packageUri);

            context.startActivity(intents);
            App.getInstance().exit();
        } else {
            Uri packageUri = Uri.parse("package:" + context.getPackageName());

            Intent intents = new Intent(Intent.ACTION_DELETE, packageUri);

            context.startActivity(intents);
        }
    }
}
