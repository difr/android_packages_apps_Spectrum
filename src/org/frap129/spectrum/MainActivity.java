package org.frap129.spectrum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import java.util.List;
import java.util.Objects;

import static org.frap129.spectrum.Utils.checkSupport;
import static org.frap129.spectrum.Utils.checkSU;
import static org.frap129.spectrum.Utils.getProp;
import static org.frap129.spectrum.Utils.setProp;

public class MainActivity extends AppCompatActivity {

    private CardView oldCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Define existing CardViews
        final CardView card0 = (CardView) findViewById(R.id.card0);
        final CardView card1 = (CardView) findViewById(R.id.card1);
        final CardView card2 = (CardView) findViewById(R.id.card2);
        final int balColor = ContextCompat.getColor(this, R.color.colorBalance);
        final int perColor = ContextCompat.getColor(this, R.color.colorPerformance);
        final int batColor = ContextCompat.getColor(this, R.color.colorBattery);

        // Check for Spectrum Support
        if (!Utils.checkSupport(this)) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.no_spectrum_support_dialog_title))
                    .setMessage(getString(R.string.no_spectrum_support_dialog_message))
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    })
                    .show();
            return;
        }

        // Ensure root access
        if (!Utils.checkSU()) {
            new AlertDialog.Builder(this)
                    .setTitle(getString(R.string.no_root_detected_dialog_title))
                    .setMessage(getString(R.string.no_root_detected_dialog_message))
                    .setCancelable(false)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            finish();
                        }
                    })
                    .show();
            return;
        }

        // Highlight current profile
        initSelected();

        // Set system property on click
        card0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClick(card0, 0, balColor);
            }
        });

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClick(card1, 1, perColor);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardClick(card2, 2, batColor);
            }
        });

    }

    // Method that detects the selected profile on launch
    private void initSelected() {

        String curProp = Utils.getProp();

        if (curProp.contains("0")) {
            CardView card0 = (CardView) findViewById(R.id.card0);
            int balColor = ContextCompat.getColor(this, R.color.colorBalance);
            card0.setCardBackgroundColor(balColor);
            oldCard = card0;
        } else if (curProp.contains("1")) {
            CardView card1 = (CardView) findViewById(R.id.card1);
            int perColor = ContextCompat.getColor(this, R.color.colorPerformance);
            card1.setCardBackgroundColor(perColor);
            oldCard = card1;
        } else if (curProp.contains("2")) {
            CardView card2 = (CardView) findViewById(R.id.card2);
            int batColor = ContextCompat.getColor(this, R.color.colorBattery);
            card2.setCardBackgroundColor(batColor);
            oldCard = card2;
        }
    }

    // Method that completes card onClick tasks
    private void cardClick(CardView card, int prof, int color) {
        if (oldCard != card) {
            ColorStateList ogColor = card.getCardBackgroundColor();
            card.setCardBackgroundColor(color);
            if (oldCard != null)
                oldCard.setCardBackgroundColor(ogColor);
            Utils.setProp(prof);
            oldCard = card;
        }
    }

}
