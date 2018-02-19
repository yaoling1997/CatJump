package com.example.yaolingjump.screen;

import android.util.Log;

import com.example.yaolingjump.Hero;
import com.example.yaolingjump.Information;
import com.example.yaolingjump.Macro.MapChar;
import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.enemy.Chestnut;
import com.example.yaolingjump.enemy.Enemy;
import com.example.yaolingjump.enemy.Tortoise;
import com.example.yaolingjump.item.Coin;
import com.example.yaolingjump.item.Key;
import com.example.yaolingjump.item.PassGate;

import java.util.ArrayList;

import loon.action.ActionBind;
import loon.action.ActionListener;
import loon.action.DelayTo;
import loon.action.RotateTo;
import loon.action.map.TileMap;
import loon.action.sprite.ActionObject;
import loon.action.sprite.Animation;
import loon.action.sprite.JumpObject;
import loon.action.sprite.SpriteBatch;
import loon.action.sprite.SpriteBatchScreen;
import loon.canvas.LColor;
import loon.component.LPad;
import loon.event.ActionKey;
import loon.event.GameKey;
import loon.event.GameTouch;
import loon.event.SysKey;

import static java.lang.Math.abs;

/**
 * Created on 2018/2/14.
 */

public class GameScreen extends SpriteBatchScreen {
    public static final int gridLength=35;
    public static final int maxSpeed=20;//物品运动速度不能超过每帧多少像素
    public static final int coinToHP=100;//100个硬币换一条命
    private static final int initHP=3;
    private TileMap tileMap;//绘制的地图
    private Hero hero;//玩家控制的主角
    private Animation emptyAnimation;//空动画(无奈用它的引擎必须要往构造函数里丢动画)
    private Animation backgroundAnimation;//背景动画
    private LPad pad;//玩家控制的键盘
    private int score=0;//得分
    private int coin=0;//收集的硬币数
    private int HP=0;//角色当前生命值

    private int world=1;//当前世界
    private int level=1;//当前世界的关卡

    private ArrayList<Enemy> enemyManager;
    public ArrayList<String> maps;//

    public GameScreen() {
        HP=initHP;
        score=0;
        maps= new ArrayList<>();
        maps.add(MyAssets.MAP1_1);
        maps.add(MyAssets.MAP1_2);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    private void initAnimation(){
        emptyAnimation=Animation.getDefaultAnimation(MyAssets.EMPTY,32,32,200);
        //backgroundAnimation=Animation.getDefaultAnimation(MyAssets.GAME_BACKGROUND,(int)tileMap.getWidth(),(int)tileMap.getHeight(),150);
    }

    private boolean stepOn(ActionObject a,ActionObject b){//a是否踩了b
        return a.y()+a.getHeight()<b.y()+b.getHeight()/2;
    }

    private void addEnemy(Enemy e){
        enemyManager.add(e);
        add(e);
    }
    private void removeEnemy(final Enemy e){
        addScore(e.getScore());
        enemyManager.remove(e);
        removeTileObject(e);
    }
    private void initMap(){
        //读取地图
        if (!maps.isEmpty())
            tileMap =TileMap.loadCharsMap(maps.get(0),gridLength,gridLength);
        else {//通关了
            return;
        }
        //哪些地方不能走
        tileMap.setLimit(new int[]{
                MapChar.BLOCK,
                MapChar.COIN_BLOCK,
                MapChar.CHESTNUT_BLOCK,
                MapChar.EMPTY_BLOCK,
                MapChar.GREEN_DOOR
        });
        //设置字符对应图片
        int tmp;
        tileMap.putTile(MapChar.BLOCK,MyAssets.BLOCK);
        tmp= tileMap.putTile(MapChar.COIN_BLOCK,MyAssets.QUESTION_BLOCK);
        tileMap.putTile(MapChar.CHESTNUT_BLOCK,tmp);
        tileMap.putTile(MapChar.EMPTY_BLOCK,MyAssets.EMPTY_BLOCK);
        tileMap.putTile(MapChar.GREEN_DOOR,MyAssets.GREEN_DOOR);
        //获得地图对应的二维数组
        int [][]indexMap=tileMap.getMap();
        int colNum=tileMap.getRow();//得到宽度，列数
        int rowNum=tileMap.getCol();//得到高度，行数
        Log.i("yaoling1997","rowNum"+rowNum);
        Log.i("yaoling1997","colNum"+colNum);
        //添加物品到窗体

        for (int i=0;i<rowNum;i++)
            for (int j=0;j<colNum;j++) {
                switch (indexMap[i][j]) {
                    case MapChar.COIN:
                        Coin coin= new Coin(tileMap.tilesToPixelsX(j),tileMap.tilesToPixelsY(i),
                                new Animation(emptyAnimation),tileMap);
                        addTileObject(coin);
                        break;
                    case MapChar.CHESTNUT:
                        Chestnut chestnut= new Chestnut(tileMap.tilesToPixelsX(j),tileMap.tilesToPixelsY(i),
                                new Animation(emptyAnimation),tileMap);
                        addEnemy(chestnut);
                        break;
                    case MapChar.TORTOISE:
                        Tortoise tortoise= new Tortoise(tileMap.tilesToPixelsX(j),tileMap.tilesToPixelsY(i),
                                new Animation(emptyAnimation),tileMap);
                        addEnemy(tortoise);
                        break;
                    case MapChar.GREEN_KEY:
                        Key key= new Key(tileMap.tilesToPixelsX(j),tileMap.tilesToPixelsY(i),
                                new Animation(emptyAnimation),tileMap,MapChar.GREEN_KEY);
                        add(key);
                        break;
                    case MapChar.PASS_GATE:
                        PassGate passGate= new PassGate(tileMap.tilesToPixelsX(j),tileMap.tilesToPixelsY(i),
                                new Animation(emptyAnimation),tileMap);
                        add(passGate);
                        break;
                    case MapChar.HERO:
                        hero= new Hero(tileMap.tilesToPixelsX(j),tileMap.tilesToPixelsY(i),Hero.stillWidth,Hero.stillHeight,emptyAnimation,tileMap);
                        add(hero);
                        break;
                }
            }
    }

    private void initHeroAction(){
        // 监听跳跃事件
        hero.listener=new JumpObject.JumpListener() {
            @Override
            public void update(long l) {

            }
            // 检查角色与地图中瓦片的碰撞
            @Override
            public void check(int x, int y) {
                hitBlock(x,y);
                x++;//判断右边相邻的格子
                if (hero.getX()+hero.getWidth()>tileMap.tilesToPixelsX(x)){
                    hitBlock(x,y);
                }
            }
        };
        // 对应向左行走的键盘事件
        ActionKey goLeftKey= new ActionKey(){
            @Override
            public void act(long l) {//满足玩家按键心理需求，镜像等通过按键来设置
                hero.setDown(false);
                hero.setMirror(true);
                hero.accelerateLeft();
            }
        };
        addActionKey(SysKey.LEFT, goLeftKey);
        // 对应向右行走的键盘事件
        ActionKey goRightKey= new ActionKey(){
            @Override
            public void act(long l) {
                hero.setDown(false);
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
        // 对应向右行走的键盘事件
        ActionKey downKey= new ActionKey(){
            @Override
            public void act(long l) {
                hero.setDown(true);
            }
        };
        addActionKey(SysKey.DOWN, downKey);
    }

    private void initPad(){//初始化玩家操控的键盘
        pad= new LPad(10,180);
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
                hero.setDown(false);
            }
        });
        add(pad);
    }

    private void initInformation(){
        Information information= new Information(this);
        //information居于屏幕顶端
        topOn(information);
        //添加information到screen
        add(information);
    }

    @Override
    public void create() {
        //setBackground("assets/game_background.jpg");
        enemyManager= new ArrayList<>();
        initAnimation();
        initMap();
        //putReleases(coinAnimation,enemyAnimation);
        setBackground(LColor.black);
        //加载地图
        putTileMap(tileMap);
        // 让地图跟随指定对象产生移动（无论插入有多少张数组地图，此跟随默认对所有地图生效）
        follow(hero);

        //初始化角色的行动
        initHeroAction();

        initPad();

        // 地图中角色事件监听(每帧都会触发一次此监听)
        updateListener= new UpdateListener() {
            @Override
            public void act(ActionObject actionObject, long l) {
                if (hero.isCollision(actionObject)){
                    if (actionObject instanceof Coin){//与硬币相撞
                        Coin c=(Coin)actionObject;
                        addScore(Coin.score);
                        addCoin(1);
                        removeTileObject(c);
                    }else if (actionObject instanceof Chestnut){//与板栗相撞
                        Chestnut chestnut= (Chestnut)actionObject;
                        if (stepOn(hero,chestnut)||hero.isBall){
                            hero.setForceJump(true);
                            hero.jump();
                            removeEnemy(chestnut);
                        }else {
                            damage();
                        }
                    }else if (actionObject instanceof Tortoise){//与乌龟相撞
                        Tortoise tortoise= (Tortoise)actionObject;
                        if (stepOn(hero,tortoise)||hero.isBall){//把乌龟踩停或撞停
                            tortoise.isDefense=true;
                            tortoise.resetDefenseTime();//重置累积的防御时间
                            tortoise.stop();//横向速度置为0
                            hero.setForceJump(true);
                            hero.jump();
                        }else {//没把乌龟踩停或撞停
                            if (tortoise.vx!=0) {//乌龟有横向速度
                                float tortoiseDirection= tortoise.vx/ abs(tortoise.vx);
                                if (hero.getVx()==0||tortoiseDirection!=hero.getVx()/abs(hero.getVx()))//角色横向静止或与角色横向方向不一致，GG
                                    damage();
                                else {//方向一致
                                    float tmp= hero.getX()-tortoise.getX();
                                    if (tmp/abs(tmp)==tortoiseDirection)//角色在乌龟运动前方，GG
                                        damage();
                                }
                            }else {
                                tortoise.vx= hero.getVx()/ abs(hero.getVx())*Tortoise.defenseSpeed;//让乌龟运动起来
                            }
                        }
                    }else if (actionObject instanceof Key){//与钥匙相撞
                        Key k=(Key)actionObject;
                        openDoor(k.color);
                        removeTileObject(k);
                    }else if (actionObject instanceof PassGate){//与过关门相撞
                        //PassGate p= (PassGate)actionObject;
                        addScore(PassGate.score);
                        maps.remove(0);
                        level++;
                        setScreen(new RestartScreen(GameScreen.this));
                    }
                }
            }
        };
        initInformation();
    }

    private void addScore(int x){
        score+=x;
    }

    private void addCoin(int x){
        coin+=x;
        if (coin>=coinToHP){
            addHP(coin/coinToHP);
            coin%=coinToHP;
        }
    }

    private void addHP(int x){
        HP+=x;
    }

    public void openDoor(char color){
        Log.i("yaoling1997","openDoor color:"+color);
        int [][]indexMap=tileMap.getMap();
        int colNum=tileMap.getRow();//得到宽度，列数
        int rowNum=tileMap.getCol();//得到高度，行数
        Log.i("yaoling1997","rowNum"+rowNum);
        Log.i("yaoling1997","colNum"+colNum);
        //添加物品到窗体
        for (int i=0;i<rowNum;i++)
            for (int j=0;j<colNum;j++) {
                if (indexMap[i][j]-'A'+'a'==(int)color) {
                    tileMap.setTileID(j,i,MapChar.EMPTY);
                    // 标注地图已脏，强制缓存刷新
                    tileMap.setDirty(true);
                }
            }
    }
    public void hitBlock(int x,int y){
        switch (tileMap.getTileID(x,y)){
            case MapChar.COIN_BLOCK:
                tileMap.setTileID(x,y,MapChar.EMPTY_BLOCK);
                Coin coin= new Coin(tileMap.tilesToPixelsX(x),tileMap.tilesToPixelsY(y-1),
                        new Animation(emptyAnimation),tileMap);
                addTileObject(coin);
                // 标注地图已脏，强制缓存刷新
                tileMap.setDirty(true);
                break;
            case MapChar.CHESTNUT_BLOCK:
                tileMap.setTileID(x,y,MapChar.EMPTY_BLOCK);
                Chestnut chestnut= new Chestnut(tileMap.tilesToPixelsX(x),tileMap.tilesToPixelsY(y-1),
                        new Animation(emptyAnimation),tileMap);
                addEnemy(chestnut);
                // 标注地图已脏，强制缓存刷新
                tileMap.setDirty(true);
                break;
        }
    }
    public void damage(){//主角被敌人干了
        if (hero.isDead)//无敌状态
            return;
        addHP(-1);
        releaseActionKeys();
        pad.setListener(null);
        follow(null);
        hero.dead();
        RotateTo rotate= new RotateTo(180f,30f);
        //总共转多少度，每帧累加多少度
        rotate.setActionListener(new ActionListener() {
            @Override
            public void start(ActionBind actionBind) {
                hero.setForceJump(true);
                hero.jump();

            }

            @Override
            public void process(ActionBind actionBind) {

            }

            @Override
            public void stop(ActionBind actionBind) {
                DelayTo delayTo= new DelayTo(1.5f);
                delayTo.setActionListener(new ActionListener() {
                    @Override
                    public void start(ActionBind actionBind) {

                    }

                    @Override
                    public void process(ActionBind actionBind) {

                    }

                    @Override
                    public void stop(ActionBind actionBind) {
                        //hero.setFilterColor(LColor.white);
                        setScreen(new RestartScreen(GameScreen.this));
                    }
                });
                addAction(delayTo,hero);
            }
        });
        addAction(rotate,hero);
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


    public void eMeetE(Enemy a, Enemy b){//敌人撞敌人，改变横向方向
        if (a.vx==0) {
            Enemy t=a;
            a=b;
            b=t;
        }
        if (a.vx==0)//a，b速度都为0
            return;
        else {//a速度不为0
            float directionA = a.vx / abs(a.vx);
            float directionC = b.getX() - a.getX();//b相对于a的方向，1右边，-1左边，0重合
            directionC = directionC == 0 ? 0 : directionC / abs(directionC);
            float directionB = b.vx==0?0:b.vx / abs(b.vx);//b的移动方向，1右边，-1左边，0静止
            //b速度为0归为以下哪一类均可
            if (directionA == directionB) {//相向行走
                if (directionC == directionA)
                    a.vx *= -1;
                else
                    b.vx *= -1;
            } else {//反向行走
                if (directionC == directionA) {//速度反向将他们分开
                    a.vx *= -1;
                    b.vx *= -1;
                }
            }
        }
    }

    public void cMeetC(Chestnut a, Chestnut b){//板栗撞板栗
        eMeetE(a, b);
    }
    public void cMeetT(Chestnut a, Tortoise b){//板栗撞乌龟
        if (b.isDefense&&b.vx!=0){//乌龟是防御状态且横向速度不为0
            a.isDead=true;//板栗死了
        }else{
            eMeetE(a, b);
        }
    }
    public void tMeetT(Tortoise a, Tortoise b){//乌龟a撞乌龟b
        if (!a.isDefense&&b.isDefense) {//a行走,b防御
            Tortoise c=a;
            a=b;
            b=c;
        }
        if (a.isDefense) {//a防御状态，b防御或行走
            if (a.vx!=0){//横向速度不为0
                if (b.isDefense){//b防御状态
                    if (b.vx!=0){
                        eMeetE(a,b);
                    }else {
                        b.vx=a.vx;
                        a.vx=0;
                    }
                }else{//b在行走，被撞死
                    b.isDead=true;
                }
            }else {//横向速度为0
                if (b.isDefense){//b防御状态
                    if (b.vx!=0){
                        a.vx=b.vx;
                        b.vx=0;
                    }
                }else{//b在行走，转向
                    eMeetE(a,b);
                }
            }
        }else {//a和b在行走
            eMeetE(a,b);
        }
    }
    @Override
    public void update(long l) {
        if (hero!=null){
            hero.stop();
        }
        if (enemyManager!=null){//判断相撞
            int len= enemyManager.size();
            ArrayList<Enemy> removeEnemyManager= new ArrayList<>();//一轮判断后哪些敌人要移走
            for (int i=0;i<len;i++) {
                Enemy u = enemyManager.get(i);
                for (int j = i + 1; j < len; j++) {
                    Enemy v = enemyManager.get(j);
                    if (!u.isCollision(v))//没相撞，跳过
                        continue;
                    if (u instanceof Chestnut) {
                        if (v instanceof Chestnut) {//板栗撞板栗
                            cMeetC((Chestnut)u, (Chestnut)v);
                        } else if (v instanceof Tortoise) {//板栗撞乌龟
                            cMeetT((Chestnut)u, (Tortoise)v);
                        }
                    } else if (u instanceof Tortoise) {
                        if (v instanceof Chestnut) {//乌龟撞板栗
                            cMeetT((Chestnut)v, (Tortoise)u);
                        } else if (v instanceof Tortoise) {//乌龟撞乌龟
                            tMeetT((Tortoise)u, (Tortoise)v);
                        }
                    }
                }
                if (u.isDead)
                    removeEnemyManager.add(u);
            }
            for (Enemy e:removeEnemyManager)
                removeEnemy(e);
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

    public int getCoin() {
        return coin;
    }

    public int getHP() {
        return HP;
    }

    public int getWorld() {
        return world;
    }

    public int getLevel() {
        return level;
    }
}
