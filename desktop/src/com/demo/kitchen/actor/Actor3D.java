package com.demo.kitchen.actor;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.GameObject;

public class Actor3D extends BaseActor3D {
    public Actor3D(float x, float y, float z) {
        this(x,y,z,null);
    }

    public Actor3D(float x,float y,float z,Model model){
        super(x,y,z);
        if (model != null) {
            GameObject gameObject = new GameObject(model, new Vector3(0, 0, 0));
            setModelInstance(gameObject);
        }
    }
}
