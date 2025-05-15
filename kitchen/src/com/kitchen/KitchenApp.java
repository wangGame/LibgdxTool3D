package com.kitchen;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.kitchen.content.Content;
import com.kitchen.screen.KitchenGame;
import com.kitchen.screen.LoadingScreen;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;

public class KitchenApp extends BaseGame {
    @Override
    public void create() {
        super.create();
        Constant.viewColor.set(1,1,1,1);
        setScreen(LoadingScreen.class);
    }
}
