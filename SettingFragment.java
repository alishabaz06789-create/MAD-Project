package com.example.smartnotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class SettingsFragment extends Fragment {

    private SwitchCompat switchDarkMode;
    private SwitchCompat switchNotifications;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        switchDarkMode = view.findViewById(R.id.switch_dark_mode);
        switchNotifications = view.findViewById(R.id.switch_notifications);

        sharedPreferences = requireActivity().getSharedPreferences("AppSettings", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Load saved settings
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);
        boolean notificationsEnabled = sharedPreferences.getBoolean("notifications", true);

        switchDarkMode.setChecked(isDarkMode);
        switchNotifications.setChecked(notificationsEnabled);

        // Dark Mode Toggle
        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("dark_mode", isChecked);
            editor.apply();

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }

            // Recreate activity to apply theme
            requireActivity().recreate();
        });

        // Notifications Toggle
        switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            editor.putBoolean("notifications", isChecked);
            editor.apply();

            String message = isChecked ? "Notifications enabled" : "Notifications disabled";
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
