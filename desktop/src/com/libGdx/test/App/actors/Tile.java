package com.libGdx.test.App.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.kw.gdx.asset.Asset;
import com.kw.gdx.d3.actor.BaseActor3D;

public class Tile extends BaseActor3D {
    private float height;
    //对角线长度
    private float diagonalLength;

    private int index;
    private String type;
    private boolean isAIpath;
    private String secretMovementDirection;
    private boolean isWinCondition;

    private boolean isOpeningSecret;
    private float speed = 0.04f;
//    private long secretSoundID; //音效不要
    private Vector3 originalPosition;
    private int secretLength;
    private String texture;
    private float rotation;
    public Tile(float x, float y, float z) {
        super(x, y, z);

        this.height = 4;
        this.diagonalLength = (float) Math.sqrt(2 * Math.pow(height, 2));
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSecretMovementDirection(String secretMovementDirection) {
        this.secretMovementDirection = secretMovementDirection;
    }

    public void setSecretLength(int secretLength) {
        this.secretLength = secretLength;
    }

    public void setTexture(String texture) {
        this.texture = texture;
    }

    public void setAIpath(boolean AIpath) {
        isAIpath = AIpath;
    }

    public void setWinCondition(boolean winCondition) {
        isWinCondition = winCondition;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void buildAll(){
        if (texture.equalsIgnoreCase("blank")) {
            buildModel(width,height,depth,true);
        }else {
            buildModel(width,height,depth,false);
        }
        setBaseRectangle();
        loadImage(new TextureRegion(Asset.getAsset().getTexture("tiles/"+texture)));
        turnBy(-180+rotation);

        if (type == "4th floor")
            position.x = height * 3;
        else if (type == "3rd floor")
            position.x = height * 2;
        else if (type == "2nd floor")
            position.x = height;
        else if (type == "U1")
            position.x = -height;
        originalPosition = getPosition().cpy();
    }

    @Override
    public void act(float dt) {
        super.act(dt);
        if(isOpeningSecret){
            openSecret();
        }
    }

    public boolean isSecretTriggered(){
        if (isOpeningSecret){
            return true;
        }
        isOpeningSecret = true;
//        secretSoundID =
        return false;
    }

    public void setBloody(){
        loadImage(new TextureRegion(Asset.getAsset().getTexture("tiles/bloody/" + texture)));
    }

    private void openSecret() {
        if (secretMovementDirection.equalsIgnoreCase("up"))
            openSecretThatGoesUp();
        else if (secretMovementDirection.equalsIgnoreCase("down"))
            openSecretThatGoesDown();
        else if (secretMovementDirection.equalsIgnoreCase("north"))
            openSecretThatGoesNorth();
        else if (secretMovementDirection.equalsIgnoreCase("east"))
            openSecretThatGoesEast();
        else if (secretMovementDirection.equalsIgnoreCase("south"))
            openSecretThatGoesSouth();
        else if (secretMovementDirection.equalsIgnoreCase("west"))
            openSecretThatGoesWest();
    }

    private void openSecretThatGoesWest() {
        if (isOpeningSecret){
            if (getPosition().y < originalPosition.y - height * secretLength) {
                setPosition(getPosition().x, getPosition().y - speed, getPosition().y);
            }else {
                isCollisionEnabled = false;
                isVisible = false;
            }
        }
    }

    private void openSecretThatGoesSouth() {
        if (isOpeningSecret) {
            if (getPosition().z > originalPosition.z - height * secretLength) {
                setPosition(getPosition().x, getPosition().y, getPosition().z - speed);
            } else {
                isCollisionEnabled = false;
                isVisible = false;
            }
        }

    }

    private void openSecretThatGoesEast() {
        if (isOpeningSecret){
            if (getPosition().y < originalPosition.y + height * secretLength) {
                setPosition(getPosition().x, getPosition().y+speed, getPosition().y);
            }else {
                isCollisionEnabled = false;
                isVisible = false;
            }
        }
    }

    private void openSecretThatGoesNorth() {
        if (isOpeningSecret){
            if (getPosition().z < originalPosition.z + height * secretLength) {
                setPosition(getPosition().x, getPosition().y, getPosition().y+speed);
            }else {
                isCollisionEnabled = false;
                isVisible = false;
            }
        }
    }

    private void openSecretThatGoesDown() {
        if (isOpeningSecret){
            if (getPosition().x < -height * secretLength) {
                setPosition(getPosition().x - speed, getPosition().y, getPosition().y);
            }else {
                isCollisionEnabled = false;
                isVisible = false;
            }
        }
    }

    /**
     * ？？？
     */
    private void openSecretThatGoesUp() {
        if (isOpeningSecret){
            if (getPosition().x < height * secretLength) {
                setPosition(getPosition().x + speed, getPosition().y, getPosition().y);
            }else {
                isCollisionEnabled = false;
                isVisible = false;
            }
        }
    }
}
