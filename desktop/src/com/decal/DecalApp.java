package com.decal;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.decals.SimpleOrthoGroupStrategy;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.kitchen.KitchenApp;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.constant.Constant;
import com.libGdx.test.A;
import com.libGdx.test.base.LibGdxTestMain;

public class DecalApp extends ApplicationAdapter {
    private static final int NUM_DECALS = 3;
    DecalBatch batch;
    Array<Decal> decals = new Array<Decal>();
    PerspectiveCamera camera;
//    PerspectiveCamController controller;
//    FPSLogger logger = new FPSLogger();

    public void create () {


        camera = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.near = 1;
        camera.far = 300;
        camera.position.set(0, 0, 5);
        camera.lookAt(0,0,0);
//        controller = new PerspectiveCamController(camera);

//        Gdx.input.setInputProcessor(controller);
        batch = new DecalBatch(new CameraGroupStrategy(camera));

        TextureRegion[] textures = {new TextureRegion(new Texture(Gdx.files.internal("kitchen/img.png"))),
                new TextureRegion(new Texture(Gdx.files.internal("grandpa/arrowhead.png"))),
                new TextureRegion(new Texture(Gdx.files.internal("grandpa/arrowhead.png")))};

        Decal decal = Decal.newDecal(100, 100, textures[1]);
        decal.setPosition(0, 0, 10);

        decals.add(decal);

        decal = Decal.newDecal(1, 1, textures[0], true);
        decal.setPosition(0.5f, 0.5f, 1);
        decals.add(decal);

        decal = Decal.newDecal(1, 1, textures[0], true);
        decal.setPosition(1, 1, -1);
        decals.add(decal);

        decal = Decal.newDecal(1, 1, textures[2]);
        decal.setPosition(1.5f, 1.5f, -2);
        decals.add(decal);

        decal = Decal.newDecal(1, 1, textures[1]);
        decal.setPosition(2, 2, -1.5f);
        decals.add(decal);
    }

    Vector3 dir = new Vector3();
    private boolean billboard = true;

    public void render () {
//        ScreenUtils.clear(Color.DARK_GRAY, true);
        Gdx.gl.glClearColor(Constant.viewColor.r,Constant.viewColor.g,Constant.viewColor.b,Constant.viewColor.a);
        Gdx.gl.glClear(
                GL20.GL_COLOR_BUFFER_BIT
                        | GL20.GL_DEPTH_BUFFER_BIT
                        | GL20.GL_STENCIL_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_DEPTH_TEST);

        camera.update();
        for (int i = 0; i < decals.size; i++) {
            Decal decal = decals.get(i);
            if (billboard) {
                decal.lookAt(camera.position, camera.up);
            }
            batch.add(decal);
        }
        batch.flush();
    }

    @Override
    public void dispose () {
        batch.dispose();
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil = 8;
        config.y = 0;
        config.height = (int) ( 1080* 0.5f);
        config.width = (int) (1080 * 0.5f);
        new LwjglApplication(new DecalApp(), config);
    }
}
