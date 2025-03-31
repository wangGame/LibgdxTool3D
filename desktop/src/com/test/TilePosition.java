package com.test;

public class TilePosition {
    public float x;
    public float y;
    public float z;
    public String texturePath;
    public TilePosition(float x,float y,float z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.texturePath = "mahjong_tile_" + ((int)(Math.random() * 26+1))+".png";
    }
}
