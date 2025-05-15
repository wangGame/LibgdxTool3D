package com.kitchen.food;

import com.kitchen.content.Content;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class CheeseFood extends FoodGroup {
    private ModelActor3D unCutFood;
    private ModelActor3D cutFood;
    public CheeseFood(){
        id = Content.CHEESE;
        unCutFood = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Cheese block.g3db"));
        addActor3D(unCutFood);
        setScale(2,2,2);
        cutFood = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Cheese slice.g3db"));
        addActor3D(cutFood);
        cutFood.setVisible(false);
    }

    @Override
    public void changeCutStatus() {
        super.changeCutStatus();
        cutFood.setVisible(true);
        unCutFood.setVisible(false);
    }
}
