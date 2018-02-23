package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MapChar;
import com.example.yaolingjump.Macro.MyAssets;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/18.
 */

public class Key extends ActionObject {
    public static final int keyLength=30;
    public char color;
    private Animation greenKeyAnimation;
    public Key(float v, float v1, Animation animation, TileMap tileMap,char color) {
        super(v, v1, keyLength, keyLength, animation, tileMap);
        this.color=color;
        String animPath="";
        if (color== MapChar.GREEN_KEY)
            animPath= MyAssets.GREEN_KEY;
        else if (color== MapChar.YELLOW_KEY)
            animPath= MyAssets.YELLOW_KEY;
        greenKeyAnimation=Animation.getDefaultAnimation(animPath,340,618,150);
        setAnimation(greenKeyAnimation);
    }
    @Override
    public void update(long l) {
        animation.update(l);
    }
}
