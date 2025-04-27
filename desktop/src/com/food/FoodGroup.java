package com.food;

import com.Content;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.d3.actor.BaseActor3DGroup;

public abstract class FoodGroup extends BaseActor3DGroup {
    protected int id;
    protected Array<FoodGroup> foodGroups;
    private FoodGroup pickActor;
    public int getId() {
        return id;
    }

    public void changeCutStatus(){

    }

    public FoodGroup pickPlate(FoodGroup foodGroupTa){
        if (foodGroups == null){
            foodGroups = new Array<>();
        }
        for (FoodGroup foodGroup : foodGroups) {
            if (foodGroup.getId() == foodGroupTa.getId()) {
                return null;
            }
        }
        pickActor = foodGroupTa;
        foodGroups.add(pickActor);
        foodGroupTa.remove();
        addActor3D(pickActor);
        pickActor.setPosition(0,5,0);
        return pickActor;
    }

    public FoodGroup pickPlate(int i) {
        if (foodGroups == null){
            foodGroups = new Array<>();
        }
        for (FoodGroup foodGroup : foodGroups) {
            if (foodGroup.getId() == i) {
                return null;
            }
        }
        if (i == Content.BREAD) {
            pickActor = new BreadFood();
        }else if (i == Content.CABBAGE){
            pickActor = new CabbageFood();
        }else if (i == Content.CHEESE){
            pickActor = new CheeseFood();
        }else if (i == Content.MEAT){
            pickActor = new MeatFood();
        }else if (i == Content.TOMATO){
            pickActor = new TomatoFood();
        }else if (i == Content.PLATE){
            pickActor = new PlateGroup();
        }
        foodGroups.add(pickActor);
        addActor3D(pickActor);
        return pickActor;
    }

    public Array<FoodGroup> getFoodGroups() {
        return foodGroups;
    }
}
