package com.test.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.demo.kitchen.actor.Actor3D;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;

public class ShowAllModelScreen extends BaseScreen3D {
    public ShowAllModelScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        FileHandle internal = Gdx.files.internal("grandpa");

        Model model = Asset3D.getAsset3D().getModel("tile/table.g3db");
        Actor3D bg = new Actor3D(model);
        stage3D.addActor(bg);
        Texture woodTexture = Asset.getAsset().getTexture("tile/Bd.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        bg.setMaterialTexture(woodTexture);
        bg.setPosition(0,-0.5f,0);
        bg.setScale(6,6,6);


        Array<String> all = new Array<>();
        for (FileHandle fileHandle : internal.list()) {
            if (fileHandle.name().endsWith(".g3db")) {
                all.add(fileHandle.name());
            }
        }

        float i = 0;
        float y = 0;
        BaseActor3DGroup group = new BaseActor3DGroup();
        Actor3D abutton = new Actor3D(Asset3D.getAsset3D().getModel("grandpa/a-button.g3db"));
        abutton.setMaterialTexture(Asset.getAsset().getTexture("grandpa/a-button.png"));
        group.addActor3D(abutton);
        abutton.setPosition(i,0,y);
        stage3D.addActor(group);
        BoundingBox bounds = abutton.getBounds();
        float max = 2.0f/Math.max(bounds.getWidth(), bounds.getHeight());
        abutton.setScale(max,max,max);

        group.setPosition(-8,0,-8);
        commonMathod(group,"grandpa/a-button.g3db","grandpa/a-button.png", 0,0);
        commonMathod(group,"grandpa/adbuddiz.g3db","grandpa/adbuddiz.png", 3,0);
        commonMathod(group,"grandpa/africa.g3db","grandpa/africa.png", 6,0);


    }

    private void commonMathod(BaseActor3DGroup group,String modelPath,String texture, float i,float y) {
        BoundingBox bounds;
        float max;
        Actor3D adbuddiz = new Actor3D(Asset3D.getAsset3D().getModel(modelPath));
        adbuddiz.setMaterialTexture(Asset.getAsset().getTexture(texture));
        group.addActor3D(adbuddiz);
        adbuddiz.setPosition(i,0,y);

        bounds = adbuddiz.getBounds();
        max = 2.0f/Math.max(bounds.getWidth(), bounds.getHeight());
        adbuddiz.setScale(max,max,max);
    }
}
