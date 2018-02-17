package com.example.yaolingjump;

import loon.action.map.TileMap;
import loon.action.sprite.Animation;
import loon.action.sprite.JumpObject;

/**
 * Created on 2018/2/16.
 */

public class Hero extends JumpObject {
    public static final int stillWidth =28;
    public static final int stillHeight =35;
    public static final int downWidth=28;
    public static final int downHeight=21;

    public Animation heroAnimation_still;//主角静止动画
    public Animation heroAnimation_dead;//主角死亡动画
    public Animation heroAnimation_run;//主角奔跑动画
    public Animation heroAnimation_jump;//主角跳跃动画
    public Animation heroAnimation_down;//主角趴下动画
    public Animation heroAnimation_rest;//主角休息动画
    public boolean isDead;
    public boolean isDown;
    public long stillTime=0;
    public Hero(float v, float v1, float v2, float v3, Animation animation, TileMap tileMap) {
        super(v, v1, v2, v3, animation, tileMap);
        isDead =false;
        isDown=false;
        setSpeed(5);
        heroAnimation_still= Animation.getDefaultAnimation(MyAssets.HERO_STILL,122,173,150);
        heroAnimation_dead= Animation.getDefaultAnimation(MyAssets.HERO_DEAD,312,297,150);
        heroAnimation_run= Animation.getDefaultAnimation(MyAssets.HERO_RUN,122,173,80);
        heroAnimation_jump= Animation.getDefaultAnimation(MyAssets.HERO_JUMP,256,240,150);
        heroAnimation_down= Animation.getDefaultAnimation(MyAssets.HERO_DOWN,256,183,150);
        heroAnimation_rest= Animation.getDefaultAnimation(MyAssets.HERO_REST,215,335,150);
        //GRAVITY=0.5f;
    }
    public void dead(){
        isDead =true;
    }

    public void setDown(boolean down) {
        isDown = down;
    }
    @Override
    public void update(long l) {
        stillTime+= l;
        if (isDead) {
            stillTime=0;
            setSize(stillWidth,stillHeight);
            setAnimation(heroAnimation_dead);
        }else if (isDown) {
            stillTime=0;
            setSize(downWidth,downHeight);
            setAnimation(heroAnimation_down);
            isDown=false;
            stop();
        }else if (vy!=0) {
            stillTime=0;
            setSize(stillWidth,stillHeight);
            setAnimation(heroAnimation_jump);
        }else if (vx!=0) {
            stillTime=0;
            setSize(stillWidth,stillHeight);
            setAnimation(heroAnimation_run);
        }else if (stillTime<=5000){
            setSize(stillWidth,stillHeight);
            setAnimation(heroAnimation_still);
        }else {
            setSize(stillWidth,stillHeight);
            setAnimation(heroAnimation_rest);
        }
        super.update(l);
    }
}
