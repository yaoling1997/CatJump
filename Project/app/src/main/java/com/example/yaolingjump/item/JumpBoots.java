package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/14.
 */

public class JumpBoots extends ActionObject {
    public static final int score=200;
    private Animation jumpBootsAnimation;
    public JumpBoots(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        jumpBootsAnimation =Animation.getDefaultAnimation(MyAssets.JUMP_BOOTS,109,94,200);
        setAnimation(jumpBootsAnimation);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }
}
