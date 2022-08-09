/*
 * Copyright (C) 2017, 2019 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.core.model.filetree;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.full.torrent.no.ads.private1.encrypted.core.model.data.Priority;

import java.io.Serializable;

public class FilePriority implements Serializable
{
    private int priority;
    private Type type;

    public enum Type {MIXED, IGNORE, NORMAL, HIGH}

    public FilePriority(Priority priority)
    {
        this.priority = priority.value();
        this.type = typeFrom(priority);
    }

    public FilePriority(Type type)
    {
        this.priority = priorityFrom(type);
        this.type = type;
    }

    public Priority getPriority()
    {
        return Priority.fromValue(priority);
    }

    public Type getType()
    {
        return type;
    }

    public static Type typeFrom(Priority priority)
    {
        switch (priority) {
            case IGNORE:
                return Type.IGNORE;
            case LOW:
            case TWO:
            case THREE:
            case DEFAULT:
            case FIVE:
            case SIX:
                return Type.NORMAL;
            case TOP_PRIORITY:
                return Type.HIGH;
            default:
                return null;
        }
    }

    private static int priorityFrom(Type type)
    {
        switch (type) {
            case IGNORE:
                return Priority.IGNORE.value();
            case NORMAL:
                return Priority.DEFAULT.value();
            case HIGH:
                return Priority.TOP_PRIORITY.value();
            default:
                return -1;
        }
    }

    @Override
    public boolean equals(@Nullable Object o)
    {
        if (!(o instanceof FilePriority))
            return false;

        if (o == this)
            return true;

         FilePriority filePriority = (FilePriority)o;

         return priority == filePriority.priority && type.equals(filePriority.getType());
    }

    @NonNull
    @Override
    public String toString()
    {
        return "FilePriority{" +
                "priority=" + priority +
                ", type=" + type +
                '}';
    }
}