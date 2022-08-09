/*
 * Copyright (C) 2018 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.ui.feeditems;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.full.torrent.no.ads.private1.encrypted.R;
import com.full.torrent.no.ads.private1.encrypted.core.utils.Utils;
import com.full.torrent.no.ads.private1.encrypted.ui.FragmentCallback;

public class FeedItemsActivity extends AppCompatActivity
        implements FragmentCallback
{
    private static final String TAG = FeedItemsActivity.class.getSimpleName();

    public static final String TAG_FEED_ID = "feed_id";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        setTheme(Utils.getAppTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);

        if (Utils.isTwoPane(this)) {
            finish();
            return;
        }

        setContentView(R.layout.activity_feed_items);

        FeedItemsFragment feedItemsFragment = (FeedItemsFragment)getSupportFragmentManager()
                .findFragmentById(R.id.feed_items_fragmentContainer);

        if (feedItemsFragment != null)
            feedItemsFragment.setFeedId(getIntent().getLongExtra(TAG_FEED_ID, -1));
    }

    @Override
    public void onFragmentFinished(@NonNull Fragment f, Intent intent,
                                   @NonNull FragmentCallback.ResultCode code)
    {
        finish();
    }
}
