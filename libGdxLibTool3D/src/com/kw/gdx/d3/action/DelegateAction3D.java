package com.kw.gdx.d3.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;

public abstract class DelegateAction3D extends Action {
    protected Action action;

    /** Sets the wrapped action. */
    public void setAction (Action action) {
        this.action = action;
    }

    public Action getAction () {
        return action;
    }

    abstract protected boolean delegate (float delta);

    public final boolean act (float delta) {
        Pool pool = getPool();
        setPool(null); // Ensure this action can't be returned to the pool inside the delegate action.
        try {
            return delegate(delta);
        } finally {
            setPool(pool);
        }
    }

    public void restart () {
        if (action != null) action.restart();
    }

    public void reset () {
        super.reset();
        action = null;
    }

    public void setActor (Actor actor) {
        if (action != null) action.setActor(actor);
        super.setActor(actor);
    }

    public void setTarget (Actor target) {
        if (action != null) action.setTarget(target);
        super.setTarget(target);
    }

    public String toString () {
        return super.toString() + (action == null ? "" : "(" + action + ")");
    }
}
