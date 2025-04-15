package com.test.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attributes;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalMaterial;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.demo.kitchen.actor.ModelActor3D;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.action.Action3Ds;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;
import com.sun.jdi.connect.Connector;
import com.test.TilePosition;
import com.test.TitleLevel;

public class GameScreen extends BaseScreen3D {
    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        Model model = Asset3D.getAsset3D().getModel("tile/table.g3db");
        ModelActor3D modelActor3D = new ModelActor3D(model){
            @Override
            public void touchUp(Vector3 vector3, int pointer, int button) {
                super.touchUp(vector3, pointer, button);
                setColor(Color.BLUE);
            }
        };
        stage3D.addActor(modelActor3D);
        Texture woodTexture = Asset.getAsset().getTexture("tile/Bd.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        modelActor3D.setMaterialTexture(woodTexture);
        modelActor3D.setScale(7,7,7);
        modelActor3D.setPosition(0,-0.5f,0);

        BoundingBox boundingBox = new BoundingBox();
        modelActor3D.getModel().calculateBoundingBox(boundingBox);
        System.out.println(boundingBox);
        modelActor3D.getModel().transform.setToScaling(0.1f,0.1f,0.1f);
        modelActor3D.getModel().calculateBoundingBox(boundingBox);
        System.out.println(boundingBox);

//
//        Actor3D plateActor =  new Actor3D(Asset3D.getAsset3D().getModel("tile/Plate.g3db"));
//        stage3D.addActor(plateActor);
//        plateActor.setPosition(3,3,3);

        ModelActor3D teaCup = new ModelActor3D(Asset3D.getAsset3D().getModel("tile/teacup.g3db"));
//        stage3D.addActor(teaCup);
        teaCup.setPosition(-13,-0.5f,0);
        Attributes attributes1 = new Attributes();


//        model = modelBuilder.createBox(5, 5, 5, mat,
//                VertexAttributes.Usage.Position |
//                        VertexAttributes.Usage.Normal);
//        instance = new ModelInstance(model);

//        我们可以在ColorAttribute类之后添加FloatAttribute. flashShininess（8f），这将使盒子周围的灯光闪闪发光
//        模型它有位置属性，因为每个立方体都需要位置，normal属性是为了确保灯光正常工作

        attributes1.set(
                ColorAttribute.createDiffuse(0.39f, 0.09f, 0.07f, 1.0f),
                ColorAttribute.createSpecular(1.0f, 1.0f, 1.0f, 1.0f)
                , FloatAttribute.createShininess(1000.0f)
        );
        teaCup.setMetal(attributes1);
        teaCup.setScale(1,1,1);

        ModelActor3D teaPot = new ModelActor3D(Asset3D.getAsset3D().getModel("tile/teapot.g3db"));
//        stage3D.addActor(teaPot);
        teaPot.setPosition(13,-0.5f,0);

        Attributes attributes = new Attributes();
        attributes.set(ColorAttribute.createDiffuse(0.39f, 0.09f, 0.07f, 1.0f),
                ColorAttribute.createSpecular(1.0f, 1.0f, 1.0f, 1.0f),
                FloatAttribute.createShininess(100.0f));
        teaPot.setMetal(attributes);

        teaPot.addAction(Action3Ds.rotation3D(180,180,180,3, Interpolation.bounceIn));
        teaPot.addAction(Action3Ds.moveToAction3D(-10,3,-10,4,Interpolation.linear));


        ModelActor3D modelActor3D1 = new ModelActor3D();
        stage3D.addActor(modelActor3D1);
        modelActor3D1.buildModel(3,3,3,true);


        ModelActor3D plate = new ModelActor3D(Asset3D.getAsset3D().getModel("tile/Plate.g3db"));
//        stage3D.addActor(plate);
        plate.setScale(0.5f,0.5f,0.5f);

        TitleLevel titleLevel = new TitleLevel();
        titleLevel.initLevel();
        Array<TilePosition> tilePositions = titleLevel.getTilePositions();
        for (TilePosition tilePosition : tilePositions) {

            ModelActor3D majActor = new ModelActor3D(Asset3D.getAsset3D().getModel("tile/mahjong_tile.g3db")){
                @Override
                public void touchUp(Vector3 vector3, int pointer, int button) {
                    super.touchUp(vector3, pointer, button);
                    Vector3 position = getPosition();
                    addAction(
                            Action3Ds.parallel3D(
                                    Action3Ds.moveToAction3D(position.x,position.y+10,position.z,1f,Interpolation.linear)
                                    )
                    );
                }
            };
            stage3D.addActor(majActor);
            majActor.setPosition(tilePosition.x,tilePosition.y,tilePosition.z );
            majActor.setMaterialTexture(Asset.getAsset().getTexture("tile/"+tilePosition.texturePath));

        }

    }
}
