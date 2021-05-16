package com.example.newsinformation.activity.login;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

import com.example.newsinformation.R;

public class StartActivity extends Activity {
    private static int SPLASH_DISPLAY_LENGHT= 3000;    //延迟2秒
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);//去掉标题
        setContentView(R.layout.activity_start);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_DISPLAY_LENGHT);  //StartActivity，将其回收，否则按返回键会返回此界面
    }
}