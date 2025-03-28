package com.demo.kitchen.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;
import com.demo.kitchen.actor.Actor3D;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.GameObject;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;



public class GameScreen extends BaseScreen3D {
    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
//        createBread();

        BaseActor3D floor = new BaseActor3D(0,0,-0f);
        floor.buildModel(200,1,200,false);
        stage3D.addActor(floor);
        floor.setPosition(0,-0.5f,0);
        floor.setMaterialTexture(Asset.getAsset().getTexture("img.png"));


        BaseActor3D player = new BaseActor3D(0,0,0);






    }

    private void createBread() {
        String path = "model/Breadtop.g3db";
        Model model = Asset3D.getAsset3D().getModel(path);
        Actor3D braed = new Actor3D(0,0,0,model);
        stage3D.addActor(braed);
        braed.setScale(0.1f,0.1f,0.1f);
    }
}
