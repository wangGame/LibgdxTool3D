package com.demo.kitchen.group;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.demo.kitchen.actor.ModelActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.asset.Asset3D;

public class PlayerActor extends BaseActor3DGroup {
    private float speed = 105;
    public PlayerActor(float x, float y, float z) {
        super(x, y, z);
    }

    public void initView(){
        String path = "model/qiu.g3db";
        Model model = Asset3D.getAsset3D().getModel(path);
        ModelActor3D headActor = new ModelActor3D(0,0,0,model);
        addActor3D(headActor);
        headActor.setMetal();
        headActor.setColor(Color.BROWN);
        headActor.setPosition(0,3.4f,0f);
        ModelActor3D bodyActor = new ModelActor3D(0,0,0,model);
        addActor3D(bodyActor);
        bodyActor.setScale(1.5f,1.5f,1.5f);
        bodyActor.setMetal();
        bodyActor.setColor(Color.BROWN);
        bodyActor.getPosition().y = 1.5f;
//        buildModel(4,4,4,true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            moveRight(-1*delta * speed);
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.D)){
            moveRight(1*delta * speed);
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.W)){
            moveForward(1*delta * speed);
        }else if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
            moveForward(-1*delta * speed);
        }

//        rotation.setFromAxis(0,1,0,angle+1);


    }
}
