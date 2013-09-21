package com.teambros.tbrpginspace;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;


public class UserInterface extends InputAdapter {
	
		private static UserInterface instance;
        private Stage stage;
        private static Skin skin;
        private TextureAtlas atlas;
    	private TextButton atkButton;
    	private TextButton endButton;
    	private ImageButton menuButton;
    	private ImageButton consoleButton;
    	private BitmapFont font;
        
        // info box
        public static final String UNKNOWN = "Unknown";
        private static final int MAX_SIZE = 24;
        private static Label genPwr;
        private static Label status;
        private static Label atkPwr;
        private static Label rowColumn;
    	
        private UserInterface() {
        	
        	stage = new Stage();
//    		atlas = H3XUtils.getAssetManager().get("data/boxes.pack", TextureAtlas.class);
    		skin = new Skin();
    		skin.addRegions(atlas);
            
            
            /** ---------------- info box ----------------- **/            
    		LabelStyle style = new LabelStyle();
//    		style.background = skin.getDrawable("noinfobox");
//    		FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("data/homespun.ttf"));
    		BitmapFont font;
    		Drawable drawable = skin.getDrawable("blueinfobox");
    		
    		double ratio = (double) Gdx.graphics.getWidth() / 320;
    		ratio *= 14;
    		
    		if (ratio > MAX_SIZE) {
    			ratio = MAX_SIZE;
    		}
    		
//    		font = gen.generateFont((int) ratio);
    		
//    		style.font = font;
    		
//    		gen.dispose();
    		style.fontColor = new Color(.0f, .8f, 1f, .8f);
            
            // non-changing labels
            Label genPowerLabel = new Label("Gen Power: ", style);
            Label statusLabel = new Label("Status:", style);
            Label attackPowerLabel = new Label("Strength: ", style);
            Label rowColumnLabel = new Label("Node: ", style);
            
            // dynamic info labels
            genPwr = new Label("<NULL>", style);
            status = new Label("<NULL>", style);
            atkPwr = new Label("<NULL>", style);
            rowColumn = new Label("<NULL>", style);
            
            Table infoTable = new Table();
            
            infoTable.left();
            infoTable.add(rowColumnLabel).padLeft(10).top().left().uniform();
            infoTable.add(rowColumn).top().left().uniform();
            
            infoTable.add(statusLabel).top().left().uniform();
            infoTable.add(status).top().left().uniform();
            
            infoTable.row();
            infoTable.add(attackPowerLabel).padLeft(10).top().left().uniform();
            infoTable.add(atkPwr).top().left().uniform();
            
            infoTable.add(genPowerLabel).top().left().uniform();
            infoTable.add(genPwr).top().left().uniform();        
            
            infoTable.setWidth(Gdx.graphics.getWidth());
            infoTable.setHeight((style.font.getXHeight() * 4));
            infoTable.setX(0);
            infoTable.setY(0);
            infoTable.setBackground(drawable);
            /** ------------------------------------------- **/

            
    		/** -------------- action buttons -------------- **/
    		Table actionBar = new Table();
            actionBar.setX(0);
            actionBar.setY(0 + infoTable.getHeight());
            actionBar.setWidth(Gdx.graphics.getWidth());
            actionBar.setHeight((Gdx.graphics.getHeight() / 6) - infoTable.getHeight());

            drawable = skin.getDrawable("blueinfobox");
            actionBar.setBackground(drawable);
            
    		font = new BitmapFont(Gdx.files.internal("data/actionfont.fnt"), false);
    		TextButtonStyle tStyle = new TextButtonStyle();
    		tStyle.up = skin.getDrawable("noinfobox");
    		tStyle.checked = skin.getDrawable("greeninfobox");
    		tStyle.fontColor = new Color(.0f, .8f, 1f, .8f);
    		tStyle.font = font;
    		
            atkButton = new TextButton("Move", tStyle);
            atkButton.addListener(new ClickListener());
            
            endButton = new TextButton("End", tStyle);
            endButton.addListener(new ClickListener());
            
            TextButton anotherButton = new TextButton("WTF Mate", tStyle);
            anotherButton.addListener(new ClickListener());
            
            atkButton.setHeight(actionBar.getHeight());
	        endButton.setHeight(actionBar.getHeight());
            
            actionBar.top().left();
	        actionBar.add(atkButton).left().padLeft(10);
	        actionBar.add(endButton).left().padLeft(30);
	        actionBar.add(anotherButton).left().padLeft(30);

	        actionBar.row();
            /** ------------------------------------------- **/
	        
                        
            /** --------------- menu button --------------- **/
//            Sprite settings = new Sprite(H3XUtils.getAssetManager().get("data/settingsButton.png", Texture.class));
//            Sprite settingsPressed = new Sprite(H3XUtils.getAssetManager().get("data/settingsButtonPressed.png", Texture.class));
//            ImageButton.ImageButtonStyle menuBS = new ImageButton.ImageButtonStyle();
//            menuBS.down = new SpriteDrawable(settingsPressed);
//            menuBS.up = new SpriteDrawable(settings);
//            menuButton = new ImageButton(menuBS);
//            menuButton.addListener(new MenuListener());
//            
//            Sprite consoleDown = new Sprite(H3XUtils.getAssetManager().get("data/consoleDown.png", Texture.class));
//            Sprite consoleUp = new Sprite(H3XUtils.getAssetManager().get("data/consoleUp.png", Texture.class));
//            ImageButton.ImageButtonStyle consoleBS = new ImageButton.ImageButtonStyle();
//            consoleBS.checked = new SpriteDrawable(consoleUp);
//            consoleBS.up = new SpriteDrawable(consoleDown);
//            consoleButton = new ImageButton(consoleBS);
//            consoleButton.addListener(new MenuListener());
            
//            Table menuTable = new Table();
//            menuTable.setX(Gdx.graphics.getWidth() - settings.getWidth());
//            menuTable.setY(Gdx.graphics.getHeight() - settings.getHeight());
//            menuTable.add(menuButton);//.padTop(10);
//            menuTable.setHeight(settings.getHeight());
//            menuTable.setWidth(settings.getWidth());
//
//            Table consoleTable = new Table();
//            consoleTable.setX(0);
//            consoleTable.setY(Gdx.graphics.getHeight() - consoleDown.getHeight());
//            consoleTable.add(consoleButton);
//            consoleTable.setHeight(consoleUp.getHeight());
//            consoleTable.setWidth(consoleUp.getWidth());            
            /** ------------------------------------------- **/
            
//            stage.addActor(consoleTable);
//            stage.addActor(menuTable);
//            stage.addActor(infoTable);
//            stage.addActor(actionBar);
        }
        
        public Stage getStage(){
        	return stage;
        }
        
        public void enable(boolean enabled){
        	if (enabled) {
        		atkButton.setDisabled(false);
        		endButton.setDisabled(false);
        	} else {
        		atkButton.setDisabled(true);
        		endButton.setDisabled(true);
        		
        	}
        }
        
        public void draw(){
        	stage.act();
        	stage.draw();
        }
        
        public static UserInterface getInstance(boolean clear){
        	if (clear && instance != null) {
        		instance.dispose();
        		instance = null;
        	}
        	
            if(instance == null){
                instance = new UserInterface();
            }
            return instance;
        }
        
        public void dispose() {
        	stage.dispose();
        	skin.dispose();
//        	atlas.dispose();
        }
        
        public void setInputs(InputMultiplexer imp){
        	imp.addProcessor(stage);
        }

        //
        // User Interface methods
        //
        public static void setLabels(String row, String col, String statusName, String atkPower, String genPower){
        	if (row.equals("") && col.equals(""))
        		row = "<NULL>";
        	else
        		row = row + "," + col;
        	if (statusName.equals(""))
        		statusName = "<NULL>";
        	if (atkPower.equals(""))
        		atkPower = "<NULL>";
        	if (genPower.equals(""))
        		genPower = "<NULL>";
        	
        	rowColumn.setText(row);
        	status.setText(statusName);
        	atkPwr.setText(atkPower);
        	genPwr.setText(genPower);
        }
        
        
        

//        private static class AttackListener extends ClickListener {
//        	
//        	TextButton button;
//        	
//        	public AttackListener(TextButton button){
//        		this.button = button;
//        	}
//
//    		@Override
//        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)  {
//    			
//    			return true;
//        	}
//    		
//    		@Override
//        	public void touchUp(InputEvent event, float x, float y, int pointer, int button)  {			
//
//    			if (button != 0)
//    				return;			
//    			
//    			if (this.button.isChecked()) {
//    				GameManager.performingAction = true;
//    				
//    			} else
//    				GameManager.performingAction = false;
//    			
//    		}
//        }
//        
//        
//        private static class MoveListener extends ClickListener {
//        	
//
//    		@Override
//        	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)  {
//    			if (Gameboard.selected)
//    				return false;
//    			return true;
//        	}
//    		
//    		@Override
//        	public void touchUp(InputEvent event, float x, float y, int pointer, int button)  {			
//    			
//    			GameManager.performingAction = true;
//    			
//    			if (button != 0)
//    				return;	
//    						
//    		}
//        }

}