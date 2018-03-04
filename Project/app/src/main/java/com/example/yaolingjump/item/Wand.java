package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/14.
 */

public class Wand extends ActionObject {
    public static final int score=1000;
    private Animation wandAnimation;
    public Wand(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        wandAnimation =Animation.getDefaultAnimation(MyAssets.WAND,184,186,200);
        setAnimation(wandAnimation);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }
}
