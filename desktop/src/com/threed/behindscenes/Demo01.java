package com.threed.behindscenes;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
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
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;


public class Demo01 extends ApplicationAdapter {
    public PerspectiveCamera cam;
    public CameraInputController camController;
    public ModelBatch modelBatch;
    public AssetManager assets;
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    public Environment environment;
    public boolean loading;

    public Array<ModelInstance> blocks = new Array<ModelInstance>();
    public Array<ModelInstance> invaders = new Array<ModelInstance>();
    public ModelInstance ship;
    public ModelInstance space;

    @Override
    public void create () {
        modelBatch = new ModelBatch();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 7f, 10f);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        assets = new AssetManager();
        assets.setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(), assets.getFileHandleResolver()));
        assets.setLoader(Model.class, ".obj", new ObjLoader(assets.getFileHandleResolver()));
        assets.load("assets/model/data/invaderscene.g3db", Model.class);
        loading = true;
    }

    private void doneLoading() {
        Model model = assets.get("assets/model/data/invaderscene.g3db", Model.class);
//        for (int i = 0; i < model.nodes.size; i++) {
//            String id = model.nodes.get(i).id;
//            ModelInstance instance = new ModelInstance(model, id);
//            Node node = instance.getNode(id);
//
//            instance.transform.set(node.globalTransform);
//            node.translation.set(0,0,0);
//            node.scale.set(1,1,1);
//            node.rotation.idt();
//            instance.calculateTransforms();
//
//            if (id.equals("space")) {
//                space = instance;
//                continue;
//            }
//
//            instances.add(instance);
//
//            if (id.equals("ship"))
//                ship = instance;
//            else if (id.startsWith("block"))
//                blocks.add(instance);
//            else if (id.startsWith("invader"))
//                invaders.add(instance);
//        }
        instances.add(new ModelInstance(model));
        loading = false;
    }

    @Override
    public void render () {
        if (loading && assets.update())
            doneLoading();
        camController.update();

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        modelBatch.begin(cam);
        modelBatch.render(instances, environment);
        if (space != null)
            modelBatch.render(space);
        modelBatch.end();
    }

    @Override
    public void dispose () {
        modelBatch.dispose();
        instances.clear();
        assets.dispose();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
        new LwjglApplication(new Demo01(), config);
    }
}
