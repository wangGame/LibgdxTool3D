package com.kitchen.counter;

import com.kitchen.view.OrderView;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;

public class DeliveryCounter extends CommonCounter{
    private OrderView view;
    public DeliveryCounter(OrderView orderView){
        this.view = orderView;
        ModelActor3D baseCounter = new ModelActor3D(Asset3D.getAsset3D()
                .getModel("kitchen/model/Kitchen Counter.g3db"));
        addActor3D(baseCounter);
//
        baseCounter.setMaterialTexture(
                Asset.getAsset().getTexture(
                        "kitchen/model/Kitchen Counter_Blue_AlbedoTransparency.png"));
    }

    @Override
    public void option() {
        super.option();

    }
}
