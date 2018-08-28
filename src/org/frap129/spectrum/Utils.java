package org.frap129.spectrum;

import android.content.Context;

import java.util.List;

import eu.chainfire.libsuperuser.Shell;

class Utils {

    public static String profileProp = "persist.spectrum.profile";

    public static String kernelProp = "persist.spectrum.kernel";

    // Method to check if kernel supports
    public static boolean checkSupport(final Context context) {
        List<String> shResult;
        String supportProp = "spectrum.support";
        shResult = Shell.SH.run(String.format("getprop %s", supportProp));
        String support = listToString(shResult);

        return !support.isEmpty();
    }

    // Method to check if the device is rooted
    public static boolean checkSU() {
        return Shell.SU.available();
    }

    // Method that gets system property
    public static String getProp() {
        List<String> shResult;
        shResult = Shell.SH.run(String.format("getprop %s", profileProp));
        return listToString(shResult);
    }

    // Method that sets system property
    public static void setProp(final int profile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Shell.SU.run(String.format("setprop %s %s", profileProp, profile));
            }
        }).start();
    }

    // Method that converts List<String> to String
    public static String listToString(List<String> list) {
        StringBuilder Builder = new StringBuilder();
        for(String out : list){
            Builder.append(out);
        }
        return Builder.toString();
    }

}
