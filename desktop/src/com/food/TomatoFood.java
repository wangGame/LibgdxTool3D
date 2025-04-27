package com.food;

import com.Content;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class TomatoFood extends FoodGroup {
    private ModelActor3D noCutTomatoed;
    private ModelActor3D cutTomatoed;
    public TomatoFood() {
        id = Content.TOMATO;
        noCutTomatoed = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Tomato.g3db"));
        addActor3D(noCutTomatoed);
        setScale(2,2,2);
        cutTomatoed = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Tomato slice.g3db"));
        addActor3D(cutTomatoed);
        cutTomatoed.setVisible(false);
    }

    @Override
    public void changeCutStatus() {
        super.changeCutStatus();
        cutTomatoed.setVisible(true);
        noCutTomatoed.setVisible(false);
    }
}