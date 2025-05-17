package com.terrin;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.pixwar.PixWar;

public class TerrinApp {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil = 8;
        config.y = 0;
        config.height = (int) ( 1080* 0.5f);
        config.width = (int) (1920 * 0.5f);
        new LwjglApplication(new PixWar(), config);
    }
}
