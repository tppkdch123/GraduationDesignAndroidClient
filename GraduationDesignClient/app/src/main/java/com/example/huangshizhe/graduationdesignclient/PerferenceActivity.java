package com.example.huangshizhe.graduationdesignclient;

import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class PerferenceActivity extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.perference);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        if ("select_linkage".equals(preference.getKey())) {
            CheckBoxPreference checkBox = (CheckBoxPreference) findPreference("select_linkage");
            ListPreference editBox = (ListPreference) findPreference("select_city");
            editBox.setEnabled(checkBox.isChecked());
        }
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }
}
