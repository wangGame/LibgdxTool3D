package com.kitchen.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.scrollpanel.ScrollPane;
import com.kw.gdx.utils.Layer;

public class OrderView extends Group {
    private ScrollPane scrollPane;
    private Table table;

    public OrderView(){
        setSize(1000,Constant.GAMEHIGHT);
        Image shadow = Layer.getShadow();
        shadow.setSize(1000,Constant.GAMEHIGHT);
        addActor(shadow);
        shadow.setColor(Color.BLACK);
        shadow.getColor().a = 0.2f;
        table = new Table();
        scrollPane = new ScrollPane(table);
        addActor(scrollPane);
        scrollPane.setSize(1000, Constant.GAMEHIGHT);
        setDebug(true);

        for (int i = 0; i < 10; i++) {
            int random = MathUtils.random(4)+1;
            table.add(new OrderItem(random));
            table.row();
        }
        table.pack();
    }
}
