package com.example.yaolingjump.screen;

import com.example.yaolingjump.Macro.MyAssets;

import loon.action.sprite.SpriteBatch;
import loon.action.sprite.SpriteBatchScreen;
import loon.canvas.LColor;
import loon.component.LPaper;
import loon.event.GameKey;
import loon.event.GameTouch;
import loon.font.LFont;
import loon.opengl.GLEx;

/**
 * Created on 2018/2/17.
 */

public class RestartScreen extends SpriteBatchScreen {
    private int tim=0;
    private GameScreen gs;

    public RestartScreen(GameScreen gs) {
        this.gs = gs;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void create() {
        tim=0;
        LPaper info= new LPaper(MyAssets.INFO_BACKGROUND){
            @Override
            public void paint(GLEx g) {
                LColor color= g.getColor();
                int size= g.getFont().getSize();
                g.setColor(LColor.white);
                g.setFont(LFont.getFont(18));
                if (gs.getHP()<=0)
                    g.drawString("Score "+gs.getScore(),155,5);
                else
                    g.drawString("HP "+gs.getHP(),155,5);
                g.setColor(color);
                g.setFont(LFont.getFont(size));
            }
        };
        add(info);
        centerOn(info);
    }

    @Override
    public void after(SpriteBatch spriteBatch) {

    }

    @Override
    public void before(SpriteBatch spriteBatch) {

    }

    @Override
    public void press(GameKey gameKey) {

    }

    @Override
    public void release(GameKey gameKey) {

    }

    @Override
    public void update(long l) {
        tim+=l;
        if (tim>3000) {
            if (gs.getHP()>0)
                setScreen(gs);
            else setScreen(new MainScreen());
        }
    }

    @Override
    public void dispose() {

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
}
