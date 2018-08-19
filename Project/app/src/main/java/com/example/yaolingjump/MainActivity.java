package com.example.yaolingjump;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.yaolingjump.Macro.Macro;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;
import com.example.yaolingjump.screen.MainScreen;

import loon.LSetting;
import loon.Screen;
import loon.android.Loon;
import loon.component.LPaper;
import loon.event.SysKey;

public class MainActivity extends Loon{
    public static MainActivity mainActivity;
    public static GameScreen gs=null;
    //还得自己弄操作手柄，渣渣LGame
    public LinearLayout linearLayout;
    public Button padLeft,padRight;
    @Override
    public void onMain() {//android:configChanges="orientation|screenSize|keyboardHidden，注意得有screenSize，不然锁屏会有问题
        LSetting setting= new LSetting();
        setting.width=480;
        setting.height=320;
        setting.logoPath= MyAssets.LOGO;
        setting.isLogo=true;//是否在游戏进入前显示标志
        setting.isFPS=false;//是否显示FPS
        setting.fontName="黑体";//字体
        setting.appName= Macro.GAME_NAME;
        mainActivity=this;

        register(setting, new Data() {
            @Override
            public Screen onScreen() {
                return new MainScreen();
            }
        });
    }
    private void initPad(){
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        padLeft = new android.support.v7.widget.AppCompatButton(this){
            public boolean isHit(LPaper a,float x,float y){
                x=x/(getWidth()*2);
                y=y/getHeight();
                return (a.getX()/gs.getWidth()<=x&&x<=(a.getX()+a.getWidth())/gs.getWidth())&&
                        (a.getY()/gs.getHeight()<=y&&y<=(a.getY()+a.getHeight())/gs.getHeight());
            }
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                Log.i("yaoling1997","padLeft onTouchEvent:"+event.getAction());
                if (gs!=null){
                    int action= event.getAction();
                    if (action==MotionEvent.ACTION_UP||
                            action==MotionEvent.ACTION_POINTER_UP||
                            action==MotionEvent.ACTION_POINTER_2_UP) {
                        gs.releaseActionKeys();
                        return super.onTouchEvent(event);
                    }
                    if (gs.hero.isDead)
                        return super.onTouchEvent(event);
                    float x=event.getX();
                    float y=event.getY();
                    Log.i("yaoling1997","padLeft touch location:("+x/getWidth()+","+y/getHeight()+")");
                    Log.i("yaoling1997","btnUp location:("+gs.btnLeft.getX()/gs.getWidth()+","+gs.btnLeft.getY()/gs.getHeight()+")");
                    if (gs.btnLeft!=null&&isHit(gs.btnLeft,x,y)){
                        gs.pressActionKey(SysKey.LEFT);
                        return super.onTouchEvent(event);
                    }else if (gs.btnRight!=null&&isHit(gs.btnRight,x,y)){
                        gs.pressActionKey(SysKey.RIGHT);
                        return super.onTouchEvent(event);
                    }else {
                        gs.releaseActionKeys();
                        return super.onTouchEvent(event);
                    }
                }
                return super.onTouchEvent(event);
            }
        };
        padRight= new android.support.v7.widget.AppCompatButton(this){
            public boolean isHit(LPaper a,float x,float y){
                x=(x+getWidth())/(getWidth()*2);
                y=y/getHeight();
                return (a.getX()/gs.getWidth()<=x&&x<=(a.getX()+a.getWidth())/gs.getWidth())&&
                        (a.getY()/gs.getHeight()<=y&&y<=(a.getY()+a.getHeight())/gs.getHeight());
            }
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                Log.i("yaoling1997","padRight onTouchEvent:"+event.getAction());
                if (gs!=null){
                    int action= event.getAction();
                    if (action==MotionEvent.ACTION_UP||
                            action==MotionEvent.ACTION_POINTER_UP||
                            action==MotionEvent.ACTION_POINTER_2_UP) {
                        gs.releaseActionKeys();
                        return super.onTouchEvent(event);
                    }
                    if (gs.hero.isDead)
                        return super.onTouchEvent(event);
                    float x=event.getX();
                    float y=event.getY();
                    Log.i("yaoling1997","padRight touch location:("+x/getWidth()+","+y/getHeight()+")");
                    Log.i("yaoling1997","btnUp location:("+gs.btnUp.getX()/gs.getWidth()+","+gs.btnUp.getY()/gs.getHeight()+")");
                    if (gs.btnUp!=null&&isHit(gs.btnUp,x,y)){
                        Log.i("yaoling1997", "gs.btnUp");
                        //gs.pressActionKey(SysKey.UP);
                        Log.i("yaoling1997", "gs.hero.jump()");
                        gs.hero.jump();
                        return super.onTouchEvent(event);
                    }else if (gs.btnDown!=null&&isHit(gs.btnDown,x,y)){
                        gs.releaseActionKeys();
                        gs.pressActionKey(SysKey.DOWN);
                        return super.onTouchEvent(event);
                    }else if (gs.btnSpell!=null&&isHit(gs.btnSpell,x,y)){
                        gs.releaseActionKeys();
                        gs.hero.castSpell();
                        return super.onTouchEvent(event);
                    }else {
                        gs.releaseActionKeys();
                        return super.onTouchEvent(event);
                    }
                }
                return super.onTouchEvent(event);
            }
        };
        padLeft.setBackgroundColor(Color.TRANSPARENT);
        padRight.setBackgroundColor(Color.TRANSPARENT);
        padLeft.setClickable(false);
        padRight.setClickable(false);

        linearLayout.addView(padLeft);
        linearLayout.addView(padRight);
        linearLayout.setBackgroundColor(Color.TRANSPARENT);
        linearLayout.setClickable(false);

        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) padLeft.getLayoutParams();
        Log.i("yaoling1997","linearParams.width:"+linearParams.width);
        Log.i("yaoling1997","linearParams.height:"+linearParams.height);
        // 取控件aaa当前的布局参数
        linearParams.height = -1;
        linearParams.width = -1;
        linearParams.weight=1;
        padLeft.setLayoutParams(linearParams); // 使设置好的布局参数应用到控件

        linearParams=(LinearLayout.LayoutParams) padRight.getLayoutParams();
        linearParams.height = -1;
        linearParams.width = -1;
        linearParams.weight=1;
        padRight.setLayoutParams(linearParams);
        addView(linearLayout);
    }
    public static void stopBgMusic(){
        Intent intent= new Intent(MainActivity.mainActivity,MusicService.class);
        intent.putExtra(Macro.BG_MUSIC,Macro.CLOSE);//close
        intent.putExtra(Macro.BG_MUSIC_SOURCE,MyAssets.BATTLE_BG_MUSIC);
        MainActivity.mainActivity.startService(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("yaoling1997","onCreate");
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setContentView(R.layout.activity_main);
        initPad();
    }

    @Override
    protected void onStart() {
        Log.i("yaoling1997","onStart");
        super.onStart();
    }

    @Override
    protected void onRestart() {
        Log.i("yaoling1997","onRestart");
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Log.i("yaoling1997","onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("yaoling1997","onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("yaoling1997","onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("yaoling1997","onDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i("yaoling1997","onTouchEvent:"+event.getAction());
        //return super.onTouchEvent(event);
        return true;
    }
}
