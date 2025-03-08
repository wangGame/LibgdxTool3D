package com.libGdx.test.App.actors.enemy;

import com.badlogic.gdx.utils.Array;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.libGdx.test.A;
import com.libGdx.test.App.actors.Tile;

public class Enemy extends BaseActor3D {
    private static float avtivationRange = 30;
    private int health = 1;
    private boolean isDead;
    private boolean isActive;
    private boolean isRanged;
    private boolean isHitscan;
    private int score;

    private Array<Tile> patrol;
    private int patrolIndex;


    public Enemy(float x, float y, float z) {
        super(x, y, z);
        this.patrol = new Array<>();
    }
}
