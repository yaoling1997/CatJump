package com.example.yaolingjump.enemy;

import com.example.yaolingjump.Macro.MyAssets;

import loon.action.map.TileMap;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/18.
 */

public class Chestnut extends LandEnemy {//板栗
    public static final int score=100;
    private Animation runAnimation;
    public Chestnut(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, animation, tileMap);
        runAnimation=Animation.getDefaultAnimation(MyAssets.CHESTNUT,32,32,200);
        setAnimation(runAnimation);
    }
}
