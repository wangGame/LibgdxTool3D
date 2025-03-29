package com.kw.gdx.d3.actor;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.d3.action.Action3D;

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
        GameObject modelData1 = getModelData();
        if (modelData1!=null){
            modelData1.transform.set(computeTransform());
        }
        super.draw(batch,env);
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
        worldTransform.idt();
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

    public Array<BaseActor3D> getActor3DS() {
        return actor3DS;
    }

    public BaseActor3D checkCollisions(Ray ray) {
        Matrix4 transform = calculateTransform();
        Ray localRay = new Ray(ray.origin.cpy().mul(transform.inv()), ray.direction.cpy().mul(transform.inv()));
        for (BaseActor3D actor : actor3DS) {
            if (actor.checkCollision(localRay)) {
                return actor;  // 如果碰撞，返回发生碰撞的 Actor
            }
        }
        return null;
    }
}
