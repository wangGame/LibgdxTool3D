package com.test.screen;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.demo.kitchen.actor.Actor3D;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.GameObject;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;

public class TestScreen extends BaseScreen3D {
    public TestScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();

        Actor3D actor3D = new Actor3D(Asset3D.getAsset3D().getModel("mo/1.g3db"));
        stage3D.addActor(actor3D);
        actor3D.setScale(3,3,3);
        GameObject modelData = actor3D.getModelData();
        System.out.println(modelData);
        for (Material material : modelData.materials) {
            Attribute c = null;
            for (Attribute attribute : material) {
                if (attribute instanceof TextureAttribute) {
                    c = attribute;
                    ((TextureAttribute)(c)).set(new TextureRegion(Asset.getAsset().getTexture("mo/xx.png")));
                }
            }
            material.clear();
            material.set(c);
        }
    }
}
