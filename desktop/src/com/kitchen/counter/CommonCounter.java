package com.kitchen.counter;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;

public abstract class CommonCounter extends BaseActor3DGroup {
    protected BaseActor3D modelActor3D;
    public CommonCounter(){
        setBodyOff(new Vector3(0,-50,0));
    }
    public BaseActor3D getModelActor3D() {
        return modelActor3D;
    }

    public void setModelActor3D(BaseActor3D modelActor3D) {
        this.modelActor3D = modelActor3D;
        if (modelActor3D!=null){
            modelActor3D.setPosition(-5,95,0);
        }

    }
}
