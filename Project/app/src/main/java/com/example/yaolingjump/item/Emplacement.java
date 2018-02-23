package com.example.yaolingjump.item;

import android.util.Log;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

/**
 * Created on 2018/2/21.
 */

public class Emplacement extends ActionObject {//炮台
    private GameScreen gs;
    private Animation emplacementAnimation;
    private int limit;//多久发一次炮
    private int cnt;//准备了多久
    private int speed;//炮弹飞行速度
    private float length;//炮弹边长
    public Emplacement(float v, float v1, Animation animation, TileMap tileMap, GameScreen gs) {
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        emplacementAnimation =Animation.getDefaultAnimation(MyAssets.EMPLACEMENT,21,24,100);
        setAnimation(emplacementAnimation);
        this.gs= gs;
        limit=5000;
        cnt=0;
        speed=2;
        length=10;
    }
    @Override
    public void update(long l) {
        float vx,vy=0;//炮弹飞行速度
        if (gs.hero.getX()<getX()) {
            vx=-speed;
            setMirror(true);
        }else {
            vx=speed;
            setMirror(false);
        }
        if (cnt>=limit){//发了
            Log.i("yaoling1997","Emplacement update,vx= "+vx);
            cnt=0;
            gs.addHardItem(new Fire(getX()+getWidth()/2,getY()+2,vx,vy,length,length,gs.getEmptyAnimation(),tiles));
        }else{
            cnt+= l;
        }
        animation.update(l);
    }

}
