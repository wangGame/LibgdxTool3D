package com.test.screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.demo.kitchen.actor.ModelActor3D;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;

import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;

public class TestScreen extends BaseScreen3D {
    private ModelActor3D domi;
    public TestScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Bullet.init();
        domi = new ModelActor3D(Asset3D.getAsset3D().getModel("mo/1.g3db"));
        stage3D.addActor(domi);
        domi.setPosition(0,19,0);
        ModelInstance model = domi.getModel();
        for (Material material : model.materials) {
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

        Model tableModel = Asset3D.getAsset3D().getModel("tile/table.g3db");
        ModelActor3D modelActor3D = new ModelActor3D(tableModel);
        stage3D.addActor(modelActor3D);
        Texture woodTexture = Asset.getAsset().getTexture("tile/Bd.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        modelActor3D.setMaterialTexture(woodTexture);
        modelActor3D.setScale(7,7,7);
        modelActor3D.setPosition(0,-0.5f,0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        if (domi!=null){
            domi.setPosition(domi.getX(),domi.getY() - delta,domi.getZ());
        }
    }
}
