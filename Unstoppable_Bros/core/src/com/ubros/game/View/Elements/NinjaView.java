package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Gui.PlayGameScreen;
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
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    public NinjaView(UbrosGame game, TextureAtlas atlas) {
        super(game, atlas, GameController.getInstance(game).getNinja());

        this.currentState = this.previousState = ElementView.CharacterState.STANDING;
        this.stateTimer = 0;
        this.runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 10; i++)
            frames.add(new TextureRegion(atlas.findRegion("Run (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        ninjaRunning = new Animation<TextureRegion>(0.15f, frames);
        frames.clear();

        for (int i = 1; i <= 10; i++)
            frames.add(new TextureRegion(atlas.findRegion("Jump (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        ninjaJumping = new Animation<TextureRegion>(0.08f, frames);
        frames.clear();

        for (int i = 1; i <= 10; i++)
            frames.add(new TextureRegion(atlas.findRegion("Dead (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        ninjaDying = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();

        ninjaDefault = new TextureRegion(atlas.findRegion("Idle (1)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        setBounds(0, 0, CHARACTER_WIDTH / PlayGameScreen.PIXEL_TO_METER, CHARACTER_HEIGHT / PlayGameScreen.PIXEL_TO_METER);

        setRegion(ninjaDefault);
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        //System.out.println("VIEW --- " +  super.getElement().getBody().getPosition().x + " ------ " + super.getElement().getBody().getPosition().y);
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

    public TextureRegion getFrame(float delta) {
        this.currentState = getState(super.getElement());
        TextureRegion region = null;

        switch (currentState) {
            case RUNNING:
                region = ninjaRunning.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                region = ninjaDefault;
                break;
            case JUMPING:
                region = ninjaJumping.getKeyFrame(stateTimer);
                break;
            case FALLING:
                region = new TextureRegion(getAtlas().findRegion("Jump (10)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);
                break;
            case DEAD:
                region = ninjaDying.getKeyFrame(stateTimer);
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

    public ElementView.CharacterState getState(ElementBody element) {

        if (currentState == CharacterState.DEAD)
            return CharacterState.DEAD;
        else if (element.getBody().getLinearVelocity().y > 0 || element.getBody().getLinearVelocity().y < 0 && previousState == ElementView.CharacterState.JUMPING)
            return ElementView.CharacterState.JUMPING;
        else if (element.getBody().getLinearVelocity().y < 0)
            return ElementView.CharacterState.FALLING;
        else if (element.getBody().getLinearVelocity().x != 0)
            return ElementView.CharacterState.RUNNING;
        else
            return ElementView.CharacterState.STANDING;
    }

    @Override
    public void setCurrentState(ElementView.CharacterState state) {
        this.currentState = state;
    }
}

