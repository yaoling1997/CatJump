package com.example.yaolingjump.screen;

import android.util.Log;

import com.example.yaolingjump.Macro.MyAssets;

import loon.Screen;
import loon.canvas.LColor;
import loon.component.LButton;
import loon.event.ActionKey;
import loon.event.GameTouch;
import loon.opengl.GLEx;
import loon.utils.timer.LTimerContext;

/**
 * Created on 2018/2/16.
 */

public class MainScreen extends Screen {
    private int margin=10;//按钮间隔多少像素
    private LButton btnStart;//开始按钮
    private LButton btnScoreboard;//积分榜按钮
    private LButton btnExit;//退出按钮
    @Override
    public void draw(GLEx glEx) {

    }
    private void initBtn(){
        btnStart= new LButton(MyAssets.BTN_START){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    //replaceScreen(new GameScreen(),MoveMethod.OUT_DOWN);
                    setScreen(new GameScreen());
                }
            }
        };
        btnScoreboard= new LButton(MyAssets.BTN_SCOREBOARD){
//            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
//            @Override
//            public void doClick() {
//                if (!action.isPressed()){
//                    action.press();
//                    //replaceScreen(new GameScreen(),MoveMethod.OUT_DOWN);
//                    setScreen(new GameScreen());
//                }
//            }
        };
        btnExit= new LButton(MyAssets.BTN_EXIT){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    //replaceScreen(new GameScreen(),MoveMethod.OUT_DOWN);
                    System.exit(-1);
                }
            }
        };

        centerOn(btnStart);
        btnScoreboard.setLocation(btnStart.getX(),btnStart.getY()+btnStart.height()+margin);
        btnExit.setLocation(btnScoreboard.getX(),btnScoreboard.getY()+btnScoreboard.height()+margin);
        btnStart.setEnabled(true);
        btnScoreboard.setEnabled(true);
        btnExit.setEnabled(true);
        add(btnStart);
        add(btnScoreboard);
        add(btnExit);
    }
    @Override
    public void onLoad() {
        Log.i("yaoling1997","screenNum: "+getScreenCount());
        initBtn();
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
