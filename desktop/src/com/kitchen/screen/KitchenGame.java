package com.kitchen.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.food.CabbageFood;
import com.food.CheeseFood;
import com.food.MeatFood;
import com.kitchen.actor.PlayerActor;
import com.kitchen.counter.BreadCounter;;
import com.kitchen.counter.CabbageCounter;
import com.kitchen.counter.CheeseCounter;
import com.kitchen.counter.ClearCounter;
import com.kitchen.counter.CommonCounter;
import com.kitchen.counter.CutCounter;
import com.kitchen.counter.MeatCounter;
import com.kitchen.counter.StoneCounter;
import com.kitchen.counter.TomatoCounter;
import com.kitchen.manager.KitchenManager;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;
import com.kw.gdx.d3.world.WorldSystem;

public class KitchenGame extends BaseScreen3D {
    private PlayerActor player;
    private WorldSystem worldSystem;
    private KitchenManager manager;
    BaseActor3DGroup ro;
    public KitchenGame(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        manager = new KitchenManager();
        worldSystem = WorldSystem.getInstance();
        PerspectiveCamera camera = stage3D.getCamera();
        worldSystem.setCam1(camera);
        //bg
        Model model = Asset3D.getAsset3D().getModel("kitchen/model/table.g3db");
        ModelActor3D modelActor3D = new ModelActor3D(model);
        stage3D.addActor(modelActor3D);
        modelActor3D.setScale(190,0.01f,190);
        Texture woodTexture = Asset.getAsset().getTexture("kitchen/Texture/ButtonBackground.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        modelActor3D.setMaterialTexture(woodTexture);
        modelActor3D.setPosition(0,-0.5f,-190);

        worldSystem.addCollision(modelActor3D.getScale().cpy().scl(5),modelActor3D.getPosition(),0,null);

        player = new PlayerActor();
        stage3D.addActor(player);
        player.initPlayer();
        Vector3 bodyPosition = player.getPosition().cpy();
        bodyPosition.y += 50f;
        player.setBodyOff(new Vector3(200,-50,0));
        worldSystem.addCollision(player.getScale().cpy().scl(50),bodyPosition,1,player);


//        CutCounter counter = new CutCounter();
//        stage3D.addActor(counter);


        StoneCounter stoneCounter = new StoneCounter();
        stage3D.addActor(stoneCounter);
//
//        ro = new BaseActor3DGroup(){
//            private float angle = 0;
//            @Override
//            public void act(float delta) {
//                super.act(delta);
//                angle += delta*40;
//                ro.setFromAxis(0,1,0,angle);
//            }
//        };
//        ro.setPosition(310,10,130);
//        ModelActor3D modelActor3D1 = new ModelActor3D(Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db"));
//        ro.addActor3D(modelActor3D1);
//        modelActor3D1.setFromAxis(1,0,0,50);
//        stage3D.addActor(ro);


//        testFood();

//        counterQia();

//
//        Decal decal = Decal.newDecal(new TextureRegion(
//                Asset.getAsset().getTexture("3_34_24.png")));
//        DecalActor decalActor = new DecalActor(decal);
//        stage3D.addActor(decalActor);
    }

    private void testFood() {
//        BreadFood breadFood = new BreadFood();
//        stage3D.addActor(breadFood);
//        breadFood.setScale(2,2,2);
//        breadFood.setPosition(100,100,4);
    }

    private void counterQia() {

        BaseActor3D breadActor3D1 = new BreadCounter();
        stage3D.addActor(breadActor3D1);
        breadActor3D1.setPosition(90*-5,0,5*90);
        breadActor3D1.setFromAxis(0,1,0,180);
        worldSystem.addCollision(breadActor3D1.getScale().cpy().scl(50),breadActor3D1.getPosition().cpy(),0,breadActor3D1);

        BaseActor3D cabbageActor3D1 = new CabbageCounter();
        stage3D.addActor(cabbageActor3D1);
        cabbageActor3D1.setPosition(90*-4,0,5*90);
        cabbageActor3D1.setFromAxis(0,1,0,180);
        worldSystem.addCollision(cabbageActor3D1.getScale().cpy().scl(50),cabbageActor3D1.getPosition().cpy(),0,cabbageActor3D1);

        BaseActor3D cheeseActor3D1 = new CheeseCounter();
        stage3D.addActor(cheeseActor3D1);
        cheeseActor3D1.setPosition(90*-3,0,5*90);
        cheeseActor3D1.setFromAxis(0,1,0,180);
        worldSystem.addCollision(cheeseActor3D1.getScale().cpy().scl(50),cheeseActor3D1.getPosition().cpy(),0,cheeseActor3D1);

        BaseActor3D meatActor3D1 = new MeatCounter();
        stage3D.addActor(meatActor3D1);
        meatActor3D1.setPosition(90*-2,0,5*90);
        meatActor3D1.setFromAxis(0,1,0,180);
        worldSystem.addCollision(meatActor3D1.getScale().cpy().scl(50),meatActor3D1.getPosition().cpy(),0,meatActor3D1);

        BaseActor3D tomatoActor3D1 = new TomatoCounter();
        stage3D.addActor(tomatoActor3D1);
        tomatoActor3D1.setPosition(90*-1,0,5*90);
        tomatoActor3D1.setFromAxis(0,1,0,180);
        worldSystem.addCollision(tomatoActor3D1.getScale().cpy().scl(50),tomatoActor3D1.getPosition().cpy(),0,tomatoActor3D1);

        for (int i = -5; i <= 5; i++) {
            ClearCounter modelActor3D1 = new ClearCounter();
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(-6*90,0,i*90);
            worldSystem.addCollision(modelActor3D1.getScale().cpy().scl(50),modelActor3D1.getPosition().cpy(),0,modelActor3D1);
        }
        for (int i = -5; i <= 5; i++) {
            ClearCounter modelActor3D1 = new ClearCounter();
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(6*90,0,i*90);
            worldSystem.addCollision(modelActor3D1.getScale().cpy().scl(50),modelActor3D1.getPosition().cpy(),0,modelActor3D1);
        }
        for (int i = -5; i <= 5; i++) {
            ClearCounter modelActor3D1 = new ClearCounter();
            stage3D.addActor(modelActor3D1);
            modelActor3D1.setPosition(i*90,0,-5*90);
            worldSystem.addCollision(modelActor3D1.getScale().cpy().scl(50),modelActor3D1.getPosition().cpy(),0,modelActor3D1);
        }
    }

    @Override
    public void render(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.leftMove();
        }else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.rightMove();
        }else if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            player.upMove();
        }else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            player.downMove();
        }else if (Gdx.input.isKeyPressed(Input.Keys.W)){
            player.pickPlate(1);
        }


        if (Gdx.input.isKeyJustPressed(Input.Keys.E)){
            Vector3 position = player.getPosition();
            Vector2 currentDir = player.getCurrentDir();
            Vector3 startV3 = position.cpy();
            startV3.y = 50;
            Vector3 endV3 = startV3.cpy();
            endV3.x += currentDir.x*60;
            endV3.z -= currentDir.y*60;
            BaseActor3D actor3D = worldSystem.rayTest(startV3, endV3);
            if (actor3D!=null) {
                if (actor3D instanceof CommonCounter) {
                    CommonCounter actor3D1 = (CommonCounter) (actor3D);
//                ((ModelActor3D)(actor3D)).setMaterialTexture(Asset.getAsset().getTexture("shuoming.png"));
                    manager.option(player,actor3D1);
                }
            }
        }

        super.render(delta);
        worldSystem.update();
    }
}
