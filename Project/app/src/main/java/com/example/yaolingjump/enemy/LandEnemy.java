package com.example.yaolingjump.enemy;

import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;
import loon.geom.Vector2f;

import static java.lang.Math.max;

/**
 * Created on 2018/2/14.
 */

public abstract class LandEnemy extends Enemy {//陆地上的敌人，在地上走的那种
    public LandEnemy(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, animation, tileMap);
        vx=-speed;
        vy=0;
    }

    @Override
    public void update(long l) {
        float x=getX();
        float y=getY();
        //判断预期坐标是否可以走，有没有障碍物（瓷砖）
        vy= max(vy+0.6f,GameScreen.maxSpeed);
        float newY=y+vy;
        Vector2f tile= tiles.getTileCollision(this,x,newY);
        if (tile==null){
            y=newY;
        }else{
            if (vy>0){
                y=tiles.tilesToPixelsY(tile.y)-getHeight();
            }else if (vy<0){
                y=tiles.tilesToPixelsY(tile.y+1);
            }
            vy=0;
        }
        float newX=x+vx;
        tile= tiles.getTileCollision(this,newX,y);
        if (tile==null){//没有相撞
            x=newX;
        }else {//相撞了
            if (vx>0){//撞到右边障碍
                x=tiles.tilesToPixelsX(tile.x)-getWidth();
            }else if (vx<0){//撞到左边障碍
                x=tiles.tilesToPixelsX(tile.x+1);
            }
            vx=-vx;
        }
        //注入新坐标
        setLocation(x,y);
        super.update(l);
    }
}
