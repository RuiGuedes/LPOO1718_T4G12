package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.UbrosGame;

public abstract class ElementView extends Sprite {

    /**
     * Possible character states
     */
    public enum CharacterState {
        FALLING, JUMPING, STANDING, WALKING, RUNNING, RUNNING_SHOOT, SHOOT, ATTACKING, DEAD
    }

    /**
     * Character character width
     */
    int CHARACTER_WIDTH = 48;

    /**
     * Character character height
     */
    int CHARACTER_HEIGHT = 70;

    /**
     * Game object
     */
    private UbrosGame game;

    /**
     * Contains character possible animations
     */
    private TextureAtlas atlas;

    /**
     * Element body
     */
    private ElementBody element;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the asset manager to get textures.
     * @param atlas   the texture atlas that contains character animations
     * @param element the view element body
     */
    ElementView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        this.game = game;
        this.atlas = atlas;
        this.element = element;
    }

    /**
     * Draws the sprite from this view using a sprite batch.
     */
    public abstract void draw(float delta);

    /**
     * Returns game object instance
     *
     * @return game object instance
     */
    public UbrosGame getGame() {
        return game;
    }

    /**
     * Returns texture atlas
     *
     * @return texture atlas associated to a certain view
     */
    public TextureAtlas getAtlas() {
        return atlas;
    }

    /**
     * Returns element body
     *
     * @return element body
     */
    public ElementBody getElement() {
        return element;
    }

    /**
     * Set's new state to a certain view
     *
     * @param state new state to be set
     */
    public abstract void setCurrentState(ElementView.CharacterState state);

    /**
     * Updates view
     *
     * @param delta time since last renders in seconds
     */
    public abstract void update(float delta);
}
