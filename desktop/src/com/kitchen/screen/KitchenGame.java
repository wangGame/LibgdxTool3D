package com.kitchen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.kitchen.actor.PlayerActor;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;
import com.kw.gdx.d3.world.WorldSystem;

public class KitchenGame extends BaseScreen3D {
    private PlayerActor player;
    private WorldSystem worldSystem;
    public KitchenGame(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        worldSystem = WorldSystem.getInstance();
        PerspectiveCamera camera = stage3D.getCamera();
        worldSystem.setCam1(camera);
        //bg
        Model model = Asset3D.getAsset3D().getModel("kitchen/model/table.g3db");
        ModelActor3D modelActor3D = new ModelActor3D(model);
        stage3D.addActor(modelActor3D);
        modelActor3D.setScale(500,0.01f,500);
        Texture woodTexture = Asset.getAsset().getTexture("kitchen/Texture/ButtonBackground.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        modelActor3D.setMaterialTexture(woodTexture);
        modelActor3D.setPosition(0,-0.5f,-190);

        worldSystem.addCollision(modelActor3D.getScale().cpy().scl(5),modelActor3D.getPosition(),0,null);

        player = new PlayerActor();
        stage3D.addActor(player);
        player.initPlayer();
        Vector3 bodyPosition = player.getPosition().cpy();
        bodyPosition.y += 50f;
        player.setBodyOff(new Vector3(0,-50,0));
        worldSystem.addCollision(player.getScale().cpy().scl(50),bodyPosition,1,player);


//
        for (int i = -6; i <= 6; i++) {
            ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(90*i,0,5*90);
            worldSystem.addCollision(modelActor3D1.getScale().cpy().scl(50),modelActor3D1.getPosition().cpy(),0,modelActor3D1);
        }
        for (int i = -5; i <= 5; i++) {
            ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(-6*90,0,i*90);
            worldSystem.addCollision(modelActor3D1.getScale().cpy().scl(50),modelActor3D1.getPosition().cpy(),0,modelActor3D1);
        }
        for (int i = -5; i <= 5; i++) {
            ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(6*90,0,i*90);
            worldSystem.addCollision(modelActor3D1.getScale().cpy().scl(50),modelActor3D1.getPosition().cpy(),0,modelActor3D1);
        }
        for (int i = -5; i <= 5; i++) {
            ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(i*90,0,-5*90);
            worldSystem.addCollision(modelActor3D1.getScale().cpy().scl(50),modelActor3D1.getPosition().cpy(),0,modelActor3D1);
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
        worldSystem.update();
    }
}
