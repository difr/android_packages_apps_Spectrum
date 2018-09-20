package org.frap129.spectrum;

import java.util.List;

import eu.chainfire.libsuperuser.Shell;

class Utils {

    private static Boolean useShellSU = false;

    public static String profileProp = "spectrum.profile";

    // Method that gets system property
    public static String getProp() {
        return listToString(Shell.SH.run(String.format("getprop %s", profileProp)));
    }

    // Method that sets system property
    public static void setProp(final int profile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String shResult;
                if (!useShellSU) {
                    shResult = listToString(Shell.SH.run(String.format("setprop %s %s >/dev/null 2>&1; echo $?", profileProp, profile)));
                    useShellSU = !shResult.equals("0");
                }
                if (useShellSU) {
                    Shell.SU.run(String.format("setprop %s %s", profileProp, profile));
                }
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
