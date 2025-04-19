package com.kitchen.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Vector3;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class PlayerActor extends BaseActor3DGroup {
    private float speed;
    public PlayerActor(){
        speed = 39300;
    }

    public void initPlayer(){
        ModelActor3D modelActor3D
                = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/player.g3db"));
        modelActor3D.updateBox();
        addActor3D(modelActor3D);
        modelActor3D.setScale(0.4f,0.4f,0.4f);
    }

    private Vector3 vector3 = new Vector3();
    public void leftMove(){
        vector3.setZero();
        vector3.x -= speed * Gdx.graphics.getDeltaTime();
        rotation.setEulerAngles(0,0,0);
        body.setLinearVelocity(vector3);
    }

    public void rightMove() {
        vector3.setZero();
        vector3.x += speed * Gdx.graphics.getDeltaTime();
        rotation.setEulerAngles(180,0,0);
        body.setLinearVelocity(vector3);
    }

    public void upMove() {
        vector3.setZero();
        vector3.z -= speed * Gdx.graphics.getDeltaTime();
        rotation.setEulerAngles(-90,0,0);
        body.setLinearVelocity(vector3);
    }

    public void downMove() {
        vector3.setZero();
        vector3.z += speed * Gdx.graphics.getDeltaTime();
        rotation.setEulerAngles(90,0,0);
        body.setLinearVelocity(vector3);
    }

    @Override
    public void draw(ModelBatch batch, Environment env) {
        super.draw(batch, env);
    }

    private boolean pickPlate = false;
    public void pickPlate() {
        if (pickPlate)return;
        this.pickPlate = true;
        ModelActor3D actor3D = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Cabbage.g3db"));
        addActor3D(actor3D);
        actor3D.setScale(2,2,2);
        actor3D.setPosition(-80,60,0);
    }

}
