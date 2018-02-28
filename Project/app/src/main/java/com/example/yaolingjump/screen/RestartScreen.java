package com.example.yaolingjump.screen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.yaolingjump.Macro.Macro;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.MainActivity;
import com.example.yaolingjump.MusicService;

import loon.action.sprite.SpriteBatch;
import loon.action.sprite.SpriteBatchScreen;
import loon.canvas.LColor;
import loon.component.LPaper;
import loon.event.GameKey;
import loon.event.GameTouch;
import loon.font.LFont;
import loon.opengl.GLEx;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created on 2018/2/17.
 */

public class RestartScreen extends SpriteBatchScreen {
    private int tim=0;
    private GameScreen gs;
    private LPaper info;
    private LPaper game_over;//生命值没了，游戏结束
    private int margin=10;

    public RestartScreen(GameScreen gs) {
        this.gs = gs;
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
    private void updateScoreboard(){//更新积分榜
        SharedPreferences prefs= MainActivity.mainActivity.getSharedPreferences(Macro.PREFS_FILE, Context.MODE_PRIVATE);
        int tmpScore= gs.getScore();
        for (int i=0;i<ScoreboardScreen.NoNum;i++){
            int oldScore=prefs.getInt(Macro.NO[i],0);
            if (oldScore<tmpScore){
                SharedPreferences.Editor editor = prefs.edit();
                editor.putInt(Macro.NO[i],tmpScore);
                editor.commit();
                tmpScore=oldScore;
            }
        }
    }

    @Override
    public void create() {
        tim=0;
        info= new LPaper(MyAssets.INFO_BACKGROUND){
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
        if (gs.getHP()<=0){
            game_over=  new LPaper(MyAssets.GAME_OVER);
            game_over.setLocation(getWidth()/2-game_over.getWidth()/2,info.getY()+info.getHeight());
            add(game_over);
        }
        applyPrefs();
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
        if (tim>5000){//没命了
            updateScoreboard();
            setScreen(new MainScreen());
        }else if (tim>3000) {//显示时间
            if (gs.getHP()>0) {
                if (gs.maps.isEmpty()) {//通关了
                    Log.i("yaoling1997","score: "+gs.getScore());
                    updateScoreboard();
                    Log.i("yaoling1997","pass all levels");
                    setScreen(new World1PassAVGScreen());
                }else {//死了但是还有命，重玩本关
                    setScreen(gs);
                }
            }
        }
    }
    private void applyPrefs(){
        Intent intent= new Intent(MainActivity.mainActivity,MusicService.class);
        intent.putExtra(Macro.BG_MUSIC,Macro.CLOSE);//close
        intent.putExtra(Macro.BG_MUSIC_SOURCE,MyAssets.BATTLE_BG_MUSIC);
        MainActivity.mainActivity.startService(intent);
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
