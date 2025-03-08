package com.libGdx.test.App.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.screen.BaseScreen3D;
import com.kw.gdx.listener.OrdinaryButtonListener;
import com.kw.gdx.screen.BaseScreen;
import com.libGdx.test.App.label.MadeByLabel;

import java.nio.file.OpenOption;

public class MenuScreen extends BaseScreen3D {
    private Table uiTable;
    public MenuScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        uiTable = new Table();
        uiTable.setFillParent(true);
        stage.addActor(uiTable);
        TextureAtlas atlas = Asset.getAsset().getAtlas("images/included/packed/images.pack.atlas");
        Image featureGraphics = new Image(atlas.findRegion("feature graphics"));
        featureGraphics.setOrigin(Align.center);
        featureGraphics.addAction(Actions.sequence(Actions.fadeOut(0), Actions.fadeIn(.5f)));
        uiTable.add(featureGraphics)
                .padBottom(Gdx.graphics.getHeight() * .045f)
                .size(Gdx.graphics.getWidth() * .6f, Gdx.graphics.getHeight() * .225f)
                .row();

        //按钮
        uiTable.defaults()
                .width(Gdx.graphics.getWidth() * 0.125f)
                .width(Gdx.graphics.getHeight() * 0.075f)
                .spaceTop(Gdx.graphics.getHeight()*0.01f);


        uiTable.add(startButton()).row();
        uiTable.add(optionsButton()).row();
        uiTable.add(exitButton()).row();
        uiTable.defaults().reset();


        uiTable.add(new MadeByLabel())
                .fillX()
                .padTop(Gdx.graphics.getHeight() * .09f);


    }

    private TextButton exitButton() {
        TextButton button = new TextButton("exit btn",new TextButton.TextButtonStyle(){
            {
                font = Asset.getAsset().loadBitFont("fonts/arcade26.fnt");
                fontColor = Color.BLACK;
            }
        });
        button.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });
        return button;
    }

    private TextButton optionsButton() {
        TextButton button = new TextButton("option btn",new TextButton.TextButtonStyle(){
            {
                font = Asset.getAsset().loadBitFont("fonts/arcade26.fnt");
                fontColor = Color.BLACK;
            }
        });
        button.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(OptionScreen.class);
            }
        });
        return button;
    }

    private TextButton startButton() {
        TextButton button = new TextButton("start btn",new TextButton.TextButtonStyle(){
            {
                font = Asset.getAsset().loadBitFont("fonts/arcade26.fnt");
                fontColor = Color.BLACK;
            }
        });
        button.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(GameScreen.class);
            }
        });
        return button;
    }
}
