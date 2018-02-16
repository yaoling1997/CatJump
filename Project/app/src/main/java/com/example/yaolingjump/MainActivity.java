package com.example.yaolingjump;

import loon.LSetting;
import loon.Screen;
import loon.android.Loon;

public class MainActivity extends Loon{

    @Override
    public void onMain() {
        LSetting setting= new LSetting();
        setting.width=480;
        setting.height=320;
        setting.logoPath="assets/block.png";
        setting.isFPS=true;
        setting.fontName="黑体";
        setting.appName=Macro.GAME_NAME;
        register(setting, new Data() {
            @Override
            public Screen onScreen() {
                return new GameScreen();
            }
        });
    }
}
