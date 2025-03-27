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
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.GameObject;
import com.kw.gdx.d3.screen.BaseScreen3D;



public class GameScreen extends BaseScreen3D {
    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        String path = "model/Breadtop.g3db";
        AssetManager assetManager = Asset.getAsset().getAssetManager();
        assetManager.setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(),
                assetManager.getFileHandleResolver()));
        assetManager.setLoader(Model.class, ".obj", new ObjLoader(assetManager.getFileHandleResolver()));
        Asset.getAsset().getAssetManager().load(path, Model.class);
        assetManager.finishLoading();

        Model model = assetManager.get(path, Model.class);
        BaseActor3D actor3D = new BaseActor3D(0,0,0);
        actor3D.setScale(0.1f,0.1f,0.1f);
        GameObject gameObject = new GameObject(model, new Vector3(0,0,0));
        actor3D.setModelInstance(gameObject);
        stage3D.addActor(actor3D);

        BaseActor3D floor = new BaseActor3D(0,0,-1);
        floor.buildModel(10,1,10,false);
        stage3D.addActor(floor);
        floor.setPosition(0,-1,0);
        GameObject modelData = floor.getModelData();


        Texture woodTexture = Asset.getAsset().getTexture("img.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);


        for (Material material : modelData.materials) {
            TextureAttribute diffuse = TextureAttribute.createDiffuse(new TextureRegion(woodTexture));
            diffuse.textureDescription.set(woodTexture,Texture.TextureFilter.Linear,Texture.TextureFilter.Linear,
                    Texture.TextureWrap.Repeat,Texture.TextureWrap.Repeat);
            material.set(diffuse);
            System.out.println("===========");
        }




    }
}
