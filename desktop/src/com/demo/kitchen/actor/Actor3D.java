package com.demo.kitchen.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.math.Vector3;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.GameObject;
import com.kw.gdx.d3.utils.Box;

public class Actor3D extends BaseActor3D {
    public Actor3D(Model model){
        this(0,0,0,model);
    }

    public Actor3D(float x, float y, float z) {
        this(x,y,z,null);
    }

    public Actor3D(float x,float y,float z,Model model){
        super(x,y,z);
        if (model != null) {
            GameObject gameObject = new GameObject(model, new Vector3(0, 0, 0));
            setModelInstance(gameObject);
            gameObject.calculateBoundingBox(bounds);
            gameObject.shape = new Box(bounds);

        }
    }

    public void setMetal() {
        for (Material material : modelData.materials) {
            material.set(
                    ColorAttribute.createDiffuse(0.7f, 0.7f, 0.7f, 1.0f),
                    ColorAttribute.createSpecular(1.0f, 1.0f, 1.0f, 1.0f),
                    FloatAttribute.createShininess(100.0f)
            );
        }
    }
}
