package com.libGdx.test;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.demo.kitchen.actor.Actor3D;
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
            Actor3D actor3D = new Actor3D(0,2,-43);
            modelStage.addActor(actor3D);
            actor3D.buildModel(1,1,1,false);
            actor3D.setScale(2,2,2);
            actor3D.setTurnAngle(10);




    }
}
