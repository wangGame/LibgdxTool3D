package com.decal;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector3;

public class TestDecal extends ScreenAdapter {


    private int viewHeight, viewWidth;


    private DecalBatch decalBatch;
    private Camera cam;
    private Vector3 viewDir = new Vector3();
    private Decal decal;

    @Override
    public void show() {
        cam = new PerspectiveCamera(50, viewWidth, viewHeight);
        cam.position.set(30f, 10f, 10f);
        cam.lookAt(0, 0, 0);
        cam.near = 2f;
        cam.far = 10000;
        cam.update();

        decal = Decal.newDecal(new TextureRegion(new Texture("assets/com_btn_green1.png")));
        decalBatch = new DecalBatch(new CameraGroupStrategy(cam));


        decal.setScale(0.1f);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        int mask = GL20.GL_COLOR_BUFFER_BIT;
        mask = mask | GL20.GL_DEPTH_BUFFER_BIT;
        Gdx.gl.glClear(mask);




        viewDir.set(cam.direction).scl(-1);    // direction that decals should be facing: opposite of camera view vector
        // (don't point the decals at the (perspective) camera because then the rectangles get skewed with horrible jaggies)
        decal.setPosition(new Vector3(-20,10,0));
        decal.setRotation(viewDir,Vector3.Y);
        decalBatch.add(decal);
        decalBatch.flush();
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug("GameScreen", "resize("+width+", "+height+")");
        // adjust aspect ratio after a windows resize
        viewWidth = width;
        viewHeight = height;
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.update();
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide");
        dispose();
    }





}
