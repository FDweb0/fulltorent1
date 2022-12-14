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

package com.full.torrent.no.ads.private1.encrypted.ui.addtag;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.full.torrent.no.ads.private1.encrypted.core.RepositoryHelper;
import com.full.torrent.no.ads.private1.encrypted.core.model.data.entity.TagInfo;
import com.full.torrent.no.ads.private1.encrypted.core.storage.TagRepository;
import com.full.torrent.no.ads.private1.encrypted.core.utils.Utils;

import io.reactivex.Completable;

public class AddTagViewModel extends AndroidViewModel {
    private final TagRepository tagRepo;
    public AddTagState state;

    public AddTagViewModel(@NonNull Application application) {
        super(application);

        state = new AddTagState();
        tagRepo = RepositoryHelper.getTagRepository(getApplication());
    }

    public void setInitValues(@NonNull TagInfo info) {
        state.setExistsTagId(info.id);
        state.setName(info.name);
        state.setColor(info.color);
    }

    public void setRandomColor() {
        state.setColor(Utils.getRandomColor());
    }

    Completable saveTag() {
        TagInfo info;
        Long existsTagId = state.getExistsTagId();
        if (existsTagId == null) {
            info = new TagInfo(state.getName(), state.getColor());
        } else {
            info = new TagInfo(existsTagId, state.getName(), state.getColor());
        }
        return Completable.fromCallable(() -> {
            if (existsTagId == null) {
                TagInfo oldTag = tagRepo.getByName(info.name);
                if (oldTag != null) {
                    throw new TagAlreadyExistsException();
                }
                tagRepo.insert(info);
            } else {
                tagRepo.update(info);
            }
            return null;
        });
    }

    public static class TagAlreadyExistsException extends Exception { }
}
