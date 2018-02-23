package com.example.yaolingjump.screen;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.yaolingjump.Macro.Macro;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.MainActivity;

import loon.Screen;
import loon.canvas.LColor;
import loon.component.LButton;
import loon.component.LPaper;
import loon.component.LTextBar;
import loon.event.ActionKey;
import loon.event.GameTouch;
import loon.font.LFont;
import loon.opengl.GLEx;
import loon.utils.timer.LTimerContext;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created on 2018/2/16.
 */

public class ScoreboardScreen extends Screen {
    public static final int NoNum=5;
    private SharedPreferences prefs;
    private int margin=10;//间隔多少像素
    private LPaper background;//充当背景，直接setbackground在界面切换的时候有可能背景显示不出来(游戏引擎的bug)
    private LPaper scoreboard;
    private LPaper []no;
    private int []tvNo= new int[NoNum];
    private LPaper btnBack;
    private LPaper btnReset;
    @Override
    public void draw(GLEx glEx) {

    }

    private void initViews(){
        background= new LPaper(MyAssets.SCOREBOARD_SCREEN_BACKGROUND);
        add(background);
        scoreboard= new LPaper(MyAssets.SCOREBOARD_BACKGROUND){
            @Override
            public void paint(GLEx g) {
                LColor color= g.getColor();
                int size= g.getFont().getSize();
                g.setColor(LColor.red);
                g.setFont(LFont.getFont(18));
                for (int i=0;i<NoNum;i++) {
                    float x=0,y=0;
                    if (no[i]!=null){
                        x= scoreboard.getWidth()/2;
                        y= no[i].getY()-scoreboard.getY();
                    }
                    g.drawString("  " + tvNo[i], x, y);
                }
                g.setColor(color);
                g.setFont(LFont.getFont(size));
            }
        };
        centerOn(scoreboard);
        add(scoreboard);

        no= new LPaper[5];
        for (int i=0;i<NoNum;i++) {
            no[i]= new LPaper(MyAssets.NO[i]);
            if (i==0)
                no[i].setLocation(scoreboard.getX() + margin, scoreboard.getY() + margin);
            else
                no[i].setLocation(scoreboard.getX()+margin,no[i-1].getY()+no[i-1].getHeight()+margin);
            add(no[i]);
        }
        btnReset = new LPaper(MyAssets.BTN_RESET){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    prefs=MainActivity.mainActivity.getSharedPreferences(Macro.PREFS_FILE,MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();//清空用户分数
                    for (int i=0;i<NoNum;i++) {
                        editor.remove(Macro.NO[i]);
                    }
                    editor.commit();
                    updateNo();
                }
            }
        };
        btnReset.setLocation(scoreboard.getX()+scoreboard.getWidth()-margin+5-btnReset.getWidth(),scoreboard.getY()+scoreboard.getHeight()-margin+2-btnReset.getHeight());
        add(btnReset);
        btnBack = new LPaper(MyAssets.BTN_BACK){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    replaceScreen(new MainScreen(),MoveMethod.OUT_RIGHT);
                    //setScreen(new MainScreen());
                }
            }
        };
        btnBack.setLocation(margin,getHeight()-btnBack.height()-margin);
        add(btnBack);
    }
    private void updateNo() {
        prefs=MainActivity.mainActivity.getSharedPreferences(Macro.PREFS_FILE,MODE_PRIVATE);
        for (int i=0;i<NoNum;i++) {
            int score = prefs.getInt(Macro.NO[i], 0);
            tvNo[i]=score;
        }
    }

    @Override
    public void onLoad() {
        initViews();
        updateNo();
    }

    @Override
    public void alter(LTimerContext lTimerContext) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void touchDown(GameTouch gameTouch) {

    }

    @Override
    public void touchUp(GameTouch gameTouch) {

    }

    @Override
    public void touchMove(GameTouch gameTouch) {

    }

    @Override
    public void touchDrag(GameTouch gameTouch) {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void close() {

    }
}
