package com.ubros.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ubros.game.Gui.MainMenuScreen;

public class UbrosGame extends Game {

	private SpriteBatch batch;
    private AssetManager assetManager;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
        assetManager = new AssetManager();

        startGame();
	}

    /**
     * Starts the game.
     */
    //private void startGame() {setScreen(new MainMenuScreen(this)); }
	private void startGame() {
		//setScreen(new GameView(this));
		setScreen(new MainMenuScreen(this));
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
	 * Returns the sprite batch used to improve drawing performance.
	 *
	 * @return the sprite batch
	 */
	public Batch getBatch() { return batch;}

	/**
	 * Disposes of all assets.
	 */
	@Override
	public void dispose () {
		batch.dispose();
		assetManager.dispose();
	}

}
