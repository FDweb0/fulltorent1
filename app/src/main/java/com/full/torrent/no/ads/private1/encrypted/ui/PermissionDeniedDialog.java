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

package com.full.torrent.no.ads.private1.encrypted.ui;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.full.torrent.no.ads.private1.encrypted.R;

public class PermissionDeniedDialog extends BaseAlertDialog {
    public static PermissionDeniedDialog newInstance() {
        PermissionDeniedDialog frag = new PermissionDeniedDialog();

        Bundle args = new Bundle();
        frag.setArguments(args);

        return frag;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        String title = getString(R.string.perm_denied_title);
        String message = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R ?
                getString(R.string.perm_denied_warning_android_r) :
                getString(R.string.perm_denied_warning);
        String positiveText = getString(R.string.yes);
        String negativeText = getString(R.string.no);

        return buildDialog(
                title,
                message,
                null,
                positiveText,
                negativeText,
                null,
                false
        );
    }
}
