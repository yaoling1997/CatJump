package com.example.yaolingjump.enemy;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.item.Fire;
import com.example.yaolingjump.screen.GameScreen;

import java.util.Random;

import loon.action.map.TileMap;
import loon.action.sprite.Animation;
import loon.geom.Vector2f;

/**
 * Created on 2018/2/22.
 */

public class FireBoss extends LandEnemy{
    private GameScreen gs;
    private Animation runAnimation;
    private Animation defenseAnimation;
    private int directionChangeLimit;//多久改变一次方向
    private int directionChangeCnt;//多久改变一次方向累积时间
    private int bulletReadyLimit;//多久发一次炮
    private int bulletReadyCnt;//准备了多久
    private int defenseLimit;//防御时间上限
    public int defenseCnt;//防御了多久
    private int bulletSpeed;//炮弹飞行速度
    private float bulletLength;//炮弹边长
    public boolean isDefense;//是否处于防御状态

    public FireBoss(float v, float v1, Animation animation, TileMap tileMap, GameScreen gs) {
        super(v, v1,animation, tileMap);
        runAnimation=Animation.getDefaultAnimation(MyAssets.FIRE_BOSS,50,40,100);
        defenseAnimation=Animation.getDefaultAnimation(MyAssets.FIRE,52,66,100);
        setAnimation(runAnimation);
        this.gs= gs;
        int len=(int)(GameScreen.gridLength*2.5);
        setSize(len,len);
        score=1000;
        HP=10;//踩10次就死了
        directionChangeLimit=3000;
        directionChangeCnt=0;
        bulletReadyLimit=3000;
        bulletReadyCnt=0;
        defenseLimit=3000;
        defenseCnt=0;
        bulletSpeed=2;
        bulletLength=30;
        isDefense=false;
    }

    @Override
    public void damage() {
        if (isDefense)
            return;
        super.damage();
        if (HP==5){
            isDefense=true;
            setAnimation(defenseAnimation);
        }
    }

    public boolean isDead(){
        return HP<=0;
    }

    @Override
    public void update(long l) {
        directionChangeCnt += l;
        bulletReadyCnt+=l;
        if (directionChangeCnt >= directionChangeLimit){
            directionChangeCnt =0;
            if (new Random().nextInt(2)==0)//随机选择是否改变横向方向
                vx*=-1;
        }
        if (isDefense){
            defenseCnt+=l;
            setAnimation(defenseAnimation);
            if (defenseCnt>=defenseLimit){
                defenseCnt=0;
                setAnimation(runAnimation);
                isDefense=false;
            }
        }
        if (bulletReadyCnt>=bulletReadyLimit){//发炮了
            bulletReadyCnt=0;
            Vector2f a= new Vector2f(getX()+getWidth()/2,getY()+getHeight()/2);
            Vector2f b= new Vector2f(gs.hero.getX()+gs.hero.getWidth()/2,gs.hero.getY()+gs.hero.getHeight()/2);
            Vector2f v= b.sub(a);
            v=v.div(v.length()).mul(bulletSpeed);
            gs.addHardItem(new Fire(a.x-bulletLength,a.y-bulletLength,v.x,v.y,bulletLength,bulletLength,gs.getEmptyAnimation(),tiles));
        }
        super.update(l);
    }
    public boolean isImmune(){
        return isDefense&&defenseCnt>=100;
    }
}
