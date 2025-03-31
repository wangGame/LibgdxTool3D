package com.cube;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;

public class Cube extends BaseGame {
    @Override
    public void create() {
        super.create();
        Constant.viewColor = new Color(1f,0.5f,0.5f,1.0f);
        setScreen(new CbeGameScreen(this));
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
        new LwjglApplication(new Cube(), config);
    }
}
