/*
 * Copyright (C) 2021 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.ui.tag;

import androidx.annotation.NonNull;

import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.TagInfo;

public class TagItem {
    @NonNull
    public final TagInfo info;

    public TagItem(@NonNull TagInfo info) {
        this.info = info;
    }

    public boolean isSame(TagItem o) {
        return info.id == o.info.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagItem tagItem = (TagItem) o;

        return info.equals(tagItem.info);
    }

    @Override
    public int hashCode() {
        return info.hashCode();
    }

    @NonNull
    @Override
    public String toString() {
        return "TagItem{" +
                "info=" + info +
                '}';
    }
}
