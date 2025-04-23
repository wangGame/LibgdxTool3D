package com.libGdx.test.wave;

import static java.lang.Math.abs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;

public class BaseActor extends Group {
    public boolean pause = false;
    public boolean isFacingRight = true;
    public float animationWidth = getWidth();
    public float animationHeight = getWidth();
    private Animation<TextureRegion> animation;
    private float animationTime;
    private boolean animationPaused;

    public BaseActor(float x, float y) {
        super();
        setPosition(x, y);
        animation = null;
        animationTime = 0;
        animationPaused = false;
    }

    @Override
    public void setSize(float width, float height) {
        super.setSize(width, height);
        setAnimationSize(width, height);
    }

    @Override
    public void act(float delta) {
        if (!pause)
            super.act(delta);

        if (!animationPaused)
            animationTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);

        if (animation != null && isVisible()) {
            if (isFacingRight)
                batch.draw(
                        animation.getKeyFrame(animationTime),
                        getX() + abs(getWidth() - animationWidth) / 2,
                        getY() + abs(getHeight() - animationHeight) / 2,
                        getOriginX(),
                        getOriginY(),
                        animationWidth,
                        animationHeight,
                        getScaleX(),
                        getScaleY(),
                        getRotation()
                );
            else
                batch.draw(
                        animation.getKeyFrame(animationTime),
                        getX() + getWidth(),
                        getY(),
                        getOriginX() - getWidth(),
                        getOriginY(),
                        -getWidth(),
                        getHeight(),
                        getScaleX(),
                        getScaleY(),
                        getRotation()
                );
        }
        super.draw(batch, parentAlpha);
    }

    private void setAnimationSize(Float width, Float height) {
        animationWidth = width;
        animationHeight = height;
    }

    public void flip() {
        isFacingRight = !isFacingRight;
    }

    public void setAnimationPaused(Boolean pause) {
        animationPaused = pause;
    }

    public Boolean isAnimationFinished() {
        return animation.isAnimationFinished(animationTime);
    }

    public Animation<TextureRegion> loadTexture(String fileName) {
        if (fileName.isEmpty())
            Gdx.app.error(getClass().getSimpleName(), "Error: Texture path is invalid: " + fileName);
        Array<String> fileNames = new Array(1);
        fileNames.add(fileName);
        return loadAnimationFromFiles(fileNames, 1f, true);
    }

    /**
     * 好多年没见过这种写法了
     * @param fileNames
     * @param frameDuration
     * @param loop
     * @return
     */
    private Animation<TextureRegion> loadAnimationFromFiles(Array<String> fileNames, float frameDuration, Boolean loop) {
        Array<TextureRegion> textureArray = new Array();
        for (int i = 0; i < fileNames.size; i++) {
            Texture texture = new Texture(Gdx.files.internal(fileNames.get(i)));
            texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
            textureArray.add(new TextureRegion(texture));
        }
        Animation<TextureRegion> anim = new Animation(frameDuration, textureArray);
        if (loop) {
            anim.setPlayMode(Animation.PlayMode.LOOP);
        }else {
            anim.setPlayMode(Animation.PlayMode.NORMAL);
        }
        if (animation == null) {
            setAnimation(anim);
        }
        return anim;
    }

    public void setAnimation(Animation<TextureRegion> anim) {
        animation = anim;
        TextureRegion tr = animation.getKeyFrame(0);
        float w = tr.getRegionWidth();
        float h = tr.getRegionHeight();
        setSize(w, h);
        setOrigin(Align.center);
    }

    /**
     * set middle
     * @param x
     * @param y
     */
    public void centerAtPosition(float x, float y) {
        setPosition(x, y,Align.center);
    }

    public void centerAtActor(BaseActor baseActor) {
        centerAtPosition(baseActor.getX(Align.center), baseActor.getY(Align.center));
    }

    public void setOpacity(float opacity) {
        this.getColor().a = opacity;
    }

    public void loadImage(TextureRegion region) {
        if (region == null) {
            Gdx.app.error(getClass().getSimpleName(), "Error: region is null. Are you sure the image  exists?");
        }
        setAnimation(new Animation(1f, region));
    }
}
