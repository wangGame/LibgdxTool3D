package com.food;

import com.Content;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class CheeseFood extends FoodGroup {
    private BaseActor3D noCutCheese;
    private BaseActor3D cutCheese;
    public CheeseFood(){
        id = Content.CHEESE;
        noCutCheese = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Cheese block.g3db"));
        addActor3D(noCutCheese);
        setScale(2,2,2);
        cutCheese = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Cheese slice.g3db"));
        addActor3D(cutCheese);
        cutCheese.setVisible(false);
    }

    @Override
    public void changeCutStatus() {
        super.changeCutStatus();
        cutCheese.setVisible(true);
        noCutCheese.setVisible(false);
    }
}
