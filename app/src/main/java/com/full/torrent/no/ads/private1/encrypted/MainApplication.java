package com.full.torrent.no.ads.private1.encrypted;/*
 * Copyright (C) 2016-2022 Yaroslav Pronin <proninyaroslav@mail.ru>
 *
 * This file is part of Full Torrent.
 *
 * Full Torrent is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Full Torrent is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Full Torrent.  If not, see <http://www.gnu.org/licenses/>.
 */

import android.annotation.SuppressLint;
import android.database.CursorWindow;
import android.util.Log;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.multidex.BuildConfig;
import androidx.multidex.MultiDexApplication;

import com.full.torrent.no.ads.private1.encrypted.ui.TorrentNotifier;
import com.full.torrent.no.ads.private1.encrypted.ui.errorreport.ErrorReportActivity;

import org.acra.ACRA;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.DialogConfigurationBuilder;
import org.acra.config.MailSenderConfigurationBuilder;
import org.acra.data.StringFormat;



public class MainApplication extends MultiDexApplication {
    public static final String TAG = MainApplication.class.getSimpleName();

    static {
        /* Vector Drawable support in ImageView for API < 21 */
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CoreConfigurationBuilder builder = new CoreConfigurationBuilder(this);
        builder
                .withBuildConfigClass(BuildConfig.class)
                .withReportFormat(StringFormat.JSON);
        builder.getPluginConfigurationBuilder(MailSenderConfigurationBuilder.class)
                .withMailTo("proninyaroslav@mail.ru");
        builder.getPluginConfigurationBuilder(DialogConfigurationBuilder.class)
                .withEnabled(true)
                .setReportDialogClass(ErrorReportActivity.class);
        // Set stub handler
        if (Thread.getDefaultUncaughtExceptionHandler() == null) {
            Thread.setDefaultUncaughtExceptionHandler((t, e) ->
                    Log.e(TAG, "Uncaught exception in "  + t + ": " + Log.getStackTraceString(e))
            );
        }
        ACRA.init(this, builder);

        increaseCursorWindowSize();

        TorrentNotifier.getInstance(MainApplication.this).makeNotifyChans();
    }

    @SuppressLint("DiscouragedPrivateApi")
    private void increaseCursorWindowSize() {
        try {
            var field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
            field.setAccessible(true);
            field.set(null, 100 * 1024 * 1024); // 100MB
        } catch (Exception e) {
            Log.e(TAG, "Unable to increase CursorWindow size", e);
        }
    }
}