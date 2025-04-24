package com.food;

import com.Content;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class TomatoFood extends FoodGroup {
    public TomatoFood() {
        id = Content.TOMATO;
        ModelActor3D food = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Tomato.g3db"));
        addActor3D(food);
        setScale(2,2,2);
    }
}