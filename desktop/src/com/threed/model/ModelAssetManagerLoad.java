package com.threed.model;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;


/**
 *  AssetManager 来管理模型
 */
public class ModelAssetManagerLoad extends ApplicationAdapter {
    private PerspectiveCamera camera;

    private ModelBatch modelBatch;
    private Environment environment;
    private CameraInputController camController;
    private Array<ModelInstance> modelInstances;

    @Override
    public void create() {
        super.create();
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(21,21,21);
        camera.lookAt(0,0,0);
        camera.near = 1;
        camera.far = 300;


        /*ObjLoader loader = new ObjLoader();
        Model model = loader.loadModel(Gdx.files.internal("assets/model/loadmodels/data/ship.obj"));
        */

        AssetManager assets = new AssetManager();
        assets.setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(), assets.getFileHandleResolver()));
        assets.setLoader(Model.class, ".obj", new ObjLoader(assets.getFileHandleResolver()));
        assets.load("assets/model/loadmodels/data/ship.obj", Model.class);
        assets.finishLoading();

        modelInstances = new Array<>();
        for (int i = 0; i < 10; i++) {
            Model model = assets.get("assets/model/loadmodels/data/ship.obj");
            ModelInstance instance = new ModelInstance(model);
            modelInstances.add(instance);
            instance.transform.setToTranslation(i,0,0);
        }
        modelBatch = new ModelBatch();

        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(1,0,0,new Vector3(0,-0.4f,-1)));

        camController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(camController);
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();
        camController.update();
        modelBatch.begin(camera);
        modelBatch.render(modelInstances,environment);
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
        new LwjglApplication(new ModelAssetManagerLoad(), config);
    }
}
