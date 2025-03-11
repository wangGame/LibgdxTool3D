package com.fuzzycat.mahjongsolitaire;

public class MatchedTile {
    public static final float DURATION = 0.75f;

    public Tile tile;
    public float timer;

    public MatchedTile(Tile tile) {
        this.tile = tile;
        this.timer = DURATION;
    }
}