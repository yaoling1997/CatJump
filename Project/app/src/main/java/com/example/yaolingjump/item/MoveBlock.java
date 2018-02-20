package com.example.yaolingjump.item;

import com.example.yaolingjump.Macro.MapChar;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;

import static java.lang.Math.abs;

/**
 * Created on 2018/2/20.
 */

public class MoveBlock extends ActionObject{
    private Animation moveBlockAnimation;
    private float speed;//移动速度
    private float cnt=0;//往一个方向移动的像素数
    private int limit= GameScreen.gridLength*2;//默认移动多少个格子
    public float vx=0;//横向速度
    public float vy=0;//纵向速度

    public MoveBlock(float v, float v1, Animation animation, TileMap tileMap,char type) {//移动砖块的类型，垂直移动和水平移动
        super(v, v1, GameScreen.gridLength, GameScreen.gridLength, animation, tileMap);
        moveBlockAnimation =Animation.getDefaultAnimation(MyAssets.MOVE_BLOCK,60,60,150);
        setAnimation(moveBlockAnimation);
        speed=0.3f;
        cnt=0;
        if (type== MapChar.MOVE_BLOCK_VERTICAL){
            vx=0;
            vy=-speed;
        }else{
            vx=-speed;
            vy=0;
        }
    }
    @Override
    public void update(long l) {
        setLocation(getX()+vx,getY()+vy);
        cnt+= abs(vx)+abs(vy);
        if (cnt>=limit){//移动像素达到上限，反向
            cnt=0;
            vx*=-1;
            vy*=-1;
        }
        animation.update(l);
    }

}
