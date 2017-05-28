cordova-comscore-plugin
=======================

## For ComScore SDK before v5.X

Cordova (PhoneGap) 3.0+ Plugin to connect to ComScore SDK's in Android devices.


#Installing from original repository:

```bash
cordova plugin add https://github.com/lnavarro/cordova-comscore-plugin.git
```

#Installing from Marfeel repository:

```bash
cordova plugin add https://github.com/Marfeel/cordova-comscore-plugin.git
```

#Installing from Jadwigo repository:

```bash
cordova plugin add https://github.com/jadwigo/cordova-comscore-plugin.git
```

#JavaScript Usage

In your 'deviceready' handler, set up your ComScore tracker:

* `comScorePlugin.setCustomerData("customerID", "customerKey");` where CustomerKey is your publisher secret parameter


## For ComScore SDK v5.X

Changes has been made to use the ComScore SDK v5.X.

`DONE`: Android, iOS

Sample usage in Ionic 1 project:
```
...
$ionicPlatform.ready(function() {
   ...
   ComScorePlugin.initClient("[ComScore Publisher Id]", "[ComsCore Publisher Secret]");
   ...
}
...
```
*`Note:` Replace `[ComScore Publisher Id]` and `[ComsCore Publisher Secret]` with the intended values accordingly.*
