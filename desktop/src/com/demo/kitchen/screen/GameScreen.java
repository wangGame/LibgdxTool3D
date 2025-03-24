package com.demo.kitchen.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
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
        String path = "model/Cube.obj";
        AssetManager assetManager = Asset.getAsset().getAssetManager();
        assetManager.setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(),
                assetManager.getFileHandleResolver()));
        assetManager.setLoader(Model.class, ".obj", new ObjLoader(assetManager.getFileHandleResolver()));
        Asset.getAsset().getAssetManager().load(path, Model.class);
        assetManager.finishLoading();

        Model model = assetManager.get(path, Model.class);
        BaseActor3D actor3D = new BaseActor3D(0,0,-30);
        GameObject gameObject = new GameObject(model, new Vector3(0,0,-30));
        actor3D.setModelInstance(gameObject);
        stage3D.addActor(actor3D);

    }
}
