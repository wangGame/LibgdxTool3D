package com.food;

import com.Content;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class CabbageFood extends FoodGroup {
    public CabbageFood(){
        this.id = Content.CABBAGE;
        ModelActor3D food = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Cabbage.g3db"));
        addActor3D(food);
        setScale(2,2,2);
    }
}
