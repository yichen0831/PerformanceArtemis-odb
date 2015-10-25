package com.performaceartimes.components;

import com.artemis.Component;

public class LifeComponent extends Component {
    public float life;
    public float maxLife;

    public LifeComponent() {
        life = 0;
        maxLife = 10.0f;
    }
}
