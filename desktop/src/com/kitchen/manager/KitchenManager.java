package com.kitchen.manager;

import com.cube.Cube;
import com.food.FoodGroup;
import com.food.PlateGroup;
import com.kitchen.actor.PlayerActor;
import com.kitchen.counter.BaseCounter;
import com.kitchen.counter.BreadCounter;
import com.kitchen.counter.CabbageCounter;
import com.kitchen.counter.CheeseCounter;
import com.kitchen.counter.ClearCounter;
import com.kitchen.counter.CommonCounter;
import com.kitchen.counter.CutCounter;
import com.kitchen.counter.DeliveryCounter;
import com.kitchen.counter.MeatCounter;
import com.kitchen.counter.PlateCounter;
import com.kitchen.counter.TomatoCounter;
import com.kitchen.counter.TrashBinCounter;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;

public class KitchenManager {

    public void option(PlayerActor player, CommonCounter actor3D) {
        if (player.getPickActor()==null||(player.getPickActor()!=null&&!(player.getPickActor() instanceof PlateGroup))) {
            if (actor3D instanceof BreadCounter) {
                player.pickPlate(1);
            } else if (actor3D instanceof CabbageCounter) {
                player.pickPlate(2);
            } else if (actor3D instanceof CheeseCounter) {
                player.pickPlate(3);
            } else if (actor3D instanceof MeatCounter) {
                player.pickPlate(4);
            } else if (actor3D instanceof TomatoCounter) {
                player.pickPlate(5);
            }else if (actor3D instanceof ClearCounter){
                FoodGroup pickActor = player.getPickActor();
                FoodGroup modelActor3D = actor3D.getModelActor3D();
                if (pickActor!=null) {
                    if (modelActor3D == null) {
                        player.setPickActor(null);
                        player.remove3D(pickActor);
                        pickActor.remove();
                        actor3D.setModelActor3D(pickActor);
                        actor3D.addActor3D(pickActor);
                    }else {
                        if (modelActor3D instanceof PlateGroup){
                            modelActor3D.pickPlate(pickActor.getId());
                            pickActor.remove();
                            player.setPickActor(null);
                        }
                    }
                }else {
                    if (modelActor3D!=null) {
                        actor3D.setModelActor3D(null);
                        modelActor3D.remove();
                        player.setPickActor(modelActor3D);
                        player.addActor3D(modelActor3D);

                    }
                }
            }else if (actor3D instanceof CutCounter){
                //可以切你就放上去
                FoodGroup pickActor = player.getPickActor();
                if (pickActor!=null) {
                    if (actor3D.canCutCurrentFood(pickActor.getId())) {
                        BaseActor3D modelActor3D = actor3D.getModelActor3D();
                        if (pickActor != null) {
                            if (modelActor3D == null) {
                                player.setPickActor(null);
                                player.remove3D(pickActor);
                                actor3D.setModelActor3D(pickActor);
                                actor3D.addActor3D(pickActor);
                            }
                        } else {
                            if (modelActor3D != null) {

                            }
                        }
                    }
                }else {
                    if (actor3D.getModelActor3D()!=null) {
                        FoodGroup modelActor3D = actor3D.getModelActor3D();
                        actor3D.setModelActor3D(null);
                        player.setPickActor(modelActor3D);
                        player.addActor3D(modelActor3D);
                    }
                }
            }else if (actor3D instanceof PlateCounter){
                FoodGroup pickActor = player.getPickActor();
                if (pickActor == null){
                    player.pickPlate(6);
                }
            }else if (actor3D instanceof TrashBinCounter){
                FoodGroup pickActor = player.getPickActor();
                player.remove3D(pickActor);
                player.setPickActor(null);
            }else if (actor3D.getModelActor3D() instanceof PlateGroup) {
                if (player.getPickActor()!=null) {
                    FoodGroup pickActor = player.getPickActor();
                    FoodGroup modelActor3D = actor3D.getModelActor3D();
//                    if (actor3D instanceof BreadCounter) {
//                        modelActor3D.pickPlate(1);
//                    } else if (actor3D instanceof CabbageCounter) {
//                        modelActor3D.pickPlate(2);
//                    } else if (actor3D instanceof CheeseCounter) {
//                        modelActor3D.pickPlate(3);
//                    } else if (actor3D instanceof MeatCounter) {
//                        modelActor3D.pickPlate(4);
//                    } else if (actor3D instanceof TomatoCounter) {
//                        modelActor3D.pickPlate(5);
//                    }
                    modelActor3D.pickPlate(pickActor.getId());
                }
            }
        }else {
            FoodGroup pickActor = player.getPickActor();
            if (pickActor instanceof PlateGroup){
                if (actor3D instanceof BreadCounter) {
                    pickActor.pickPlate(1);
                } else if (actor3D instanceof CabbageCounter) {
                    pickActor.pickPlate(2);
                } else if (actor3D instanceof CheeseCounter) {
                    pickActor.pickPlate(3);
                } else if (actor3D instanceof MeatCounter) {
                    pickActor.pickPlate(4);
                } else if (actor3D instanceof TomatoCounter) {
                    pickActor.pickPlate(5);
                }else if (actor3D instanceof ClearCounter){
                    FoodGroup modelActor3D = actor3D.getModelActor3D();
                    if (modelActor3D == null) {
                        player.setPickActor(null);
                        player.remove3D(pickActor);
                        actor3D.setModelActor3D(pickActor);
                        actor3D.addActor3D(pickActor);
                    } else {

                        if (pickActor.pickPlate(modelActor3D)!=null) {
                            actor3D.setModelActor3D(null);
                        }
                    }
                }else if (actor3D instanceof TrashBinCounter){
                    player.remove3D(pickActor);
                    player.setPickActor(null);
                }else if (actor3D instanceof DeliveryCounter){
                    player.setPickActor(null);
                    player.remove3D(pickActor);
//                    actor3D.setModelActor3D(pickActor);
//                    actor3D.addActor3D(pickActor);
                    actor3D.option();
                }
            }
        }
    }

    public void doKitchen(PlayerActor player, CommonCounter actor3D1) {
        if (actor3D1 instanceof CutCounter){
            BaseActor3DGroup pickActor = player.getPickActor();
            BaseActor3D modelActor3D = actor3D1.getModelActor3D();
            if (pickActor==null){
                if (modelActor3D!=null){
                    actor3D1.option();
                }
            }
        }
    }
}
