package com.sob.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sob.game.SobGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = SobGame.TITLE + " v" + SobGame.VERSION;
		config.width = 1280;
		config.height = 720;
		config.vSyncEnabled = true;
		config.resizable = false;
		new LwjglApplication(new SobGame(), config);
	}
}
