package com.ma.display.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.ma.display.base.App;
import com.ma.display.R;

/**
 * Created by MA on 07/01/2018.
 */

public class SplashActivity extends App {

    private TextView txtTitle, txtSubTitle;
    private ImageView imgIcon;
    private Animation animeText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txtTitle = findViewById(R.id.txt_title);
        txtSubTitle = findViewById(R.id.txt_subtitle);
        imgIcon = findViewById(R.id.img_icon);

        animeText = AnimationUtils.loadAnimation(this, R.anim.from_title_show);

        txtTitle.setAnimation(animeText);
        txtSubTitle.setAnimation(animeText);
        imgIcon.setAnimation(animeText);

        Thread thread = new Thread(){
            public void run(){
                try {
                    int lTimer1 = 0;
                    while (lTimer1 < 3000) {
                        sleep(100);
                        lTimer1 = lTimer1 + 100;
                    }

                    showNext();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                finally {
                    finish();
                }
            }
        };

        thread.start();
    }

    private void showNext(){
        if (usersSession.isLogin()){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }else{
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
        }

        finish();
    }
}
