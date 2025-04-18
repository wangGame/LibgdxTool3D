package com.kitchen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.demo.kitchen.actor.ModelActor3D;
import com.kitchen.actor.PlayerActor;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;


public class KitchenGame extends BaseScreen3D {
    private PlayerActor player;
    private btSphereShape ballShape;
    private btCollisionObject ballObject;
    btCollisionConfiguration collisionConfig;
    btDispatcher dispatcher;

    public KitchenGame(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();

        //bg
        Model model = Asset3D.getAsset3D().getModel("kitchen/model/table.g3db");
        ModelActor3D modelActor3D = new ModelActor3D(model);
        stage3D.addActor(modelActor3D);
        modelActor3D.setScale(200,200,200);
        Texture woodTexture = Asset.getAsset().getTexture("kitchen/Texture/ButtonBackground.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        modelActor3D.setMaterialTexture(woodTexture);
        modelActor3D.setPosition(0,-0.5f,-190);

        player = new PlayerActor();
        stage3D.addActor(player);
        player.initPlayer();

        ballShape = new btSphereShape(0.5f);

        ballObject = new btCollisionObject();
        ballObject.setCollisionShape(ballShape);
        ballObject.setWorldTransform(player.calculateTransform());


        collisionConfig = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfig);

        for (int i = -6; i <= 6; i++) {
            ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(90*i,0,5*90);
        }
        for (int i = -5; i <= 5; i++) {
            ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(-6*90,0,i*90);
        }
        for (int i = -5; i <= 5; i++) {
            ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(6*90,0,i*90);
        }
        for (int i = -5; i <= 5; i++) {
            ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(i*90,0,-5*90);
        }
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.leftMove();
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.rightMove();
        }else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.upMove();
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.downMove();
        }else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            player.pickPlate();
        }
        super.render(delta);
    }
}
