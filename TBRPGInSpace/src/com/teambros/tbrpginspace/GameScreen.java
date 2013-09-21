package com.teambros.tbrpginspace;

import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameScreen extends InputAdapter implements Screen  {

	private Game game;
	private SpriteBatch batch;
	private ArrayList<Sprite> sprites;
	private Stage stage;
	private Sprite spaceShip;
	private Sprite enemy;
	private int difficulty;
	
	public GameScreen(Game game){
		this.game = game;
		Gdx.app.log("GS", "creating GameScreen");
		sprites = new ArrayList<Sprite>();
		difficulty = 0;
	}
	
	@Override
	public void render(float delta) {
		
		if (Gdx.input.isKeyPressed(Keys.W)) {
			Vector2 vec = new Vector2(0, 20);
			vec.rotate(spaceShip.getRotation());
			spaceShip.setX(spaceShip.getX() + vec.x);
			spaceShip.setY(spaceShip.getY() + vec.y);
		}
		
		if (Gdx.input.isKeyPressed(Keys.A)) {
			spaceShip.rotate(10);
		}
		
		if (Gdx.input.isKeyPressed(Keys.D)) {
			spaceShip.rotate(-10);
		}
		
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		batch.begin();
		for (Sprite s : sprites){
			s.draw(batch);			
		}
		spaceShip.draw(batch);
		enemy.draw(batch);
		batch.end();
		stage.act();
		stage.draw();
		
		Vector2 enemyPos = new Vector2(enemy.getX() + enemy.getWidth() / 2, enemy.getY()+ enemy.getHeight() / 2);
		Vector2 playerPos = new Vector2(spaceShip.getX() + spaceShip.getWidth() / 2, spaceShip.getY()+ spaceShip.getHeight());
		
		if (enemyPos.dst(playerPos) < 100) {
			this.dispose();
			difficulty++;
			game.setScreen(new FightScreen(game, difficulty, this));
		}

	}

	@Override
	public void resize(int width, int height) {
		stage = new Stage();
		batch = new SpriteBatch();

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

		Texture shipTex = new Texture(Gdx.files.internal("data/spaceshipsmall.png"));
		spaceShip = new Sprite(shipTex);
		spaceShip.setPosition(Gdx.graphics.getWidth() / 2, 0);
		spaceShip.setOrigin(spaceShip.getWidth() / 2, spaceShip.getHeight() / 2);
		createEnemy();

		sprites.add(sprite);
		sprites.add(anotherSprite);
		sprites.add(thirdSprite);
		sprites.add(fourthSprite);
		InputMultiplexer imp = new InputMultiplexer();
		Gdx.input.setInputProcessor(imp);
		imp.addProcessor(stage);
		imp.addProcessor(this);

	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE) {
			game.setScreen(new LogoScreen(game));
			return true;
		}
		return false;	
	}

	
	private void createEnemy() {
		Vector2 shipPos = new Vector2(spaceShip.getX(), spaceShip.getY());
		Random generator = new Random();
		Vector2 enemyPos = new Vector2();
		while (true) {
			int enemyPosX= generator.nextInt(Gdx.graphics.getWidth());
			int enemyPosY = generator.nextInt(Gdx.graphics.getHeight());
			enemyPos.x = (enemyPosX * .9f);
			enemyPos.y = (enemyPosY * .9f);
			float distance = shipPos.dst(enemyPosX, enemyPosY);
			if (distance > 200) {
				Gdx.app.log("shipPos", ""+shipPos);
				Gdx.app.log("enemyPos", ""+enemyPos);
				Gdx.app.log("distance", ""+distance);
				break;
			}
		}
		Texture tex = new Texture(Gdx.files.internal("data/planet.png"));
//		Texture tex = new Texture(Gdx.files.internal("data/enemyspaceshipsmall.png"));
		enemy = new Sprite(tex);
		enemy.setX(enemyPos.x);
		enemy.setY(enemyPos.y);
		enemy.setOrigin(enemy.getWidth() / 2, enemy.getHeight() / 2);
		enemy.rotate(generator.nextInt(60) - 30);
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
	}
}
