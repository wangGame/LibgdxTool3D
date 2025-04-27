package com.kitchen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.kitchen.screen.KitchenGame;
import com.kitchen.screen.LoadingScreen;
import com.kw.gdx.BaseGame;

public class KitchenApp extends BaseGame {
    @Override
    public void create() {
        super.create();
        setScreen(LoadingScreen.class);
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil = 8;
        config.y = 0;
        config.height = (int) ( 1080* 0.5f);
        config.width = (int) (1920 * 0.5f);
        new LwjglApplication(new KitchenApp(), config);
    }
}
