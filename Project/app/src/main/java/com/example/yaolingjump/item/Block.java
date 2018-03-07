package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/14.
 */

public class Block extends ActionObject {//用来遮住一些乱七八糟的东西
    public static final int score=200;
    private Animation blockAnimation;
    public Block(float v, float v1, Animation animation, TileMap tileMap,String ass) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        blockAnimation =Animation.getDefaultAnimation(ass,32,32,200);
        setAnimation(blockAnimation);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }
}
