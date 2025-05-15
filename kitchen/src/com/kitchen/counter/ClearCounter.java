package com.kitchen.counter;

import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

/**
 * 空桌子
 */
public class ClearCounter extends CommonCounter{
    public ClearCounter() {
        super();
        ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Kitchen Counter.g3db"));
        addActor3D(modelActor3D1);
    }
}
