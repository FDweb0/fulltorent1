/*
 * Copyright (C) 2020-2021 Yaroslav Pronin <proninyaroslav@mail.ru>
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

package com.full.torrent.no.ads.private1.encrypted.ui.settings.sections;

import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.full.torrent.no.ads.private1.encrypted.R;
import com.full.torrent.no.ads.private1.encrypted.core.RepositoryHelper;
import com.full.torrent.no.ads.private1.encrypted.core.settings.SettingsRepository;
import com.full.torrent.no.ads.private1.encrypted.ui.settings.customprefs.SwitchBarPreference;

public class AnonymousModeSettingsFragment extends PreferenceFragmentCompat
        implements
        Preference.OnPreferenceChangeListener
{
    private static final String TAG = AnonymousModeSettingsFragment.class.getSimpleName();

    private SettingsRepository pref;

    public static AnonymousModeSettingsFragment newInstance()
    {
        AnonymousModeSettingsFragment fragment = new AnonymousModeSettingsFragment();

        fragment.setArguments(new Bundle());

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        pref = RepositoryHelper.getSettingsRepository(getActivity().getApplicationContext());

        String keyAnonymousMode = getString(R.string.pref_key_anonymous_mode);
        SwitchBarPreference anonymousMode = findPreference(keyAnonymousMode);
        if (anonymousMode != null) {
            anonymousMode.setChecked(pref.anonymousMode());
            bindOnPreferenceChangeListener(anonymousMode);
        }
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey)
    {
        setPreferencesFromResource(R.xml.pref_anonymous_mode, rootKey);
    }

    private void bindOnPreferenceChangeListener(Preference preference)
    {
        preference.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue)
    {
        if (preference.getKey().equals(getString(R.string.pref_key_anonymous_mode)))
            pref.anonymousMode((boolean)newValue);

        return true;
    }
}
