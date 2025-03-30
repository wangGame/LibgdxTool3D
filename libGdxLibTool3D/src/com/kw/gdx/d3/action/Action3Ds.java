package com.kw.gdx.d3.action;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.Pools;

public class Action3Ds extends Actions {
    static public <T extends Action3D> T action3D (Class<T> type) {
        Pool<T> pool = Pools.get(type);
        T action = pool.obtain();
        action.setPool(pool);
        return action;
    }

    static public AddAction3D addAction3D(Action3D action3D){
        AddAction3D addAction3D = Pools.obtain(AddAction3D.class);
        addAction3D.setAction(action3D);
        return addAction3D;
    }

    static public IntAction3D intAction3D(int start,int end,Interpolation interpolation,float duration){
        IntAction3D intAction3D = Pools.obtain(IntAction3D.class);
        intAction3D.setStart(start);
        intAction3D.setEnd(end);
        intAction3D.setInterpolation(interpolation);
        intAction3D.setDuration(duration);
        return intAction3D;
    }

    static public MoveToAction3D moveToAction3D(float x,float y,float z,float time,Interpolation interpolation){
        MoveToAction3D moveToAction3D = new MoveToAction3D();
        moveToAction3D.setPosition(x,y,z);
        moveToAction3D.setDuration(time);
        moveToAction3D.setInterpolation(interpolation);
        return moveToAction3D;
    }

    static public RotateToAction3D rotation3D(float rotationX,float rotationY ,float rotationZ,float time,Interpolation interpolation){
        RotateToAction3D action3D = new RotateToAction3D();
        action3D.setEndX(rotationX);
        action3D.setEndY(rotationY);
        action3D.setEndZ(rotationZ);
        action3D.setDuration(time);
        action3D.setInterpolation(interpolation);
        return action3D;
    }
}
