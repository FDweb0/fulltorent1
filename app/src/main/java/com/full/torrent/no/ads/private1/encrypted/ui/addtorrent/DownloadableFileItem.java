/*
 * Copyright (C) 2018, 2019 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.ui.addtorrent;

import android.os.Parcel;

import androidx.annotation.NonNull;

import com.full.torrent.no.ads.private1.encrypted.core.model.filetree.BencodeFileTree;
import com.full.torrent.no.ads.private1.encrypted.ui.FileItem;

public class DownloadableFileItem extends FileItem
{
    public boolean selected;

    public DownloadableFileItem(@NonNull BencodeFileTree tree)
    {
        super(tree.getIndex(), tree.getName(), tree.isFile(), tree.size());

        selected = tree.isSelected();
    }

    public DownloadableFileItem(Parcel source)
    {
        super(source);

        selected = source.readByte() != 0;
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

        dest.writeByte((byte)(selected ? 1 : 0));
    }

    public static final Creator<DownloadableFileItem> CREATOR =
            new Creator<DownloadableFileItem>()
            {
                @Override
                public DownloadableFileItem createFromParcel(Parcel source)
                {
                    return new DownloadableFileItem(source);
                }

                @Override
                public DownloadableFileItem[] newArray(int size)
                {
                    return new DownloadableFileItem[size];
                }
            };

    @NonNull
    @Override
    public String toString()
    {
        return "DownloadableFileItem{" +
                super.toString() +
                "selected=" + selected +
                '}';
    }
}
