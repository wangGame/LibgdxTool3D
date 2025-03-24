package com.kw.gdx.d3.screen;

import com.kw.gdx.BaseGame;
import com.kw.gdx.d3.stage.Stage3D;
import com.kw.gdx.screen.BaseScreen;


public abstract class BaseScreen3D extends BaseScreen {
    protected Stage3D stage3D;

    public BaseScreen3D(BaseGame game) {
        super(game);
        this.stage3D = new Stage3D();
    }

    @Override
    public void render(float delta) {
        //模型在下  ui在上
        //stage3D绘制是否对ui的act有影响？
        stage3D.act(delta);
        stage3D.draw();
//        super.render(delta);
    }
}