package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MapChar;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/20.
 */

public class Door extends ActionObject {//地刺
    public char color;
    private Animation doorAnimation;
    public Door(float v, float v1, Animation animation, TileMap tileMap,char color) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        this.color=color;
        if (color== MapChar.GREEN_KEY)//钥匙颜色
            doorAnimation =Animation.getDefaultAnimation(MyAssets.GREEN_DOOR,32,32,150);
        else
            doorAnimation =Animation.getDefaultAnimation(MyAssets.YELLOW_DOOR,32,32,150);
        setAnimation(doorAnimation);
    }
    @Override
    public void update(long l) {
        animation.update(l);
    }

}
