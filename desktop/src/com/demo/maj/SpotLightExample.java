package com.demo.maj;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.graphics.g3d.environment.SpotLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;

public class SpotLightExample extends ApplicationAdapter {
    private PerspectiveCamera camera;
    private ModelBatch modelBatch;
    private Environment environment;
    private ModelInstance sphereInstance;
    private ModelInstance floorInstance;

    @Override
    public void create() {
        // 初始化模型批处理器
        modelBatch = new ModelBatch();

        // 创建一个透视相机
        camera = new PerspectiveCamera(67, 800, 600);
        camera.position.set(10f, 10f, 10f); // 设置相机位置
        camera.lookAt(0, 0, 0); // 设置相机的观察目标
        camera.near = 1f;
        camera.far = 300f;
        camera.update();

        // 创建一个光源环境
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.Diffuse, 1f, 1f, 1f, 1f)); // 默认白色环境光

        // 创建一个Spot光源
        SpotLight spotLight = new SpotLight();
        spotLight.color.set(1.0f, 1.0f, 1.0f, 1.0f); // 设置白色光源
        spotLight.position.set(0f, 5f, 0f); // 设置光源位置
        spotLight.direction.set(0f, -1f, 0f); // 设置光照射向下
        spotLight.setCutoffAngle(MathUtils.PI / 4); // 设置聚光灯角度为45度
        spotLight.intensity = 1.0f; // 设置光源强度
//        spotLight.sed = 20f; // 设置光源照射范围

        // 将光源添加到环境中
        environment.add(spotLight);

        // 创建一个简单的球体模型和一个平面模型
        ModelBuilder modelBuilder = new ModelBuilder();
        Model sphereModel = modelBuilder.createSphere(2f, 2f, 2f, 20,
                20, new Material(ColorAttribute.createDiffuse(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        Model boxModel = modelBuilder.createBox(1, 1, 1, new Material(ColorAttribute.createDiffuse(Color.GRAY))
                , VertexAttributes.Usage.Position |
                        VertexAttributes.Usage.Normal);


        // 创建模型实例
        sphereInstance = new ModelInstance(sphereModel);
        floorInstance = new ModelInstance(boxModel);
    }

    @Override
    public void render() {
        // 清屏
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // 开始渲染批次
        modelBatch.begin(camera);

        // 渲染场景中的物体
        modelBatch.render(floorInstance, environment); // 渲染地面
        modelBatch.render(sphereInstance, environment); // 渲染球体

        // 结束渲染
        modelBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
    }

    @Override
    public void dispose() {
        // 释放资源
        modelBatch.dispose();
        sphereInstance.model.dispose();
        floorInstance.model.dispose();
    }
}