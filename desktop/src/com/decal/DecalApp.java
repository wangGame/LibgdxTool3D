package com.decal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kw.gdx.asset.Asset;
import com.libGdx.test.base.LibGdxTestMain;

public class DecalApp extends LibGdxTestMain {
    public static void main(String[] args) {

    }


    @Override
    public void useShow(Stage stage) {
        super.useShow(stage);

        Decal decal = Decal.newDecal(1, 1,
                new TextureRegion(Asset.getAsset().getTexture("2d/gui/badlogic.jpg")));
        decal.setPosition(10, 10, 10);
        decal.setScale(3);


    }
}
