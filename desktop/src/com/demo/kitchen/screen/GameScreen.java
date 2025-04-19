package com.demo.kitchen.screen;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Interpolation;
import com.demo.kitchen.group.PlayerActor;
import com.demo.kitchen.particle.ParticleActor;
import com.kw.gdx.BaseGame;
import com.kw.gdx.d3.action.Action3Ds;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;


public class GameScreen extends BaseScreen3D {
    public GameScreen(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();

//        BaseActor3D floor = new BaseActor3D(0,0,-0f);
//        floor.buildModel(200,1,200,false);
//        floor.setPosition(0,-0.5f,0);
//        floor.setMaterialTexture(Asset.getAsset().getTexture("img.png"));
////        stage3D.addActor(floor);

        PlayerActor playerActor = new PlayerActor(0,0,0);
        stage3D.addActor(playerActor);
        playerActor.initView();
//
//        String models[] = new String[]{
//                "BreadBottom.g3db",
//                "Breadtop.g3db",
//                "Burnedmeatpatty.g3db",
//                "Cabbage slice_shaped.g3db"
//        };
//        int x = 0;
//        for (String modelPath : models) {
//            Model model = Asset3D.getAsset3D().getModel("model/"+modelPath);
//            Actor3D actor3D = new Actor3D(x ,0,0,model){
//                @Override
//                public void act(float delta) {
//                    super.act(delta);
//                    float angle = rotation.getAngle();
//                    rotation.setFromAxis(0,1,0,angle+1);
//                }
//            };
//            x += 6;
//            stage3D.addActor(actor3D);
//            actor3D.setScale(0.1f,0.1f,0.1f);
//        }

        ParticleActor actor = new ParticleActor("effects/gKeyEffect.pfx");
        stage3D.addActor(actor);
        actor.setPosition(9,0,0);
        actor.addAction(Action3Ds.addAction3D(
                Action3Ds.intAction3D(0,100, Interpolation.bounceIn,1)
        ));
//        playerActor.addAction(Action3Ds.moveToAction3D(2,2,2,2,Interpolation.linear));

//        playerActor.addAction(
//                Action3Ds.forever3D(
//                        Action3Ds.rotation3D(0,0,360,2,Interpolation.linear)
//                )
//        );


        playerActor.addAction(
                Action3Ds.parallel3D(
                    Action3Ds.moveToAction3D(-1,7,7,2,Interpolation.linear),
                        Action3Ds.rotation3D(180,180,180,4,Interpolation.linear)
                )
        );

        playerActor.addAction(
                Action3Ds.parallel3D(

                )
        );

    }

    private void createBread() {
        String path = "model/qiu.g3db";
//        String path = "test01.g3db";

        Model model = Asset3D.getAsset3D().getModel(path);
        ModelActor3D braed = new ModelActor3D(0,0,0,model);
        stage3D.addActor(braed);
//        braed.setMaterialTexture(Asset.getAsset().getTexture("model/Bread_AlbedoTransparency.png"));
        braed.setScale(5f,5f,5f);
        braed.setMetal();



    }
}
