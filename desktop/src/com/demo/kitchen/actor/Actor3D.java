package com.demo.kitchen.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.GameObject;
import com.kw.gdx.d3.utils.Box;
import com.kw.gdx.d3.utils.Shape;

public class Actor3D extends BaseActor3D {
    private Model model;
    public Actor3D(){
        this(0,0,0);
    }

    public Actor3D(Model model){
        this(0,0,0,model);
    }

    public Actor3D(float x, float y, float z) {
        this(x,y,z,null);
    }

    public Actor3D(float x,float y,float z,Model model){
        super(x,y,z);
        this.model = model;
        if (model!=null) {
            GameObject gameObject = new GameObject(model, new Vector3(0, 0, 0));
            setModelInstance(gameObject);
            extracted();
        }
    }

    public void extracted() {
        if (model != null) {
            modelData.calculateBoundingBox(bounds);
            modelData.shape = new Box(bounds);
            Vector3 dimensions = new Vector3();
            bounds.getDimensions(dimensions);
            radius = dimensions.len() / 2f;
            bounds.getCenter(center);
        }
    }

    public Shape getShape(){
        if (modelData == null){
            return modelData.shape;
        }
        return null;
    }

    public void setMetal(){
        Attributes attributes = new Attributes();
        attributes.set(
                    ColorAttribute.createDiffuse(0.7f, 0.7f, 0.7f, 1.0f),
                    ColorAttribute.createSpecular(1.0f, 1.0f, 1.0f, 1.0f),
                    FloatAttribute.createShininess(100.0f));
        setMetal(attributes);
    }

    public void setMetal(Attributes attributes) {
        for (Material material : modelData.materials) {
            material.set(
                    attributes
            );
        }
    }

}
