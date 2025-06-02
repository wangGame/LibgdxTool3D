package com.tile;

import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;

public class TitleGame extends BaseGame {
    @Override
    public void create() {
        super.create();
        Constant.viewColor.set(0,0,0,1);
        setScreen(LoadingScreen.class);
    }
}
