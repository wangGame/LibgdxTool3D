package com.demo.kitchen.screen;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.UBJsonReader;
import com.demo.kitchen.actor.Actor3D;
import com.demo.kitchen.group.PlayerActor;
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
//        stage3D.addActor(floor);
        floor.setPosition(0,-0.5f,0);
        floor.setMaterialTexture(Asset.getAsset().getTexture("img.png"));

        Material boxMaterial = new Material();
         boxMaterial.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));

        ModelBuilder modelBuilder = new ModelBuilder();
        Model arrow = modelBuilder.createArrow(new Vector3(2, 2, 2), new Vector3(5, 5, 5), boxMaterial,
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        Actor3D player = new Actor3D(0,0,0,arrow);
//        stage3D.addActor(player);


        PlayerActor playerActor = new PlayerActor(0,0,0);
        stage3D.addActor(playerActor);
        playerActor.initView();






    }

    private void createBread() {
        String path = "model/qiu.g3db";
//        String path = "test01.g3db";

        Model model = Asset3D.getAsset3D().getModel(path);
        Actor3D braed = new Actor3D(0,0,0,model);
        stage3D.addActor(braed);
//        braed.setMaterialTexture(Asset.getAsset().getTexture("model/Bread_AlbedoTransparency.png"));
        braed.setScale(5f,5f,5f);
        braed.setMetal();

    }
}
