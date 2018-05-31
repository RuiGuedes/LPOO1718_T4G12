package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.UbrosGame;

public class NinjaView extends ElementView {

    /**
     * Robot current view state
     */
    private ElementView.CharacterState currentState;

    /**
     * Robot previous view state
     */
    private ElementView.CharacterState previousState;

    /**
     * Variable used to aid animation objects
     */
    private float stateTimer;

    /**
     * Boolean that indicates which side is robot facing
     */
    private boolean runningRight;

    /**
     * Robot default view
     */
    private TextureRegion ninjaDefault;

    /**
     * Robot running animation
     */
    private Animation<TextureRegion> ninjaRunning;

    /**
     * Robot jumping animation
     */
    private Animation<TextureRegion> ninjaJumping;

    /**
     * Robot dying animation
     */
    private Animation<TextureRegion> ninjaDying;

    /**
     * Robot last y velocity
     */
    private float lastVelocityY;

    /**
     * Is robot jumping (true) or not (false)
     */
    private boolean jumping;

    /**
     * Boolean that indicates if character is moving horizontally
     */
    private boolean horizontalMovement;

    /**
     * Creates a view belonging to a game.
     *
     * @param game  the game this view belongs to. Needed to access the asset manager to get textures.
     * @param atlas the texture atlas that contains robot animations
     */
    public NinjaView(UbrosGame game, TextureAtlas atlas) {
        super(game, atlas, GameController.getInstance(game).getNinja());

        this.currentState = this.previousState = ElementView.CharacterState.STANDING;
        this.stateTimer = 0;
        this.runningRight = true;
        this.horizontalMovement = false;

        createNinjaAnimations();

        ninjaDefault = new TextureRegion(atlas.findRegion("Idle (1)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        setBounds(0, 0, CHARACTER_WIDTH / PlayGameScreen.PIXEL_TO_METER, CHARACTER_HEIGHT / PlayGameScreen.PIXEL_TO_METER);
        setRegion(ninjaDefault);
    }

    /**
     * Creates all ninja possible animations
     */
    private void createNinjaAnimations() {
        createRunAnimation();
        createJumpAnimation();
        createDyingAnimation();
    }

    /**
     * Creates ninja running animation
     */
    private void createRunAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 10; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Run (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        ninjaRunning = new Animation<TextureRegion>(0.15f, frames);
        frames.clear();
    }

    /**
     * Creates ninja jumping animation
     */
    private void createJumpAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 10; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Jump (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        ninjaJumping = new Animation<TextureRegion>(0.08f, frames);
        frames.clear();
    }

    /**
     * Creates ninja dying animation
     */
    private void createDyingAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 10; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Dead (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        ninjaDying = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();
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
     * Check's if ninja is jumping or not
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
     * Function responsible to check ninja texture
     *
     * @param delta time since last renders in seconds
     * @return texture region associated to this view
     */
    private TextureRegion getFrame(float delta) {
        this.currentState = getState(super.getElement());

        TextureRegion region = ninjaDefault;

        switch (currentState) {
            case RUNNING:
                region = ninjaRunning.getKeyFrame(stateTimer, true);
                break;
            case JUMPING:
                region = ninjaJumping.getKeyFrame(stateTimer);
                break;
            case FALLING:
                region = new TextureRegion(getAtlas().findRegion("Jump (10)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);
                break;
            case DEAD:
                region = ninjaDying.getKeyFrame(stateTimer);
                if (ninjaDying.isAnimationFinished(stateTimer))
                    GameController.getInstance(getGame()).setState(GameController.GameStatus.GAMEOVER);
                break;
        }

        updateNinjaVariables(region, delta);

        return region;
    }

    /**
     * Constantly updates ninja view variables
     *
     * @param region ninja associated texture
     * @param delta  time since last renders in seconds
     */
    private void updateNinjaVariables(TextureRegion region, float delta) {

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

    /**
     * Get ninja current state
     *
     * @param element element that contains this view body
     * @return ninja current state
     */
    private ElementView.CharacterState getState(ElementBody element) {

        if (currentState == CharacterState.DEAD)
            return CharacterState.DEAD;
        else if (checkJumpState())
            return ElementView.CharacterState.JUMPING;
        else if (element.getBody().getLinearVelocity().y < 0)
            return ElementView.CharacterState.FALLING;
        else if ((element.getBody().getLinearVelocity().x != 0) && (horizontalMovement || !((CharacterModel) element.getModel()).isOnPlatform()))
            return ElementView.CharacterState.RUNNING;
        else
            return ElementView.CharacterState.STANDING;
    }

    /**
     * Check's if ninja is jumping or not
     * @return true if it is, false otherwise
     */
    private boolean checkJumpState() {
        if ((getElement().getBody().getLinearVelocity().y < 0) && (previousState == ElementView.CharacterState.JUMPING))
            return true;
        else if ((lastVelocityY == 0) && (getElement().getBody().getLinearVelocity().y > 0) && jumping)
            return true;
        else
            return (getElement().getBody().getLinearVelocity().y > 0) && (previousState == CharacterState.JUMPING);
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        super.draw(getGame().getBatch());
    }

    /**
     * Updates this view based on a certain model.
     */
    @Override
    public void update(float delta) {
        ElementBody element = super.getElement();

        if (currentState == ElementView.CharacterState.RUNNING) {
            if (runningRight)
                setPosition(element.getBody().getPosition().x - getWidth() / 2, element.getBody().getPosition().y - getHeight() / 2);
            else
                setPosition(element.getBody().getPosition().x - getWidth() / 2, element.getBody().getPosition().y - getHeight() / 2);
        } else {
            if (runningRight)
                setPosition(element.getBody().getPosition().x - getWidth() / 2, element.getBody().getPosition().y - getHeight() / 2);
            else
                setPosition(element.getBody().getPosition().x - getWidth() / 2, element.getBody().getPosition().y - getHeight() / 2);
        }
        setRotation(element.getAngle());
        setRegion(getFrame(delta));

        lastVelocityY = getElement().getBody().getLinearVelocity().y;

        if (getElement().getBody().getLinearVelocity().y == 0)
            jumping = false;
    }


    @Override
    public void setCurrentState(ElementView.CharacterState state) {
        this.currentState = state;
    }
}

