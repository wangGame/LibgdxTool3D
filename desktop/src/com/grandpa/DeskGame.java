package com.grandpa;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.grandpa.loading.DeskScreen;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;

public class DeskGame extends BaseGame {

    @Override
    public void create() {
        super.create();
        Constant.viewColor.set(Color.WHITE);
        setScreen(DeskScreen.class);
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil = 8;
        config.y = 0;
        config.height = (int) ( 1080* 0.5f);
        config.width = (int) (1920 * 0.5f);
        new LwjglApplication(new DeskGame(), config);
    }
}
