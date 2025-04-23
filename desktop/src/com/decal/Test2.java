package com.decal;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.kw.gdx.asset.Asset;
import com.threed.model.DecalTest;

public class Test2 implements ApplicationListener {

    public static final float Y_OFFSET = 1.5f;          // height above the unit
    public static final int NUM_STEPS = 32;             // granularity of health bar
    private Camera camera;

    private DecalBatch decalBatch;

    private final Vector3 viewDir = new Vector3();
    private final Vector3 position = new Vector3();
    private TextureRegion[] textures;           // different texture regions for different levels of health
    private Decal healthBarDecal;

    public Test2() {

    }

    private TextureRegion[] prepareTextures(int steps) {
        TextureRegion[] regions = new TextureRegion[steps];
        for(int h = 0; h < steps; h++) {
            // select colour to match health level
            Color col  = Color.GREEN;
            if(h < 0.25f*steps)
                col = Color.RED;
            else if(h < 0.5f * steps)
                col = Color.ORANGE;
            Texture tex = makeBarTexture(32, 5, h/(float)(steps-1), col);
            regions[h] = new TextureRegion(tex);
        }
        return regions;
    }





    @Override
    public void create() {

        camera = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.near = 1;
        camera.far = 700;
        camera.position.set(0, 10, 200);
        camera.lookAt(0,0,0);



        decalBatch = new DecalBatch(new CameraGroupStrategy(camera));
        textures = prepareTextures(NUM_STEPS);

        healthBarDecal = Decal.newDecal(new TextureRegion(
                Asset.getAsset().getTexture("shuoming.png")));
        healthBarDecal.setDimensions(2f, 0.4f);
        viewDir.set(camera.direction).scl(-1);    // direction that decals should be facing: opposite of camera view vector
        // (don't point the decals at the (perspective) camera because then the rectangles get skewed with horrible jaggies)


        healthBarDecal.setPosition(0,0,0);
        healthBarDecal.setColor(Color.WHITE);


    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {

        healthBarDecal.setPosition(position);
//            go.healthBarDecal.setRotation(viewDir, Vector3.Y);
        decalBatch.add(healthBarDecal);

        decalBatch.flush();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private Texture makeBarTexture(int width, int height, float health, Color color) {
        Pixmap pixmap = new Pixmap(width+4, height+2, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        pixmap.setColor(Color.GRAY);
        pixmap.fillRectangle(2,1, width, height);
        pixmap.setColor(color);
        int w = MathUtils.round((float)width*health);
        if(w > 0)
            pixmap.fillRectangle(2,1, w, height);
        return new Texture(pixmap);
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        //config.setOpenGLEmulation(Lwjgl3ApplicationConfiguration.GLEmulation.GL30, 3, 3);
        new LwjglApplication(new Test2(), config);
    }

}
