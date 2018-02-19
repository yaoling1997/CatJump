package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/19.
 */

public class PassGate extends ActionObject {
    public static final int score=1000;
    private Animation passGateAnimation;
    public PassGate(float v, float v1, Animation animation, TileMap tileMap) {//占4个格子
        super(v, v1, 2*GameScreen.gridLength, 2*GameScreen.gridLength, animation, tileMap);
        passGateAnimation=Animation.getDefaultAnimation(MyAssets.PASS_GATE,100,82,80);
        setAnimation(passGateAnimation);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }

}
