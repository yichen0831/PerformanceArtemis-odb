package com.performaceartimes.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.performaceartimes.components.RenderComponent;
import com.performaceartimes.components.TransformComponent;

public class RenderSystem extends BaseEntitySystem {

    private SpriteBatch batch;

    private ComponentMapper<TransformComponent> transformComponentCM;
    private ComponentMapper<RenderComponent> renderComponentCM;

    public RenderSystem(SpriteBatch batch) {
        super(Aspect.all(TransformComponent.class, RenderComponent.class));
        this.batch = batch;
    }


    @Override
    protected void processSystem() {
        IntBag actives = subscription.getEntities();
        int[] entityIds = actives.getData();

        for (int i = 0; i < actives.size(); i++) {
            TransformComponent transformComponent = transformComponentCM.get(entityIds[i]);
            RenderComponent renderComponent = renderComponentCM.get(entityIds[i]);
            batch.draw(renderComponent.textureRegion, transformComponent.position.x, transformComponent.position.y, transformComponent.width / 2, transformComponent.height / 2, transformComponent.width, transformComponent.height, transformComponent.scale.x, transformComponent.scale.y, transformComponent.rotation);

        }
    }
}
