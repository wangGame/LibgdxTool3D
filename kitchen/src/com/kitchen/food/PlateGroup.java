package com.kitchen.food;

import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class PlateGroup extends FoodGroup {
    private ModelActor3D plate;

    public PlateGroup(){
        plate = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Plate.g3db"));
        addActor3D(plate);
        setScale(1.5f,1.5f,1.5f);
    }

    @Override
    public FoodGroup pickPlate(int i) {
        FoodGroup group = super.pickPlate(i);
        group.getScale().sub(1.0f/1.5f);
        return group;

    }
}
