package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/14.
 */

public class HpPotion extends ActionObject {
    public static final int score=200;
    private Animation hpPotionAnimation;
    public HpPotion(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        hpPotionAnimation =Animation.getDefaultAnimation(MyAssets.HP_POTION,27,27,200);
        setAnimation(hpPotionAnimation);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }
}
