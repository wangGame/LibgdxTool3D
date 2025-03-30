package com.kw.gdx.d3.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ShaderProvider;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;

public class Stage3D extends InputAdapter {
    public boolean intervalFlag;
    public int visibleCount = 0;
    public Environment environment;
    public PerspectiveCamera camera;
//    private ArrayList<BaseActor3D> actorList3D;
    private BaseActor3DGroup actorList3D;
    private final ModelBatch modelBatch;
    private float intervalCounter;
    private final float INTERVAL_COUNTER_FREQUENCY = 1;
    public CameraInputController camController;//视角控制器

    public Stage3D() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 1f, 1f, 1f, 1f));//环境光
        DirectionalLight set = new DirectionalLight().set(1f, 1f, 1f, 30, -30, 1);
        float intensity = 0.4f;
        Color color = Color.valueOf("#FFF4D6");
        color.r = color.r * intensity;
        color.g = color.g * intensity;
        color.b = color.b * intensity;
        color.a = 0.1f;
        set.setColor(color);
        environment.add(set);
        camera = new PerspectiveCamera(60, 5, 5);
        camera.position.set(0f, 21, 21f);
        camera.direction.x = 45;
        camera.lookAt(0,0,0);
        camera.near = 0.3f;
        camera.far = 1300f;
        camController = new CameraInputController(camera);
        DefaultShader.Config config = new DefaultShader.Config();
        config.numDirectionalLights = 1;
        config.numPointLights = 50;
        config.numSpotLights = 0;
        ShaderProvider shaderProvider = new DefaultShaderProvider(config);
        modelBatch = new ModelBatch(shaderProvider);
        actorList3D = new BaseActor3DGroup(0,0,0);
    }

    public void act(float dt) {
//        camController.update();
        camera.update();
        actorList3D.act(dt);
        setIntervalFlag(dt);
    }

    public void draw() {
        modelBatch.begin(camera);
        visibleCount = 0;
        actorList3D.draw(modelBatch,environment);
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
    }

    public void addActor(BaseActor3D ba) {
        actorList3D.addActor3D(ba);
        ba.setStage3D(this);
    }

    public void removeActor(BaseActor3D ba) {
        actorList3D.remove3D(ba);
    }

    public BaseActor3DGroup getRoot() {
        return actorList3D;
    }

    public void setCameraPosition(float x, float y, float z) {
        camera.position.set(x, y, z);
    }

    public void setCameraPosition(Vector3 v) {
        camera.position.set(v);
    }

    public void moveCamera(float x, float y, float z) {
        camera.position.add(x, y, z);
    }

    public void moveCamera(Vector3 v) {
        camera.position.add(v);
    }

    public void moveCameraForward(float dist) {
        Vector3 forward = new Vector3(camera.direction.x, 0, camera.direction.z).nor();
        moveCamera(forward.scl(dist));
    }

    public void moveCameraRight(float dist) {
        Vector3 right = new Vector3(camera.direction.z, 0, -camera.direction.x).nor();
        moveCamera(right.scl(dist));
    }

    public void moveCameraUp(float dist) {
        moveCamera(dist, 0, 0);
    }

    public void setCameraDirection(Vector3 v) {
        camera.lookAt(v);
        camera.up.set(0, 1, 0);
    }

    public void setCameraDirection(float x, float y, float z) {
        setCameraDirection(new Vector3(x, y, z));
    }

    public void turnCameraX(float angle) {
        camera.rotate(Vector3.X, -angle);
    }

    public float getCameraRotation() {
        float camAngle = -(float) Math.atan2(camera.up.x, camera.up.y) * MathUtils.radiansToDegrees + 180;
        return camAngle;
    }

    public void rollCamera(float angle) {
        camera.up.set(Vector3.X);
        camera.rotate(camera.direction, angle);
    }

    public void tiltCamera(float angle) {
        Vector3 side = new Vector3(0, -camera.up.y, -camera.up.z);
        camera.direction.rotate(side, angle);
    }

    private void setIntervalFlag(float dt) {
        if (intervalCounter > INTERVAL_COUNTER_FREQUENCY) {
            intervalFlag = true;
            intervalCounter = 0;
        } else {
            intervalFlag = false;
            intervalCounter += dt;
        }
    }

    private Array<BaseActor3D> hitActors = new Array<>();
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        hitActors.clear();
        Ray ray = camera.getPickRay(Gdx.input.getX(),Gdx.input.getY());
        //这个是root
        getRoot().checkCollisions(ray,hitActors);
        float max = Float.MAX_VALUE;
        Vector3 position = camera.position;
        BaseActor3D touch = null;
        for (BaseActor3D hitActor : hitActors) {
            float dst = hitActor.getPosition().dst(position);
            if (dst<max) {
                max = dst;
                touch = hitActor;
            }
        }
        if (touch!=null) {
            touch.notifyListener();
        }
        System.out.println(touch);
        return false;
    }

}