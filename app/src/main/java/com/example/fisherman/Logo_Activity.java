package com.example.fisherman;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Logo_Activity extends Activity {
    Animation logoAnim, buttonLogoAnim,text_Anim;
    private Button bAnim;
    private ImageView logoImage;
    private TextView textAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);
        init();
        start_MainActivity();
    }

    private void init() {
        logoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_anim);
        buttonLogoAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.button_anim);
        text_Anim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alfa_anim);
        logoImage = findViewById(R.id.imageView4);
        bAnim = findViewById(R.id.button);
        textAnim = findViewById(R.id.textView);
        textAnim.startAnimation(text_Anim);
        logoImage.startAnimation(logoAnim);
        bAnim.startAnimation(buttonLogoAnim);

    }

    public void onClickStart(View view) {
        Intent i = new Intent(Logo_Activity.this, MainActivity.class);
        startActivity(i);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
    private void start_MainActivity(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(Logo_Activity.this, MainActivity.class);
                startActivity(i);
            }
        }).start();
    }
}



