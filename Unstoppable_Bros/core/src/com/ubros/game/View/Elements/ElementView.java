package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.UbrosGame;

public abstract class ElementView extends Sprite {

    /**
     * Possible character states
     */
    public enum CharacterState {FALLING, JUMPING, STANDING, RUNNING, RUNNING_SHOOT, SHOOT}

    /**
     * Character character width
     */
    public int CHARACTER_WIDTH = 48;

    /**
     * Character character height
     */
    public int CHARACTER_HEIGHT = 70;

    private UbrosGame game;

    private TextureAtlas atlas;

    private ElementBody element;

    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    ElementView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        this.game = game;
        this.atlas = atlas;
        this.element = element;
    }

    /**
     * Draws the sprite from this view using a sprite batch.
     *
     */
    public void draw() {
        super.draw(game.getBatch());
    }

    public TextureAtlas getAtlas() {
        return atlas;
    }

    public ElementBody getElement() {
        return element;
    }

    public abstract void update(float delta);
}
