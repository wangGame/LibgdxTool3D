package com.fuzzycat.mahjongsolitaire;

import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class InvalidTile {
    public static final float DURATION = 0.5f;

    public Tile tile;
    public ModelInstance overlayModelInstance;
    public float timer;

    public InvalidTile(Tile tile, ModelInstance tileOverlayModelInstance) {
        this.tile = tile;
        overlayModelInstance = tileOverlayModelInstance.copy();
        timer = DURATION;
    }
}