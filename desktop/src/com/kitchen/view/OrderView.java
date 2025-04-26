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
        setSize(Constant.GAMEWIDTH,400);
        Image shadow = Layer.getShadow();
        shadow.setSize(getWidth(),getHeight());
        addActor(shadow);
        shadow.setColor(Color.WHITE);
        shadow.getColor().a = 0.4f;
        table = new Table();
        scrollPane = new ScrollPane(table);
        addActor(scrollPane);
        scrollPane.setSize(getWidth(),getHeight());


        for (int i = 0; i < 10; i++) {
            int random = MathUtils.random(4)+1;
            table.add(new OrderItem(random)).pad(20);
        }
        table.pack();
    }
}
