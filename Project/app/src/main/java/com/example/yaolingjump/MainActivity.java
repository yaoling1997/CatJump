package com.example.yaolingjump;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.yaolingjump.Macro.Macro;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;
import com.example.yaolingjump.screen.MainScreen;

import loon.LSetting;
import loon.Screen;
import loon.android.Loon;

public class MainActivity extends Loon{
    public static Activity mainActivity;
    private SharedPreferences prefs;
    @Override
    public void onMain() {//android:configChanges="orientation|screenSize|keyboardHidden，注意得有screenSize，不然锁屏会有问题
        LSetting setting= new LSetting();
        setting.width=480;
        setting.height=320;
        setting.logoPath= MyAssets.LOGO;
        setting.isLogo=true;//是否在游戏进入前显示标志
        setting.isFPS=false;//是否显示FPS
        setting.fontName="黑体";//字体
        setting.appName= Macro.GAME_NAME;
        mainActivity=this;

//        prefs=getSharedPreferences(Macro.PREFS_FILE,MODE_PRIVATE);
//        Intent intent= new Intent(this,MusicService.class);
//        intent.putExtra(Macro.BG_MUSIC,prefs.getString(Macro.BG_MUSIC,Macro.OPEN));//open or close
//        startService(intent);

        register(setting, new Data() {
            @Override
            public Screen onScreen() {
                return new MainScreen();
            }
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("yaoling1997","onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        Log.i("yaoling1997","onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i("yaoling1997","onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i("yaoling1997","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("yaoling1997","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("yaoling1997","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("yaoling1997","onDestroy");
        super.onDestroy();
    }
}
