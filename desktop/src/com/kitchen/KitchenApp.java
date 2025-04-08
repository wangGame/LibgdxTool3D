package com.kitchen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.kitchen.screen.KitchenGame;
import com.kw.gdx.BaseGame;

public class KitchenApp extends BaseGame {
    @Override
    public void create() {
        super.create();
        setScreen(KitchenGame.class);
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil = 8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
        new LwjglApplication(new KitchenApp(), config);
    }
}
