package com.example.yaolingjump;

import android.util.Log;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.enemy.Enemy;
import com.example.yaolingjump.item.Spell;
import com.example.yaolingjump.screen.GameScreen;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;
import loon.action.sprite.JumpObject;
import loon.canvas.LColor;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created on 2018/2/16.
 */

public class Hero extends JumpObject {
    public static final int stillWidth =28;
    public static final int stillHeight =32;
    public static final int downWidth=28;
    public static final int downHeight=21;
    public static final int rest2Width =28;
    public static final int rest2Height =28;
    public static final int spellWidth =40;
    public static final int spellHeight =40;

    private GameScreen gs;//游戏屏幕
    public Animation heroAnimation_still;//静止动画
    public Animation heroAnimation_dead;//死亡动画
    public Animation heroAnimation_run;//奔跑动画
    public Animation heroAnimation_jump;//跳跃动画
    public Animation heroAnimation_down;//趴下动画
    public Animation heroAnimation_rest;//休息动画
    public Animation heroAnimation_rest2;//休息动画2
    public Animation heroAnimation_ball;//球动画
    public Animation heroAnimation_spell;//丢技能动画
    public Animation heroAnimation_spell_copy;//丢技能动画副本
    public boolean isDead;//是否死亡
    public boolean isDown;//是否趴下
    public boolean canBall;//是否能变成球
    public boolean canCastSpell;//是否能丢技能
    public boolean isBall;//是否是球形态

    public boolean isDoubleSpeed;//速度是否翻倍

    public boolean isImmune;//是否无敌
    public long immuneTime;//免疫时间
    public long immuneColorChangeTime;//免疫状态下多久改变一次颜色时间
    public long maxImmuneColorChangeTime;//免疫状态下多久改变一次颜色时间
    public long maxImmuneTime;//最大免疫时间

    public boolean isCastingSpell;//正在释放技能
    public boolean spellCreated=false;//该次施法是否产生魔法弹
    public long stillTime=0;//连续静止时间
    public long castSpellTime=0;//释放技能时间
    public long spellCreatedTime=22*70;//魔法弹产生时间
    public float spellSpeed=8;//魔法弹飞行速度
    public long castSpellFinishedTime=24*70;//施法结束时间

    public Hero(float v, float v1, float v2, float v3, Animation animation, TileMap tileMap,GameScreen gs) {
        super(v, v1, v2, v3, animation, tileMap);
        this.gs=gs;
        isDead =false;
        isDown=false;
        canBall=false;
        canCastSpell=false;
        isBall=false;
        isDoubleSpeed =false;

        maxImmuneTime=2000;
        maxImmuneColorChangeTime = 100;
        stopImmune();

        isCastingSpell=false;
        setJumperTwo(false);//不允许二级跳
        setSpeed(3.5f);
        heroAnimation_still= Animation.getDefaultAnimation(MyAssets.HERO_STILL,122,173,150);
        heroAnimation_dead= Animation.getDefaultAnimation(MyAssets.HERO_DEAD,312,297,150);
        heroAnimation_run= Animation.getDefaultAnimation(MyAssets.HERO_RUN,122,173,80);
        heroAnimation_jump= Animation.getDefaultAnimation(MyAssets.HERO_JUMP,256,240,150);
        heroAnimation_down= Animation.getDefaultAnimation(MyAssets.HERO_DOWN,256,183,150);
        heroAnimation_rest= Animation.getDefaultAnimation(MyAssets.HERO_REST,215,335,150);
        heroAnimation_rest2= Animation.getDefaultAnimation(MyAssets.HERO_REST2,192,197,150);
        heroAnimation_ball= Animation.getDefaultAnimation(MyAssets.HERO_BALL,144,144,60);
        heroAnimation_spell= Animation.getDefaultAnimation(MyAssets.HERO_SPELL,140,135,70);
        heroAnimation_spell_copy=new Animation(heroAnimation_spell);
        //GRAVITY=0.5f;
        setAnimation(heroAnimation_still);
    }
    public void dead(){
        isDead =true;
    }

    public void setDown(boolean down) {
        isDown = down;
    }
    private void resetCastingSpell(){//重置施法
        isCastingSpell=false;
        spellCreated=false;
        castSpellTime=0;
        heroAnimation_spell=new Animation(heroAnimation_spell_copy);
    }
    @Override
    public void update(long l) {
        vy=min(vy, GameScreen.maxSpeed);
        stillTime+= l;

        if (vx!=0||vy!=0) {//运动状态下不能趴下和释放技能
            isDown = false;
            resetCastingSpell();
        }

        if (isImmune){
            immuneTime+=l;
            immuneColorChangeTime+=l;
            if (immuneColorChangeTime>=maxImmuneColorChangeTime) {
                if (getFilterColor().equals(LColor.transparent))
                    setFilterColor(LColor.darkGray);
                else
                    setFilterColor(LColor.transparent);
                immuneColorChangeTime=0;
            }
            if (immuneTime>=maxImmuneTime){
                stopImmune();
            }
        }

        if (isDead) {
            stillTime=0;
            setSize(stillWidth,stillHeight);
            setAnimation(heroAnimation_dead);
        }else if (isDown) {
            stillTime=0;
            setSize(downWidth,downHeight);
            setAnimation(heroAnimation_down);
            isDown=false;
            isBall=false;
            resetCastingSpell();
            stop();
        }else if (isCastingSpell){
            stillTime=0;
            castSpellTime+=l;
            Log.i("yaoling1997","castSpellTime: "+castSpellTime);
            setSize(spellWidth,spellHeight);
            setAnimation(heroAnimation_spell);
            if (!spellCreated&&castSpellTime>=spellCreatedTime){//生成魔法弹
                spellCreated=true;
                float spellVx=spellSpeed;
                if (mirror)
                    spellVx*=-1;
                gs.addHardItem(new Spell(getX()+getWidth()/2-(mirror?Spell.maxWidth:0),getY()+getHeight()/2-5,spellVx,
                        0,gs.getEmptyAnimation(),tiles));
            }else if (castSpellTime>=castSpellFinishedTime){
                resetCastingSpell();
            }
        }else if (vy!=0) {
            stillTime=0;
            setSize(stillWidth,stillHeight);
            if (canBall) {
                setAnimation(heroAnimation_ball);
                isBall=true;
            }else {
                setAnimation(heroAnimation_jump);
            }
        }else {
            isBall=false;
            if (vx != 0) {
                stillTime = 0;
                setSize(stillWidth, stillHeight);
                setAnimation(heroAnimation_run);
            } else if (stillTime <= 5000) {
                setSize(stillWidth, stillHeight);
                setAnimation(heroAnimation_still);
            } else if (stillTime <= 10000){
                setSize(stillWidth, stillHeight);
                setAnimation(heroAnimation_rest);
            }else{
                setSize(rest2Width, rest2Height);
                setAnimation(heroAnimation_rest2);
            }
        }
        super.update(l);
    }
    public float getVx(){
        return vx;
    }

    @Override
    public void setSize(int i, int i1) {//重写改方法防止更改高度后角色从天降落
        float targetY=getY()+getHeight()-i1;
        setY(targetY);
        super.setSize(i, i1);
    }
    public void setVx(float vx){
        this.vx=vx;
    }
    public void setVy(float vy){
        this.vy=vy;
    }
    public boolean stepOn(ActionObject b){//碰撞的时候判断是否踩了b
        return getY()+getHeight()<b.y()+b.getHeight()/2;
    }
    public boolean whetherAttackEnemy(Enemy b){//碰撞的时候判断是否攻击了敌人
        return !isDead&&(isBall||stepOn(b));
    }
    public void castSpell(){//释放技能
        if (vx==0&&vy==0&&canCastSpell){
            isCastingSpell=true;
        }
    }

    public void startImmune(){//开启无敌
        isImmune=true;
        immuneTime=0;
        immuneColorChangeTime=0;
    }
    public void stopImmune(){//停止无敌
        setFilterColor(LColor.transparent);
        isImmune=false;
        immuneTime=0;
        immuneColorChangeTime=0;
    }

    public void startDoubleSpeed(){//开启双倍速度
        if (!isDoubleSpeed){
            isDoubleSpeed=true;
            gs.isDoubleSpeed=true;
            setSpeed(getSpeed()*2);
        }
    }
    public void stopDoubleSpeed(){//关闭双倍速度
        if (isDoubleSpeed){
            isDoubleSpeed=false;
            gs.isDoubleSpeed=false;
            setSpeed(getSpeed()/2);
        }
    }

    public void startJumperTwo(){//开启二级跳
        gs.jumperTwo=true;
        setJumperTwo(true);
    }
    public void stopJumperTwo(){//关闭二级跳
        gs.jumperTwo=false;
        setJumperTwo(false);
    }

    public void enableSpell(){//使英雄可以使用技能
        canCastSpell=true;
        gs.canCastSpell=true;
        if (gs.btnSpell!=null)
            gs.btnSpell.setVisible(true);
    }
    public void forbidSpell(){//使英雄不可以使用技能
        canCastSpell=false;
        gs.canCastSpell=false;
        if (gs.btnSpell!=null)
            gs.btnSpell.setVisible(false);
    }

}
