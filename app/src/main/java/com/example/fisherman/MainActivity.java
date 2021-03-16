package com.example.fisherman;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fisherman.settings.SettingsActivity;
import com.example.fisherman.utils.CustomArrayAdapter;
import com.example.fisherman.utils.ListItemClass;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.PreferenceManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
private ListView list;
private  String[] array,arraySecName;
private CustomArrayAdapter adapter;
private Toolbar toolbar;
private int category_index;
private Text_Content_Activity text_content_activity = new Text_Content_Activity();
private int[] array_fish_color=text_content_activity.array_image_fish;
private SharedPreferences def_pref;
private TextView textTest;
private String samStringText;
private int vposition;
private int vcategory;
private List<ListItemClass> lisItemMain;
private ListItemClass listItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textTest=findViewById(R.id.prostTesxt);
        list=findViewById(R.id.listView);
        array = getResources().getStringArray(R.array.fish_array);
        arraySecName = getResources().getStringArray(R.array.fish_array_2);
        lisItemMain = new ArrayList<>();

        for (int i=0;i<array.length;i++){
            listItem = new ListItemClass();
            listItem.setNamef(array[i]);
            listItem.setSecond_name(arraySecName[i]);
            listItem.setImgId(array_fish_color[i]);
            lisItemMain.add(listItem);
        }
        adapter = new CustomArrayAdapter(this,R.layout.list_view_item_1,lisItemMain,getLayoutInflater());
        list.setAdapter(adapter);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean bel=def_pref.getBoolean("darky_key",false);
        if(bel==true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        }
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Text_Content_Activity.class);
                intent.putExtra("category",category_index);
                intent.putExtra("position",position);
                startActivity(intent);

            }
        });
    }
    public void onVois(View view) {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        startActivityForResult(i,10);
    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(resultCode==RESULT_OK && data != null){
            switch (requestCode)
            {
                case 10:
                    ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    textTest.setText(text.get(0));
                    samStringText=text.get(0);
                    textTest.setTextSize(30);
                    textTest.setVisibility(View.VISIBLE);
                    if (samStringText.length()<15)
                    {
                        textTest.setTextSize(30);
                    }
                    else if(samStringText.length()>15){
                        textTest.setVisibility(View.GONE);
                    }
                    /*else if (samStringText.length()>20 && samStringText.length()<30)
                    {
                        textTest.setTextSize(24);
                    }
                    if (samStringText.length()>30 && samStringText.length()<50)
                    {
                        textTest.setTextSize(20);
                    }
                    if (samStringText.length()>50 && samStringText.length()<100)
                    {
                        textTest.setTextSize(15);
                    }
                    if (samStringText.length()>100 && samStringText.length()<300)
                    {
                        textTest.setTextSize(10);
                    }*/
                    if(samStringText.equals("яблоко")){
                        textTest.setText("АХАХАХАХАХ");
                    }
                    else if(samStringText.equals("1")){
                        textTest.setText("Цифра 1");
                    }
                    break;
            }
        }else{
            Toast.makeText(getApplicationContext(),"Failed to recognize speech!",Toast.LENGTH_LONG).show();
        }
        boolean prodolzhit=false;
        //category 0 FISH
        //ДЛЯ ГОЛОСОВОЙ ШТУКИ.Мол чото говоришь а он сразу выдает тебе это.ПРИКОЛЬНО ДА
        if (samStringText!=null) {
            if (samStringText.equals("карп")) {
                vposition = 0;
                vcategory = 0;
                prodolzhit = true;
            }
            if (samStringText.equals("щука") || samStringText.equals("чука")) {
                vposition = 1;
                vcategory = 0;
                prodolzhit = true;
            }
            if (samStringText.equals("осётр") || samStringText.equals("осетр")) {
                vposition = 2;
                vcategory = 0;
                prodolzhit = true;
            }
            if (samStringText.equals("налим")) {
                vposition = 3;
                vcategory = 0;
                prodolzhit = true;
            }
            if (samStringText.equals("сом") || samStringText.equals("Том") || samStringText.equals("дом")) {
                vposition = 4;
                vcategory = 0;
                prodolzhit = true;
            }
            //category 1 NAZHIVKA
            if (samStringText.equals("червяк") || samStringText.equals("червяк червяк") || samStringText.equals("червяк червяк червяк")) {
                vposition = 0;
                vcategory = 1;
                prodolzhit = true;
            }
            if (samStringText.equals("кукуруза") || samStringText.equals("кукуруза кукуруза") || samStringText.equals("кукуруза кукуруза кукуруза")) {
                vposition = 1;
                vcategory = 1;
                prodolzhit = true;
            }
            if (samStringText.equals("хлеб") || samStringText.equals("цепь") || samStringText.equals("хлеб хлеб")) {
                vposition = 2;
                vcategory = 1;
                prodolzhit = true;
            }
            if (samStringText.equals("рис") || samStringText.equals("из") || samStringText.equals("приз")) {
                vposition = 3;
                vcategory = 1;
                prodolzhit = true;
            }
            //category 2 SNASTI
            if (samStringText.equals("взяла") || samStringText.equals("грузило") || samStringText.equals("Mozilla") || samStringText.equals("Как дела")) {
                vposition = 0;
                vcategory = 2;
                prodolzhit = true;
            }
            if (samStringText.equals("крючки") || samStringText.equals("крючки крючки") || samStringText.equals("очки") || samStringText.equals("мишки") || samStringText.equals("девочки") || samStringText.equals("ручки") || samStringText.equals("дочки")) {
                vposition = 1;
                vcategory = 2;
                prodolzhit = true;
            }
            if (samStringText.equals("леска") || samStringText.equals("леска леска") || samStringText.equals("Паскаль") || samStringText.equals("резка") || samStringText.equals("ЦСКА")) {
                vposition = 2;
                vcategory = 2;
                prodolzhit = true;
            }
            if (samStringText.equals("блесна") || samStringText.equals("блесна блесна") || samStringText.equals("Ясно") || samStringText.equals("весна") || samStringText.equals("песня")) {
                vposition = 3;
                vcategory = 2;
                prodolzhit = true;
            }
        }
        if(prodolzhit)
        {
            Intent intent = new Intent(MainActivity.this, Text_Content_Activity.class);
            intent.putExtra("category", vcategory);
            intent.putExtra("position", vposition);
            startActivity(intent);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        toolbar.setTitle(R.string.fish);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i =new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.nav_fish){
            array_filler(R.string.fish,getResources().getStringArray(R.array.fish_array),getResources().getStringArray(R.array.fish_array_2),array_fish_color,0);
        }
        else if (id == R.id.nav_na){
            array_filler(R.string.na,getResources().getStringArray(R.array.nazhivka_array),getResources().getStringArray(R.array.nazhivka_array_2),text_content_activity.array_image_na,1);

        }
        else if (id== R.id.nav_sna){
            array_filler(R.string.sna,getResources().getStringArray(R.array.snasti_array),getResources().getStringArray(R.array.snasti_array_2),text_content_activity.array_image_sna,2);
        }
        return true;
    }
    //Эта функция заполняет КастомныйАрай их именами картинками и второстепенным именами
    private void array_filler(int title,String[] nameArray,String[] secName,int[] image,int index){
        toolbar.setTitle(title);

        adapter.clear();
        for (int i=0;i<nameArray.length;i++){
            listItem = new ListItemClass();
            listItem.setNamef(nameArray[i]);
            listItem.setSecond_name(secName[i]);
            listItem.setImgId(image[i]);
            lisItemMain.add(listItem);
        }
        adapter.notifyDataSetChanged();
        category_index=index;
        //adawdewqwdqwqwdqwd
    }
}
