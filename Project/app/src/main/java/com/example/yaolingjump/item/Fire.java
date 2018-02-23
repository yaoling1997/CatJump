package com.example.yaolingjump.item;

import android.util.Log;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Created on 2018/2/21.
 */

public class Fire extends ActionObject {
    private Animation fireAnimation;
    public float vx=0;//横向速度
    public float vy=0;//纵向速度
    public Fire(float v, float v1, float vx, float vy, float width,float height,Animation animation, TileMap tileMap) {//移动砖块的类型，垂直移动和水平移动
        super(v, v1, width, height, animation, tileMap);
        fireAnimation =Animation.getDefaultAnimation(MyAssets.FIRE,52,66,100);
        setAnimation(fireAnimation);
        this.vx= vx;
        this.vy= vy;
    }
    public Fire(float v, float v1, float vx, float vy, Animation animation, TileMap tileMap) {//移动砖块的类型，垂直移动和水平移动
        this(v, v1, vx, vy, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
    }
    @Override
    public void update(long l) {
        setLocation(getX()+vx,getY()+vy);
        if (vx<0)
            setMirror(true);
        else
            setMirror(false);
        animation.update(l);
    }

}
