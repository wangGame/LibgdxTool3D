package com.kitchen.order;

import com.kitchen.food.BreadFood;
import com.kitchen.food.CabbageFood;
import com.kitchen.food.CheeseFood;
import com.kitchen.food.MeatFood;
import com.kitchen.food.TomatoFood;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;

public class HanBaoBao extends BaseActor3DGroup {
    public HanBaoBao(){
        BreadFood breadFood = new BreadFood();
        addActor3D(breadFood);
        ModelActor3D breadUp = breadFood.getBreadUp();
        breadUp.setFromAxis(0,0,1,-20);
        breadUp.setPosition(5,6,0);

        CabbageFood cabbageFood = new CabbageFood();
        addActor3D(cabbageFood);
        cabbageFood.setPosition(0,7,0);
        cabbageFood.changeCutStatus();
        cabbageFood.setScale(3,3,3);
        CheeseFood cheeseFood = new CheeseFood();
        addActor3D(cheeseFood);
        cheeseFood.changeCutStatus();
        MeatFood meatFood = new MeatFood();
        addActor3D(meatFood);
        meatFood.setPosition(0,6,0);
        TomatoFood tomatoFood = new TomatoFood();
        addActor3D(tomatoFood);
        tomatoFood.changeCutStatus();
        setScale(5,5,5);
    }
}
