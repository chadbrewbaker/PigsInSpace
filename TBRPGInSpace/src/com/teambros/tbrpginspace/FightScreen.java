package com.teambros.tbrpginspace;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class FightScreen implements Screen {
	
	private Game game;
	private Stage stage;
	private BitmapFont font;

	
	public FightScreen(Game game){
		this.game = game;
		stage = new Stage();
		font = new BitmapFont();
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
		
		TextButton button = new TextButton("Yo", style);
		button.addListener(new ActionButtonListener());
		
		actionBoxTable.add(button).padLeft(10);
		button = new TextButton("Yo",style);
		button.addListener(new ActionButtonListener());
		actionBoxTable.row();
		actionBoxTable.add(button).padLeft(10);
		button = new TextButton("Yo",style);
		button.addListener(new ActionButtonListener());
		actionBoxTable.row();
		actionBoxTable.add(button).padLeft(10);
		button = new TextButton("Yo",style);
		button.addListener(new ActionButtonListener());
		actionBoxTable.row();
		actionBoxTable.add(button).padLeft(10);
		
		InputMultiplexer imp = new InputMultiplexer();
		Gdx.input.setInputProcessor(imp);
		imp.addProcessor(stage);
		
		stage.addActor(actionBoxTable);
	}
	
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	@Override
	public void resize(int width, int height) {

		createActionBox();
		
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
