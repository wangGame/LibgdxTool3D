package com.kitchen.counter;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.kitchen.content.Content;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;


public class CutCounter extends CommonCounter {
    private ArrayMap<Integer,Integer> cutEnd;
    public CutCounter(){
        this.cutEnd = new ArrayMap<>();
        this.canOptionFood = new Array<>();
        canOptionFood.add(Content.CABBAGE);
        canOptionFood.add(Content.CHEESE);
        canOptionFood.add(Content.TOMATO);

        ModelActor3D baseCounter = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Kitchen Counter.g3db"));
        addActor3D(baseCounter);

        ModelActor3D boardModel = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Chopping Board.g3db"));
        addActor3D(boardModel);
        boardModel.setPosition(0,85,0);

        ModelActor3D knifeModel = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/knife.g3db"));
        addActor3D(knifeModel);
        knifeModel.setPosition(30,85,10);

//        DecalActor bgjindu = new DecalActor("");
//        addActor3D(bgjindu);
    }


    public void option() {
        modelActor3D.changeCutStatus();
    }
}
