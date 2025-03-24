package com.demo.kitchen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.demo.kitchen.screen.GameScreen;
import com.kw.gdx.BaseGame;

public class KitchenGame extends BaseGame {
    @Override
    public void create() {
        super.create();
        setScreen(new GameScreen(this));
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
        new LwjglApplication(new KitchenGame(), config);
    }
}
