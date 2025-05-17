package com.pixwar.screen;

import com.pixwar.terrain.Terrain;
import com.kw.gdx.BaseGame;
import com.kw.gdx.d3.screen.BaseScreen3D;

public class LoadingScreen extends BaseScreen3D {
    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Terrain terrain = new Terrain();
        stage3D.addActor(terrain);
        terrain.setPosition(-100,100,10);
        terrain.setScale(5,5,5);

    }
}
