package org.frap129.spectrum;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import static org.frap129.spectrum.Utils.getProp;
import static org.frap129.spectrum.Utils.setProp;

public class ScreenReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
            //if (!Utils.getProp().equals("0")) {
                Utils.setProp(0);
            //}
        }
    }

}
