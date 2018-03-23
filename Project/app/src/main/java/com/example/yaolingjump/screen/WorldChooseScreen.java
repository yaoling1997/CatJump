package com.example.yaolingjump.screen;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.avg.GameStartAVGScreen;

import java.util.ArrayList;

import loon.Screen;
import loon.component.LPaper;
import loon.event.ActionKey;
import loon.event.GameTouch;
import loon.opengl.GLEx;
import loon.utils.timer.LTimerContext;

/**
 * Created on 2018/2/16.
 */

public class WorldChooseScreen extends Screen {
    private int margin=8;//间隔多少像素
    private LPaper background;//充当背景，直接setbackground在界面切换的时候有可能背景显示不出来(游戏引擎的bug我猜)
    private LPaper title;
    private LPaper undergroundCity;
    private LPaper underseaWorld;
    private LPaper btnPreviousPage;
    private ArrayList<LPaper> worldManager= new ArrayList<>();
    private LPaper btnNextPage;
    private LPaper btnBack;
    private int chosenWorld=0;//当前显示的世界
    @Override
    public void draw(GLEx glEx) {

    }

    private LPaper addWorld(String s){
        LPaper re = new LPaper(s){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    //replaceScreen(new MainScreen(),MoveMethod.OUT_DOWN);
                    setScreen(new GameStartAVGScreen());
                }
            }
        };
        re.setLocation(getWidth()/2-re.getWidth()/2,title.getY()+title.getHeight()+margin);
        //undergroundCity.setLocation(getWidth()/2-undergroundCity.getWidth()/2,2*margin);
        re.setVisible(false);
        add(re);
        worldManager.add(re);
        return re;
    }
    private void showChosenWorld(){
        if (chosenWorld<0||chosenWorld>=worldManager.size())
            return;
        worldManager.get(chosenWorld).setVisible(true);
        for (int i=0;i<worldManager.size();i++)
            if (i!=chosenWorld)
                worldManager.get(i).setVisible(false);
    }
    private void updateChosenWorld(int o){
        if (o<0||o>=worldManager.size())
            return;
        chosenWorld=o;
        showChosenWorld();
    }
    private void initViews(){
        background= new LPaper(MyAssets.MAIN_SCREEN_BACKGROUND);
        add(background);
        title= new LPaper(MyAssets.WORLD_CHOOSE_SCREEN_TITLE);
        title.setLocation(getWidth()/2-title.getWidth()/2,margin);
        add(title);

        undergroundCity = addWorld(MyAssets.WORLD1_CHOOSE_BACKGROUND);
        underseaWorld = addWorld(MyAssets.WORLD2_CHOOSE_BACKGROUND);

        btnPreviousPage = new LPaper(MyAssets.BTN_PREVIOUS_PAGE){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    if (chosenWorld>0){
                        updateChosenWorld(chosenWorld-1);
                    }
                }
            }
        };
        btnPreviousPage.setLocation(margin,getHeight()/2- btnPreviousPage.getHeight()/2);
        add(btnPreviousPage);

        btnNextPage = new LPaper(MyAssets.BTN_NEXT_PAGE){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    if (chosenWorld<worldManager.size()-1){
                        updateChosenWorld(chosenWorld+1);
                    }
                }
            }
        };
        btnNextPage.setLocation(getWidth()- btnNextPage.getWidth()-margin,getHeight()/2- btnNextPage.getHeight()/2);
        add(btnNextPage);

        btnBack = new LPaper(MyAssets.BTN_BACK){
            ActionKey action= new ActionKey(ActionKey.DETECT_INITIAL_PRESS_ONLY);
            @Override
            public void doClick() {
                if (!action.isPressed()){
                    action.press();
                    replaceScreen(new MainScreen(),MoveMethod.OUT_UP);
                    //setScreen(new MainScreen());
                }
            }
        };
        btnBack.setLocation(margin,getHeight()-btnBack.height()-margin);
        add(btnBack);
    }

    @Override
    public void onLoad() {
        initViews();
        showChosenWorld();
    }

    @Override
    public void alter(LTimerContext lTimerContext) {

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

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void close() {

    }
}
