//package com.kw.gdx.d3.action;
//
//import com.badlogic.gdx.math.MathUtils;
//import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
//
//public class RotateToAction3D extends TemporalAction3D {
//    private float start, end;
//
//    private boolean useShortestDirection = false;
//
//    public RotateToAction3D () {
//    }
//
//    /** @param useShortestDirection Set to true to move directly to the closest angle */
//    public RotateToAction3D (boolean useShortestDirection) {
//        this.useShortestDirection = useShortestDirection;
//    }
//
//    protected void begin () {
//        start = target.getRotation();
//    }
//
//    protected void update (float percent) {
//        float rotation;
//        if (percent == 0)
//            rotation = start;
//        else if (percent == 1)
//            rotation = end;
//        else if (useShortestDirection)
//            rotation = MathUtils.lerpAngleDeg(this.start, this.end, percent);
//        else
//            rotation = start + (end - start) * percent;
//        target.setRotation(rotation);
//    }
//
//    public float getRotation () {
//        return end;
//    }
//
//    public void setRotation (float rotation) {
//        this.end = rotation;
//    }
//
//    public boolean isUseShortestDirection () {
//        return useShortestDirection;
//    }
//
//    public void setUseShortestDirection (boolean useShortestDirection) {
//        this.useShortestDirection = useShortestDirection;
//    }
//}
