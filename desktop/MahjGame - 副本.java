package com.demo;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.UBJsonReader;

public class MahjGame extends ApplicationAdapter{

    PerspectiveCamera camera;
    Model model;
    ModelInstance instance;
    ModelBatch modelBatch;
    Environment environment;
    AssetManager assets;
    private ModelInstance tableModelInstance;
    CameraInputController cameraController;

    @Override
    public void create() {
        // TODO Auto-generated method stub
        super.create();

        camera = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(0f, 10f, 10f);
        camera.lookAt(0, 0, 0);
        camera.near = 1f;
        camera.far = 1000f;
        camera.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox( 5f, 5f, 5f, new Material( ColorAttribute.createDiffuse(Color.GREEN) ), Usage.Position | Usage.Normal );
        instance = new ModelInstance( model );
        modelBatch = new ModelBatch();

        environment = new Environment();
        environment.set( new ColorAttribute( ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f ) );
        environment.add( new DirectionalLight().set( 0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f ) );

        cameraController = new CameraInputController( camera );
        Gdx.input.setInputProcessor( cameraController );


        assets = new AssetManager();
        assets.setLoader(Model.class, ".g3db", new G3dModelLoader(new UBJsonReader(),assets.getFileHandleResolver()));
        assets.setLoader(Model.class, ".obj", new ObjLoader(assets.getFileHandleResolver()));
        assets.load("table.g3db", Model.class);
        assets.load("teapot.g3db", Model.class);
        assets.load("teacup.g3db", Model.class);
        assets.load("mahjong_tile.g3db", Model.class);
        assets.load("wood.png", Texture.class);
        assets.load("steam.png", Texture.class);
        assets.finishLoading();
        Model tableModel = assets.get("table.g3db", Model.class);
        tableModelInstance = new ModelInstance(tableModel);
        tableModelInstance.transform = new Matrix4()
                .scale(50.0f, 50.0f, 50.0f);


        Texture woodTexture = assets.get("wood.png", Texture.class);
        woodTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        woodTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        tableModelInstance.getMaterial("Table").set(
                TextureAttribute.createDiffuse(woodTexture)
        );

        tableModelInstance.getMaterial("Table").remove(ColorAttribute.Specular);

    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        super.render();
        Gdx.gl.glViewport( 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight() );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );

        cameraController.update();
        modelBatch.begin( camera );
        //modelBatch.render( instance );
        modelBatch.render( instance, environment );
        modelBatch.render(tableModelInstance,environment);
        modelBatch.end();

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        modelBatch.dispose();
        model.dispose();
        super.dispose();
    }

}