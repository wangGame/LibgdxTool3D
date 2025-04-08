package com.kitchen.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

import com.demo.kitchen.actor.Actor3D;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;


public class KitchenGame extends BaseScreen3D {
    public KitchenGame(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Model model = Asset3D.getAsset3D().getModel("kitchen/model/table.g3db");
        Actor3D actor3D = new Actor3D(model);
        stage3D.addActor(actor3D);
        actor3D.setScale(200,200,200);
        Texture woodTexture = Asset.getAsset().getTexture("kitchen/Texture/ButtonBackground.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        actor3D.setMaterialTexture(woodTexture);
        actor3D.setPosition(0,-0.5f,0);


        Actor3D player = new Actor3D(Asset3D.getAsset3D().getModel("kitchen/model/player.g3db"));
        stage3D.addActor(player);
        player.setPosition(0,-1,0);
        player.setScale(0.25f,0.25f,0.25f);
//
        Actor3D actor3D1 = new Actor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
        stage3D.addActor(actor3D1);
        actor3D1.setPosition(0,0,0);


        Actor3D x = new Actor3D();
        x.buildModel(1,1,1,true);
        stage3D.addActor(x);
        x.setMetal();
        x.setColor(Color.BLUE);

    }
}
