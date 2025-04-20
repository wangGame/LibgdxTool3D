package com.kitchen.font;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.kw.gdx.d3.actor.BaseActor3D;

public class Label3D extends BaseActor3D {
    public Label3D(){

        BitmapFont font = new BitmapFont();
        GlyphLayout layout = new GlyphLayout();
        layout.setText(font, "3D Label");
//        font.draw(batch, layout, modelInstance.transform.getTranslation(new Vector3()).x, modelInstance.transform.getTranslation(new Vector3()).y + 1);
    }
}
