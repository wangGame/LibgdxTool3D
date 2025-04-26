package com.kitchen.screen;

import com.KitchenInputAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;

import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.UBJsonReader;
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
import com.kitchen.counter.DeliveryCounter;
import com.kitchen.counter.MeatCounter;
import com.kitchen.counter.PlateCounter;
import com.kitchen.counter.StoneCounter;
import com.kitchen.counter.TomatoCounter;
import com.kitchen.counter.TrashBinCounter;
import com.kitchen.data.KitchenBuju;
import com.kitchen.manager.KitchenManager;
import com.kitchen.view.OrderView;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;
import com.kw.gdx.d3.world.WorldSystem;
import com.order.HanBaoBao;

public class KitchenGame extends BaseScreen3D {
    private PlayerActor player;
    private KitchenManager manager;
    private float modelCollisionScale = 35;
    private OrderView orderView;
    public KitchenGame(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        this.orderView = new OrderView();
//        addActor(orderView);
        getMultiplexer().addProcessor(new KitchenInputAdapter(this));
        manager = new KitchenManager();
        PerspectiveCamera camera = stage3D.getCamera();
        WorldSystem.getInstance().setCam1(camera);
        //bg

        envModel();
        playerModel();
        counterModel();
        showOrder();



//        counterQia();

//
//        Decal decal = Decal.newDecal(new TextureRegion(
//                Asset.getAsset().getTexture("3_34_24.png")));
//        DecalActor decalActor = new DecalActor(decal);
//        stage3D.addActor(decalActor);
    }

    private void showOrder() {


    }

    private void counterModel() {
        KitchenBuju kitchenBuju = new KitchenBuju();
        ArrayMap<Integer, Array<Integer>> buju = kitchenBuju.getBuju();
        Array<Integer> leftData = buju.get(1);
        Array<CommonCounter> leftCommonCounters = genCounter(leftData);
        for (int i = 0; i < leftCommonCounters.size; i++) {
            CommonCounter commonCounter = leftCommonCounters.get(leftCommonCounters.size-1-i);
            stage3D.addActor(commonCounter);
            commonCounter.setPosition(-4.5f*90,50,(i-3)*90);
            commonCounter.addBody(modelCollisionScale,0,commonCounter);
        }
        Array<Integer> forward = buju.get(2);
        Array<CommonCounter> forwardCommonCounters = genCounter(forward);
        for (int i = 0; i < forwardCommonCounters.size; i++) {
            CommonCounter commonCounter = forwardCommonCounters.get(i);
            stage3D.addActor(commonCounter);
            commonCounter.setPosition((i-3.5f)*90,50,-3.f*90);
            commonCounter.addBody(modelCollisionScale,0,commonCounter);
        }

        Array<Integer> right = buju.get(3);
        Array<CommonCounter> rightCommonCounters = genCounter(right);
        for (int i = 0; i < rightCommonCounters.size; i++) {
            CommonCounter commonCounter = rightCommonCounters.get(rightCommonCounters.size-1-i);
            stage3D.addActor(commonCounter);
            commonCounter.setPosition(4.5f*90,50,(i-3)*90);
            commonCounter.addBody(modelCollisionScale,0,commonCounter);
        }
        Array<Integer> back = buju.get(4);
        Array<CommonCounter> backCommonCounters = genCounter(back);
        for (int i = 0; i < backCommonCounters.size; i++) {
            CommonCounter commonCounter = backCommonCounters.get(i);
            stage3D.addActor(commonCounter);
            commonCounter.setPosition((i-3.5f)*90,50,3.f*90);
            commonCounter.addBody(modelCollisionScale,0,commonCounter);
            commonCounter.setFromAxis(0,1,0,180);
        }



    }

    private Array<CommonCounter> genCounter(Array<Integer> integers) {
        Array<CommonCounter> commonCounters = new Array<>();
        for (int i = 0; i < integers.size; i++) {
            Integer i1 = integers.get(i);
            switch (i1){
                case 0:
                    commonCounters.add(new ClearCounter());
                    break;
                case 1:
                    commonCounters.add(new TrashBinCounter());
                    break;
                case 2:
                    commonCounters.add(new PlateCounter());
                    break;
                case 3:
                    commonCounters.add(new DeliveryCounter(orderView));
                    break;
                case 4:
                    commonCounters.add(new StoneCounter());
                    break;
                case 5:
                    commonCounters.add(new MeatCounter());
                    break;
                case 6:
                    commonCounters.add(new CheeseCounter());
                    break;
                case 7:
                    commonCounters.add(new BreadCounter());
                    break;
                case 8:
                    commonCounters.add(new CabbageCounter());
                    break;
                case 9:
                    commonCounters.add(new CutCounter());
                    break;
                case 10:
                    commonCounters.add(new TomatoCounter());
                    break;
            }
        }
        return commonCounters;
    }

    private void playerModel() {
        player = new PlayerActor();
        stage3D.addActor(player);
        player.initPlayer();
        player.setBodyOff(new Vector3(0,-50,0));
        player.addBody(50,1,player);
    }

    private void envModel() {
        Model model = Asset3D.getAsset3D().getModel("kitchen/model/table.g3db");
        ModelActor3D modelActor3D = new ModelActor3D(model);
        stage3D.addActor(modelActor3D);
        modelActor3D.setScale(190,0.01f,190);
        Texture woodTexture = Asset.getAsset().getTexture("kitchen/Texture/ButtonBackground.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        modelActor3D.setMaterialTexture(woodTexture);
        modelActor3D.setPosition(0,-0.5f,-190);
        modelActor3D.addBody(5,0);
        {
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(100f, 300f, 900f,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                stage3D.addActor(a);
                a.setPosition(-500, 0, -50f);
                a.setColor(Color.valueOf("#4F687B"));
            }
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(1000f, 300f, 900f,
                        new Material(ColorAttribute.createDiffuse(Color.BLACK)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                stage3D.addActor(a);
                a.setPosition(-1050, 0, -50f);
                a.setColor(Color.BLACK);
            }
        }
        {
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(100f, 300f, 900f,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                stage3D.addActor(a);
                a.setPosition(500, 0, -50f);
                a.setColor(Color.valueOf("#4F687B"));
            }
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(1000f, 300f, 900f,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                stage3D.addActor(a);
                a.setPosition(1050, 0, -50f);
                a.setColor(Color.BLACK);
            }
        }
        {
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(1100f, 300f, 100f,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                stage3D.addActor(a);
                a.setPosition(0, 0, -350f);
                a.setColor(Color.valueOf("#4F687B"));
            }
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(2100f, 300f, 1000f,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                stage3D.addActor(a);
                a.setPosition(0, 0, -900f);
                a.setColor(Color.BLACK);
            }
        }
    }

    private void testFood() {
//        BreadFood breadFood = new BreadFood();
//        stage3D.addActor(breadFood);
//        breadFood.setScale(2,2,2);
//        breadFood.setPosition(100,100,4);





//        HanBaoBao hanBaoBao = new HanBaoBao();
//        stage3D.addActor(hanBaoBao);

//        CutCounter counter = new CutCounter();
//        stage3D.addActor(counter);


//        TrashBinCounter stoneCounter = new TrashBinCounter();
//        stage3D.addActor(stoneCounter);

//        UBJsonReader reader = new UBJsonReader();
//        JsonValue parse = reader.parse(Gdx.files.internal("kitchen/model/Kitchen Counter_Blue.mat"));
//        System.out.println(parse);


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

    }

    private void counterQia() {

//        BaseActor3D breadActor3D1 = new BreadCounter();
//        stage3D.addActor(breadActor3D1);
//        breadActor3D1.setPosition(90*-5,50,5*90);
//        breadActor3D1.setFromAxis(0,1,0,180);
//        breadActor3D1.addBody(modelCollisionScale,0,breadActor3D1);

//        BaseActor3D cabbageActor3D1 = new CabbageCounter();
//        stage3D.addActor(cabbageActor3D1);
//        cabbageActor3D1.setPosition(90*-3.5f,50,4*90);
//        cabbageActor3D1.setFromAxis(0,1,0,180);
//        cabbageActor3D1.addBody(modelCollisionScale,0,cabbageActor3D1);
//
//        BaseActor3D cheeseActor3D1 = new CheeseCounter();
//        stage3D.addActor(cheeseActor3D1);
//        cheeseActor3D1.setPosition(90*-2.5f,50,4*90);
//        cheeseActor3D1.setFromAxis(0,1,0,180);
//        cheeseActor3D1.addBody(modelCollisionScale,0,cheeseActor3D1);
//
//        BaseActor3D meatActor3D1 = new MeatCounter();
//        stage3D.addActor(meatActor3D1);
//        meatActor3D1.setPosition(90*-1.5f,50,4*90);
//        meatActor3D1.setFromAxis(0,1,0,180);
//        meatActor3D1.addBody(modelCollisionScale,0,meatActor3D1);
//
//        BaseActor3D tomatoActor3D1 = new TomatoCounter();
//        stage3D.addActor(tomatoActor3D1);
//        tomatoActor3D1.setPosition(90*-0.5f,50,4*90);
//        tomatoActor3D1.setFromAxis(0,1,0,180);
//        tomatoActor3D1.addBody(modelCollisionScale,0,tomatoActor3D1);
//
//        BaseActor3D cutCounter = new CutCounter();
//        stage3D.addActor(cutCounter);
//        cutCounter.setPosition(90*0.5f,50,4*90);
//        cutCounter.setFromAxis(0,1,0,180);
//        cutCounter.addBody(modelCollisionScale,0,cutCounter);
//
//
//        BaseActor3D plateCounter = new PlateCounter();
//        stage3D.addActor(plateCounter);
//        plateCounter.setPosition(90*1.5f,50,4*90);
//        plateCounter.setFromAxis(0,1,0,180);
//        plateCounter.addBody(modelCollisionScale,0,plateCounter);
//
//
//        BaseActor3D stoneCounter = new StoneCounter();
//        stage3D.addActor(stoneCounter);
//        stoneCounter.setPosition(90*2.5f,50,4*90);
//        stoneCounter.setFromAxis(0,1,0,180);
//        stoneCounter.addBody(modelCollisionScale, 0,stoneCounter);
//
//        BaseActor3D trashBinCounter = new TrashBinCounter();
//        stage3D.addActor(trashBinCounter);
//        trashBinCounter.setPosition(90*3.5f,50,4*90);
//        trashBinCounter.setFromAxis(0,1,0,180);
//        trashBinCounter.addBody(modelCollisionScale, 0,trashBinCounter);

//        ClearCounter clearCounter1 = new ClearCounter();
//        stage3D.addActor(clearCounter1);
//        clearCounter1.setPosition(90*4,50,5*90);
//        clearCounter1.addBody(modelCollisionScale,0,clearCounter1);

//        ClearCounter clearCounter2 = new ClearCounter();
//        stage3D.addActor(clearCounter2);
//        clearCounter2.setPosition(90*5,50,5*90);
//        clearCounter2.addBody(modelCollisionScale,0,clearCounter2);
//
//        ClearCounter clearCounter3 = new ClearCounter();
//        stage3D.addActor(clearCounter3);
//        clearCounter3.setPosition(90*6,50,5*90);
//        clearCounter3.addBody(modelCollisionScale,0,clearCounter3);

//        for (int i = -3; i <= 4; i++) {
//            ClearCounter modelActor3D1 = new ClearCounter();
//            stage3D.addActor(modelActor3D1);
//            modelActor3D1.setPosition(-4.5f*90,50,i*90);
//            modelActor3D1.setFromAxis(0,1,0,90);
//            modelActor3D1.addBody(modelCollisionScale,0,modelActor3D1);
//        }
//        for (int i = -3; i <= 4; i++) {
//            ClearCounter modelActor3D1 = new ClearCounter();
//            stage3D.addActor(modelActor3D1);
//            modelActor3D1.setPosition(4.5f*90,50,i*90);
//            modelActor3D1.setFromAxis(0,1,0,-90);
//            modelActor3D1.addBody(modelCollisionScale,0,modelActor3D1);
//        }
//        for (int i = -5; i <= 5; i++) {
//            ClearCounter modelActor3D1 = new ClearCounter();
//            stage3D.addActor(modelActor3D1);
//            modelActor3D1.setPosition(i*90,50,-4.f*90);
//            modelActor3D1.addBody(modelCollisionScale,0,modelActor3D1);
//        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        WorldSystem.getInstance().update();
    }

    public PlayerActor getPlayer() {
        return player;
    }

    public KitchenManager getManager() {
        return manager;
    }
}
