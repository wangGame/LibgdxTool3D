package com.kitchen.counter;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.food.PlateGroup;
import com.kw.gdx.d3.actor.DecalActor;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class PlateCounter extends CommonCounter {
    private Vector3 topV3;
    private Array<PlateGroup> plateGroups;
    private int maxPlate = 5;

    public PlateCounter(){
        ModelActor3D baseCounter = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Kitchen Counter.g3db"));
        addActor3D(baseCounter);

        DecalActor decalActor = new DecalActor("kitchen/Texture/CircleDashed.png");
        addActor3D(decalActor);
        decalActor.setPosition(0,90,0);
        decalActor.setScale(0.5f,0.5f,0.5f);
        decalActor.setFromAxis(1,0,0,90);
        PlateGroup plateGroup = new PlateGroup();
        addActor3D(plateGroup);
        plateGroup.setScale(1.5f,1.5f,1.5f);
        topV3 = new Vector3(0,85,0);
//        this.plateGroups = new Array<>();
        plateGroup.setPosition(topV3);
    }

}
