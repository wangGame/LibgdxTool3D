package com.test;

import com.kw.gdx.BaseGame;
import com.test.screen.GameScreen;

public class MajGame extends BaseGame {
    @Override
    protected void initScreen() {
        setScreen(GameScreen.class);
    }
}
