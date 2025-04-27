package com.kitchen.view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.food.FoodGroup;
import com.kitchen.data.KitchenOpetion;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.scrollpanel.ScrollPane;
import com.kw.gdx.utils.Layer;

public class OrderView extends Group {
    private ScrollPane scrollPane;
    private Table table;
    private Array<Integer> foodsId;
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

        foodsId = new Array<>();
        for (int i = 0; i < 10; i++) {
            int random = MathUtils.random(4)+1;
            table.add(new OrderItem(random)).pad(20);
            foodsId.add(random);
        }
        table.pack();
    }

    public void checkFoods(Array<FoodGroup> foodGroups) {
        ArrayMap<Integer, Array<Integer>> caiDan = KitchenOpetion.caiDan;
        Integer i = foodsId.get(0);
        Array<Integer> integers = caiDan.get(i);
        if (foodGroups.size != integers.size) {
            System.out.println("出餐失败");
        }else {
            for (FoodGroup foodGroup : foodGroups) {
                int id = foodGroup.getId();
                if(!integers.contains(id,false)){
                    System.out.println("出餐失败");
                }
            }
            System.out.println("出餐成功");
        }
    }
}
