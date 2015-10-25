package com.performaceartimes;

import com.artemis.World;
import com.artemis.WorldConfiguration;
import com.artemis.WorldConfigurationBuilder;
import com.artemis.utils.EntityBuilder;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.performaceartimes.components.LifeComponent;
import com.performaceartimes.components.RenderComponent;
import com.performaceartimes.components.TransformComponent;
import com.performaceartimes.components.VelocityComponent;
import com.performaceartimes.gui.Hud;
import com.performaceartimes.systems.LifeSystem;
import com.performaceartimes.systems.MovementSystem;
import com.performaceartimes.systems.RenderSystem;

public class PerformanceArtimes extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private OrthographicCamera camera;

	private Hud hud;

	private float count;

	private boolean removeEntity;

	private World world;

	public boolean getRemoveEntity() {
		return removeEntity;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		camera = new OrthographicCamera(40, 30);
		hud = new Hud(this);

		WorldConfiguration config = new WorldConfigurationBuilder()
				.with(new MovementSystem(), new RenderSystem(batch), new LifeSystem(hud))
				.build();

		world = new World(config);

		count = 0;

		removeEntity = false;

	}

	private void createEntity() {
		for (int i = 0; i < 100; i++) {
			new EntityBuilder(world).with(
					new TransformComponent(),
					new VelocityComponent(1.0f, count++),
					new RenderComponent(new TextureRegion(img)),
					new LifeComponent()
			).build();
		}
	}

	private void inputHandle() {
		if (Gdx.input.isKeyJustPressed(Input.Keys.Q)) {
			LifeSystem lifeSystem = world.getSystem(LifeSystem.class);
			removeEntity = !removeEntity;
			lifeSystem.setRemoveEntity(removeEntity);
		}
	}

	@Override
	public void render () {
		inputHandle();

		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		world.setDelta(Gdx.graphics.getDeltaTime());
		world.process();

		batch.end();

		createEntity();
		hud.draw();
	}

	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
		world.dispose();
		hud.dispose();
	}
}
