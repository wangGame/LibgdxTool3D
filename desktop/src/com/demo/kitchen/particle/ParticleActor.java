package com.demo.kitchen.particle;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffectLoader;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.PointSpriteParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.asset.Asset3D;

public class ParticleActor extends BaseActor3D {
    private ParticleEffect currentEffects;
    private ParticleSystem particleSystem;
    BillboardParticleBatch pointSpriteBatch;
    public ParticleActor(String path){
        particleSystem = ParticleSystem.get();
        pointSpriteBatch = new BillboardParticleBatch();
        particleSystem = ParticleSystem.get();
        particleSystem.add(pointSpriteBatch);
        ParticleEffect particle = Asset3D.getAsset3D().getParticle(path, particleSystem.getBatches());

        currentEffects=particle.copy();
        currentEffects.init();
        particleSystem.add(currentEffects);
        Array<ParticleController> controllers = currentEffects.getControllers();
        for (ParticleController controller : controllers) {
            controller.renderer.setBatch(pointSpriteBatch);
        }

    }

    @Override
    public void draw(ModelBatch batch, Environment env) {
        super.draw(batch, env);
        currentEffects.setTransform(calculateTransform());
        pointSpriteBatch.setCamera(stage3D.camera);
        particleSystem.update();
        particleSystem.begin();
        particleSystem.draw();
        particleSystem.end();
        batch.render(particleSystem);

    }
}
