package com.kitchen.food;

import com.kitchen.content.Content;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class CabbageFood extends FoodGroup {
    private ModelActor3D noCutCabbage;
    private ModelActor3D cutCabbage;
    public CabbageFood(){
        this.id = Content.CABBAGE;
        noCutCabbage = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Cabbage.g3db"));
        addActor3D(noCutCabbage);

        cutCabbage = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Cabbage slice_shaped.g3db"));
        addActor3D(cutCabbage);
        cutCabbage.setVisible(false);
    }

    @Override
    public void changeCutStatus() {
        noCutCabbage.setVisible(false);
        cutCabbage.setVisible(true);
    }
}
