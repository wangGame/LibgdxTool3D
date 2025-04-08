package com;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.asset.Asset3D;
import com.libGdx.test.model.MyGdxGame2;

public class Asss extends ApplicationAdapter {
    public Environment environment;//可以包含点光源集合和线光源集合
    public OrthographicCamera cam;//3D视角
    public CameraInputController camController;//视角控制器
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    public ModelBatch modelBatch;
    public boolean loading;
    private ModelInstance shipInstance;
    private AssetManager assets;
    private ModelInstance tableModelInstance;

    @Override
    public void create() {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));//环境光
        PointLight set = new PointLight();
        set.set(1.0f, 1.f, 1.f, .0f, .0f, 30, 1000.0f);
//        environment.add(set);
        environment.add(new DirectionalLight().set(0.5f, 0.5f, 0.5f, 0f, 0, -1));

        modelBatch = new ModelBatch();
        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//67可以理解成一个定值，视角宽度（67度）
        cam.position.set(0f, 0f, 180f);
        cam.lookAt(0, 0, 0);
        cam.near = 1f;
        cam.far = 1300f;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);

        loading = true;
        doneLoading();


        Model model = Asset3D.getAsset3D().getModel("kitchen/model/Counter_hole.g3db");
        tableModelInstance = new ModelInstance(model);

    }


    private void doneLoading() {



    }

    @Override
    public void render() {
        super.render();
        camController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

//        shipInstance.transform.rotate(new Vector3(1,0,0), (float) Math.toRadians(30));

        modelBatch.begin(cam);

        modelBatch.render(tableModelInstance, environment);
        modelBatch.end();
    }

    @Override
    public void dispose() {
        modelBatch.dispose();
        instances.clear();
        super.dispose();
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920 * 0.25f);
        config.width = (int) (1080 * 0.5f);
//        Gdx.isJiami = true;
        new LwjglApplication(new Asss(), config);
    }
}