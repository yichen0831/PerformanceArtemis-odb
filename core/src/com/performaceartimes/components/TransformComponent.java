package com.performaceartimes.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class TransformComponent extends Component {
    public Vector2 position;
    public float rotation;
    public Vector2 scale;

    public float width;
    public float height;

    public TransformComponent(Vector2 position, float rotation, Vector2 scale, float width, float height) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.width = width;
        this.height = height;
    }

    public TransformComponent() {
        this(new Vector2(), 0, new Vector2(1, 1), 1, 1);
    }

}
