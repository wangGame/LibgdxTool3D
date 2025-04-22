package com.kitchen.actor;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.kw.gdx.asset.Asset;

public class DecalActor {
    public DecalActor(){
        Decal decal = Decal.newDecal(1, 1,
                new TextureRegion(Asset.getAsset().getTexture("2d/gui/badlogic.jpg")));
        decal.setPosition(10, 10, 10);
        decal.setScale(3);

    }
}
