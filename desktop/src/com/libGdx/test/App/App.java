package com.libGdx.test.App;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.libGdx.test.App.screen.MenuScreen;
import com.libGdx.test.base.LibGdxTestMain;
import com.libGdx.test.wave.BaseActor;
import com.libGdx.test.wave.ShockwaveBackground;

public class App extends LibGdxTestMain {
    private BaseActor blackOverlay;
    public static void main(String[] args) {
        App app = new App();
        app.start();
    }

    @Override
    public void useShow(Stage stage) {
        super.useShow(stage);
//        enterDoorExam();
        ShockwaveBackground shockwaveBackground = new ShockwaveBackground("images/excluded/splash.jpg");
        stage.addActor(shockwaveBackground);
        blackOverlayInitialization();
    }

    private void blackOverlayInitialization() {
        this.blackOverlay = new BaseActor(0,0);
        uiStage.addActor(blackOverlay);
        TextureAtlas atlas = Asset.getAsset().getAtlas("images/included/packed/images.pack.atlas");
        blackOverlay.loadImage(atlas.findRegion("whitePixel"));
        blackOverlay.setColor(Color.BLACK);
        blackOverlay.setSize(Constant.GAMEWIDTH,Constant.GAMEHIGHT);
        blackOverlay.setTouchable(Touchable.disabled);

        float totalDurationInSeconds = 4;
//        blackOverlay.addAction(
//                Actions.sequence(
//                        Actions.fadeOut(totalDurationInSeconds / 3),
//                        Actions.delay(totalDurationInSeconds / 3),
//                        Actions.fadeIn(totalDurationInSeconds / 3)
//                )
//        );
        blackOverlay.addAction(Actions.after(
                Actions.run(()->{
                    setScreen(new MenuScreen(this));
                })
        ));
    }

    private void enterDoorExam() {
        //模型
        BaseActor3D actor3D = new BaseActor3D(0,0,-10);
        modelStage.addActor(actor3D);
        actor3D.buildModel(1,1,1,false);
        actor3D.setScale(2,2,2);
        actor3D.setTurnAngle(10);

        //显示sprite
        //ui
        Image image = new Image(Asset.getAsset().getTexture("3_34_24.png"));
        uiStage.addActor(image);
        image.setScale(0.1f);
    }
}
