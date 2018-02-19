package com.example.yaolingjump.enemy;

import com.example.yaolingjump.Macro.MyAssets;

import loon.action.map.TileMap;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/18.
 */

public class Tortoise extends LandEnemy{//乌龟
    public static final int maxDefenseTime=10000;//最大的防御时间
    public static final int defenseSpeed=7;//防御时被踢飞的速度
    private Animation runAnimation;
    private Animation defenseAnimation;
    public boolean isDefense;
    private long defenseTime=0;
    public Tortoise(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, animation, tileMap);
        runAnimation=Animation.getDefaultAnimation(MyAssets.TORTOISE_RUN,327,204,200);
        defenseAnimation=Animation.getDefaultAnimation(MyAssets.TORTOISE_DEFENSE,257,148,200);
        setAnimation(runAnimation);
        isDefense=false;
        score=200;//干掉一个敌人加多少分
    }

    @Override
    public void update(long l) {
        if (defenseTime>=maxDefenseTime) {//超过最大防御时间
            defenseTime = 0;
            isDefense=false;
            float direction= vx==0?-1:vx/Math.abs(vx);
            vx= direction*speed;//还原速度，乌龟从壳里出来了
        }
        if (isDefense) {//防御状态
            if (vx==0)
                defenseTime+=l;//累加相邻两帧的时间
            else
                defenseTime=0;
            setAnimation(defenseAnimation);
        }else {
            defenseTime=0;
            setAnimation(runAnimation);
        }
        super.update(l);
    }
    public void resetDefenseTime(){
        defenseTime=0;
    }
    public void stop(){
        vx=0;
    }

}
