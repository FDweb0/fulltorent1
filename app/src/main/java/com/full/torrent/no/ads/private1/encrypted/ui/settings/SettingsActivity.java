/*
 * Copyright (C) 2016, 2018, 2019 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.ui.settings;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.full.torrent.no.ads.private1.encrypted.R;
import com.full.torrent.no.ads.private1.encrypted.core.utils.Utils;

public class SettingsActivity extends AppCompatActivity
{
    private static final String TAG = SettingsActivity.class.getSimpleName();

    private Toolbar toolbar;
    private TextView detailTitle;
    private SettingsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        setTheme(Utils.getSettingsTheme(getApplicationContext()));
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        setContentView(R.layout.activity_settings);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.settings));
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailTitle = findViewById(R.id.detail_title);
        viewModel.detailTitleChanged.observe(this, title -> {
            if (title != null && detailTitle != null)
                detailTitle.setText(title);
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return true;
    }
}
