package org.frap129.spectrum;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class ScreenService extends Service {

    private BroadcastReceiver receiver;

    @Override
    public int onStartCommand(Intent intent, int flag, int startIs) {
        receiver = new ScreenReceiver();
        registerReceiver(receiver, new IntentFilter(Intent.ACTION_SCREEN_OFF));
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        if(receiver != null)
            unregisterReceiver(receiver);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent arg) {
        return null;
    }

}
