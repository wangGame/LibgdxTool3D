package com.demo.kitchen.particle;

import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.particles.ParticleController;
import com.badlogic.gdx.graphics.g3d.particles.ParticleEffect;
import com.badlogic.gdx.graphics.g3d.particles.ParticleSystem;
import com.badlogic.gdx.graphics.g3d.particles.batches.BillboardParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.ParticleBatch;
import com.badlogic.gdx.graphics.g3d.particles.batches.PointSpriteParticleBatch;
import com.badlogic.gdx.utils.Array;
import com.kw.gdx.d3.actor.BaseActor3D;
import com.kw.gdx.d3.actor.BaseActor3DGroup;
import com.kw.gdx.d3.asset.Asset3D;

public class ParticleActor extends BaseActor3D {
    private ParticleEffect effect;
    private ParticleSystem particleSystem;
    private BillboardParticleBatch pointSpriteBatch;

    public ParticleActor(String path){
        particleSystem = new ParticleSystem();
        ParticleEffect originalEffect = Asset3D.getAsset3D().getParticle(path);
        effect = originalEffect.copy();
        effect.init();
        pointSpriteBatch = new BillboardParticleBatch();
        effect.start();  // optional: particle will begin playing immediately
        particleSystem.add(effect);

    }

    @Override
    public void draw(ModelBatch batch, Environment env) {
        super.draw(batch, env);
        effect.setTransform(calculateTransform());
        Array<ParticleController> controllers = effect.getControllers();
        for (ParticleController controller : controllers) {
            controller.renderer.setBatch(pointSpriteBatch);
        }
        particleSystem.update(); // technically not necessary for rendering
        particleSystem.begin();
        particleSystem.draw();
        particleSystem.end();
        batch.render(particleSystem);
    }
}
