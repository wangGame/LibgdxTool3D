package com.libGdx.test.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.kw.gdx.BaseGame;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.screen.BaseScreen3D;
import com.kw.gdx.d3.stage.Stage3D;
import com.kw.gdx.screen.BaseScreen;

/**
 * @Auther jian xian si qi
 * @Date 2023/6/21 15:51
 */
public class LibGdxTestMain extends BaseGame {

    public Stage3D modelStage;
    protected Stage uiStage;

    @Override
    protected void loadingView() {
        super.loadingView();
        setScreen(new TestScreen(this));
    }


    public static void run(Class<? extends LibGdxTestMain> c){
        try{
            LibGdxTestMain libGdxTestMain = c.getDeclaredConstructor().newInstance();
            libGdxTestMain.start();
        }catch (Exception e){

        }
    }

    class TestScreen extends BaseScreen3D {
        public TestScreen(BaseGame game) {
            super(game);
            modelStage = stage3D;
            uiStage = stage;
        }

        @Override
        public void show() {
            super.show();
            useShow(stage);
        }
    }

    public void useShow(Stage stage) {

    }

    public void addModelActor(BaseActor3D actor){
        modelStage.addActor(actor);
    }

    public void addActor(Actor actor){
        uiStage.addActor(actor);
    }


    public void start() {
        start(this);
    }

    public void start(LibGdxTestMain test) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
//        Gdx.isJiami = true;
        new LwjglApplication(test, config);
    }

    @Override
    protected void initViewport() {
        stageViewport = new ExtendViewport(720,1280);
    }
}
