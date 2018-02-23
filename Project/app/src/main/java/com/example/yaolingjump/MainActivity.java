package com.example.yaolingjump;

import com.example.yaolingjump.Macro.Macro;
import com.example.yaolingjump.screen.GameScreen;
import com.example.yaolingjump.screen.MainScreen;

import loon.LSetting;
import loon.Screen;
import loon.android.Loon;

public class MainActivity extends Loon{

    @Override
    public void onMain() {
        LSetting setting= new LSetting();
        setting.width=480;
        setting.height=320;
        //setting.logoPath="assets/accelerator.gif";
        //setting.isLogo=true;//是否在游戏进入前显示标志
        setting.isFPS=true;//是否显示FPS
        setting.fontName="黑体";//字体
        setting.appName= Macro.GAME_NAME;
        register(setting, new Data() {
            @Override
            public Screen onScreen() {
                return new MainScreen();
            }
        });
    }
}
