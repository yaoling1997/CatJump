package com.example.yaolingjump;

import android.util.Log;

import loon.action.ActionBind;
import loon.action.ActionListener;
import loon.action.RotateTo;
import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;
import loon.action.sprite.JumpObject;
import loon.action.sprite.SpriteBatch;
import loon.action.sprite.SpriteBatchScreen;
import loon.canvas.LColor;
import loon.component.LLayer;
import loon.component.LPad;
import loon.component.LTextArea;
import loon.event.ActionKey;
import loon.event.GameKey;
import loon.event.GameTouch;
import loon.event.SysKey;
import loon.font.LFont;
import loon.opengl.GLEx;

/**
 * Created on 2018/2/14.
 */

public class GameScreen extends SpriteBatchScreen {
    public static final int gridLength=32;
    private static final int initHP=3;
    private TileMap tileMap;//绘制的地图
    private int [][]indexMap;//绘制地图对应的二维数组
    private JumpObject hero;//玩家控制的主角
    private Animation heroAnimation;//主角动画
    private Animation coinAnimation;//金币动画
    private Animation enemyAnimation;//敌人动画
    private Animation accelAnimation;//加速道具动画
    private Animation jumperTwoAnimation;//二级跳动画
    private RotateTo rotate;//旋转

    private int score=0;//得分
    private int HP=0;//角色当前生命值
    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }
    private void initAnimation(){
        coinAnimation=Animation.getDefaultAnimation("assets/coin.png",32,32,200);
        enemyAnimation=Animation.getDefaultAnimation("assets/enemy.gif",32,32,200);
        accelAnimation=Animation.getDefaultAnimation("assets/accelerator.gif",32,32,200);
        jumperTwoAnimation=Animation.getDefaultAnimation("assets/jumper_two.gif",32,32,200);
        //获得主角动画
        heroAnimation=Animation.getDefaultAnimation("assets/yaoling.png",122,173,150);
    }
    private boolean stepOn(ActionObject a,ActionObject b){//a是否踩了b
        return a.y()+a.getHeight()<b.y()+b.getHeight();
    }
    @Override
    public void create() {
        initAnimation();
        putReleases(coinAnimation,enemyAnimation,accelAnimation,jumperTwoAnimation,hero);
        HP=initHP;
        score=0;
        //读取地图
        tileMap =TileMap.loadCharsMap("assets/map.chr",gridLength,gridLength);
        //哪些地方不能走
        tileMap.setLimit(new int[]{'B','C','i','c'});
        //设置字符对应图片
        tileMap.putTile('B',"assets/block.png");
        tileMap.putTile('C',"assets/coin_block.gif");
        int imgId= tileMap.putTile('c',"assets/coin_block2.gif");
        tileMap.putTile('i',imgId);
        //加载地图
        putTileMap(tileMap);
        //获得地图对应的二维数组
        indexMap=tileMap.getMap();
        int colNum=tileMap.getRow();//得到宽度，列数
        int rowNum=tileMap.getCol();//得到高度，行数
        Log.i("yaoling1997","rowNum"+rowNum);
        Log.i("yaoling1997","colNum"+colNum);
        //添加物品到窗体
        for (int i=0;i<rowNum;i++)
            for (int j=0;j<colNum;j++) {
                Log.i("yaoling1997","i: "+i);
                Log.i("yaoling1997","j: "+j);
                switch (indexMap[i][j]) {
                    case 'o':
                        Coin coin= new Coin(tileMap.tilesToPixelsX(j),tileMap.tilesToPixelsY(i),
                                new Animation(coinAnimation),tileMap);
                        addTileObject(coin);
                        break;
                    case 'k':
                        Enemy enemy= new Enemy(tileMap.tilesToPixelsX(j),tileMap.tilesToPixelsY(i),
                                new Animation(enemyAnimation),tileMap);
                        addTileObject(enemy);
                        break;
                }
            }
        hero=addJumpObject(192,32,20,28,heroAnimation);
        hero.setJumperTwo(true);//允许二级跳
        // 让地图跟随指定对象产生移动（无论插入有多少张数组地图，此跟随默认对所有地图生效）
        follow(hero);
        // 监听跳跃事件
        hero.listener=new JumpObject.JumpListener() {
            @Override
            public void update(long l) {

            }
            // 检查角色与地图中瓦片的碰撞
            @Override
            public void check(int x, int y) {
                if (tileMap.getTileID(x,y)=='C'){
                    tileMap.setTileID(x,y,'c');
                    Enemy enemy= new Enemy(tileMap.tilesToPixelsX(x),tileMap.tilesToPixelsY(y-1),
                            new Animation(enemyAnimation),tileMap);
                    add(enemy);
                    // 标注地图已脏，强制缓存刷新
                    tileMap.setDirty(true);
                }
                x++;//判断右边相邻的格子
                if (tileMap.getTileID(x,y)=='C'&&hero.getX()+hero.getWidth()>tileMap.tilesToPixelsX(x)){
                    tileMap.setTileID(x,y,'c');
                    Enemy enemy= new Enemy(tileMap.tilesToPixelsX(x),tileMap.tilesToPixelsY(y-1),
                            new Animation(enemyAnimation),tileMap);
                    add(enemy);
                    // 标注地图已脏，强制缓存刷新
                    tileMap.setDirty(true);
                }
            }
        };
        // 对应向左行走的键盘事件
        ActionKey goLeftKey= new ActionKey(){
            @Override
            public void act(long l) {
                hero.setMirror(true);
                hero.accelerateLeft();
            }
        };
        addActionKey(SysKey.LEFT, goLeftKey);
        // 对应向右行走的键盘事件
        ActionKey goRightKey= new ActionKey(){
            @Override
            public void act(long l) {
                hero.setMirror(false);
                hero.accelerateRight();
            }
        };
        addActionKey(SysKey.RIGHT, goRightKey);
        // 对应跳跃的键盘事件（DETECT_INITIAL_PRESS_ONLY表示在放开之前，此按键不会再次触发）
        ActionKey jumpKey = new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY) {
            public void act(long e) {
                hero.jump();
            }
        };
        addActionKey(SysKey.UP, jumpKey);
        LPad pad= new LPad(10,180);
        pad.setListener(new LPad.ClickListener() {
            @Override
            public void up() {
                pressActionKey(SysKey.UP);
            }
            @Override
            public void down() {
                pressActionKey(SysKey.DOWN);
            }
            @Override
            public void left() {
                pressActionKey(SysKey.LEFT);
            }
            @Override
            public void right() {
                pressActionKey(SysKey.RIGHT);
            }
            @Override
            public void other() {
                releaseActionKeys();
            }
        });
        add(pad);
        // 地图中角色事件监听(每帧都会触发一次此监听)
        updateListener= new UpdateListener() {
            @Override
            public void act(ActionObject actionObject, long l) {
                if (hero.isCollision(actionObject)){
                    //与敌人相撞
                    if (actionObject instanceof Enemy){
                        Enemy e= (Enemy)actionObject;
                        if (stepOn(hero,e)){
                            hero.setForceJump(true);
                            hero.jump();
                            removeTileObject(e);
                        }else {
                            damage();
                        }
                    }else if (actionObject instanceof Coin){
                        Coin c=(Coin)actionObject;
                        score+= c.getScore();
                        removeTileObject(c);
                    }
                }
            }
        };
        Information information= new Information(this);
        //information居于屏幕顶端
        topOn(information);
        //添加information到screen
        add(information);
    }
    public void damage(){
        HP--;
        //主角被敌人干了
        if (rotate==null){
            //总共转多少度，每帧累加多少度
            rotate= new RotateTo(360f,5f);
            rotate.setActionListener(new ActionListener() {
                @Override
                public void start(ActionBind actionBind) {
                    hero.setFilterColor(LColor.gold);
                    hero.setForceJump(true);
                    hero.jump();
                }

                @Override
                public void process(ActionBind actionBind) {

                }

                @Override
                public void stop(ActionBind actionBind) {
                    hero.setFilterColor(LColor.white);
                    hero.setRotation(0);
                }
            });
            addAction(rotate,hero);
        }else if (rotate.isComplete()){
            //重置rotate对象
            rotate.start(hero);
            // 重新插入(LGame的方针是Action事件触发且结束后，自动删除该事件，所以需要重新插入)
            addAction(rotate,hero);
        }
    }
    @Override
    public void after(SpriteBatch spriteBatch) {

    }

    @Override
    public void before(SpriteBatch spriteBatch) {

    }

    @Override
    public void press(GameKey gameKey) {

    }

    @Override
    public void release(GameKey gameKey) {

    }

    @Override
    public void update(long l) {
        if (hero!=null){
            hero.stop();
        }
    }

    @Override
    public void dispose() {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void touchDown(GameTouch gameTouch) {

    }

    @Override
    public void touchUp(GameTouch gameTouch) {

    }

    @Override
    public void touchMove(GameTouch gameTouch) {

    }

    @Override
    public void touchDrag(GameTouch gameTouch) {

    }

    public int getScore() {
        return score;
    }

    public int getHP() {
        return HP;
    }
}
