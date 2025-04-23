package com.decal;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kw.gdx.asset.Asset;
import com.libGdx.test.base.LibGdxTestMain;

public class DecalApp extends LibGdxTestMain {
    public static void main(String[] args) {
        DecalApp app = new DecalApp();
        app.start();
    }

    @Override
    public void useShow(Stage stage) {
        super.useShow(stage);
        setScreen(new TestDecal());
    }
}
