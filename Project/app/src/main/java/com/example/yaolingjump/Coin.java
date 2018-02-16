package com.example.yaolingjump;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/14.
 */

public class Coin extends ActionObject {

    private int score=1;
    public Coin(float v, float v1, Animation animation, TileMap tileMap) {
        super(v, v1, animation, tileMap);
    }

    @Override
    public void update(long l) {
        animation.update(l);
    }
    public int getScore() {
        return score;
    }
}
