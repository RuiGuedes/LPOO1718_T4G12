package com.ubros.game.View;

import com.badlogic.gdx.ScreenAdapter;
import com.ubros.game.UbrosGame;

public class GameView extends ScreenAdapter {

    /**
     * How much meters does a pixel represent.
     */
    public final static float PIXEL_TO_METER= 0.04f;

    private UbrosGame game;

    public GameView(UbrosGame game) {
        this.game = game;

        loadAssets();
    }

    private void loadAssets() {

    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }



}
