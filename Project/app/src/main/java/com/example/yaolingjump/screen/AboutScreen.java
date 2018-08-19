package com.example.yaolingjump.screen;

import android.content.SharedPreferences;

import com.example.yaolingjump.Macro.Macro;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.MainActivity;

import loon.Screen;
import loon.canvas.LColor;
import loon.component.LPaper;
import loon.event.ActionKey;
import loon.event.GameTouch;
import loon.font.LFont;
import loon.opengl.GLEx;
import loon.utils.timer.LTimerContext;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created on 2018/2/16.
 */

public class AboutScreen extends Screen {
    public static final int NoNum=5;
    private int margin=8;//间隔多少像素
    private LPaper background;//充当背景，直接setbackground在界面切换的时候有可能背景显示不出来(游戏引擎的bug我猜)
    private LPaper title;
    private LPaper scoreboard;
    private LPaper btnBack;
    @Override
    public void draw(GLEx glEx) {

    }

    private void initViews(){
        background= new LPaper(MyAssets.MAIN_SCREEN_BACKGROUND);
        add(background);
        title= new LPaper(MyAssets.ABOUT_SCREEN_TITLE);
        title.setLocation(getWidth()/2-title.getWidth()/2,margin);
        add(title);
        scoreboard= new LPaper(MyAssets.SCOREBOARD_BACKGROUND){
            @Override
            public void paint(GLEx g) {
                LColor color= g.getColor();
                int size= g.getFont().getSize();
                g.setColor(LColor.maroon);
                g.setFont(LFont.getFont(18));
                g.drawString("版本号：V1.1",margin,margin);
                g.drawString("集齐100个硬币能兑换为一条命",margin,margin+20);
                g.drawString("取消了计分板",margin,margin+40);
                g.drawString("下调了世界1中boss的血量",margin,margin+60);
                g.drawString("增加了魔杖和鞋子等道具",margin,margin+80);
                g.drawString("添加了游戏背景",margin,margin+100);
                g.drawString("本游戏非商业用途，仅供娱乐",margin,margin+120);
//                g.drawString("欢迎学弟学妹报考北京航空航天大学",margin,margin+40);
                g.drawString("游戏制作：李奕君",getWidth()-150-margin,getHeight()-20-margin);
                g.setColor(color);
                g.setFont(LFont.getFont(size));
            }
        };
        scoreboard.setLocation(getWidth()/2-scoreboard.getWidth()/2,title.getY()+title.getHeight()+margin);
        add(scoreboard);

        btnBack = new LPaper(MyAssets.BTN_BACK){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    replaceScreen(new MainScreen(),MoveMethod.OUT_DOWN);
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
