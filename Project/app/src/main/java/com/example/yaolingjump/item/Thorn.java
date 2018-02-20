package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/20.
 */

public class Thorn extends ActionObject {
    private Animation thornAnimation;
    public Thorn(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        thornAnimation =Animation.getDefaultAnimation(MyAssets.THORN,307,159,150);
        setAnimation(thornAnimation);
    }
    @Override
    public void update(long l) {
        animation.update(l);
    }

}
