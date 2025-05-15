package com.kitchen.food;

import com.kitchen.content.Content;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class TomatoFood extends FoodGroup {
    private ModelActor3D nuCut;
    private ModelActor3D cut;
    public TomatoFood() {
        id = Content.TOMATO;
        nuCut = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Tomato.g3db"));
        addActor3D(nuCut);
        setScale(2,2,2);
        cut = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Tomato slice.g3db"));
        addActor3D(cut);
        cut.setVisible(false);
    }

    @Override
    public void changeCutStatus() {
        super.changeCutStatus();
        nuCut.setVisible(false);
        cut.setVisible(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}