package org.frap129.spectrum;

import android.annotation.TargetApi;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import static org.frap129.spectrum.Utils.getProp;
import static org.frap129.spectrum.Utils.setProp;

@TargetApi(Build.VERSION_CODES.N)
public class ProfileTile extends TileService {

    private int curProf = -1;

    @Override
    public void onStartListening() {
        updateTile("");
    }

    @Override
    public void onClick() {
        int newProf = curProf + 1;
        if (newProf > 2) {
            newProf = 0;
        }
        Utils.setProp(newProf);
        updateTile(Integer.toString(newProf));
    }

    private void updateTile(String newProp) {
        Tile tile = this.getQsTile();
        Icon newIcon;
        String newLabel;
        String curProp;
        if (newProp.isEmpty()) {
            curProp = Utils.getProp();
        } else {
            curProp = newProp;
        }
        int oldProf = curProf;
        if (curProp.contains("2")) {
            curProf = 2;
            newLabel = getString(R.string.prof2);
            newIcon = Icon.createWithResource(getApplicationContext(), R.drawable.battery);
        } else if (curProp.contains("1")) {
            curProf = 1;
            newLabel = getString(R.string.prof1);
            newIcon = Icon.createWithResource(getApplicationContext(), R.drawable.rocket);
        } else {
            curProf = 0;
            newLabel = getString(R.string.prof0);
            newIcon = Icon.createWithResource(getApplicationContext(), R.drawable.atom);
        }
        if (oldProf != curProf) {
            tile.setLabel(newLabel);
            tile.setIcon(newIcon);
            tile.updateTile();
        }
    }

}
