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

package com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent.pages.trackers;

import androidx.annotation.NonNull;

import com.full.torrent.no.ads.private1.encrypted.core.model.data.TrackerInfo;

public class TrackerItem extends TrackerInfo
{
    public TrackerItem(@NonNull TrackerInfo state)
    {
        super(state.url, state.message, state.tier, state.status);
    }

    @Override
    public int hashCode()
    {
        return url.hashCode();
    }

    public boolean equalsContent(Object o)
    {
        return super.equals(o);
    }

    @Override
    public boolean equals(Object o)
    {
        if (!(o instanceof TrackerItem))
            return false;

        if (o == this)
            return true;

        return url.equals(((TrackerItem)o).url);
    }
}
