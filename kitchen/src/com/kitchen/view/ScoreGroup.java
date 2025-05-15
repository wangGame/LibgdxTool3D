package com.kitchen.view;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.utils.Layer;

public class ScoreGroup extends Group {
    public ScoreGroup(){
        setSize(300,300);
        setDebug(true);
        Image shadow = Layer.getShadow();
        addActor(shadow);
        shadow.setSize(getWidth(),getHeight());
        Label score = new Label("han bao bao",new Label.LabelStyle(){{font= Asset.getAsset().loadBitFont("fonts/arcadeRounded.fnt");
        }});
        addActor(score);
        score.setAlignment(Align.center);
        score.setPosition(150,150,Align.center);

    }
}
