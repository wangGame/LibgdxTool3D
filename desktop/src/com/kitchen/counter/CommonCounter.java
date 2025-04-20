package com.kitchen.counter;

import com.badlogic.gdx.graphics.g3d.Model;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;

public abstract class CommonCounter extends BaseActor3DGroup {
    protected ModelActor3D modelActor3D;
    public CommonCounter(Model model){
        this.modelActor3D = new ModelActor3D(model);
        addActor3D(modelActor3D);
    }
}
