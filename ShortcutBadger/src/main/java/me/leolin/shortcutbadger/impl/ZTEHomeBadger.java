package me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import java.util.Collections;
import java.util.List;

import me.leolin.shortcutbadger.Badger;
import me.leolin.shortcutbadger.ShortcutBadgeException;

public class ZTEHomeBadger implements Badger {

    public static final String PACKAGE_NAME_MFV = "com.zte.mifavor.launcher";

    @Override
    public void executeBadge(Context context, ComponentName componentName, int badgeCount)
            throws ShortcutBadgeException {
        Bundle extra = new Bundle();
        extra.putInt("app_badge_count", badgeCount);
        extra.putString("app_badge_component_name", componentName.flattenToString());

        boolean stockSuccess = false;
        try {
            context.getContentResolver().call(
                    Uri.parse("content://com.android.launcher3.cornermark.unreadbadge"),
                    "setAppUnreadCount", null, extra);
            stockSuccess = true;
        } catch (Exception ignored) {
        }

        boolean mifavorSuccess = false;
        try {
            context.getContentResolver().call(
                    Uri.parse("content://com.zte.mifavor.launcher.unreadbadge"),
                    "setAppUnreadCount", null, extra);
            mifavorSuccess = true;
        } catch (Exception ignored) {
        }

        if (!stockSuccess && !mifavorSuccess)
            throw new ShortcutBadgeException("Both stock and mifavor content resolver have failed");
    }

    @Override
    public List<String> getSupportLaunchers() {
        return Collections.singletonList(PACKAGE_NAME_MFV);
    }
} 

