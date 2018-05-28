package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.UbrosGame;

public class ObjectView extends ElementView {


    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the
     *                asset manager to get textures.
     * @param atlas
     * @param element
     */
    ObjectView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        super(game, atlas, element);
    }

    @Override
    public void draw(float delta) {

    }

    @Override
    public void setCurrentState(CharacterState state) {

    }

    @Override
    public void update(float delta) {

    }
}
