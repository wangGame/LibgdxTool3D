package com.kitchen.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.food.BreadFood;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class PlayerActor extends BaseActor3DGroup {
    private float speed;
    private Vector2 forWard;
    private boolean pickPlate = false;
    private Vector3 moveV = new Vector3();
    private Vector3 pickV3;
    private BaseActor3DGroup pickActor;
    public PlayerActor(){
        speed = 39300;
        forWard = new Vector2();
        this.pickV3 = new Vector3(-80,90,0);
    }

    public void initPlayer(){
        ModelActor3D modelActor3D
                = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/player.g3db"));
        modelActor3D.updateBox();
        addActor3D(modelActor3D);
        modelActor3D.setScale(0.4f,0.4f,0.4f);
    }


    public void leftMove(){
        moveV.setZero();
        moveV.x -= speed * Gdx.graphics.getDeltaTime();
        rotation.setEulerAngles(0,0,0);
        body.setLinearVelocity(moveV);
        forWard.set(-1,0);
    }

    public void rightMove() {
        moveV.setZero();
        moveV.x += speed * Gdx.graphics.getDeltaTime();
        rotation.setEulerAngles(180,0,0);
        body.setLinearVelocity(moveV);
        forWard.set(1,0);
    }

    public void upMove() {
        moveV.setZero();
        moveV.z -= speed * Gdx.graphics.getDeltaTime();
        rotation.setEulerAngles(-90,0,0);
        body.setLinearVelocity(moveV);
        forWard.set(0,1);
    }

    public void downMove() {
        moveV.setZero();
        moveV.z += speed * Gdx.graphics.getDeltaTime();
        rotation.setEulerAngles(90,0,0);
        body.setLinearVelocity(moveV);
        forWard.set(0,-1);
    }

    public void pickPlate() {
        if (pickPlate)return;
        this.pickPlate = true;
        pickActor = new BreadFood();
        addActor3D(pickActor);
        pickActor.setPosition(pickV3.x,pickV3.y,pickV3.z);
    }

    public Vector2 getCurrentDir() {
        return forWard;
    }

    public BaseActor3DGroup getPickActor() {
        return pickActor;
    }

    public void setPickActor(BaseActor3DGroup pickActor) {
        this.pickActor = pickActor;
        if (pickActor == null){
            pickPlate = false;
        }
    }
}
