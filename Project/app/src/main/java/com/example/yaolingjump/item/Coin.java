package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/14.
 */

public class Coin extends ActionObject {
    public static final int score=200;
    private Animation coinAnimation;
    public Coin(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        coinAnimation=Animation.getDefaultAnimation(MyAssets.COIN,32,32,200);
        setAnimation(coinAnimation);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }
}
