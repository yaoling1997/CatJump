package com.example.yaolingjump.screen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.yaolingjump.Macro.Macro;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.MainActivity;
import com.example.yaolingjump.MusicService;

import loon.Screen;
import loon.component.LPaper;
import loon.event.ActionKey;
import loon.event.GameTouch;
import loon.opengl.GLEx;
import loon.utils.timer.LTimerContext;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created on 2018/2/16.
 */

public class MainScreen extends Screen {
    private SharedPreferences prefs;
    private int margin=10;//按钮间隔多少像素
    private LPaper background;//背景
    private LPaper title;//界面的标题
    private LPaper btnStart;//开始按钮
    private LPaper btnScoreboard;//积分榜按钮
    private LPaper btnExit;//退出按钮
    private LPaper btnBgMusicOpen;//背景音乐打开按钮
    private LPaper btnBgMusicClose;//背景音乐按钮关闭，只是用来遮挡上面那个按钮，直接setBackground有时显示不出来，我也是醉了
    private LPaper btnAbout;//关于按钮
    @Override
    public void draw(GLEx glEx) {

    }
    private void initViews(){
        background= new LPaper(MyAssets.MAIN_SCREEN_BACKGROUND);
        add(background);
        title= new LPaper(MyAssets.MAIN_SCREEN_TITLE);
        title.setLocation(getWidth()/2-title.getWidth()/2,margin);
        add(title);
        btnStart= new LPaper(MyAssets.BTN_START){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    replaceScreen(new WorldChooseScreen(),MoveMethod.FROM_UP);
                    //setScreen(new WorldChooseScreen());
                }
            }
        };
        btnScoreboard= new LPaper(MyAssets.BTN_SCOREBOARD){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    replaceScreen(new ScoreboardScreen(),MoveMethod.FROM_RIGHT);
                    //setScreen(new ScoreboardScreen());
                }
            }
        };
        btnScoreboard.setVisible(false);//积分榜功能停用
        btnExit= new LPaper(MyAssets.BTN_EXIT){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    System.exit(-1);
                }
            }
        };
        btnBgMusicOpen = new LPaper(MyAssets.BTN_BG_MUSIC_OPEN){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    prefs=MainActivity.mainActivity.getSharedPreferences(Macro.PREFS_FILE,MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    if (prefs.getString(Macro.BG_MUSIC,Macro.CLOSE).equals(Macro.CLOSE)){//close->open
                        editor.putString(Macro.BG_MUSIC,Macro.OPEN);
                    }else {//open->close
                        editor.putString(Macro.BG_MUSIC,Macro.CLOSE);
                    }
                    editor.commit();
                    applyPrefs();//更改按钮状态以及音乐播放服务
                }
            }
        };
        btnBgMusicClose= new LPaper(MyAssets.BTN_BG_MUSIC_CLOSE){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    prefs=MainActivity.mainActivity.getSharedPreferences(Macro.PREFS_FILE,MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    if (prefs.getString(Macro.BG_MUSIC,Macro.CLOSE).equals(Macro.CLOSE)){//close->open
                        editor.putString(Macro.BG_MUSIC,Macro.OPEN);
                    }else {//open->close
                        editor.putString(Macro.BG_MUSIC,Macro.CLOSE);
                    }
                    editor.commit();
                    applyPrefs();//更改按钮状态以及音乐播放服务
                }
            }
        };
        btnAbout= new LPaper(MyAssets.BTN_ABOUT){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    replaceScreen(new AboutScreen(),MoveMethod.FROM_DOWN);
                }
            }
        };
        centerOn(btnStart);
        btnScoreboard.setLocation(btnStart.getX(),btnStart.getY()+btnStart.height()+margin);
        btnExit.setLocation(btnScoreboard.getX(),btnScoreboard.getY()+btnScoreboard.height()+margin);
        btnBgMusicOpen.setLocation(getWidth()-btnAbout.getWidth()-margin,margin);
        btnBgMusicClose.setLocation(getWidth()-btnAbout.getWidth()-margin,margin);
        btnAbout.setLocation(getWidth()-btnAbout.getWidth()-margin,getHeight()-btnAbout.getHeight()-margin);

        btnStart.setEnabled(true);
        btnScoreboard.setEnabled(true);
        btnExit.setEnabled(true);
        btnBgMusicOpen.setEnabled(true);
        btnBgMusicClose.setEnabled(true);
        btnAbout.setEnabled(true);

        add(btnStart);
        add(btnScoreboard);
        add(btnExit);
        add(btnBgMusicOpen);
        add(btnBgMusicClose);
        add(btnAbout);
        applyPrefs();
    }
    void initBgMusicPrefs(){
        prefs= MainActivity.mainActivity.getSharedPreferences(Macro.PREFS_FILE,MODE_PRIVATE);
        if (prefs.getString(Macro.BG_MUSIC,"").equals("")) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(Macro.BG_MUSIC, Macro.OPEN);
            editor.commit();
        }
    }

    private void applyPrefs(){
        prefs= MainActivity.mainActivity.getSharedPreferences(Macro.PREFS_FILE,MODE_PRIVATE);
        Intent intent= new Intent(MainActivity.mainActivity,MusicService.class);
        if (prefs.getString(Macro.BG_MUSIC,Macro.CLOSE).equals(Macro.OPEN)) {
            //btnBgMusicOpen.setBackground(MyAssets.BTN_BG_MUSIC_OPEN);
            btnBgMusicOpen.setVisible(true);
            btnBgMusicClose.setVisible(false);
            intent.putExtra(Macro.BG_MUSIC,prefs.getString(Macro.BG_MUSIC,Macro.OPEN));//open or close
        }else {
            //btnBgMusicOpen.setBackground(MyAssets.BTN_BG_MUSIC_CLOSE);
            btnBgMusicOpen.setVisible(false);
            btnBgMusicClose.setVisible(true);
            intent.putExtra(Macro.BG_MUSIC,prefs.getString(Macro.BG_MUSIC,Macro.CLOSE));//open or close
        }
        intent.putExtra(Macro.BG_MUSIC_SOURCE,MyAssets.WELCOME_BG_MUSIC);
        MainActivity.mainActivity.startService(intent);
    }

    @Override
    public void onLoad() {
        Log.i("yaoling1997","screenNum: "+getScreenCount());
        initBgMusicPrefs();
        initViews();
        //playSound(MyAssets.WELCOME_BG_MUSIC,true);//音乐放不完整
//        prefs= MainActivity.mainActivity.getSharedPreferences(Macro.PREFS_FILE,MODE_PRIVATE);
//        Intent intent= new Intent(MainActivity.mainActivity,MusicService.class);
//        intent.putExtra(Macro.BG_MUSIC,prefs.getString(Macro.BG_MUSIC,Macro.OPEN));//open or close
//        MainActivity.mainActivity.startService(intent);

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
