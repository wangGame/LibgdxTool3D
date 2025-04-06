package com.kw.gdx.d3.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.Attribute;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.d3.RayBean;
import com.kw.gdx.d3.action.Action3D;
import com.kw.gdx.d3.listener.Listener3D;
import com.kw.gdx.d3.stage.Stage3D;
import com.kw.gdx.d3.utils.Box;

/**
 * model Actor
 */
public class BaseActor3D {
    protected BaseActor3DGroup parent3D;
    protected boolean isCollisionEnabled = true;
    protected boolean isPreventOverlapEnabled = true;
    protected GameObject modelData;
    private final Vector3 position;
    protected float width;
    protected float height;
    protected float depth;
    protected float radius;
    protected Stage3D stage3D;
    protected BoundingBox bounds = new BoundingBox();
    private Quaternion rotation;
    private Vector3 scale;
    private final Array<Action3D> actions = new Array(0);
    private Matrix4 matrix4 = new Matrix4();
    private Array<Listener3D> listener3DS;
    protected Vector3 center = new Vector3();
    public BaseActor3D(){
        this(0,0,0);
    }

    public BaseActor3D(float x, float y, float z) {
        this.modelData = null;
        this.position = new Vector3(x, y, z);
        this.rotation = new Quaternion();
        this.scale = new Vector3(1, 1, 1);
        this.listener3DS = new Array<>();
    }

    public void setModelInstance(GameObject m) {
        modelData = m;
    }

    /**
     * 计算当前的mat
     * @return
     */
    public Matrix4 calculateTransform() {
        matrix4.idt();
        Matrix4 rotate = matrix4.rotate(rotation);
        Matrix4 matrix4 = rotate.trn(position.x, position.y,
                position.z).scale(scale.x, scale.y, scale.z);
        return matrix4;
    }

    //更新位置
    public void act(float delta) {
        Array<Action3D> actions = this.actions;
        if (actions.size == 0) return;
        if (stage3D != null) Gdx.graphics.requestRendering();
        try {
            for (int i = 0; i < actions.size; i++) {
                Action3D action = actions.get(i);
                if (action.act(delta) && i < actions.size) {
                    Action3D current = actions.get(i);
                    int actionIndex = current == action ? i : actions.indexOf(action, true);
                    if (actionIndex != -1) {
                        actions.removeIndex(actionIndex);
                        action.setActor3D(null);
                        i--;
                    }
                }
            }
        } catch (RuntimeException ex) {
            String context = toString();
            throw new RuntimeException("Actor: " + context.substring(0, Math.min(context.length(), 128)), ex);
        }
    }

    public GameObject getModelData() {
        return modelData;
    }

    public void drawShadow(ModelBatch batch,Environment environment){
        if (modelData!=null) {
            if (!isCaremaClip())return;
            Matrix4 matrix4 = calculateTransform();
            if (parent3D!=null){
                Matrix4 pM = parent3D.computeTransform();
                pM.mul(matrix4);
                modelData.transform.set(pM);
            }else {
                modelData.transform.set(matrix4);
            }
            batch.render(modelData);
        }
    }

    public void draw(ModelBatch batch, Environment env) {
        if (modelData!=null) {
            if (!isCaremaClip())return;
            Matrix4 matrix4 = calculateTransform();
            if (parent3D!=null){
                Matrix4 pM = parent3D.computeTransform();
                pM.mul(matrix4);
                modelData.transform.set(pM);
            }else {
                modelData.transform.set(matrix4);
            }
            batch.render(modelData, env);
        }
    }

    public void setColor(Color c) {
        for (Material m : modelData.materials)
            m.set(ColorAttribute.createDiffuse(c));
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 v) {
        position.set(v);
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
    }

    public void moveBy(Vector3 v) {
        position.add(v);
    }

    public void moveBy(float x, float y, float z) {
        moveBy(new Vector3(x, y, z));
    }

    public void moveByForward(float dist) {
        moveBy(rotation.transform(new Vector3(0, 0, 1)).scl(dist));
    }

    public void moveByUp(float dist) {
        moveBy(rotation.transform(new Vector3(1, 0, 0)).scl(dist));
    }

    public void moveByRight(float dist) {
        moveBy(rotation.transform(new Vector3(0, 1, 0)).scl(dist));
    }

    public void moveForward(float dist){
        position.z += dist;
    }

    public void moveUp(float dist){
        position.y += dist;
    }

    public void moveRight(float dist){
        position.x -= dist;
    }


    public float getTurnAngle() {
        return rotation.getAngleAround(1, 0, 0);
    }

    public void setTurnAngle(float degrees) {
        rotation.set(new Quaternion(Vector3.X, degrees));
    }

    public void turnBy(float degrees) {
        rotation.mul(new Quaternion(Vector3.X, -degrees));
    }

    public void setScale(float x, float y, float z) {
        scale.set(x, y, z);
    }

    public void remove() {
        if (stage3D!=null) {
            stage3D.removeActor(this);
        }
    }

    public void buildModel(float width, float height, float depth, boolean blending) {
        ModelBuilder modelBuilder = new ModelBuilder();
        Material boxMaterial = new Material();
        if (blending)
            boxMaterial.set(new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA));
        int usageCode =
                VertexAttributes.Usage.Position +
                VertexAttributes.Usage.ColorPacked +
                VertexAttributes.Usage.Normal +
                VertexAttributes.Usage.TextureCoordinates;

        this.width = width;
        this.height = height;
        this.depth = depth;
        Model boxModel = modelBuilder.createBox(width, height, depth, boxMaterial, usageCode);
        Vector3 position = new Vector3(0, 0, 0);

        GameObject instance = new GameObject(boxModel, position);
        setModelInstance(instance);
        instance.calculateBoundingBox(bounds);
        instance.shape = new Box(bounds);
    }

    public void setStage3D(Stage3D stage3D) {
        this.stage3D = stage3D;
    }

    public void addAction (Action3D action) {
        action.setActor3D(this);
        actions.add(action);
        if (stage3D != null) {
            Gdx.graphics.requestRendering();
        }
    }

    public void removeAction (Action3D action) {
        if (action != null && actions.removeValue(action, true)) action.setActor3D(null);
    }

    public Array<Action3D> getActions () {
        return actions;
    }

    public void clearActions () {
        for (int i = actions.size - 1; i >= 0; i--)
            actions.get(i).setActor3D(null);
        actions.clear();
    }

    public Quaternion getRotation() {
        return rotation;
    }

    public Vector3 getScale() {
        return scale;
    }

    public float getAngle() {
        return rotation.getAngle();
    }

    public void setMaterialTexture(Texture texture) {
        for (Material material : modelData.materials) {
            boolean setTexture = false;
            for (Attribute attribute : material) {
                if (attribute instanceof TextureAttribute) {
                    setTexture = true;
                    ((TextureAttribute) (attribute)).set(new TextureRegion(texture));
                }
            }
            if (!setTexture){
                material.set(TextureAttribute.createDiffuse(new TextureRegion(texture)));
            }
        }
    }

    public void setParent3D(BaseActor3DGroup parent3D) {
        this.parent3D = parent3D;
    }

    public BaseActor3DGroup getParent3D() {
        return parent3D;
    }

    private BoundingBox boundingBoxTemp = new BoundingBox();

    protected RayBean checkCollision(Ray ray, RayBean rayBean) {
        Vector3 position1 = getPosition();
//        ray在使用之前已经经过变换
        boundingBoxTemp.set(position1.cpy().add(bounds.min),position1.cpy().add(bounds.max));
        Vector3 vector3 = new Vector3();
        if (Intersector.intersectRayBounds(ray,boundingBoxTemp,vector3)) {
            float dst = vector3.dst(stage3D.getCamera().position);
            if (rayBean.getBaseActor3D()!=null){
                if (dst<rayBean.getLength()) {
                    rayBean.setBaseActor3D(this);
                    rayBean.setVector3(vector3);
                    rayBean.setLength(dst);
                }
            }else {
                rayBean.setBaseActor3D(this);
                rayBean.setVector3(vector3);
                rayBean.setLength(dst);
            }
            return rayBean;
        }
        return null;
    }

//    public void notifyListener() {
//        setColor(Color.GRAY);
//    }

    public void touchUp(Vector3 vector3, int pointer, int button){

    }

    public float getX(){
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getZ() {
        return position.z;
    }

    public void setFromAxis(int i, int i1, int i2, float rotationA) {
        rotation.setFromAxis(i,i1,i2,rotationA);
    }

    public void setRotation(Quaternion quaternion){
        this.rotation.set(quaternion);
    }

    public BoundingBox getBounds() {
        return bounds;
    }

    private Vector3 positionTmep = new Vector3();
    public boolean isCaremaClip(){
        if (stage3D!=null) {
            modelData.transform.getTranslation(positionTmep);
            positionTmep.add(center);
            return stage3D.getCamera().frustum.sphereInFrustum(position, radius);
        }
        return false;
    }
}
