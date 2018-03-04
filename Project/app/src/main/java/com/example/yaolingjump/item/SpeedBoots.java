package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/14.
 */

public class SpeedBoots extends ActionObject {
    public static final int score=200;
    private Animation jumpBootsAnimation;
    public SpeedBoots(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        jumpBootsAnimation =Animation.getDefaultAnimation(MyAssets.SPEED_BOOTS,96,88,200);
        setAnimation(jumpBootsAnimation);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }
}
