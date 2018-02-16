package com.example.yaolingjump;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;
import loon.geom.Vector2f;

/**
 * Created on 2018/2/14.
 */

public class Enemy extends ActionObject {
    private static final float SPEED=1;
    protected float vx;
    protected float vy;
    public Enemy(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength,animation, tileMap);
        vx=-SPEED;
        vy=0;
    }

    @Override
    public void update(long l) {
        float x=getX();
        float y=getY();
        vy+= 0.6f;
        float newX=x+vx;
        //判断预期坐标是否可以走，有没有障碍物（瓷砖）
        Vector2f tile= tiles.getTileCollision(this,newX,y);
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
        float newY=y+vy;
        tile=tiles.getTileCollision(this,x,newY);
        if (tile==null){
            y=newY;
        }else{
            if (vy>0){
                y=tiles.tilesToPixelsY(tile.y)-getHeight();
            }else if (vy<0){
                y=tiles.tilesToPixelsY(tile.y+1);
                vy=0;
            }
        }
        animation.update(l);
        //注入新坐标
        setLocation(x,y);
    }
}
