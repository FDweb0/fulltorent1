/*
 * Copyright (C) 2022 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.core.model;

import java.util.HashSet;

public class SessionErrorFilter {
    static final HashSet<String> filteredErrors = new HashSet<>();
    static {
        filteredErrors.add("Operation not supported on transport endpoint, code 95");
    }

    boolean skip(String errorMsg) {
        return filteredErrors.contains(errorMsg);
    }
}
