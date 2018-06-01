package com.ubros.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.ubros.game.Gui.MainMenuScreen;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Gui.SettingsScreen;

public class UbrosGame extends Game {

    /**
     * Game batch
     */
    private SpriteBatch batch;

    /**
     * Game asset manager
     */
    private AssetManager assetManager;

	/**
	 * Tiled map
	 */
	public static TiledMap map;

    /**
     * Main menu screen
     */
	public static ScreenAdapter mainMenu;

    /**
     * Play game screen
     */
	public static ScreenAdapter playGame;

    /**
     * Settings screen
     */
	public static ScreenAdapter settings;

	@Override
	public void create () {
		batch = new SpriteBatch();
        assetManager = new AssetManager();

        mainMenu = new MainMenuScreen(this);
        playGame = new PlayGameScreen(this, true);
		settings = new SettingsScreen(this);

        startGame();
	}

    /**
     * Starts the game.
     */
	private void startGame() {
		setScreen(mainMenu);
	}

	/**
	 * Returns the asset manager used to load all textures and sounds.
	 *
	 * @return the asset manager
	 */
	public AssetManager getAssetManager() {
		return assetManager;
	}

    /**
     * Set's new asset manager
     * @param assetManager new asset manager
     */
	public void setAssetManager(AssetManager assetManager) {
	    this.assetManager = assetManager;
    }

	/**
	 * Returns the sprite batch used to improve drawing performance.
	 *
	 * @return the sprite batch
	 */
	public Batch getBatch() { return batch;}

	@Override
	public void render() {
		super.render();
	}

	/**
	 * Disposes of all assets.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}

}
