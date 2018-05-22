package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.UbrosGame;

public class HeroView extends ElementView {

    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public HeroView(UbrosGame game, TextureAtlas atlas) {
        super(game,atlas);
    }

    @Override
    public Sprite createSprite(UbrosGame game) {
        return null;
    }
}
