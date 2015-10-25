package com.performaceartimes.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class RenderComponent extends Component {
    public TextureRegion textureRegion;

    public RenderComponent(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }
}
