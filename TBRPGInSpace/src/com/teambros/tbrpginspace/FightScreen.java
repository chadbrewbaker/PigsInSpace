package com.teambros.tbrpginspace;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FightScreen extends InputAdapter implements Screen {
	
	private Game game;
	private Stage stage;
	private BitmapFont font;
	private Sprite background;
	private SpriteBatch batch;
	private ArrayList<Sprite> sprites;
	private int difficulty;
	private GameScreen gameScreen;
	
	public FightScreen(Game game, int difficulty, GameScreen gameScreen){
		this.game = game;
		sprites = new ArrayList<Sprite>();
		stage = new Stage();
		font = new BitmapFont();
		batch = new SpriteBatch();
		this.difficulty = difficulty;
		this.gameScreen = gameScreen;
	}
	
	private void createActionBox(){
		Table actionBoxTable = new Table();
		actionBoxTable.setPosition(0, 0);
		actionBoxTable.setWidth(Gdx.graphics.getWidth() / 4);
		actionBoxTable.setHeight(Gdx.graphics.getHeight() / 3);
		actionBoxTable.setColor(Color.BLUE);

		TextButtonStyle style = new TextButtonStyle();
		
		style.checkedFontColor = Color.GREEN;
		style.font = font;
		style.fontColor = new Color(1f, 1f, 1f, 1f);

		actionBoxTable.left().bottom();
		
		TextButton button = new TextButton("Action1", style);
		button.addListener(new ActionButtonListener());
		actionBoxTable.add(button).padLeft(10);
		button = new TextButton("Action2",style);
		button.addListener(new ActionButtonListener());
		actionBoxTable.row();
		actionBoxTable.add(button).padLeft(10);
		button = new TextButton("Action3",style);
		button.addListener(new ActionButtonListener());
		actionBoxTable.row();
		actionBoxTable.add(button).padLeft(10);
		button = new TextButton("Action4",style);
		button.addListener(new ActionButtonListener());
		actionBoxTable.row();
		actionBoxTable.add(button).padLeft(10);
		
		InputMultiplexer imp = new InputMultiplexer();
		Gdx.input.setInputProcessor(imp);
		imp.addProcessor(stage);
		imp.addProcessor(this);
		
		stage.addActor(actionBoxTable);
	}
	
	public void victory(){
		game.setScreen(gameScreen);
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		for (Sprite s : sprites){
			s.draw(batch);			
		}
		background.draw(batch);
		batch.end();
		stage.act();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {		
		createActionBox();
		Texture tex = new Texture(Gdx.files.internal("data/fantasyplanetsurface.png"));
		background = new Sprite(tex);
		background.setPosition(-300, -100);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.ESCAPE) {
			game.setScreen(gameScreen);
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
		font.dispose();
		
	}
	
	private class ActionButtonListener extends ClickListener {
		
		@Override
		public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
			return true;
		}
					
		public void touchUp(InputEvent event, float x, float y, int pointer, int button) {			
			Gdx.app.log("team bros","bros2");			
		}
	}
}
