package com.kitchen.manager;

import com.kitchen.actor.PlayerActor;
import com.kitchen.counter.BaseCounter;
import com.kitchen.counter.BreadCounter;
import com.kitchen.counter.CabbageCounter;
import com.kitchen.counter.CheeseCounter;
import com.kitchen.counter.ClearCounter;
import com.kitchen.counter.CommonCounter;
import com.kitchen.counter.MeatCounter;
import com.kitchen.counter.TomatoCounter;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;

public class KitchenManager {

    public void option(PlayerActor player, CommonCounter actor3D) {
        if (actor3D instanceof BreadCounter) {
            player.pickPlate(1);
        }else if (actor3D instanceof CabbageCounter){
            player.pickPlate(2);
        }else if (actor3D instanceof CheeseCounter){
            player.pickPlate(3);
        }else if (actor3D instanceof MeatCounter){
            player.pickPlate(4);
        }else if (actor3D instanceof TomatoCounter){
            player.pickPlate(5);
        }else if (actor3D instanceof ClearCounter){
            BaseActor3DGroup pickActor = player.getPickActor();
            BaseActor3D modelActor3D = actor3D.getModelActor3D();
            if (pickActor!=null){
                if (modelActor3D==null){
                    player.setPickActor(null);
                    player.remove3D(pickActor);
                    actor3D.setModelActor3D(pickActor);
                    actor3D.addActor3D(pickActor);
                }
            }
        }
    }
}
