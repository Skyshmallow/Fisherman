package com.example.fisherman;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.preference.PreferenceManager;

import com.google.android.material.shadow.ShadowDrawableWrapper;


public class Text_Content_Activity extends AppCompatActivity {
    private int category=0;
    private int position=0;
    private final int [] array_fish={R.string.fish_1,R.string.fish_2,R.string.fish_3,R.string.fish_4,R.string.fish_5};
    private final int [] array_image_fish={R.drawable.karp,R.drawable.shuka,R.drawable.osetr,R.drawable.nalim,R.drawable.som};
    private final String [] array_title_fish={"Карп","Щука","Осетр","Налим","Сом"};
    private final int [] array_na={R.string.na_1,R.string.na_2,R.string.na_3,R.string.na_4};
    private final int [] array_image_na={R.drawable.chervak,R.drawable.kukuruza,R.drawable.xleb,R.drawable.ris};
    private final String [] array_title_na={"Червяк","Кукуруза","Хлеб","Рис"};
    private final int [] array_sna={R.string.sna_1,R.string.sna_2,R.string.sna_3,R.string.sna_4};
    private final int [] array_image_sna={R.drawable.gruzila,R.drawable.kruchki,R.drawable.leska,R.drawable.blesna};
    private final String [] array_title_sna={"Грузила","Крючки","Леска","Блесна"};
    private TextView content_ofstuff;
    private ImageView iContent;
    private ActionBar actionBar;
    private SharedPreferences def_pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_ofstuff);
        init();
        reciveIntent();
    }
    private void reciveIntent(){
        Intent i = getIntent();
        if(i!=null){
            category=i.getIntExtra("category",0);
            position=i.getIntExtra("position",0);
        }
        switch (category){
            case 0:
                content_ofstuff.setText(array_fish[position]);
                iContent.setImageResource(array_image_fish[position]);
                actionBar.setTitle(array_title_fish[position]);
                break;
            case 1:
                content_ofstuff.setText(array_na[position]);
                iContent.setImageResource(array_image_na[position]);
                actionBar.setTitle(array_title_na[position]);
                break;
            case 2:
                content_ofstuff.setText(array_sna[position]);
                iContent.setImageResource(array_image_sna[position]);
                actionBar.setTitle(array_title_sna[position]);
                break;
        }
    }
    @SuppressLint("ResourceAsColor")
    private void init(){
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        content_ofstuff=findViewById(R.id.text_main_content);
        iContent=findViewById(R.id.image_content);
        Typeface shr_balsaSans = Typeface.createFromAsset(this.getAssets(), "fonts/BalsamiqSans-Regular.ttf");
        content_ofstuff.setTypeface(shr_balsaSans);
        actionBar=getSupportActionBar();
        String text = def_pref.getString("text_sizekey","Средний");
        boolean bel_chor=def_pref.getBoolean("darky_key",false);
        LinearLayout nav_view = findViewById(R.id.nav_view);
        LinearLayout ashilatin = findViewById(R.id.ashilatin);
        ImageView image_content = findViewById(R.id.image_content);
        LinearLayout content_layout = findViewById(R.id.content_layout);
        ConstraintLayout content_constraint = findViewById(R.id.content_constaraint);
        TextView text_main_content = findViewById(R.id.text_main_content);
        if(text!=null){
        switch (text){
            case "Большой":
                content_ofstuff.setTextSize(24);
                break;
            case "Средний":
                content_ofstuff.setTextSize(19);
                break;
            case "Маленький":
                content_ofstuff.setTextSize(14);
                break;
        }


        if(bel_chor){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

            
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


        }
        }
        }

    }






