package com.kitchen.counter;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.DecalActor;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class BaseCounter extends CommonCounter {
    private ModelActor3D baseCounter;
    private ModelActor3D holeCounter;
    private ModelActor3D singleDoor;
    public BaseCounter(String kitchen){
        super();
        baseCounter = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_solid.g3db"));
        addActor3D(baseCounter);
        holeCounter = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
        addActor3D(holeCounter);
        singleDoor = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Single door.g3db"));
        addActor3D(singleDoor);
        singleDoor.setPosition(-55,85,-30);
        // 计算复合旋转
        singleDoor.setScale(1.5f,1.5f,1f);
        singleDoor.setEulerAngles(0,-90,-90);

        DecalActor decalActor = new DecalActor(kitchen);
        addActor3D(decalActor);
        decalActor.setFromAxis(1,0,0,90);
        decalActor.setScale(0.2f,0.2f,0.2f);
        decalActor.setPosition(0,90,20);

    }

    public void select(){

    }
}
