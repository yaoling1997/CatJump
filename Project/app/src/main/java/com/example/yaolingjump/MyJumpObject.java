package com.example.yaolingjump;

import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;
import loon.action.sprite.JumpObject;
import loon.geom.Vector2f;

/**
 * Created on 2018/3/8.
 */

public class MyJumpObject extends ActionObject {
    private static final long serialVersionUID = 1L;
    public JumpObject.JumpListener listener;
    public float GRAVITY = 0.6F;
    protected float vx = 0.0F;
    protected float vy = 0.0F;
    private float speed = 6.0F;
    protected float jumpSpeed = 12.0F;
    private boolean onGround = false;
    private boolean forceJump = false;
    private boolean jumperTwo = false;
    private boolean canJumperTwo = true;

    public MyJumpObject(float var1, float var2, Animation var3, TileMap var4) {
        super(var1, var2, 0.0F, 0.0F, var3, var4);
    }

    public MyJumpObject(float var1, float var2, float var3, float var4, Animation var5, TileMap var6) {
        super(var1, var2, var3, var4, var5, var6);
    }

    public void stop() {
        this.vx = 0.0F;
    }

    public void accelerateLeft() {
        this.vx = -this.speed;
    }

    public void accelerateRight() {
        this.vx = this.speed;
    }

    public void accelerateUp() {
        this.vy = this.speed;
    }

    public void accelerateDown() {
        this.vy = -this.speed;
    }

    public void jump() {
        if(!this.onGround && !this.forceJump) {
            if(this.jumperTwo && this.canJumperTwo) {
                this.vy = -this.jumpSpeed;
                this.canJumperTwo = false;
            }
        } else {
            this.vy = -this.jumpSpeed;
            this.onGround = false;
            this.forceJump = false;
        }

    }

    public void setForceJump(boolean var1) {
        this.forceJump = var1;
    }

    public float getSpeed() {
        return this.speed;
    }

    public void setSpeed(float var1) {
        this.speed = var1;
    }

    public void setJumperTwo(boolean var1) {
        this.jumperTwo = var1;
    }

    public void update(long var1) {
        if(this.animation != null) {
            this.animation.update(var1);
        }

        TileMap var3 = this.tiles;
        float var4 = this.getX();
        float var5 = this.getY();
        this.vy += this.GRAVITY;
        float var6 = var4 + this.vx;
        Vector2f var7 = var3.getTileCollision(this, var6, var5);
        if(var7 == null) {
            var4 = var6;
        } else {
            if(this.vx > 0.0F) {
                var4 = (float)var3.tilesToPixelsX(var7.x) - this.getWidth();
            } else if(this.vx < 0.0F) {
                var4 = (float)var3.tilesToPixelsY(var7.x + 1.0F);
            }

            this.vx = 0.0F;
        }

        float var8 = var5 + this.vy;
        var7 = var3.getTileCollision(this, var4, var8);
        if(var7 == null) {
            var5 = var8;
            this.onGround = false;
        } else if(this.vy > 0.0F) {
            var5 = (float)var3.tilesToPixelsY(var7.y) - this.getHeight();
            this.vy = 0.0F;
            this.onGround = true;
            this.canJumperTwo = true;
        } else if(this.vy < 0.0F) {
            var5 = (float)var3.tilesToPixelsY(var7.y + 1.0F);
            this.vy = 0.0F;
            this.isCheck(var7.x(), var7.y());
        }

        this.setLocation(var4, var5);
        if(this.listener != null) {
            this.listener.update(var1);
        }

    }

    public void isCheck(int var1, int var2) {
        if(this.listener != null) {
            this.listener.check(var1, var2);
        }

    }

    public JumpObject.JumpListener getJumpListener() {
        return this.listener;
    }

    public void setJumpListener(JumpObject.JumpListener var1) {
        this.listener = var1;
    }

    public interface JumpListener {
        void update(long var1);

        void check(int var1, int var2);
    }

}
