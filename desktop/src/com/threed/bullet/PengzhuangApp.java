package com.threed.bullet;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Octree;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.ContactListener;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCapsuleShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionObject;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btCollisionWorld;
import com.badlogic.gdx.physics.bullet.collision.btConeShape;
import com.badlogic.gdx.physics.bullet.collision.btCylinderShape;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btSphereShape;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.physics.bullet.linearmath.btTransform;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.*;
import com.badlogic.gdx.physics.bullet.*;

public class PengzhuangApp implements ApplicationListener {

    private btBroadphaseInterface broadphase;
    private btDefaultCollisionConfiguration collisionConfiguration;
    private btDispatcher dispatcher;
    private btSequentialImpulseConstraintSolver solver;
    private btDiscreteDynamicsWorld world;
    private DebugDrawer drawer;
    private PerspectiveCamera cam1;
    @Override
    public void create() {
        Bullet.init();

        // 设置输入处理器，监听鼠标点击
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                // 获取屏幕点击的坐标
                // 转换为世界坐标（如果需要）
                Vector3 worldCoordinates = cam1.unproject(new Vector3(screenX, screenY, 0));
//                System.out.println("World coordinates: " + worldCoordinates.x + ", " + worldCoordinates.y);

                // 通过射线与物体进行碰撞检测
                Ray ray = new Ray(cam1.position, worldCoordinates.sub(cam1.position).nor());
                performRayCast(ray);

                return true; // 返回 true，表示事件已处理
            }
        });

        cam1 = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam1.position.set(3f, 7f, 10f);
        cam1.lookAt(0, 0f, 0);
        cam1.near = 1f;
        cam1.far = 300f;
        cam1.update();


        this.drawer = new DebugDrawer();

        this.broadphase = new btDbvtBroadphase(); //AABB
        this.collisionConfiguration = new btDefaultCollisionConfiguration(); //更加细粒度
        this.dispatcher = new btCollisionDispatcher(collisionConfiguration);
        this.solver = new btSequentialImpulseConstraintSolver();
        this.world = new btDiscreteDynamicsWorld(dispatcher,broadphase,solver,collisionConfiguration);
        this.world.setGravity(new Vector3(0,-9.8f,0));


        world.setDebugDrawer(drawer);
        {
            // 创建刚体的碰撞形状
            btCollisionShape shape = new btBoxShape(new Vector3(1f, 1f, 1f)); // 创建一个边长为 2 的立方体
            // 设置刚体的质量
            Vector3 inertia = new Vector3(0f, 0f, 0f);  // 惯性张量为零
            float mass = 1f;  // 动态刚体的质量
            shape.calculateLocalInertia(mass, inertia);

            // 创建刚体
            btRigidBody.btRigidBodyConstructionInfo rbInfo = new btRigidBody.btRigidBodyConstructionInfo(mass, null, shape, inertia);
            btRigidBody rigidBody = new btRigidBody(rbInfo);


            // 将刚体添加到物理世界中
            world.addRigidBody(rigidBody);
        }

        {
            // 创建静态刚体的碰撞形状
            btCollisionShape shape = new btBoxShape(new Vector3(10f, 1f, 10f));  // 创建一个较大的立方体（地面）

            // 设置静态刚体的质量为 0
            float mass = 0f;  // 静态刚体的质量为 0
            Vector3 inertia = new Vector3(0f, 0f, 0f);  // 静态刚体的惯性张量为零

            // 创建静态刚体的构造信息
            btRigidBody.btRigidBodyConstructionInfo rbInfo = new btRigidBody.btRigidBodyConstructionInfo(mass, null, shape, inertia);

            // 设置静态刚体的新位置
            btTransform transform = new btTransform();
            transform.setIdentity();  // 重置为单位矩阵

            // 设置新的位置 (X, Y, Z)
            transform.setOrigin(new Vector3(0f, -5f, 0f));  // 例如，将静态刚体位置设置为 (0, -5, 0)

            rbInfo.setStartWorldTransform(transform);
            // 创建静态刚体（实际上就是创建一个不可移动的物体）
            btRigidBody staticRigidBody = new btRigidBody(rbInfo);


            // 将静态刚体添加到物理世界中
            world.addRigidBody(staticRigidBody);
        }
    }

    // 射线投射与碰撞检测
    private boolean performRayCast(Ray ray) {
        ClosestRayResultCallback callback = new ClosestRayResultCallback(
                ray.origin, ray.direction);
        System.out.println("changshi jiance ");
        world.rayTest(ray.origin, ray.origin.add(ray.direction.scl(1000f)), callback);

        if (callback.hasHit()) {
            System.out.println("点击到了 ====  "+callback.getCollisionObject());
            return true;
        }

        return false;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1.f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        world.stepSimulation(1f / 60f, 10);
        drawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_MAX_DEBUG_DRAW_MODE);
        drawer.begin(cam1);
        world.debugDrawWorld();
        drawer.end();
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


    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.x = 1000;
        config.stencil = 8;
        config.y = 0;
        config.height = (int) (1920 * 0.5f);
        config.width = (int) (1080 * 0.8f);
        new LwjglApplication(new PengzhuangApp(), config);
    }
}