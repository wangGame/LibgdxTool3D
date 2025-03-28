package com.kw.gdx.d3.action;

import com.badlogic.gdx.utils.Pool;
import com.kw.gdx.d3.actor.BaseActor3D;

public abstract class Action3D implements Pool.Poolable {
    private BaseActor3D action3D;
    public void setActor3D(BaseActor3D baseActor3D) {
        this.action3D = baseActor3D;
    }
    protected BaseActor3D actor;
    /** The actor this action targets, or null if a target has not been set. */
    protected BaseActor3D target;
    private Pool pool;
    abstract public boolean act (float delta);
    public void restart () {
    }
    public void setActor (BaseActor3D actor) {
        this.actor = actor;
        if (target == null) setTarget(actor);
        if (actor == null) {
            if (pool != null) {
                pool.free(this);
                pool = null;
            }
        }
    }
    public BaseActor3D getActor () {
            return actor;
        }
    public void setTarget (BaseActor3D target) {
        this.target = target;
    }

    public BaseActor3D getTarget () {
        return target;
    }
    public void reset () {
        actor = null;
        target = null;
        pool = null;
        restart();
    }
    public Pool getPool () {
            return pool;
        }
    public void setPool (Pool pool) {
        this.pool = pool;
    }

    public String toString () {
        String name = getClass().getName();
        int dotIndex = name.lastIndexOf('.');
        if (dotIndex != -1) name = name.substring(dotIndex + 1);
        if (name.endsWith("Action")) name = name.substring(0, name.length() - 6);
        return name;
    }
}
