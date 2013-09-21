package com.teambros.tbrpginspace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LogoScreen implements Screen {
	
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
	private Sprite sprite;
	private GameScreen gameScreen;
	private Stage stage;
	private Game tbrpg;

	public LogoScreen(final TBRPGInSpace tbrpg){
		this.tbrpg = tbrpg;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera(w, h);
		batch = new SpriteBatch();
		
		texture = new Texture(Gdx.files.internal("data/logo.gif"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		sprite = new Sprite(texture);
		
//		sprite.setSize(0.9f, 0.9f * sprite.getHeight() / sprite.getWidth());
		sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
		sprite.setPosition(sprite.getOriginX(), sprite.getOriginY());
		
		stage = new Stage();
		Actor a = new Actor();
		Gdx.app.log("sprite x", ""+sprite.getX());
		Gdx.app.log("sprite x", ""+sprite.getY());
		Gdx.app.log("sprite x", ""+sprite.getWidth());
		Gdx.app.log("sprite height", ""+sprite.getWidth());
		
		a.setX(sprite.getX());
		a.setY(sprite.getY());
		a.setWidth(sprite.getWidth());
		a.setHeight(sprite.getHeight());
		a.addListener(new InputListener() {
			
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
						
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				Gdx.app.log("touchDown", ""+event.getButton());
				tbrpg.setScreen(new GameScreen(tbrpg));
			}
			
		});
		
		stage.addActor(a);
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
//		batch.setProjectionMatrix(camera.combined);
		stage.act();
		stage.draw();
		batch.begin();
		sprite.draw(batch);
		batch.end();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {

		stage.clear();
		stage.dispose();
		batch.dispose();
		texture.dispose();
		
	}

}
