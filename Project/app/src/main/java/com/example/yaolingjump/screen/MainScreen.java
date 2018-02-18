package com.example.yaolingjump.screen;

import com.example.yaolingjump.Macro.MyAssets;

import loon.Screen;
import loon.component.LButton;
import loon.event.ActionKey;
import loon.event.GameTouch;
import loon.opengl.GLEx;
import loon.utils.timer.LTimerContext;

/**
 * Created on 2018/2/16.
 */

public class MainScreen extends Screen {
    @Override
    public void draw(GLEx glEx) {

    }

    @Override
    public void onLoad() {
        LButton btn= new LButton(MyAssets.GAME_START){
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
        centerOn(btn);
        btn.setEnabled(true);
        add(btn);
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
