package com.kw.gdx.d3.actor;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.math.Affine2;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class BaseActor3DGroup extends BaseActor3D{
    private Array<BaseActor3D> actor3DS;
    private final Affine2 worldTransform = new Affine2();
    private
    public BaseActor3DGroup(float x, float y, float z) {
        super(x, y, z);
        this.actor3DS = new Array<>();
    }

    @Override
    public void draw(ModelBatch batch, Environment env) {
        super.draw(batch, env);
    }



    /** Returns the transform for this group's coordinate system. */
    protected Matrix4 computeTransform () {
        Affine2 worldTransform = this.worldTransform;
        worldTransform.setToTrnRotScl(position.x, position.y, rotation, scaleX, scaleY);
        if (originX != 0 || originY != 0) worldTransform.translate(-originX, -originY);

        // Find the first parent that transforms.
        Group parentGroup = parent;
        while (parentGroup != null) {
            if (parentGroup.transform) break;
            parentGroup = parentGroup.parent;
        }
        if (parentGroup != null) worldTransform.preMul(parentGroup.worldTransform);

        computedTransform.set(worldTransform);
        return computedTransform;
    }

}
