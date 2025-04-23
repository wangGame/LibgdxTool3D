package com.threed.model;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.asset.Asset;


public class DecalTest extends ApplicationAdapter {
    private static final int NUM_DECALS = 3;
    DecalBatch batch;
    Array<Decal> decals = new Array<Decal>();
    PerspectiveCamera camera;
    private CameraInputController camController;

    Vector3 dir = new Vector3();
    private boolean billboard = true;
    @Override
    public void create() {
        super.create();

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.near = 1;
        camera.far = 300;
        camera.position.set(0, 10, 100);
        camera.lookAt(0,0,0);
        batch = new DecalBatch(new CameraGroupStrategy(camera));
        camController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(camController);
        TextureRegion[] textures = {
                new TextureRegion(Asset.getAsset().getTexture("assets/Screenshot.png")),
                new TextureRegion(Asset.getAsset().getTexture("assets/tile/mahjong_tile_2.png")),
                new TextureRegion(Asset.getAsset().getTexture("assets/tile/mahjong_tile_3.png"))
        };

        Decal decal = Decal.newDecal(1, 1, textures[1]);
        decal.setPosition(0, -100, 0);
        decals.add(decal);
        decal.setColor(Color.RED);

//
//        viewDir.set(cam.direction).scl(-1);    // direction that decals should be facing: opposite of camera view vector
//        // (don't point the decals at the (perspective) camera because then the rectangles get skewed with horrible jaggies)
//
//
//        decal.setPosition(new Vector3(-20,0,0));
//        decal.setRotation(viewDir,Vector3.Y);
//        decalBatch.add(decal);
//        decalBatch.flush();





    }

    private Vector3 viewDir;
    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        camera.update();
        camController.update();
        for (int i = 0; i < decals.size; i++) {
            Decal decal = decals.get(i);
            decal.lookAt(camera.position, camera.up);
            batch.add(decal);
        }
        batch.flush();

    }


    @Override
    public void dispose() {

    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
        new LwjglApplication(new DecalTest(), config);
    }
}
