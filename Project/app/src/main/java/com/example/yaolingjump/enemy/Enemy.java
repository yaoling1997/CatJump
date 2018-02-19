package com.example.yaolingjump.enemy;

import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/19.
 */

public class Enemy extends ActionObject {
    protected int score=0;
    public int speed =1;//起始速度
    public float vx;//横向速度
    public float vy;//纵向速度
    public boolean isDead;//是否死亡
    public Enemy(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength,animation, tileMap);
        vx=-speed;
        vy=0;
        isDead=false;//出生的那一刻是活着的
    }

    @Override
    public void update(long l) {
        animation.update(l);
        if (vx<0)        //设置镜像
            setMirror(true);
        else
            setMirror(false);
    }
    public int getScore() {
        return score;
    }
}
