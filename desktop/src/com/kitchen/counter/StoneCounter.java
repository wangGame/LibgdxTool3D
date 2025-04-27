package com.kitchen.counter;

import com.Content;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class StoneCounter extends CommonCounter {
    public StoneCounter(){
        top.set(0,95,0);
        this.canOptionFood = new Array<>();
        canOptionFood.add(Content.MEAT);
//        canOptionFood.add(Content.CHEESE);
//        canOptionFood.add(Content.TOMATO);

        ModelActor3D baseCounter = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Kitchen Counter.g3db"));
        addActor3D(baseCounter);

        ModelActor3D boardModel = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Stove Counter.g3db"));
        addActor3D(boardModel);
        boardModel.setPosition(0,95,0);

        ModelActor3D knifeModel = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Frying Pan.g3db"));
        addActor3D(knifeModel);
        knifeModel.setPosition(0,95,-5);
    }

    @Override
    public void option() {
        super.option();
        modelActor3D.changeCutStatus();
    }
}
