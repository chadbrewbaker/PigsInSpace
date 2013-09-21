package com.teambros.tbrpginspace;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "DEEP SPACE";
		cfg.width = 1000;
		cfg.height = 700;
		cfg.useGL20 = true;
		
		new LwjglApplication(new TBRPGInSpace(), cfg);
	}
}
