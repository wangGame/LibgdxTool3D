package com.kitchen.counter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.DecalActor;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class BreadCounter extends CommonCounter {
    private ModelActor3D baseCounter;
    private ModelActor3D holeCounter;
    private ModelActor3D singleDoor;
    public BreadCounter(){
        super();
        baseCounter = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_solid.g3db"));
        addActor3D(baseCounter);
        holeCounter = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
        addActor3D(holeCounter);
        holeCounter.setMaterialTexture(Asset.getAsset().getTexture("kitchen/model/Kitchen Counter_AlbedoTransparency.png"));
        holeCounter.setPosition(0,46,40);
        holeCounter.setFromAxis(1,0,0,-90);
        singleDoor = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Single door.g3db"));
        addActor3D(singleDoor);
        singleDoor.setPosition(-55,85,-30);
        // 计算复合旋转
        singleDoor.setScale(1.5f,1.5f,1f);
        singleDoor.setEulerAngles(0,-90,-90);

        Decal decal = Decal.newDecal(new TextureRegion(Asset.getAsset().getTexture("shuoming.png")));
        DecalActor decalActor = new DecalActor(decal);
        addActor3D(decalActor);

    }

    public void select(){

    }
}
