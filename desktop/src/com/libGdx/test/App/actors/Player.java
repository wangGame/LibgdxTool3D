package com.libGdx.test.App.actors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.stage.Stage3D;

public class Player extends BaseActor3D {
    private boolean isMoving;
    private float mevementSpeed;
    private float sprintScalar;
    private Vector2 moveVector;
    private float rotateSpeed;
    private float TIME_RESET_THRESHOLD =10000f;
    private float totalTime;

    private float bobFrequency;
    private float bobAmount;
    private float bobCounter;

    private boolean isForcedToMove;
    private float forceMoveY;
    private float forceMoveZ;
    private float forceTime;
    private float SECONDS_FORCED_TO_MOVE = 0.25f;

    private boolean isShaking;
    private float shakeAmount;

    private float rollAngle;
    private float ROLL_ANGLE_MAX = .8f;
    private float ROLL_INCREMENT = 0.02f;

    private Vector2 lastPosition;
    private static final int MOVE_FORWARD = Input.Keys.W;
    private static final int MOVE_LEFT = Input.Keys.A;
    private static final int MOVE_BACKWARD = Input.Keys.S;
    private static final int MOVE_RIGHT = Input.Keys.D;
    private static final int SPRINT = Input.Keys.SHIFT_LEFT;

    private Stage3D stage3D;
    private Stage stage;
    public Player(float x, float y, float z, float rotation, Stage stage, Stage3D stage3D) {
        super(x, y, z);

        this.stage = stage;
        this.stage3D = stage3D;

        buildModel(1.7f,3f,1.7f,true);
        setBaseRectangle();
        isVisible = false;
        turnBy(rotation);

        stage3D.camera.position.y = getPosition().y;
        stage3D.camera.position.z = getPosition().z;

        lastPosition = new Vector2(getPosition().y,getPosition().z);
    }

    @Override
    public void act(float dt) {
        super.act(dt);

        if (isPause){
            return;
        }

        if (isForcedToMove){
            forceMove(dt);
        }else {
            keyBoardPolling(dt);
        }
        mousePolling();

        stage3D.rollCamera(rollAngle);

        stage3D.camera.position.y = position.y;
        stage3D.camera.position.z = position.z;

        if (isShaking && !isForcedToMove){
            shake();
        }else {
            headBobbing(dt);
        }

        checkIfMoving();
    }

    public void muzzleLight(){
        stage3D.lightManager.addMuzzleLight(position);
    }

    public void forceMoveAwayFrom(BaseActor3D source){
        isForcedToMove = true;
        if (position.y - source.getPosition().y <= 1){
            forceMoveY *= -1;
        }
        if (position.z - source.getPosition().z <= 1){
            forceMoveZ *= -1;
        }

        forceTime +=SECONDS_FORCED_TO_MOVE;
    }

    public void shakeyCam(float duration,float amount){

    }

    private void checkIfMoving() {

    }

    private void headBobbing(float dt) {
    }

    private void shake() {

    }

    private void mousePolling() {

    }

    private void keyBoardPolling(float dt) {

    }

    private void forceMove(float dt) {

    }
}
