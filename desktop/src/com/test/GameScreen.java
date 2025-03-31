package com.test;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.Array;
import com.demo.kitchen.actor.Actor3D;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;

public class GameScreen extends BaseScreen3D {
    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Model model = Asset3D.getAsset3D().getModel("tile/table.g3db");
        Actor3D actor3D = new Actor3D(model);
        stage3D.addActor(actor3D);
        Texture woodTexture = Asset.getAsset().getTexture("tile/Bd.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        actor3D.setMaterialTexture(woodTexture);
        actor3D.setScale(3,3,3);
        actor3D.setPosition(0,-0.5f,0);

//        Actor3D plateActor =  new Actor3D(Asset3D.getAsset3D().getModel("tile/Plate.g3db"));
//        stage3D.addActor(plateActor);
//        plateActor.setPosition(3,3,3);

        Actor3D teaCup = new Actor3D(Asset3D.getAsset3D().getModel("tile/teacup.g3db"));
        stage3D.addActor(teaCup);
        teaCup.setPosition(-10,-0.5f,0);

        Actor3D teaPot = new Actor3D(Asset3D.getAsset3D().getModel("tile/teapot.g3db"));
        stage3D.addActor(teaPot);
        teaPot.setPosition(10,-0.5f,0);



//        Actor3D plate = new Actor3D(Asset3D.getAsset3D().getModel("tile/Plate.g3db"));
//        stage3D.addActor(plate);
//        plate.setScale(0.5f,0.5f,0.5f);

        TitleLevel titleLevel = new TitleLevel();
        titleLevel.initLevel();
        Array<TilePosition> tilePositions = titleLevel.getTilePositions();
        for (TilePosition tilePosition : tilePositions) {

            Actor3D majActor = new Actor3D(Asset3D.getAsset3D().getModel("tile/mahjong_tile.g3db"));
            stage3D.addActor(majActor);
            majActor.setPosition(tilePosition.x - 10,tilePosition.y,tilePosition.z - 12);
            majActor.setMaterialTexture(Asset.getAsset().getTexture("tile/"+tilePosition.texturePath));

        }
    }
}
