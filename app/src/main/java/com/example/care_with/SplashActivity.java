package com.example.care_with;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        moveMain(2);
    }
    private void moveMain(int sec){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("test", "test");
                Intent intent= new Intent(getApplicationContext(),GuideActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1000*sec);
    }
}
