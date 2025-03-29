package com.kw.gdx.d3.actor;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;

public class BaseActor3DGroup extends BaseActor3D{
    private Array<BaseActor3D> actor3DS;
    private final Matrix4 worldTransform = new Matrix4();
    private Matrix4 computedTransform = new Matrix4();
    public BaseActor3DGroup(){

    }
    public BaseActor3DGroup(float x, float y, float z) {
        super(x, y, z);
        this.actor3DS = new Array<>();
    }

    @Override
    public void draw(ModelBatch batch, Environment env) {
        for (BaseActor3D actor3D : actor3DS) {
            actor3D.draw(batch,env);
        }
    }

    /** Returns the transform for this group's coordinate system. */
    protected Matrix4 computeTransform () {
        Matrix4 worldTransform = this.worldTransform;
        worldTransform.translate(position.x,position.y,position.z);
        worldTransform.rotate(rotation);
        worldTransform.scale(scale.x,scale.y,scale.z);
        if (parent3D!=null) {
            worldTransform.mul(parent3D.worldTransform);
        }
        computedTransform.set(worldTransform);
        return computedTransform;
    }

    public void addActor3D(BaseActor3D ba) {
        actor3DS.add(ba);
        ba.setParent3D(this);
    }

    public void remove3D(BaseActor3D ba) {
        actor3DS.removeValue(ba,false);
    }

    @Override
    public void act(float delta) {
        for (BaseActor3D actor3D : actor3DS) {
            actor3D.act(delta);
        }
    }
}
