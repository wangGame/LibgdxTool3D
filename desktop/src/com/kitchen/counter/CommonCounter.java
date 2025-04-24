package com.kitchen.counter;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.food.FoodGroup;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;

public abstract class CommonCounter extends BaseActor3DGroup {
    public Array<Integer> canOptionFood;
    protected FoodGroup modelActor3D;
    public CommonCounter(){
        setBodyOff(new Vector3(0,-50,0));
    }

    public BaseActor3D getModelActor3D() {
        return modelActor3D;
    }

    public void setModelActor3D(FoodGroup modelActor3D) {
        this.modelActor3D = modelActor3D;
        if (modelActor3D!=null){
            modelActor3D.setPosition(-5,95,0);
        }
    }

    public void option() {

    }

    public boolean canCutCurrentFood(int id){
        return canOptionFood.contains(id,false);
    }

}
