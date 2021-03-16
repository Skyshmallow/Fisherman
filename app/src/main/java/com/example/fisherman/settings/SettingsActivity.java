package com.example.fisherman.settings;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ComponentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.preference.PreferenceManager;

import com.example.fisherman.Logo_Activity;
import com.example.fisherman.MainActivity;
import com.example.fisherman.R;
import com.example.fisherman.Text_Content_Activity;

import java.util.Set;

public class SettingsActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private SharedPreferences def_pref;

    @SuppressLint("CommitTransaction")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getSupportActionBar() != null){
            actionBar=getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.action_settings));
            getFragmentManager().beginTransaction().replace(android.R.id.content,new SettingsFragment()).commit();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        tochnobelilchor();
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        tochnobelilchor();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        tochnobelilchor();
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        tochnobelilchor();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==android.R.id.home){
            finish();

        }
       tochnobelilchor();
        return true;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tochnobelilchor();
    }
    public void  tochnobelilchor(){
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean bel=def_pref.getBoolean("darky_key",false);
        if(bel){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
    }
}
