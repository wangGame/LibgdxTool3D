package com.kitchen.counter;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.kitchen.food.FoodGroup;
import com.kw.gdx.d3.actor.BaseActor3DGroup;

public abstract class CommonCounter extends BaseActor3DGroup {
    public Array<Integer> canOptionFood;
    protected FoodGroup modelActor3D;
    protected Vector3 top;
    public CommonCounter(){
        top = new Vector3(-5,88,0);
        setBodyOff(new Vector3(0,-50,0));
    }

    public FoodGroup getModelActor3D() {
        return modelActor3D;
    }

    public void setModelActor3D(FoodGroup modelActor3D) {
        this.modelActor3D = modelActor3D;
        if (modelActor3D!=null){
            modelActor3D.setPosition(-5,88,0);
        }
    }

    public void option() {

    }

    public boolean canCutCurrentFood(int id){
        return canOptionFood.contains(id,false);
    }

}
