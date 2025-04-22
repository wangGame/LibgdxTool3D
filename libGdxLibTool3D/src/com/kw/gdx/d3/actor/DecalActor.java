package com.kw.gdx.d3.actor;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class DecalActor extends BaseActor3D{
    private Decal decal;

    public DecalActor(Decal decal){
        this.decal = decal;
    }

    @Override
    protected void drawDecal(DecalBatch decalBatch) {
        super.drawDecal(decalBatch);

        decalBatch.add(decal);
    }
}
