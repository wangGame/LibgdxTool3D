package com.kw.gdx.d3.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
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
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import com.badlogic.gdx.math.collision.Ray;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.action.Action3D;
import com.kw.gdx.d3.stage.Stage3D;
import com.kw.gdx.d3.utils.Box;

import org.w3c.dom.Text;

/**
 * model Actor
 */
public class BaseActor3D {
    protected BaseActor3DGroup parent3D;
    protected boolean isCollisionEnabled = true;
    protected boolean isPreventOverlapEnabled = true;
    protected GameObject modelData;
    protected final Vector3 position;

    protected float width;
    protected float height;
    protected float depth;

    public Polygon boundingPolygon;
    protected Stage3D stage3D;
    protected BoundingBox bounds = new BoundingBox();
    protected Quaternion rotation;
    protected Vector3 scale;
    private final Array<Action3D> actions = new Array(0);

    public BaseActor3D(){
        this(0,0,0);
    }

    public BaseActor3D(float x, float y, float z) {
        modelData = null;
        position = new Vector3(x, y, z);
        rotation = new Quaternion();
        scale = new Vector3(1, 1, 1);
        boundingPolygon = null;
    }

    public void setModelInstance(GameObject m) {
        modelData = m;
        setBaseRectangle();
    }

    public Matrix4 calculateTransform() {
        return new Matrix4(position, rotation, scale);
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

    public void draw(ModelBatch batch, Environment env) {
        if (modelData!=null) {
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

    // 2D collision detection
    public void setBaseRectangle() {
        BoundingBox modelBounds = modelData.calculateBoundingBox(new BoundingBox());
        Vector3 max = modelBounds.max;
        Vector3 min = modelBounds.min;

        float[] vertices =
                {max.y, max.z, min.y, max.z, min.y, min.z, max.y, min.z};

        boundingPolygon = new Polygon(vertices);
        boundingPolygon.setOrigin(0, 0);
    }

    public Polygon getBoundaryPolygon() {
        boundingPolygon.setPosition(position.y, position.z);
        boundingPolygon.setRotation(getTurnAngle());
        boundingPolygon.setScale(scale.y, scale.z);
        return boundingPolygon;
    }

    public boolean overlaps(BaseActor3D other) {
        if (!isCollisionEnabled || !other.isCollisionEnabled) return false;
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return false;

        Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();

        return Intersector.overlapConvexPolygons(poly1, poly2, mtv);
    }

    public void preventOverlap(BaseActor3D other) {
        if (!isPreventOverlapEnabled || !other.isPreventOverlapEnabled)
            return;
        Polygon poly1 = this.getBoundaryPolygon();
        Polygon poly2 = other.getBoundaryPolygon();

        // initial test to improve performance
        if (!poly1.getBoundingRectangle().overlaps(poly2.getBoundingRectangle()))
            return;

        Intersector.MinimumTranslationVector mtv = new Intersector.MinimumTranslationVector();
        boolean polygonOverlap = Intersector.overlapConvexPolygons(poly1, poly2, mtv);

        if (polygonOverlap)
            this.moveBy(0, mtv.normal.x * mtv.depth, mtv.normal.y * mtv.depth);
    }

    public boolean isWithinDistance(Float distance, BaseActor3D other) {
        return distanceBetween(other) <= distance;
    }

    public boolean isOnCenter(BaseActor3D other) {
        return (int) position.y == (int) other.position.y &&
                (int) position.z == (int) other.position.z;
    }

    public float distanceBetween(BaseActor3D other) {
        return (float) Math.sqrt(Math.pow(Math.abs(other.position.y - position.y), 2) + Math.pow(Math.abs(other.position.z - position.z), 2));
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

    public float getRotation() {
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

    protected boolean checkCollision(Ray ray) {
        Matrix4 inverseTransform = calculateTransform().cpy().inv();

        Ray localRay = new Ray(ray.origin.cpy().mul(inverseTransform), ray.direction.cpy().mul(inverseTransform));

        Vector3 vector3 = new Vector3();
        if (Intersector.intersectRayBounds(localRay,bounds,vector3)) {
            return true;
        }
        return false;
    }

    public void notifyListener() {
        Array<BaseActor3D> actor3DS = new Array<>();
        BaseActor3DGroup parent3D1 = parent3D;
        while (parent3D1 != null) {
            actor3DS.add(parent3D);
            parent3D1 = parent3D1.parent3D;
        }
        for (BaseActor3D actor3D : actor3DS) {
            actor3D.runEvent();
        }
    }

    private void runEvent() {
        System.out.println("=========touch down ============= ;"+this);
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
}
