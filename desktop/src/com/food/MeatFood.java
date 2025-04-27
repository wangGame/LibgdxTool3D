package com.food;

import com.Content;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class MeatFood extends FoodGroup {
    private int status = 1;
    ModelActor3D unCooked;
    ModelActor3D cooked;
    ModelActor3D burnCooked;
    public MeatFood() {
        id = Content.MEAT;
        unCooked = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Uncooked meat patty.g3db"));
        addActor3D(unCooked);
        setScale(2,2,2);
        cooked = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Cooked meat patty.g3db"));
        addActor3D(cooked);
        cooked.setVisible(false);
//
        burnCooked = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Burnedmeatpatty.g3db"));
        addActor3D(burnCooked);
        burnCooked.setVisible(false);

    }

    @Override
    public void changeCutStatus() {
        super.changeCutStatus();
        if (status == 1){
            unCooked.setVisible(false);
            cooked.setVisible(true);
            status = 2;
        }else if (status == 2){
            cooked.setVisible(false);
            burnCooked.setVisible(true);
            status = 3;
        }
    }
}