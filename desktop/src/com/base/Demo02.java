package com.base;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Demo02 extends ApplicationAdapter {
    private PerspectiveCamera camera;
    private ModelInstance instance;
    private ModelBatch modelBatch;
    private Environment environment;

    @Override
    public void create() {
        super.create();
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(10,10,10);
        camera.lookAt(0,0,0);
        camera.near = 1;
        camera.far = 300;


        ModelBuilder modelBuilder = new ModelBuilder();
        Model model = modelBuilder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.WHITE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);

        modelBatch = new ModelBatch();

        environment = new Environment();
//        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(1,0,0,new Vector3(0,-0.4f,-1)));
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();
        modelBatch.begin(camera);
        modelBatch.render(instance,environment);
        modelBatch.end();
    }


    @Override
    public void dispose() {
        modelBatch.dispose();
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
        new LwjglApplication(new Demo02(), config);
    }
}
