package com.example.yaolingjump.item;

import android.util.Log;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/3/3.
 */

public class Spell extends ActionObject {
    public static final float maxWidth=64;//最大多宽
    public static final float maxHeight=24;//最大多高
    private Animation spellAnimation;
    public float vx=0;//横向速度
    public float vy=0;//纵向速度
    public float currentScale;//当前放大倍数
    public float addScale;//每次增大多少倍
    public long flyTime;//飞行时间
    public long maxFlyTime;//最大飞行时间
    public Spell(float v, float v1, float vx, float vy, float width,float height,Animation animation, TileMap tileMap) {//移动砖块的类型，垂直移动和水平移动
        super(v, v1, width, height, animation, tileMap);
        Log.i("yaoling1997","魔法弹生成");
        spellAnimation =Animation.getDefaultAnimation(MyAssets.SPELL,196,75,100);
        setAnimation(spellAnimation);
        this.vx= vx;
        this.vy= vy;
        currentScale=0;
        addScale=0.5f;
        flyTime=0;
        maxFlyTime=2000;
        if (vx<0)
            setMirror(true);
        else
            setMirror(false);
        setScale(currentScale);
    }
    public Spell(float v, float v1, float vx, float vy, Animation animation, TileMap tileMap) {//移动砖块的类型，垂直移动和水平移动
        this(v, v1, vx, vy, maxWidth, maxHeight, animation, tileMap);
    }
    @Override
    public void update(long l) {
        setLocation(getX()+vx,getY()+vy);
        if (currentScale<1) {
            currentScale += addScale;
            setScale(currentScale);
        }
        flyTime+=l;
        if (vx<0)
            setMirror(true);
        else
            setMirror(false);
        animation.update(l);
    }

}
