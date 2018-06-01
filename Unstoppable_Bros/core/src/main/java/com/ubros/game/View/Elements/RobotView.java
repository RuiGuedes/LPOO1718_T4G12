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

public class RobotView extends CharacterView {

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
     * Robot jumping animation
     */
    private Animation<TextureRegion> robotRunShooting;

    /**
     * Robot jumping animation
     */
    private Animation<TextureRegion> robotShooting;

    /**
     * Robot dying animation
     */
    private Animation<TextureRegion> robotDying;

    /**
     * Robot last x position
     */
    private float lastX;

    /**
     * Is robot shooting (true) or not (false)
     */
    private boolean shoot;

    /**
     * Creates a view belonging to a game.
     *
     * @param game  the game this view belongs to. Needed to access the asset manager to get textures.
     * @param atlas the texture atlas that contains robot animations
     */
    public RobotView(UbrosGame game, TextureAtlas atlas) {
        super(game, atlas, GameController.getInstance(game).getRobot());

        this.currentState = this.previousState = ElementView.CharacterState.STANDING;
        this.stateTimer = 0;
        this.runningRight = true;
        this.horizontalMovement = this.jumping = this.shoot = false;

        createRobotAnimations();

        robotDefault = new TextureRegion(atlas.findRegion("Idle (1)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        setBounds(0, 0, CHARACTER_WIDTH / PlayGameScreen.PIXEL_TO_METER, CHARACTER_HEIGHT / PlayGameScreen.PIXEL_TO_METER);
        setRegion(robotDefault);
    }

    /**
     * Creates all robot possible animations
     */
    private void createRobotAnimations() {
        createRunningAnimation();
        createJumpingAnimation();
        createDyingAnimation();
        createRunShootAnimation();
        createShootAnimation();
    }

    /**
     * Creates robot running animation
     */
    private void createRunningAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 8; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Run (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        robotRunning = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
    }

    /**
     * Creates robot jumping animation
     */
    private void createJumpingAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 9; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Jump (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        robotJumping = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();
    }

    /**
     * Creates robot run shooting animation
     */
    private void createRunShootAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 9; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("RunShoot (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        robotRunShooting = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
    }

    /**
     * Creates robot shooting animation
     */
    private void createShootAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 4; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Shoot (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        robotShooting = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
    }

    /**
     * Creates robot dying animation
     */
    private void createDyingAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 7; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Dead (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        robotDying = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();
    }

    /**
     * Set's shoot new value
     *
     * @param shoot shoot new value
     */
    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    /**
     * Function responsible to check robot texture
     *
     * @param delta time since last renders in seconds
     * @return texture region associated to this view
     */
    private TextureRegion getFrame(float delta) {
        this.currentState = getState(super.getElement());

        TextureRegion region = robotDefault;

        switch (currentState) {
            case RUNNING:
                region = robotRunning.getKeyFrame(stateTimer, true);
                break;
            case JUMPING:
                region = robotJumping.getKeyFrame(stateTimer);
                break;
            case FALLING:
                region = new TextureRegion(getAtlas().findRegion("Jump (10)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);
                break;
            case DEAD:
                region = robotDying.getKeyFrame(stateTimer);
                if (robotDying.isAnimationFinished(stateTimer))
                    GameController.getInstance(getGame()).setState(GameController.GameStatus.GAMEOVER);
                break;
            case SHOOT:
                region = robotShooting.getKeyFrame(stateTimer);
                break;
            case RUNNING_SHOOT:
                region = robotRunShooting.getKeyFrame(stateTimer, true);
                break;
        }

        updateVariables(region, delta);

        return region;
    }

    /**
     * Get robot current state
     *
     * @param element element that contains this view body
     * @return robot current state
     */
    private ElementView.CharacterState getState(ElementBody element) {

        float diffX = Math.abs(lastX - getElement().getX());

        if (currentState == CharacterState.DEAD)
            return CharacterState.DEAD;
        else if (shoot && (diffX <= 0.001f))
            return CharacterState.SHOOT;

        if (diffX >= 0.001f) {
            if (checkJumpState())
                return CharacterState.JUMPING;
            else if (element.getBody().getLinearVelocity().y < 0)
                return ElementView.CharacterState.FALLING;
            else if (element.getBody().getLinearVelocity().x != 0 && (horizontalMovement || !((CharacterModel) element.getModel()).isOnPlatform()) && shoot)
                return CharacterState.RUNNING_SHOOT;
            else if (element.getBody().getLinearVelocity().x != 0 && (horizontalMovement || !((CharacterModel) element.getModel()).isOnPlatform()))
                return ElementView.CharacterState.RUNNING;
        }

        return ElementView.CharacterState.STANDING;
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        super.draw(getGame().getBatch());
    }

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

        lastX = getElement().getX();
        lastVelocityY = getElement().getBody().getLinearVelocity().y;

        if (getElement().getBody().getLinearVelocity().y == 0)
            jumping = false;
    }
}
