package com.test;

import com.kw.gdx.BaseGame;
import com.test.screen.GameScreen;
import com.test.screen.ShowAllModelScreen;

public class MajGame extends BaseGame {
    @Override
    protected void initScreen() {
        setScreen(ShowAllModelScreen.class);
    }
}
