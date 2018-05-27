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

public class RobotView extends ElementView {

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
    private TextureRegion robotDefault;

    /**
     * Robot running animation
     */
    private Animation<TextureRegion> robotRunning;

    /**
     * Robot jumping animation
     */
    private Animation<TextureRegion> robotJumping;

    /**
     * Robot dying animation
     */
    private Animation<TextureRegion> robotDying;

    /**
     * Boolean that indicates if character is moving horizontally
     */
    private boolean horizontalMovement;

    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public RobotView(UbrosGame game, TextureAtlas atlas) {
        super(game, atlas, GameController.getInstance(game).getRobot());

        this.currentState = this.previousState = ElementView.CharacterState.STANDING;
        this.stateTimer = 0;
        this.runningRight = true;
        this.horizontalMovement = false;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 8; i++)
            frames.add(new TextureRegion(atlas.findRegion("Run (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        robotRunning = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();

        for (int i = 1; i <= 9; i++)
            frames.add(new TextureRegion(atlas.findRegion("Jump (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        robotJumping = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();

        for (int i = 1; i <= 7; i++)
            frames.add(new TextureRegion(atlas.findRegion("Dead (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        robotDying = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();

        robotDefault = new TextureRegion(atlas.findRegion("Idle (1)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        setBounds(0, 0, CHARACTER_WIDTH / PlayGameScreen.PIXEL_TO_METER, CHARACTER_HEIGHT / PlayGameScreen.PIXEL_TO_METER);

        setRegion(robotDefault);
    }

    public boolean isRunningRight() {
        return runningRight;
    }

    public void setHorizontalMovement(boolean horizontalMovement) {
        this.horizontalMovement = horizontalMovement;
    }

    private TextureRegion getFrame(float delta) {
        this.currentState = getState(super.getElement());

        TextureRegion region = null;

        switch (currentState) {
            case RUNNING:
                region = robotRunning.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                region = robotDefault;
                break;
            case JUMPING:
                region = robotJumping.getKeyFrame(stateTimer);
                break;
            case FALLING:
                region = new TextureRegion(getAtlas().findRegion("Jump (10)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);
                break;
            case DEAD:
                region = robotDying.getKeyFrame(stateTimer);
                break;
        }


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

        return region;
    }

    private ElementView.CharacterState getState(ElementBody element) {

        if (currentState == CharacterState.DEAD)
            return CharacterState.DEAD;
        else if ((element.getBody().getLinearVelocity().y > 0 || element.getBody().getLinearVelocity().y < 0 && previousState == ElementView.CharacterState.JUMPING) && (element.getBody().getLinearVelocity().y == 0))
            return ElementView.CharacterState.JUMPING;
        else if (element.getBody().getLinearVelocity().y < 0)
            return ElementView.CharacterState.FALLING;
        else if (element.getBody().getLinearVelocity().x != 0 && (horizontalMovement || !((CharacterModel) element.getModel()).isOnPlatform()))
            return ElementView.CharacterState.RUNNING;
        else
            return ElementView.CharacterState.STANDING;
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
    }


    @Override
    public void setCurrentState(ElementView.CharacterState state) {
        this.currentState = state;
    }
}
