package com.test;

import com.kw.gdx.BaseGame;
import com.test.screen.ModelTestScreen;
import com.test.screen.TestScreen;

public class MajGame extends BaseGame {
    @Override
    protected void initScreen() {
        setScreen(TestScreen.class);
    }
}
