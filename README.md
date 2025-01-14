ShortcutBadgerX: [![](https://jitpack.io/v/rlgo/ShortcutBadgerX.svg)](https://jitpack.io/#rlgo/ShortcutBadgerX) [![Android CI](https://github.com/rlgo/ShortcutBadgerX/actions/workflows/android.yml/badge.svg)](https://github.com/rlgo/ShortcutBadgerX/actions/workflows/android.yml)
===================================
Helper library to set android app icon badge count for different phone brand.\
Forked from [leolin310148/ShortcutBadger](https://github.com/leolin310148/ShortcutBadger) which seems no longer maintained.

Usage
===================================

1. Add jitpack to your build script.
    ```gradle
        repositories {
            maven { url 'https://jitpack.io' }
        }
    ```
2. Add dependencies for ShortcutBadgerX.
    ```gradle     
        dependencies {
            implementation 'com.github.rlgo:ShortcutBadgerX:1.2.1'
        }
    ```
3. Add the codes below:
    ```java
        int badgeCount = 1;
        Notification.Builder builder = new Notification.Builder(getApplicationContext())
                    .setContentTitle("ShortcutBadgerX")
                    .setContentText("testing")
                    .setNumber(badgeCount)
                    .setSmallIcon(R.drawable.ic_launcher);
        Notification notification = builder.build();
        ShortcutBadger.applyNotification(getApplicationContext(), notification, badgeCount);
        notificationManager.notify(notificationId, notification);
        ShortcutBadger.applyCount(getApplicationContext(), badgeCount);
    ```
