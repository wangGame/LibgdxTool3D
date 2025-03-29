package com.demo.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.demo.kitchen.actor.Actor3D;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.stage.Stage3D;

public class Listener3D implements EventListener {
    private Camera camera;
    private Vector3 intersectionPoint = new Vector3();
    private ModelInstance closestModelInstance;

    private Listener3D(Camera camera){
        this.camera = camera;
    }

    @Override
    public boolean handle(Event event) {
        return false;
    }

    public void tes(){
        Stage3D stage3D = new Stage3D();
        Ray ray = camera.getPickRay(Gdx.input.getX(), Gdx.input.getY());
        // 找到与射线相交的最近模型
        closestModelInstance = null;
        float closestDistance = Float.MAX_VALUE;
        BaseActor3DGroup root = stage3D.getRoot();
        for (BaseActor3D modelInstance : root.getActor3DS()) {
            Matrix4 inverseTransform = modelInstance.getModelData().transform.cpy().inv();  // 计算变换矩阵的逆矩阵
            while (modelInstance.getParent3D()!=null) {

            }
            Ray transformedRay = new Ray(ray.origin.mul(inverseTransform), ray.direction.mul(inverseTransform));
            // 检测当前模型是否与射线相交
            if (checkCollision(transformedRay)) {
                // 计算相交点距离相机的距离
                float distance = intersectionPoint.dst(camera.position);
                if (distance < closestDistance) {
                    closestDistance = distance;
//                    closestModelInstance = modelInstance;
                }
            }
        }

        // 如果找到了最近的模型，打印信息
        if (closestModelInstance != null) {
            System.out.println("射线与物体相交，点击位置：" + intersectionPoint);
            System.out.println("最近模型位置：" + closestModelInstance.transform.getTranslation(new Vector3()));
        }
    }


    // 检测射线是否与物体相交
    private boolean checkCollision(Ray ray) {
        // 这里使用简单的 AABB 检测或更复杂的检测
        // 假设我们仍然使用一个简单的包围盒进行测试
        float minX = -1f, maxX = 1f;
        float minY = -1f, maxY = 1f;
        float minZ = -1f, maxZ = 1f;

        // 简单的包围盒检测，假设模型的包围盒位于模型的局部坐标系中
        if (ray.origin.x > minX && ray.origin.x < maxX &&
                ray.origin.y > minY && ray.origin.y < maxY &&
                ray.origin.z > minZ && ray.origin.z < maxZ) {

            intersectionPoint.set(ray.origin);
            return true;
        }
        return false;
    }
}
