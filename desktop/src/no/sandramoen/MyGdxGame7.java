package no.sandramoen;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.UBJsonReader;
import com.kw.gdx.d3.asset.Asset3D;

public class MyGdxGame7 extends ApplicationAdapter {
    private ModelInstance table;
    public Environment environment;//可以包含点光源集合和线光源集合
    public PerspectiveCamera cam;//3D视角
    public CameraInputController camController;//视角控制器
    public ModelBatch modelBatch;
    public boolean loading;
    private ModelInstance shipInstance;
    Vector3 position = new Vector3();

    @Override
    public void create () {
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.5f, 0.5f, 0.5f, 1f));//环境光
        environment.add(new DirectionalLight().set(0.5f, 0.5f, 0.5f, 0f, 0, -1));

        modelBatch = new ModelBatch();
        cam = new PerspectiveCamera( 67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//67可以理解成一个定值，视角宽度（67度）
        cam.position.set(0f, 10f, 10);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 1300f;
        cam.update();

        camController = new CameraInputController(cam);
//        Gdx.input.setInputProcessor(camController);

        loading = true;
        doneLoading();
    }


    private void doneLoading() {
        Model t = Asset3D.getAsset3D().getModel("tile/table.g3db");
        table = new ModelInstance(t);
        table.transform.setTranslation(new Vector3(0,-10,0));

        Model modelUp = new ObjLoader().loadModel(Gdx.files.internal("model/Cube_0.obj"));
        shipInstance = new ModelInstance(modelUp);
        // 遍历并为所有Node的NodePart应用材质
        loading = false;
        shipInstance.nodes.get(0).scale.set(new Vector3(30,60,10));
        shipInstance.nodes.get(1).scale.set(new Vector3(30,60,10));
        shipInstance.calculateTransforms();

    }

    @Override
    public void render () {
        super.render();
        camController.update();
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        move();
        rotation();
//        shipInstance.transform.rotate(new Vector3(1,0,0), (float) Math.toRadians(30));

        modelBatch.begin(cam);
        modelBatch.render(table,environment);
        modelBatch.render(shipInstance,environment);
        modelBatch.end();
    }

    private float xxx = 0;
    public void rotation(){
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1))
            shipInstance.transform.rotate(Vector3.X,
                    Gdx.graphics.getDeltaTime() * 100);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2))
            shipInstance.transform.rotate(Vector3.Y,
                    Gdx.graphics.getDeltaTime() * 100);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3))
            shipInstance.transform.rotate(Vector3.Z,
                    Gdx.graphics.getDeltaTime() * 100);
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_4))
            shipInstance.transform.setFromEulerAngles(0,
                    0,xxx += Gdx.graphics.getDeltaTime() * 100);
    }

    public void move(){
        shipInstance.transform.getTranslation(position);
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            position.x+=Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            position.z+=Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            position.z-=Gdx.graphics.getDeltaTime();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            position.x-=Gdx.graphics.getDeltaTime();
        }
        shipInstance.transform.setTranslation(position);
    }

    @Override
    public void dispose() {
        modelBatch.dispose();

        super.dispose();
    }

    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil=8;
        config.y = 0;
        config.height = (int) (1920*0.5f);
        config.width = (int) (1080*0.5f);
//        Gdx.isJiami = true;
        new LwjglApplication(new MyGdxGame7(), config);
//        int index = 0;
//        for (int i = 0; i <= 6; i++) {
//            for (int i1 = i; i1 <= 6; i1++) {
//                System.out.println(i+ "  " +i1);
//                index++;
//            }
//        }
//        System.out.println(index);
    }
}