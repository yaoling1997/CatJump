package com.example.yaolingjump;

import com.example.yaolingjump.Macro.MyAssets;
import com.example.yaolingjump.screen.GameScreen;

import loon.canvas.LColor;
import loon.component.LLayer;
import loon.component.LPaper;
import loon.font.LFont;
import loon.opengl.GLEx;

/**
 * Created on 2018/2/16.
 */

public class Information extends LLayer {
    private GameScreen gs;
    public Information(final GameScreen gs){
        super(400,60,true);
        //层级最高（层级高的显示在最上面）
        setLayer(101);
        //锁定移动
        setLocked(true);
        //层上的元素不能拖动
        setActorDrag(false);
        //多久更新一次
        setDelay(200);
        LPaper infoContent= new LPaper(MyAssets.INFO_BACKGROUND){
            @Override
            public void paint(GLEx g) {
                LColor color= g.getColor();
                int size= g.getFont().getSize();
                g.setColor(LColor.white);
                g.setFont(LFont.getFont(18));
                //g.drawString("Score",100,5);
                //g.drawString(""+gs.getScore(),100,25);
                g.drawString("Coin",200,5);
                g.drawString(""+gs.getCoin(),200,25);
                g.drawString("HP",300,5);
                g.drawString(""+gs.getHP(),300,25);
                g.drawString("World",400,5);
                g.drawString(gs.getWorld()+"-"+gs.getLevel(),400,25);
                g.setColor(color);
                g.setFont(LFont.getFont(size));
            }
        };

        add(infoContent);
    }
}
