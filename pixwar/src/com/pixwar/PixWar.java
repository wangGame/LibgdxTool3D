package com.pixwar;

import com.pixwar.screen.LoadingScreen;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;

public class PixWar extends BaseGame {
    @Override
    public void create() {
        super.create();
        Constant.viewColor.set(0.5f,0.5f,0.5f,1);
        setScreen(LoadingScreen.class);
    }
}
