/*
 * Copyright (C) 2019-2021 Yaroslav Pronin <proninyaroslav@mail.ru>
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

import androidx.annotation.NonNull;

import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.FastResume;
import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.TagInfo;
import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.Torrent;

import java.io.IOException;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;

public interface TorrentRepository
{
    void addTorrent(@NonNull Torrent torrent);

    void updateTorrent(@NonNull Torrent torrent);

    void deleteTorrent(@NonNull Torrent torrent);

    Torrent getTorrentById(@NonNull String id);

    Single<Torrent> getTorrentByIdSingle(@NonNull String id);

    Flowable<Torrent> observeTorrentById(@NonNull String id);

    List<Torrent> getAllTorrents();

    void addFastResume(@NonNull FastResume fastResume);

    FastResume getFastResumeById(@NonNull String torrentId);

    void saveSession(@NonNull byte[] data) throws IOException;

    String getSessionFile();

    void replaceTags(@NonNull String torrentId, @NonNull List<TagInfo> tags);

    void addTag(@NonNull String torrentId, @NonNull TagInfo tag);

    void deleteTag(@NonNull String torrentId, @NonNull TagInfo tag);
}
