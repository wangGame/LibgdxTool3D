package com.kitchen.counter;

import com.badlogic.gdx.graphics.g3d.Model;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

/**
 * 空桌子
 */
public class ClearCounter extends CommonCounter{
    public ClearCounter() {
        super();
        ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
        addActor3D(modelActor3D1);
    }
}
