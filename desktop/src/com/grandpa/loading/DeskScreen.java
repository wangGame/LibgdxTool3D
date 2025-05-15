package com.grandpa.loading;

import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;

public class DeskScreen extends BaseScreen3D {
    public DeskScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        {
            ModelActor3D modelActor3D = new ModelActor3D(Asset3D.getAsset3D().getModel("assets/grandpa/a-button.g3db"));
            stage3D.addActor(modelActor3D);
            modelActor3D.setScale(100,100,100);
            modelActor3D.setMaterialTexture(Asset.getAsset().getTexture("assets/grandpa/a-button.png"));
            modelActor3D.setPosition(-700,0,-600);
        }
        {
            ModelActor3D modelActor3D = new ModelActor3D(Asset3D.getAsset3D().getModel("assets/grandpa/adbuddiz.g3db"));
            stage3D.addActor(modelActor3D);
            modelActor3D.setScale(300, 300, 300);
            modelActor3D.setMaterialTexture(Asset.getAsset().getTexture("assets/grandpa/adbuddiz.png"));
            modelActor3D.setPosition(-400,0,-200);
        }
        {

            ModelActor3D modelActor3D = new ModelActor3D(Asset3D.getAsset3D().getModel("assets/grandpa/africa.g3db"));
            stage3D.addActor(modelActor3D);
            modelActor3D.setScale(300, 300, 300);
            modelActor3D.setMaterialTexture(Asset.getAsset().getTexture("assets/grandpa/africa.png"));

        }

        {

            ModelActor3D modelActor3D = new ModelActor3D(Asset3D.getAsset3D().getModel("assets/grandpa/desk.g3db"));
            stage3D.addActor(modelActor3D);
            modelActor3D.setScale(50, 50, 50);
            modelActor3D.setMaterialTexture(Asset.getAsset().getTexture("assets/grandpa/desk.png"));

        }
        {

            ModelActor3D modelActor3D = new ModelActor3D(Asset3D.getAsset3D().getModel("assets/grandpa/chest.g3db"));
            stage3D.addActor(modelActor3D);
            modelActor3D.setScale(50, 50, 50);
            modelActor3D.setMaterialTexture(Asset.getAsset().getTexture("assets/grandpa/chest.png"));

        }
    }
}
