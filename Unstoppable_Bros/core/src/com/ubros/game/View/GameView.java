package com.ubros.game.View;

import com.badlogic.gdx.ScreenAdapter;
import com.ubros.game.UbrosGame;

public class GameView extends ScreenAdapter {

    /**
     * The game this screen belongs to.
     */
    private final UbrosGame game;

    /**
     * Creates this screen.
     *
     * @param game The game this screen belongs to
     */
    public GameView(UbrosGame game) {
        this.game = game;

        //loadAssets();

        //camera = createCamera();
    }

}
