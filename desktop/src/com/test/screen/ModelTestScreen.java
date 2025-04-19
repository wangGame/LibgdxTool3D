package com.test.screen;

import com.badlogic.gdx.math.Vector3;
import com.kw.gdx.BaseGame;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;

public class ModelTestScreen extends BaseScreen3D {
    public ModelTestScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        ModelActor3D teaCup = new ModelActor3D(Asset3D.getAsset3D().getModel("tile/teacup.g3db"));
        stage3D.addActor(teaCup);
        teaCup.setPosition(-4,-0.5f,0);

        {
//            实时更新
            Vector3 vector3 = new Vector3();
            teaCup.getModel().transform.getTranslation(vector3);
            vector3.x += 3;
            teaCup.getModel().transform.setTranslation(vector3);


        }
    }
}
