package com.performaceartimes.systems;

import com.artemis.Aspect;
import com.artemis.BaseEntitySystem;
import com.artemis.ComponentMapper;
import com.artemis.utils.IntBag;
import com.performaceartimes.components.LifeComponent;
import com.performaceartimes.gui.Hud;

public class LifeSystem extends BaseEntitySystem {

    private boolean removeEntity;
    private Hud hud;

    private ComponentMapper<LifeComponent> lifeComponentCM;

    public void setRemoveEntity(boolean removeEntity) {
        this.removeEntity = removeEntity;
    }


    public LifeSystem(Hud hud) {
        super(Aspect.all(LifeComponent.class));

        this.hud = hud;
        removeEntity = false;
    }


    @Override
    protected void processSystem() {
        float deltaTime = world.getDelta();

        IntBag actives = subscription.getEntities();
        int[] entityIds = actives.getData();

        hud.setTotalEntities(actives.size());

        for (int i = 0; i < actives.size(); i++) {

            LifeComponent lifeComponent = lifeComponentCM.get(entityIds[i]);
            lifeComponent.life += deltaTime;

            if (removeEntity) {
                if (lifeComponent.life > lifeComponent.maxLife) {
                    world.delete(entityIds[i]);
                }
            }
        }
    }
}
