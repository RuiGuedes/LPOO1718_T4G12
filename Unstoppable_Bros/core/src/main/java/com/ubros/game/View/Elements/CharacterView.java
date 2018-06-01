package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.UbrosGame;

public class CharacterView extends ElementView {

    /**
     * Character current view state
     */
    ElementView.CharacterState currentState;

    /**
     * Character previous view state
     */
    ElementView.CharacterState previousState;

    /**
     * Variable used to aid animation objects
     */
    float stateTimer;

    /**
     * Boolean that indicates which side is character facing
     */
    boolean runningRight;

    /**
     * Character last y velocity
     */
    float lastVelocityY;

    /**
     * Is character jumping (true) or not (false)
     */
    boolean jumping;

    /**
     * Boolean that indicates if character is moving horizontally
     */
    boolean horizontalMovement;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the asset manager to get textures.
     * @param atlas   the texture atlas that contains character animations
     * @param element the character body
     */
    CharacterView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        super(game, atlas, element);
    }

    /**
     * Sets horizontal movement with new value
     *
     * @param horizontalMovement true for horizontal movement, false otherwise
     */
    public void setHorizontalMovement(boolean horizontalMovement) {
        this.horizontalMovement = horizontalMovement;
    }

    /**
     * Check's if character is jumping or not
     *
     * @return true if it is, false otherwise
     */
    public boolean isJumping() {
        return jumping;
    }

    /**
     * Set's jumping new value
     *
     * @param jumping new boolean value
     */
    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    /**
     * Checks whether character is running right or not
     *
     * @return true if it is, false otherwise
     */
    public boolean isRunningRight() {
        return runningRight;
    }

    /**
     * Check's if character is jumping or not
     *
     * @return true if it is, false otherwise
     */
    boolean checkJumpState() {
        if ((getElement().getBody().getLinearVelocity().y < 0) && (previousState == ElementView.CharacterState.JUMPING))
            return true;
        else if ((lastVelocityY == 0) && (getElement().getBody().getLinearVelocity().y > 0) && jumping)
            return true;
        else
            return (getElement().getBody().getLinearVelocity().y > 0) && (previousState == CharacterState.JUMPING);
    }

    /**
     * Constantly updates character view variables
     *
     * @param region character associated texture
     * @param delta  time since last renders in seconds
     */
    void updateVariables(TextureRegion region, float delta) {

        if ((super.getElement().getBody().getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true, false);
            runningRight = false;
        } else if ((super.getElement().getBody().getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true, false);
            runningRight = true;
        }

        if (currentState == previousState)
            stateTimer += delta;
        else {
            stateTimer = 0;
        }

        previousState = currentState;

    }

    @Override
    public void draw(float delta) {
    }

    @Override
    public void setCurrentState(ElementView.CharacterState state) {
        this.currentState = state;
    }

    @Override
    public void update(float delta) {
    }
}
