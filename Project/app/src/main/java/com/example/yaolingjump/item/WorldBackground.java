package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/14.
 */

public class WorldBackground extends ActionObject {
    public static final int score=200;
    private Animation worldBackgroundAnimation;
    public WorldBackground(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, tileMap.getWidth(), tileMap.getHeight(), animation, tileMap);
        worldBackgroundAnimation =Animation.getDefaultAnimation(MyAssets.WORLD1_BACKGROUND,480,320,200);
        setAnimation(worldBackgroundAnimation);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }
}
