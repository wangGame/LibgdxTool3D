package com.tile;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.TimeUtils;
import com.kw.gdx.BaseGame;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.action.Action3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.actor.ModelActor3D;
import com.kw.gdx.d3.asset.Asset3D;
import com.kw.gdx.d3.screen.BaseScreen3D;

public class LoadingScreen extends BaseScreen3D {
    public LoadingScreen(BaseGame game) {
        super(game);
    }

    private Vector3 _prevPosition = new Vector3();
    private boolean playerTouchDown = false;

    private ModelActor3D actor3D;
    private Quaternion currentRotation = new Quaternion();  // 当前旋转
    private float autoRotateSpeed = 20f;

    @Override
    public void initView() {
        super.initView();

        Model model = Asset3D.getAsset3D().getModel("model/Cube.obj");

        BaseActor3DGroup actor3DGroup = new BaseActor3DGroup();
        stage3D.addActor(actor3DGroup);

        actor3D = new ModelActor3D(model);
        actor3D.setScale(4000, 8000, 1000);
        actor3D.setMaterialTexture(Asset.getAsset().getTexture("model/space.jpg"));

        actor3DGroup.addActor3D(actor3D);

        actor3D.setRotation(currentRotation);

        // 自转逻辑（绕 Y 轴）
        stage.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if (inertiaSpeed > 0.1f) {

                    // 惯性旋转中
                    Quaternion q = new Quaternion(inertiaAxis, inertiaSpeed * delta);
                    currentRotation.mulLeft(q);
                    inertiaSpeed *= 0.99f; // 阻尼减速
                }else if (!playerTouchDown) {
                    autoRotateSpeed = Math.min(100,autoRotateSpeed);
                    Quaternion rotateY = new Quaternion(Vector3.Y, autoRotateSpeed * delta);
                    currentRotation.mulLeft(rotateY);
                }
                actor3D.setRotation(currentRotation);
                return false;
            }
        });

        // 拖动监听器
        Actor dragArea = new Actor();
        dragArea.setSize(9999, 9999); // 全屏都可以控制旋转
        addActor(dragArea);

        dragArea.addListener(new ClickListener() {
            long lastTime;
            float lastDx, lastDy;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                _prevPosition.set(x, y, 0);
                playerTouchDown = true;
                return true;
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                float dx = x - _prevPosition.x;
                float dy = y - _prevPosition.y;


                dx = MathUtils.clamp(dx, -100, 100);
                dy = MathUtils.clamp(dy, -100, 100);

                float sensitivity = 0.5f;

                // 创建基于拖动的旋转增量
                Quaternion deltaRotX = new Quaternion(Vector3.X, -dy * sensitivity);
                Quaternion deltaRotY = new Quaternion(Vector3.Y, dx * sensitivity);

                // 顺序重要！Y 轴先应用
                currentRotation.mulLeft(deltaRotY);
                currentRotation.mulLeft(deltaRotX);

                actor3D.setRotation(currentRotation);

                _prevPosition.set(x, y, 0);

                lastDx = dx;
                lastDy = dy;
                lastTime = TimeUtils.millis();
            }

            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                playerTouchDown = false;

                float dt = 0.016667f;
                float speed = (float) Math.sqrt(lastDx * lastDx + lastDy * lastDy) / dt;

                // 设置惯性旋转轴和速度
                inertiaAxis.set(-lastDy, lastDx, 0).nor();
                inertiaSpeed = speed * dragSensitivity * 0.1f;
            }

            @Override
            public void cancel() {
                playerTouchDown = false;
            }
        });
    }
    private float inertiaSpeed = 0f;
    private float dragSensitivity = 0.5f;
    private Vector3 inertiaAxis = new Vector3();
}