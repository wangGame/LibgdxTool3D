package com.kitchen.counter;

import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class CutCounter extends CommonCounter {
    public CutCounter(){
        ModelActor3D baseCounter = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Kitchen Counter.g3db"));
        addActor3D(baseCounter);

        ModelActor3D boardModel = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Chopping Board.g3db"));
        addActor3D(boardModel);
        boardModel.setPosition(-10,85,0);

        ModelActor3D knifeModel = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/knife.g3db"));
        addActor3D(knifeModel);
        knifeModel.setPosition(30,85,10);
    }
}
