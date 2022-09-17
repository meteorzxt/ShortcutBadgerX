package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import java.util.Arrays;
import java.util.List;

import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

/**
 * @author Jason Ling
 */
public class HuaweiHomeBadger implements Badger {

    private static final String HUAWEI_URI = "content://com.huawei.android.launcher.settings/badge/";
    private static final String HONOR_URI = "content://com.hihonor.android.launcher.settings/badge/";

    @Override
    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        Bundle localBundle = new Bundle();
        localBundle.putString("package", context.getPackageName());
        localBundle.putString("class", componentName.getClassName());
        localBundle.putInt("badgenumber", badgeCount);

        boolean huaweiSuccess = false;
        try {
            context.getContentResolver().call(Uri.parse(HUAWEI_URI), "change_badge", null, localBundle);
            huaweiSuccess = true;
        } catch (Exception ignored) {

        }

        boolean honorSuccess = false;
        try {
            context.getContentResolver().call(Uri.parse(HONOR_URI), "change_badge", null, localBundle);
            honorSuccess = true;
        } catch (Exception ignored) {

        }

        if (!huaweiSuccess && !honorSuccess)
            throw new ShortcutBadgeException("Both huawei and honor content resolver have failed");
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList(
                "com.huawei.android.launcher",
                "com.hihonor.android.launcher"
        );
    }
}
