package com.bullet;

import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.libGdx.test.base.LibGdxTestMain;

public class TestApp extends LibGdxTestMain {
    public static void main(String[] args) {
        TestApp app = new TestApp();
        app.start();
    }

    @Override
    public void useShow(Stage stage) {
        super.useShow(stage);
        //开启S
        Bullet.init(true);
    }
}
