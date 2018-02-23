package com.example.yaolingjump.enemy;

import com.example.yaolingjump.Macro.Macro;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.Animation;
import loon.geom.Vector2f;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Created on 2018/2/21.
 */

public class FlyEnemy extends Enemy {
    protected float limit;//默认移动多少像素
    private float cnt;//累积移动了多少像素
    public FlyEnemy(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, animation, tileMap);
        vx=-speed;
        vy=0;
        cnt=0;
        limit= Macro.OO;
    }

    @Override
    public void update(long l) {
        cnt+= abs(vx)+abs(vy);
        if (cnt>=limit){//移动像素达到上限，反向
            cnt=0;
            vx*=-1;
            vy*=-1;
        }

        float x=getX();
        float y=getY();
        //判断预期坐标是否可以走，有没有障碍物（瓷砖）

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
            vy=-vy;
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
