package com.kitchen;

import com.kitchen.view.GameView;
import com.kitchen.view.OrderView;
import com.kitchen.view.ScoreGroup;
import com.kitchen.input.KitchenInputAdapter;
import com.badlogic.gdx.utils.Align;
import com.kitchen.actor.PlayerActor;
import com.kitchen.manager.KitchenManager;
import com.kw.gdx.BaseGame;
import com.kw.gdx.constant.Constant;
import com.kw.gdx.d3.screen.BaseScreen3D;
import com.kw.gdx.d3.world.WorldSystem;

public class KitchenGame extends BaseScreen3D {
    private OrderView orderView;
    private GameView gameView;
    public KitchenGame(BaseGame game) {
        super(game);
    }

    @Override
    public void initView() {
        super.initView();
        getMultiplexer().addProcessor(new KitchenInputAdapter(this));
        this.orderView = new OrderView();
        addActor(orderView);
        orderView.setY(Constant.GAMEHIGHT, Align.top);
        gameView = new GameView(orderView);
        stage3D.addActor(gameView);
        gameView.setPosition(0,0,170);
        WorldSystem.getInstance().setCam1(stage3D.getCamera());
        ScoreGroup scoreGroup = new ScoreGroup();
        addActor(scoreGroup);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        WorldSystem.getInstance().update();
    }

    public PlayerActor getPlayer() {
        return gameView.getPlayer();
    }

    public KitchenManager getManager() {
        return gameView.getManager();
    }
}
