package com.teambros.tbrpginspace;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class GameScreen implements Screen {

	private Game tbrpg;
	private SpriteBatch batch;
//	private Sprite sprite;
	private Texture texture;
	private Camera camera;
	private ArrayList<Sprite> sprites;
	private BitmapFont font;
	private Stage stage;
	
	public GameScreen(Game tbrpg){
		this.tbrpg = tbrpg;
		Gdx.app.log("GS", "creating GameScreen");
		sprites = new ArrayList<Sprite>();
		font = new BitmapFont();
		stage = new Stage();
		createActionBox();
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		for (Sprite s : sprites){
			s.draw(batch);			
		}
		batch.end();
		stage.act();
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		batch = new SpriteBatch();
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);

		texture = new Texture(Gdx.files.internal("data/spacetile.png"));
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

	}
	
	private void createActionBox(){
		Table actionBoxTable = new Table();
		actionBoxTable.setPosition(0, 0);
		actionBoxTable.setWidth(Gdx.graphics.getWidth() / 4);
		actionBoxTable.setHeight(Gdx.graphics.getHeight() / 3);
//		Texture tex = new Texture(Gdx.files.internal("data/logo.png"));
//		Sprite sprite = new Sprite(tex);
//		sprite.setX(actionBoxTable.getX());
//		sprite.setY(actionBoxTable.getY());
//		actionBoxTable.setBackground(new SpriteDrawable(sprite));
		
		LabelStyle style = new LabelStyle();
		style.font = font;
		style.fontColor = new Color(1f, 1f, 1f, 1f);
		
		actionBoxTable.left().bottom();
		actionBoxTable.add(new Label("Wtf mate", style)).padLeft(10);
		actionBoxTable.row();
		actionBoxTable.add(new Label("Wtf mate", style)).padLeft(10);
		actionBoxTable.row();
		actionBoxTable.add(new Label("Wtf mate", style)).padLeft(10);
		actionBoxTable.row();
		actionBoxTable.add(new Label("Wtf mate", style)).padLeft(10);

		
		stage.addActor(actionBoxTable);
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
		// TODO Auto-generated method stub
		
	}

}
