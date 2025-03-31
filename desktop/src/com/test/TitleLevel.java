package com.test;

import com.badlogic.gdx.utils.Array;

public class TitleLevel{
    private Array<TilePosition>  tilePositions;
    public void initLevel(){
        tilePositions = new Array<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1; j++) {
                for (int z = 0; z < 10; z++) {
                    TilePosition tilePosition = new TilePosition(i*2-10,j*4,z*2.7f-12);
                    tilePositions.add(tilePosition);
                }
            }
        }
    }

    public Array<TilePosition> getTilePositions() {
        return tilePositions;
    }
}
