package com.kw.gdx.d3;

import com.badlogic.gdx.math.Vector3;
import com.kw.gdx.d3.action.Action3D;
import com.kw.gdx.d3.actor.BaseActor3D;

public class RayBean {
    private float length;
    private Vector3 vector3;
    private BaseActor3D baseActor3D;

    public Vector3 getVector3() {
        return vector3;
    }

    public void setVector3(Vector3 vector3) {
        this.vector3 = vector3;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public BaseActor3D getBaseActor3D() {
        return baseActor3D;
    }

    public void setBaseActor3D(BaseActor3D baseActor3D) {
        this.baseActor3D = baseActor3D;
    }
}
