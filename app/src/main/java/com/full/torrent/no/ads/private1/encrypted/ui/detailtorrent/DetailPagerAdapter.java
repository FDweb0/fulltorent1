/*
 * Copyright (C) 2018-2021 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent.pages.DetailTorrentInfoFragment;
import com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent.pages.DetailTorrentStateFragment;
import com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent.pages.files.DetailTorrentFilesFragment;
import com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent.pages.peers.DetailTorrentPeersFragment;
import com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent.pages.pieces.DetailTorrentPiecesFragment;
import com.full.torrent.no.ads.private1.encrypted.ui.detailtorrent.pages.trackers.DetailTorrentTrackersFragment;

public class DetailPagerAdapter extends FragmentStateAdapter
{
    @ViewPager2.OffscreenPageLimit
    public static final int NUM_FRAGMENTS = 6;

    public static final int INFO_FRAG_POS = 0;
    public static final int STATE_FRAG_POS = 1;
    public static final int FILES_FRAG_POS = 2;
    public static final int TRACKERS_FRAG_POS = 3;
    public static final int PEERS_FRAG_POS = 4;
    public static final int PIECES_FRAG_POS = 5;


    public DetailPagerAdapter(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case INFO_FRAG_POS:
                return DetailTorrentInfoFragment.newInstance();
            case STATE_FRAG_POS:
                return DetailTorrentStateFragment.newInstance();
            case FILES_FRAG_POS:
                return DetailTorrentFilesFragment.newInstance();
            case TRACKERS_FRAG_POS:
                return DetailTorrentTrackersFragment.newInstance();
            case PEERS_FRAG_POS:
                return DetailTorrentPeersFragment.newInstance();
            case PIECES_FRAG_POS:
                return DetailTorrentPiecesFragment.newInstance();
            default:
                return new Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_FRAGMENTS;
    }
}
