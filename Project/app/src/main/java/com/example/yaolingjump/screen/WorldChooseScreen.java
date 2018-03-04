package com.example.yaolingjump.screen;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.avg.GameStartAVGScreen;

import loon.Screen;
import loon.component.LPaper;
import loon.event.ActionKey;
import loon.event.GameTouch;
import loon.opengl.GLEx;
import loon.utils.timer.LTimerContext;

/**
 * Created on 2018/2/16.
 */

public class WorldChooseScreen extends Screen {
    private int margin=8;//间隔多少像素
    private LPaper background;//充当背景，直接setbackground在界面切换的时候有可能背景显示不出来(游戏引擎的bug我猜)
    private LPaper title;
    private LPaper undergroundCity;
    private LPaper btnBack;
    @Override
    public void draw(GLEx glEx) {

    }

    private void initViews(){
        background= new LPaper(MyAssets.SCOREBOARD_SCREEN_BACKGROUND);
        add(background);
        title= new LPaper(MyAssets.WORLD_CHOOSE_SCREEN_TITLE);
        title.setLocation(getWidth()/2-title.getWidth()/2,margin);
        add(title);

        undergroundCity = new LPaper(MyAssets.UNDERGROUND_CITY){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    //replaceScreen(new MainScreen(),MoveMethod.OUT_DOWN);
                    setScreen(new GameStartAVGScreen());
                }
            }
        };
        undergroundCity.setLocation(margin,title.getY()+title.getHeight()+margin);
        add(undergroundCity);

        btnBack = new LPaper(MyAssets.BTN_BACK){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    replaceScreen(new MainScreen(),MoveMethod.OUT_UP);
                    //setScreen(new MainScreen());
                }
            }
        };
        btnBack.setLocation(margin,getHeight()-btnBack.height()-margin);
        add(btnBack);
    }

    @Override
    public void onLoad() {
        initViews();
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
