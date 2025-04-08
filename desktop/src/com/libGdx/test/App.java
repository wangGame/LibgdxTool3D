package com.libGdx.test;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.demo.kitchen.actor.ModelActor3D;
import com.libGdx.test.base.LibGdxTestMain;

public class App extends LibGdxTestMain {
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    @Override
    public void useShow(Stage stage) {
        super.useShow(stage);
            //模型
            ModelActor3D modelActor3D = new ModelActor3D(0,2,-43);
            modelStage.addActor(modelActor3D);
            modelActor3D.buildModel(1,1,1,false);
            modelActor3D.setScale(2,2,2);
            modelActor3D.setTurnAngle(10);




    }
}
