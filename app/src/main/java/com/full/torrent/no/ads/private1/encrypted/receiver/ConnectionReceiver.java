/*
 * Copyright (C) 2019 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.full.torrent.no.ads.private1.encrypted.core.model.TorrentEngine;

/*
 * The receiver for Wi-Fi connection state changes and roaming state.
 */

public class ConnectionReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        if (action != null && action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            TorrentEngine engine = TorrentEngine.getInstance(context);
            engine.rescheduleTorrents();
        }
    }

    public static IntentFilter getFilter()
    {
        return new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    }
}
