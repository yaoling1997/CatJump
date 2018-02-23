package com.example.yaolingjump.enemy;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import java.util.Random;

import loon.action.map.TileMap;
import loon.action.sprite.Animation;

import static java.lang.Math.abs;

/**
 * Created on 2018/2/21.
 */

public class Bat extends FlyEnemy {

    private Animation flyAnimation;

    public Bat(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, animation, tileMap);
        flyAnimation =Animation.getDefaultAnimation(MyAssets.BAT_FLY,46,43,100);
        setAnimation(flyAnimation);
        score=200;
        limit=3*GameScreen.gridLength;
    }

    @Override
    public void update(long l) {
        super.update(l);
    }
}
