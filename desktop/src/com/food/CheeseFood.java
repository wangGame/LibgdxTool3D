package com.food;

import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class CheeseFood extends BaseActor3DGroup {
    public CheeseFood(){
        ModelActor3D food = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Cheese block.g3db"));
        addActor3D(food);
    }
}
