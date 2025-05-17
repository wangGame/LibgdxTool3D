package com.kitchen;

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
