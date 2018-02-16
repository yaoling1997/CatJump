package com.example.yaolingjump;

import loon.canvas.Canvas;
import loon.canvas.Image;
import loon.canvas.LColor;
import loon.component.LLayer;
import loon.component.LPaper;
import loon.component.LTextArea;
import loon.font.LFont;
import loon.opengl.GLEx;

/**
 * Created on 2018/2/16.
 */

public class Information extends LLayer {
    private GameScreen gs;
    public Information(final GameScreen gs){
        super(370,20,true);
        //层级最高（层级高的显示在最上面）
        setLayer(101);
        //锁定移动
        setLocked(true);
        //层上的元素不能拖动
        setActorDrag(false);
        //多久更新一次
        setDelay(200);
        LPaper infoContent= new LPaper("assets/info_background.png"){
            @Override
            public void paint(GLEx g) {
                LColor color= g.getColor();
                int size= g.getFont().getSize();
                g.setColor(LColor.white);
                g.setFont(LFont.getFont(18));
                g.drawString("Score："+gs.getScore(),165,1);
                g.drawString("HP："+gs.getHP(),300,1);
                g.setColor(color);
                g.setFont(LFont.getFont(size));
            }
        };
        add(infoContent);
    }
}
