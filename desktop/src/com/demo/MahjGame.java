package com.demo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.FloatAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;
import com.kw.gdx.d3.actor.GameObject;
import com.kw.gdx.d3.utils.Box;
import com.sun.org.apache.xpath.internal.operations.Mod;

public class MahjGame extends Game {
    private AssetManager assets;
    private ModelInstance tableModelInstance;
    private ModelBatch modelBatch;
    private Environment tableEnvironment;
    private PerspectiveCamera perspectiveCamera;
    private RotationalCameraInputController rotationalCameraInputController;
    private ModelInstance teaInstance;
    private ModelInstance teacupModelInstance;
    private ModelInstance mahjongTile;
    @Override
    public void create() {
        assets = new AssetManager();
        assets.setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(),assets.getFileHandleResolver()));
        assets.setLoader(Model.class, ".obj", new ObjLoader(assets.getFileHandleResolver()));
        assets.load("table.g3db", Model.class);
        assets.load("teapot.g3db", Model.class);
        assets.load("teacup.g3db", Model.class);
        assets.load("mahjong_tile.g3db", Model.class);
        assets.load("wood.png", Texture.class);
        assets.finishLoading();



        perspectiveCamera = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        perspectiveCamera.position.set(0f, 10f, 10f);
        perspectiveCamera.lookAt(0, 0, 0);
        perspectiveCamera.near = 1f;
        perspectiveCamera.far = 1000f;
        perspectiveCamera.update();


        rotationalCameraInputController = new RotationalCameraInputController(perspectiveCamera, 1.0f,
                1.0f, 1.0f, 1.0f, 4.0f);
        rotationalCameraInputController.setZoom(3.0f);
        rotationalCameraInputController.getRotation().x = -30.0f;


        Gdx.input.setInputProcessor(new InputMultiplexer(rotationalCameraInputController, new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                Graphics g = Gdx.graphics;
                if (keycode == Input.Keys.F) {
                    if (g.isFullscreen()) {
                        g.setWindowedMode(800, 600);
                    } else {
                        g.setFullscreenMode(g.getDisplayMode(g.getPrimaryMonitor()));
                    }
                } else if (keycode == Input.Keys.ESCAPE) {
                    Gdx.app.exit();
                } else if (keycode == Input.Keys.LEFT) {

                } else if (keycode == Input.Keys.RIGHT) {

                } else {
                    return false;
                }
                return true;
            }
        }));

        this.modelBatch = new ModelBatch();

        Model tableModel = assets.get("table.g3db", Model.class);
        tableModelInstance = new ModelInstance(tableModel);
        tableModelInstance.transform = new Matrix4()
                .scale(50.0f, 500.0f, 50.0f);

        Texture woodTexture = assets.get("wood.png", Texture.class);
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        tableModelInstance.getMaterial("Table").set(
                TextureAttribute.createDiffuse(woodTexture)
        );
        tableEnvironment = new Environment();
//        tableEnvironment.add(new PointLight().set(1.0f, 0.96f, 0.83f, 0.0f, 25.0f, 0.0f, 1000.0f));
//        tableEnvironment.add(new DirectionalLight().set(1.0f, 0.96f, 0.83f, 0.0f, -1, 0));
        SpotLight spotLight = new SpotLight();
        spotLight.setPosition(0.0f, -1, 0);
        spotLight.setDirection(0f, 0f, -0f); // 光源向下
        spotLight.setColor(1f, 1f, 1f, 1f); // 白色光
        spotLight.setCutoffAngle(60);
        spotLight.setIntensity(100);
        spotLight.setExponent(100);
//        spotLight.setExponent(30f); // 强度衰减
        tableEnvironment.add(spotLight);
        tableModelInstance.getMaterial("Table").remove(ColorAttribute.Specular);


        Model model = assets.get("teapot.g3db", Model.class);
        teaInstance = new ModelInstance(model);

        teaInstance.transform = new Matrix4()
                .translate(20.0f, 0.0f, -8.0f)
                .rotate(0.0f, 1.0f, 0.0f, -45.0f)
                .scale(4.0f, 4.0f, 4.0f);
        teaInstance.getMaterial("Teapot").set(
                ColorAttribute.createDiffuse(0.39f, 0.09f, 0.07f, 1.0f),
                ColorAttribute.createSpecular(1.0f, 1.0f, 1.0f, 1.0f),
                FloatAttribute.createShininess(100.0f)
        );

        Model teacupModel = assets.get("teacup.g3db", Model.class);
        teacupModelInstance = new ModelInstance(teacupModel);
        Matrix4 teacupTransform = new Matrix4()
                .translate(-18.0f, 0.0f, 3.0f)
                .rotate(0.0f, 1.0f, 0.0f, -45.0f);
        teacupModelInstance.transform = new Matrix4(teacupTransform)
                .scale(4.0f, 4.0f, 4.0f);
        teacupModelInstance.getMaterial("Teacup").set(
                ColorAttribute.createDiffuse(0.39f, 0.09f, 0.07f, 1.0f),
                ColorAttribute.createSpecular(1.0f, 1.0f, 1.0f, 1.0f),
                FloatAttribute.createShininess(100.0f)
        );
        teacupModelInstance.getMaterial("Tea").set(
                ColorAttribute.createDiffuse(0.32f, 0.3f, 0.14f, 0.7f),
                new BlendingAttribute()
        );

        Model tileModel = assets.get("mahjong_tile.g3db", Model.class);
        mahjongTile = new ModelInstance(tileModel);
    }

    @Override
    public void render() {
        rotationalCameraInputController.update();
        Gdx.gl.glViewport( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        modelBatch.begin(perspectiveCamera);
        modelBatch.render(teacupModelInstance,tableEnvironment);
        modelBatch.render(teaInstance,tableEnvironment);
        modelBatch.render(tableModelInstance,tableEnvironment);
        modelBatch.render(mahjongTile,tableEnvironment);
        modelBatch.end();
    }
}
