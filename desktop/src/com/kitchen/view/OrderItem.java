package com.kitchen.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.asset.Asset3D;

public class OrderItem extends Group {
    public OrderItem(int type){
        setSize(1000,400);
        setDebug(true);
        Label name = new Label("han bao bao",new Label.LabelStyle(){{font= Asset.getAsset().loadBitFont("fonts/arcadeRounded.fnt");
        }});
        name.setFontScale(2);
        addActor(name);
        name.setPosition(40,getHeight()-40, Align.topLeft);
        if (type == 1) {
            addActor(new Table() {{
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/Bread.png")));
                pack();
            }});
        }else if (type == 2){
            addActor(new Table() {{
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/Bread.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/CabbageSlices.png")));
                pack();
            }});
        }else if (type == 3){
            addActor(new Table() {{
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/Bread.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/CabbageSlices.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/CheeseSlice.png")));
                pack();
            }});
        }else if (type == 4){
            addActor(new Table() {{
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/Bread.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/CabbageSlices.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/CheeseSlice.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/MeatPattyCooked.png")));
                pack();
            }});
        }else if (type == 5){
            addActor(new Table() {{
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/Bread.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/CabbageSlices.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/CheeseSlice.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/MeatPattyCooked.png")));
                add(new Image(Asset.getAsset().getTexture("kitchen/Texture/TomatoSlice.png")));
                pack();
            }});
        }
    }


}
