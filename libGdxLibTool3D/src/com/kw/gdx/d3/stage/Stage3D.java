package com.kw.gdx.d3.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ShaderProvider;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.stage.light.LightManager;

import java.util.ArrayList;

public class Stage3D {
    public boolean intervalFlag;
    public int visibleCount = 0;
    public Environment environment;
    public PerspectiveCamera camera;
    public LightManager lightManager;
    private ArrayList<Actor> actorList;
    private ArrayList<BaseActor3D> actorList3D;
    private final ModelBatch modelBatch;
    private float intervalCounter;
    private final float INTERVAL_COUNTER_FREQUENCY = 1;

    public Stage3D() {
        environment = new Environment();
        lightManager = new LightManager(environment);

        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.rotate(-90, 0, 0, 1);
        camera.lookAt(0, 0, 0);
        camera.near = .01f;
        camera.far = 145;
        camera.update();

        DefaultShader.Config config = new DefaultShader.Config();
        config.numDirectionalLights = 1;
        config.numPointLights = 50;
        config.numSpotLights = 0;

        ShaderProvider shaderProvider = new DefaultShaderProvider(config);
        modelBatch = new ModelBatch(shaderProvider);

        actorList3D = new ArrayList();
        actorList = new ArrayList();
    }

    public void act(float dt) {
        camera.update();
        lightManager.update(dt);
        for (BaseActor3D ba : actorList3D)
            ba.act(dt);
        setIntervalFlag(dt);
    }

    public void draw() {
        modelBatch.begin(camera);
        visibleCount = 0;
        for (int i = 0; i < actorList3D.size(); i++) {
            if (actorList3D.get(i).modelData.isVisible(camera) && actorList3D.get(i).isVisible) {
                actorList3D.get(i).draw(modelBatch, environment);
                visibleCount++;
            }
        }
        modelBatch.end();
    }

    public void dispose() {
        modelBatch.dispose();
    }

    public void addActor(BaseActor3D ba) {
        actorList3D.add(ba);
    }

    public void addActor(Actor a) {
        actorList.add(a);
    }

    public void removeActor(BaseActor3D ba) {
        actorList3D.remove(ba);
    }

    public void removeActor(Actor a) {
        actorList.remove(a);
    }

    public ArrayList<BaseActor3D> getActors3D() {
        return actorList3D;
    }

    public ArrayList<Actor> getActors() {
        return actorList;
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

}
