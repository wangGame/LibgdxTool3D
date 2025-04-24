package com.food;

import com.Content;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class BreadFood extends FoodGroup {
    private ModelActor3D breadUp;
    private ModelActor3D breadDown;
    public BreadFood() {
        this.id = Content.BREAD;
        breadDown = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/BreadBottom.g3db"));
        breadUp = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Breadtop.g3db"));
        addActor3D(breadDown);
        addActor3D(breadUp);
        breadUp.setPosition(5,4,0);
        setScale(2,2,2);
    }
}
