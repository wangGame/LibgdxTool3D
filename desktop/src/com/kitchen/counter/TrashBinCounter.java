package com.kitchen.counter;

import com.kw.gdx.d3.actor.DecalActor;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class TrashBinCounter extends CommonCounter {
    public TrashBinCounter(){
        ModelActor3D baseCounter = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Trash bin.g3db"));
        addActor3D(baseCounter);
    }
}
