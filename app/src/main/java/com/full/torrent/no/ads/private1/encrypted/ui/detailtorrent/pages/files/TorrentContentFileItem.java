/*
 * Copyright (C) 2018-2020 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent.pages.files;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.full.torrent.no.ads.private1.encrypted.core.model.filetree.FilePriority;
import com.full.torrent.no.ads.private1.encrypted.core.model.filetree.TorrentContentFileTree;
import com.full.torrent.no.ads.private1.encrypted.ui.FileItem;

public class TorrentContentFileItem extends FileItem
{
    public FilePriority priority;
    public long receivedBytes;
    public double availability;

    public TorrentContentFileItem(@NonNull TorrentContentFileTree tree)
    {
        super(tree.getIndex(), tree.getName(), tree.isFile(), tree.size());

        priority = tree.getFilePriority();
        receivedBytes = tree.getReceivedBytes();
        availability = tree.getAvailability();
    }

    public TorrentContentFileItem(Parcel source)
    {
        super(source);

        priority = (FilePriority)source.readSerializable();
        receivedBytes = source.readLong();
        availability = source.readDouble();
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        super.writeToParcel(dest, flags);

        dest.writeSerializable(priority);
        dest.writeLong(receivedBytes);
        dest.writeDouble(availability);
    }

    public static final Parcelable.Creator<TorrentContentFileItem> CREATOR =
            new Parcelable.Creator<TorrentContentFileItem>()
            {
                @Override
                public TorrentContentFileItem createFromParcel(Parcel source)
                {
                    return new TorrentContentFileItem(source);
                }

                @Override
                public TorrentContentFileItem[] newArray(int size)
                {
                    return new TorrentContentFileItem[size];
                }
            };

    public boolean equalsContent(@Nullable Object o)
    {
        if (!equals(o))
            return false;

        TorrentContentFileItem item = (TorrentContentFileItem)o;

        return priority.equals(item.priority) &&
                receivedBytes == item.receivedBytes &&
                availability == item.availability;
    }

    @NonNull
    @Override
    public String toString()
    {
        return "TorrentContentFileItem{" +
                super.toString() +
                ", receivedBytes=" + receivedBytes +
                ", availability=" + availability +
                '}';
    }
}
