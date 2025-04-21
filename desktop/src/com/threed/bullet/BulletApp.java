package com.threed.bullet;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.btBoxShape;
import com.badlogic.gdx.physics.bullet.collision.btBroadphaseInterface;
import com.badlogic.gdx.physics.bullet.collision.btCollisionDispatcher;
import com.badlogic.gdx.physics.bullet.collision.btCollisionShape;
import com.badlogic.gdx.physics.bullet.collision.btConvexHullShape;
import com.badlogic.gdx.physics.bullet.collision.btDbvtBroadphase;
import com.badlogic.gdx.physics.bullet.collision.btDefaultCollisionConfiguration;
import com.badlogic.gdx.physics.bullet.collision.btDispatcher;
import com.badlogic.gdx.physics.bullet.dynamics.btDiscreteDynamicsWorld;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import com.badlogic.gdx.physics.bullet.dynamics.btSequentialImpulseConstraintSolver;
import com.badlogic.gdx.physics.bullet.linearmath.btDefaultMotionState;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.physics.bullet.linearmath.btTransform;

public class BulletApp implements ApplicationListener {
    private btBroadphaseInterface broadphase;
    private btDefaultCollisionConfiguration collisionConfiguration;
    private btDispatcher dispatcher;
    private btSequentialImpulseConstraintSolver solver;
    private btDiscreteDynamicsWorld world;
    private DebugDrawer drawer;
    private PerspectiveCamera cam1;

    private btRigidBody body;
    @Override
    public void create() {

        Bullet.init();

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
        new LwjglApplication(new BulletApp(), config);
    }
}
