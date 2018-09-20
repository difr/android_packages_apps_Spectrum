# <img src="https://raw.githubusercontent.com/difr/spectrum/master/res/drawable-xxxhdpi/ic_launcher.png" width="70" height="70" /> Spectrum difrED
A very simple profile manager.
Here are only two profiles: battery and performance.
Second one (performance) is temporary for highload apps, it will be switched back to default (battery) when screen will be off.
It doesn't require superuser permissions if selinux is off.

## How to add Spectrum support
Put "init/spectrum.rc" in "/system/etc/init".
