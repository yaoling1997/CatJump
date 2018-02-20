package com.example.yaolingjump;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.Animation;
import loon.action.sprite.JumpObject;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created on 2018/2/16.
 */

public class Hero extends JumpObject {
    public static final int stillWidth =28;
    public static final int stillHeight =32;
    public static final int downWidth=28;
    public static final int downHeight=21;
    public static final int rest2Width =28;
    public static final int rest2Height =28;

    public Animation heroAnimation_still;//静止动画
    public Animation heroAnimation_dead;//死亡动画
    public Animation heroAnimation_run;//奔跑动画
    public Animation heroAnimation_jump;//跳跃动画
    public Animation heroAnimation_down;//趴下动画
    public Animation heroAnimation_rest;//休息动画
    public Animation heroAnimation_rest2;//休息动画2
    public Animation heroAnimation_ball;//球动画
    public boolean isDead;//是否死亡
    public boolean isDown;//是否趴下
    public boolean canBall;//是否能变成球
    public boolean isBall;//是否是球形态
    public long stillTime=0;

    public Hero(float v, float v1, float v2, float v3, Animation animation, TileMap tileMap) {
        super(v, v1, v2, v3, animation, tileMap);
        isDead =false;
        isDown=false;
        canBall=false;
        isBall=false;
        setJumperTwo(false);//不允许二级跳
        setSpeed(5);
        heroAnimation_still= Animation.getDefaultAnimation(MyAssets.HERO_STILL,122,173,150);
        heroAnimation_dead= Animation.getDefaultAnimation(MyAssets.HERO_DEAD,312,297,150);
        heroAnimation_run= Animation.getDefaultAnimation(MyAssets.HERO_RUN,122,173,80);
        heroAnimation_jump= Animation.getDefaultAnimation(MyAssets.HERO_JUMP,256,240,150);
        heroAnimation_down= Animation.getDefaultAnimation(MyAssets.HERO_DOWN,256,183,150);
        heroAnimation_rest= Animation.getDefaultAnimation(MyAssets.HERO_REST,215,335,150);
        heroAnimation_rest2= Animation.getDefaultAnimation(MyAssets.HERO_REST2,192,197,150);
        heroAnimation_ball= Animation.getDefaultAnimation(MyAssets.HERO_BALL,144,144,60);
        //GRAVITY=0.5f;
        setAnimation(heroAnimation_still);
    }
    public void dead(){
        isDead =true;
    }

    public void setDown(boolean down) {
        isDown = down;
    }
    @Override
    public void update(long l) {
        vy=min(vy, GameScreen.maxSpeed);
        stillTime+= l;

        if (vx!=0||vy!=0)//运动状态下不能趴下
            isDown=false;

        if (isDead) {
            stillTime=0;
            setSize(stillWidth,stillHeight);
            setAnimation(heroAnimation_dead);
        }else if (isDown) {
            stillTime=0;
            setSize(downWidth,downHeight);
            setAnimation(heroAnimation_down);
            isDown=false;
            isBall=false;
            stop();
        }else if (vy!=0) {
            stillTime=0;
            setSize(stillWidth,stillHeight);
            if (canBall) {
                setAnimation(heroAnimation_ball);
                isBall=true;
            }else {
                setAnimation(heroAnimation_jump);
            }
        }else {
            isBall=false;
            if (vx != 0) {
                stillTime = 0;
                setSize(stillWidth, stillHeight);
                setAnimation(heroAnimation_run);
            } else if (stillTime <= 5000) {
                setSize(stillWidth, stillHeight);
                setAnimation(heroAnimation_still);
            } else if (stillTime <= 10000){
                setSize(stillWidth, stillHeight);
                setAnimation(heroAnimation_rest);
            }else{
                setSize(rest2Width, rest2Height);
                setAnimation(heroAnimation_rest2);
            }
        }
        super.update(l);
    }
    public float getVx(){
        return vx;
    }

    @Override
    public void setSize(int i, int i1) {//重写改方法防止更改高度后角色从天降落
        float targetY=getY()+getHeight()-i1;
        setY(targetY);
        super.setSize(i, i1);
    }
    public void setVx(float vx){
        this.vx=vx;
    }
    public void setVy(float vy){
        this.vy=vy;
    }
}
