package org.frap129.spectrum;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.service.quicksettings.Tile;
import android.service.quicksettings.TileService;

import com.ruesga.preferences.MultiProcessSharedPreferencesProvider;

import java.util.ArrayList;
import java.util.Arrays;

@TargetApi(Build.VERSION_CODES.N)
public class ProfileTile extends TileService {

    @Override
    public void onStartListening() {
        updateTile();
    }

    @Override
    public void onClick() {
        setProfile();
    }

    private void setProfile() {
        MultiProcessSharedPreferencesProvider.MultiProcessSharedPreferences profile =
                MultiProcessSharedPreferencesProvider.getSharedPreferences(ProfileTile.this, "profile");
        SharedPreferences.Editor editor = profile.edit();
        String curProfile = profile.getString("profile", "");

        // Set profile and update tile
        if (curProfile.contains("performance")) {
            Utils.setProfile(2);
            editor.putString("profile", "battery");
            editor.apply();
        } else if (curProfile.contains("balanced")) {
            Utils.setProfile(1);
            editor.putString("profile", "performance");
            editor.apply();
        } else {
            Utils.setProfile(0);
            editor.putString("profile", "balanced");
            editor.apply();
        }

        updateTile();
    }

    private void updateTile() {
        String profile = MultiProcessSharedPreferencesProvider
                .getSharedPreferences(getApplicationContext(), "profile")
                .getString("profile", "");
        Tile tile = this.getQsTile();
        Icon newIcon;
        String newLabel;
        int newState = Tile.STATE_ACTIVE;

        // Update tile
        if (profile.contains("battery")) {
            newLabel = getString(R.string.prof2);
            newIcon = Icon.createWithResource(getApplicationContext(), R.drawable.battery);
        } else if (profile.contains("performance")) {
            newLabel = getString(R.string.prof1);
            newIcon = Icon.createWithResource(getApplicationContext(), R.drawable.rocket);
        } else if (profile.contains("balanced")) {
            newLabel = getString(R.string.prof0);
            newIcon = Icon.createWithResource(getApplicationContext(), R.drawable.atom);
        } else {
            newLabel = "Custom";
            newIcon = Icon.createWithResource(getApplicationContext(), R.drawable.ic_mono);
        }

        // Change the UI of the tile.
        tile.setLabel(newLabel);
        tile.setIcon(newIcon);
        tile.setState(newState);
        tile.updateTile();
    }
}
