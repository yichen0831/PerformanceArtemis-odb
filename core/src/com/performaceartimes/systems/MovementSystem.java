package com.performaceartimes.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.performaceartimes.components.TransformComponent;
import com.performaceartimes.components.VelocityComponent;

public class MovementSystem extends BaseEntitySystem {

    private ComponentMapper<TransformComponent> transformComponentCM;
    private ComponentMapper<VelocityComponent> velocityComponentCM;

    public MovementSystem() {
        super(Aspect.all(TransformComponent.class, VelocityComponent.class));
    }

    @Override
    protected void processSystem() {
        IntBag actives = subscription.getEntities();
        int[] ids = actives.getData();

        float deltaTime = world.getDelta();

        for (int i = 0; i < actives.size(); i++) {
            TransformComponent transformComponent = transformComponentCM.get(ids[i]);
            VelocityComponent velocityComponent = velocityComponentCM.get(ids[i]);

            transformComponent.position.x += velocityComponent.velocity.x * deltaTime;
            transformComponent.position.y += velocityComponent.velocity.y * deltaTime;

        }
    }

}
