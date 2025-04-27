package com.kitchen.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.kitchen.actor.PlayerActor;
import com.kitchen.counter.BreadCounter;
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
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class GameView extends BaseActor3DGroup {
    private PlayerActor player;
    private KitchenManager manager;
    private float modelCollisionScale = 35;
    private OrderView orderView;
    public GameView(OrderView orderView){
        this.orderView = orderView;
        manager = new KitchenManager();
        envModel();
        counterModel();
        playerModel();
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
        addActor3D(player);
        player.initPlayer();
        player.setBodyOff(new Vector3(0,-50,0));
        player.addBody(50,1,player);
    }

    private void envModel() {
        Model model = Asset3D.getAsset3D().getModel("kitchen/model/table.g3db");
        ModelActor3D modelActor3D = new ModelActor3D(model);
        addActor3D(modelActor3D);
        modelActor3D.setScale(90,0.01f,90);
        Texture woodTexture = Asset.getAsset().getTexture("kitchen/Texture/ButtonBackground.png");
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        modelActor3D.setMaterialTexture(woodTexture);
        modelActor3D.setPosition(0,-0.5f,-20);
        modelActor3D.addBody(5,0);
        {
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(100f, 300f, 900f,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                addActor3D(a);
                a.setPosition(-500, 0, -50f);
                a.setColor(Color.valueOf("#4F687B"));
            }
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(1000f, 300f, 900f,
                        new Material(ColorAttribute.createDiffuse(Color.BLACK)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                addActor3D(a);
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
                addActor3D(a);
                a.setPosition(500, 0, -50f);
                a.setColor(Color.valueOf("#4F687B"));
            }
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(1000f, 300f, 900f,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                addActor3D(a);
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
                addActor3D(a);
                a.setPosition(0, 0, -350f);
                a.setColor(Color.valueOf("#4F687B"));
            }
            {
                ModelBuilder modelBuilder = new ModelBuilder();
                Model squareModel = modelBuilder.createBox(2100f, 300f, 1000f,
                        new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelActor3D a = new ModelActor3D(squareModel);
                addActor3D(a);
                a.setPosition(0, 0, -900f);
                a.setColor(Color.BLACK);
            }
        }
    }


    private void counterModel() {
        KitchenBuju kitchenBuju = new KitchenBuju();
        ArrayMap<Integer, Array<Integer>> buju = kitchenBuju.getBuju();
        Array<Integer> leftData = buju.get(1);
        Array<CommonCounter> leftCommonCounters = genCounter(leftData);
        for (int i = 0; i < leftCommonCounters.size; i++) {
            CommonCounter commonCounter = leftCommonCounters.get(leftCommonCounters.size-1-i);
            addActor3D(commonCounter);
            commonCounter.setPosition(-4.5f*90,50,(i-3)*90);
            commonCounter.addBody(modelCollisionScale,0,commonCounter);
        }
        Array<Integer> forward = buju.get(2);
        Array<CommonCounter> forwardCommonCounters = genCounter(forward);
        for (int i = 0; i < forwardCommonCounters.size; i++) {
            CommonCounter commonCounter = forwardCommonCounters.get(i);
            addActor3D(commonCounter);
            commonCounter.setPosition((i-3.5f)*90,50,-3.f*90);
            commonCounter.addBody(modelCollisionScale,0,commonCounter);
        }

        Array<Integer> right = buju.get(3);
        Array<CommonCounter> rightCommonCounters = genCounter(right);
        for (int i = 0; i < rightCommonCounters.size; i++) {
            CommonCounter commonCounter = rightCommonCounters.get(rightCommonCounters.size-1-i);
            addActor3D(commonCounter);
            commonCounter.setPosition(4.5f*90,50,(i-3)*90);
            commonCounter.addBody(modelCollisionScale,0,commonCounter);
        }
        Array<Integer> back = buju.get(4);
        Array<CommonCounter> backCommonCounters = genCounter(back);
        for (int i = 0; i < backCommonCounters.size; i++) {
            CommonCounter commonCounter = backCommonCounters.get(i);
            addActor3D(commonCounter);
            commonCounter.setPosition((i-3.5f)*90,50,3.f*90);
            commonCounter.addBody(modelCollisionScale,0,commonCounter);
            commonCounter.setFromAxis(0,1,0,180);
        }
    }

    public PlayerActor getPlayer() {
        return player;
    }

    public KitchenManager getManager() {
        return manager;
    }
}
