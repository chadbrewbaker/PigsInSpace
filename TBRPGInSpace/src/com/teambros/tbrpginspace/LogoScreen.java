package com.teambros.tbrpginspace;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class LogoScreen extends InputAdapter implements Screen {
	
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Texture texture;
//	private Sprite sprite;
	private GameScreen gameScreen;
	private ArrayList<Sprite> sprites;
	private Stage stage;
	private Game game;
	private BitmapFont font;

	public LogoScreen(Game game){
		this.game = game;
		batch = new SpriteBatch();
		font = new BitmapFont();
		stage = new Stage();
		sprites = new ArrayList<Sprite>();
	}
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Sprite s : sprites){
			s.draw(batch);			
		}
//		sprite.draw(batch);
		batch.end();
	
		stage.act();
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		Texture texture = new Texture(Gdx.files.internal("data/spacetile.png"));
		texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);

		Sprite sprite = new Sprite(texture);
		Sprite anotherSprite = new Sprite(texture);
		Sprite thirdSprite = new Sprite(texture);
		Sprite fourthSprite = new Sprite(texture);
		
		sprite.setPosition(0, 0);
		anotherSprite.setPosition(sprite.getX() + sprite.getWidth(), 0);
		thirdSprite.setPosition(0, sprite.getX() + sprite.getHeight());
		fourthSprite.setPosition(sprite.getX() + sprite.getWidth(),
				sprite.getX() + sprite.getHeight());
		
		fourthSprite.rotate(180);
		anotherSprite.rotate(270);
		
		sprites.add(sprite);
		sprites.add(anotherSprite);
		sprites.add(thirdSprite);
		sprites.add(fourthSprite);
		
		Gdx.input.setInputProcessor(this);
		
		// TODO Auto-generated method stub
		Table actionBoxTable = new Table();
		actionBoxTable.setPosition(0, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()  / 3);
		actionBoxTable.setColor(Color.BLUE);

		LabelStyle style = new LabelStyle();
		
		font.setScale(10);
		style.font = font;
		style.fontColor = new Color(1f, 1f, 1f, 1f);
		
		Label label = new Label("DEEP SPACE", style);
		actionBoxTable.right();
		actionBoxTable.addActor(label);
		stage.addActor(actionBoxTable);

	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.SPACE) {
			game.setScreen(new GameScreen(game));
			return true;
		}
		return false;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == 0) {
			game.setScreen(new GameScreen(game));
			return true;			
		}
		return false;
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
