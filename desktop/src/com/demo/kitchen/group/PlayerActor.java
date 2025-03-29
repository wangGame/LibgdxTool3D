package com.demo.kitchen.group;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g3d.Model;
import com.demo.kitchen.actor.Actor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.asset.Asset3D;

public class PlayerActor extends BaseActor3DGroup {

    public PlayerActor(float x, float y, float z) {
        super(x, y, z);
    }

    public void initView(){
        String path = "model/qiu.g3db";
        Model model = Asset3D.getAsset3D().getModel(path);
        Actor3D headActor = new Actor3D(0,0,0,model);
        addActor3D(headActor);
        headActor.setMetal();
        headActor.setColor(Color.BROWN);
        headActor.setPosition(0,3.4f,0f);
        Actor3D bodyActor = new Actor3D(0,0,0,model);
        addActor3D(bodyActor);
        bodyActor.setScale(1.5f,1.5f,1.5f);
        bodyActor.setMetal();
        bodyActor.setColor(Color.BROWN);
        bodyActor.getPosition().y = 1.5f;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rotation.setFromAxis(0,1,0,1);
    }
}
