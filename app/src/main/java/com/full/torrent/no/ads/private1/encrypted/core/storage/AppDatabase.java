/*
 * Copyright (C) 2019-2022 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.core.storage;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.FastResume;
import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.FeedChannel;
import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.FeedItem;
import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.TagInfo;
import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.Torrent;
import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.TorrentTagInfo;
import com.full.torrent.no.ads.private1.encrypted.core.storage.converter.UriConverter;
import com.full.torrent.no.ads.private1.encrypted.core.storage.dao.FastResumeDao;
import com.full.torrent.no.ads.private1.encrypted.core.storage.dao.FeedDao;
import com.full.torrent.no.ads.private1.encrypted.core.storage.dao.TagInfoDao;
import com.full.torrent.no.ads.private1.encrypted.core.storage.dao.TorrentDao;

@Database(
        entities = {
                Torrent.class,
                FastResume.class,
                FeedChannel.class,
                FeedItem.class,
                TagInfo.class,
                TorrentTagInfo.class,
        },
        version = 9
)
@TypeConverters({UriConverter.class})

public abstract class AppDatabase extends RoomDatabase
{
    private static final String DATABASE_NAME = "Full Torrent.db";

    private static volatile AppDatabase INSTANCE;

    public abstract TorrentDao torrentDao();

    public abstract FastResumeDao fastResumeDao();

    public abstract FeedDao feedDao();

    public abstract TagInfoDao tagInfoDao();

    public static AppDatabase getInstance(@NonNull Context appContext)
    {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null)
                    INSTANCE = buildDatabase(appContext);
            }
        }

        return INSTANCE;
    }

    private static AppDatabase buildDatabase(Context appContext)
    {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addMigrations(DatabaseMigration.getMigrations(appContext))
                .build();
    }
}