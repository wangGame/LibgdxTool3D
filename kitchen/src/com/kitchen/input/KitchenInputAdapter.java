package com.kitchen.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.kitchen.counter.CommonCounter;
import com.kitchen.KitchenScreen;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.world.WorldSystem;

public class KitchenInputAdapter extends InputAdapter {
    private KitchenScreen kitchenScreen;
    public KitchenInputAdapter(KitchenScreen kitchenScreen){
        this.kitchenScreen = kitchenScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT){
            kitchenScreen.getPlayer().leftMove();
        }else if (keycode == Input.Keys.RIGHT){
            kitchenScreen.getPlayer().rightMove();
        }else if (Input.Keys.UP==keycode){
            kitchenScreen.getPlayer().upMove();
        }else if (Input.Keys.DOWN == keycode){
            kitchenScreen.getPlayer().downMove();
        }else if (Input.Keys.W == keycode){
            kitchenScreen.getPlayer().pickPlate(1);
        }else if (keycode == Input.Keys.E){
            BaseActor3D actor3D = checkPointCounter();
            if (actor3D!=null) {
                if (actor3D instanceof CommonCounter) {
                    CommonCounter actor3D1 = (CommonCounter) (actor3D);
//                ((ModelActor3D)(actor3D)).setMaterialTexture(Asset.getAsset().getTexture("shuoming.png"));
                    kitchenScreen.getManager().option(kitchenScreen.getPlayer(),actor3D1);
                }
            }
        }else if (Gdx.input.isKeyPressed(Input.Keys.C)){
            BaseActor3D actor3D = checkPointCounter();
            if (actor3D!=null) {
                if (actor3D instanceof CommonCounter) {
                    CommonCounter actor3D1 = (CommonCounter) (actor3D);
                    kitchenScreen.getManager().doKitchen(kitchenScreen.getPlayer(),actor3D1);
                }
            }
        }
        return super.keyDown(keycode);
    }


    private BaseActor3D checkPointCounter() {
        Vector3 position = kitchenScreen.getPlayer().getPosition();
        Vector2 currentDir = kitchenScreen.getPlayer().getCurrentDir();
        Vector3 startV3 = position.cpy();
        startV3.y = 50;
        Vector3 endV3 = startV3.cpy();
        endV3.x += currentDir.x*60;
        endV3.z -= currentDir.y*60;
        BaseActor3D actor3D = WorldSystem.getInstance().rayTest(startV3, endV3);
        return actor3D;
    }
}
