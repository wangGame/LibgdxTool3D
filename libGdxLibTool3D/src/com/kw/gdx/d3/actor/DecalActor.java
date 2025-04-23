package com.kw.gdx.d3.actor;

import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class DecalActor extends BaseActor3D{
    private Decal decal;

    public DecalActor(Decal decal){
        this.decal = decal;
    }

    private Vector3 positionV3 = new Vector3();
    private Quaternion rotation = new Quaternion();
    private Vector3 scaleV3 = new Vector3();
    @Override
    protected void drawDecal(DecalBatch decalBatch) {
        super.drawDecal(decalBatch);
        BaseActor3DGroup parent3D1 = parent3D;
        Matrix4 actorMatrix1 = parent3D1.getActorMatrix();
        actorMatrix1.getTranslation(positionV3);
        actorMatrix1.getRotation(rotation);
        actorMatrix1.getScale(scaleV3);
        decal.setPosition(positionV3.add(getPosition()));
        decal.setScale(decal.getScaleX()*scaleV3.x,decal.getScaleY()*scaleV3.y);
        decal.setRotation(rotation);
        decalBatch.add(decal);
    }
}
