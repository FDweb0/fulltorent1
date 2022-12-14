/*
 * Copyright (C) 2016 Yaroslav Pronin <proninyaroslav@mail.ru>
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

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.full.torrent.no.ads.private1.encrypted.R;

/*
 * Adds "Copy" item in share dialog.
 */

public class SendTextToClipboard extends Activity
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        if (intent.hasExtra(Intent.EXTRA_TEXT)) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Activity.CLIPBOARD_SERVICE);
            ClipData clip;

            CharSequence text = intent.getCharSequenceExtra(Intent.EXTRA_TEXT);

            clip = ClipData.newPlainText(intent.getType(), text);
            clipboard.setPrimaryClip(clip);

            Toast.makeText(getApplicationContext(),
                    R.string.text_copied_to_clipboard,
                    Toast.LENGTH_SHORT)
                    .show();
        }

        finish();
        overridePendingTransition(0, 0);
    }
}
