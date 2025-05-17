package com.kitchen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.kitchen.actor.PlayerActor;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;
import com.kw.gdx.listener.OrdinaryButtonListener;

public class LoadingScreen extends BaseScreen3D {
    public LoadingScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();

        Model model = Asset3D.getAsset3D().getModel("kitchen/model/table.g3db");
        ModelActor3D modelActor3D = new ModelActor3D(model);
        stage3D.addActor(modelActor3D);
        modelActor3D.setScale(90,0.01f,90);
        Texture woodTexture = Asset.getAsset().getTexture("kitchen/Texture/ButtonBackground.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        modelActor3D.setMaterialTexture(woodTexture);
        modelActor3D.setPosition(0,-0.5f,-20);

        PlayerActor player = new PlayerActor();
        stage3D.addActor(player);
        player.initPlayer();
        player.setPosition(0,0,400);




        Image image = new Image(Asset.getAsset().getTexture("kitchen/KitchenChaosLogo.png"));
        addActor(image);
        image.setOrigin(Align.center);
        image.setPosition(Constant.GAMEWIDTH/2.f,Constant.GAMEHIGHT/2f, Align.center);
        image.addListener(new OrdinaryButtonListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                setScreen(KitchenGame.class);
            }
        });
    }
}
