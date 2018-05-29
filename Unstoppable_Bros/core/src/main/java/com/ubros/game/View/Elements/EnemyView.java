package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Controller.Elements.EnemyBody;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.EnemyModel;
import com.ubros.game.UbrosGame;

public class EnemyView extends ElementView {

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
     * Boolean that indicates which side is enemy facing
     */
    private boolean walkingRight;

    /**
     * Enemy default view
     */
    private TextureRegion enemyDefault;

    /**
     * Enemy walking animation
     */
    private Animation<TextureRegion> enemyWalking;

    /**
     * Enemy attacking animation
     */
    private Animation<TextureRegion> enemyAttack;

    /**
     * Enemy dying animation
     */
    private Animation<TextureRegion> enemyDying;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the asset manager to get textures.
     * @param atlas   textures atlas that contains all enemy animations
     * @param element body associated to this view
     */
    public EnemyView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        super(game, atlas, element);

        this.currentState = this.previousState = ElementView.CharacterState.STANDING;
        this.stateTimer = 0;
        this.walkingRight = true;

        createWalkingAnimation();
        createAttackingAnimation();
        createDyingAnimation();

        enemyDefault = new TextureRegion(atlas.findRegion("Idle (1)"), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT);

        setBounds(0, 0, CHARACTER_WIDTH / PlayGameScreen.PIXEL_TO_METER, CHARACTER_HEIGHT / PlayGameScreen.PIXEL_TO_METER);
        setRegion(enemyDefault);
    }

    /**
     * Creates enemy walking animation
     */
    private void createWalkingAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 10; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Walk (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        enemyWalking = new Animation<TextureRegion>(0.1f, frames);
        frames.clear();
    }

    /**
     * Creates enemy attacking animation
     */
    private void createAttackingAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 8; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Attack (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        enemyAttack = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();
    }

    /**
     * Creates enemy attacking animation
     */
    private void createDyingAnimation() {
        Array<TextureRegion> frames = new Array<TextureRegion>();

        for (int i = 1; i <= 12; i++)
            frames.add(new TextureRegion(getAtlas().findRegion("Dead (" + i + ")").getTexture(), 0, 0, CHARACTER_WIDTH, CHARACTER_HEIGHT));

        enemyDying = new Animation<TextureRegion>(0.05f, frames);
        frames.clear();
    }

    /**
     * Function responsible to check enemy texture
     * @param delta time since last renders in seconds
     * @return texture region associated to this view
     */
    private TextureRegion getFrame(float delta) {

        this.currentState = getState(super.getElement());

        TextureRegion region = enemyDefault;

        switch (currentState) {
            case WALKING:
                region = enemyWalking.getKeyFrame(stateTimer, true);
                break;
            case ATTACKING:
                region = enemyAttack.getKeyFrame(stateTimer);
                if(enemyAttack.isAnimationFinished(stateTimer))
                    this.currentState = CharacterState.WALKING;
                break;
            case DEAD:
                region = enemyDying.getKeyFrame(stateTimer);
                if (enemyDying.isAnimationFinished(stateTimer)) {
                    GameController.getInstance(null).disablesBody(getElement().getBody());
                    ((EnemyModel)getElement().getModel()).setDead();
                }
                break;
        }

        updateEnemyVariables(region, delta);

        return region;
    }

    /**
     * Constantly updates enemy view variables
     * @param region enemy associated texture
     * @param delta time since last renders in seconds
     */
    private void updateEnemyVariables(TextureRegion region, float delta) {

        if ((getElement().getBody().getLinearVelocity().x < 0 || !walkingRight) && !region.isFlipX()) {
            region.flip(true, false);
            walkingRight = false;
        } else if ((super.getElement().getBody().getLinearVelocity().x > 0 || walkingRight) && region.isFlipX()) {
            region.flip(true, false);
            walkingRight = true;
        }

        if (currentState == previousState)
            stateTimer += delta;
        else {
            stateTimer = 0;
        }

        previousState = currentState;
    }

    /**
     * Get enemy current state
     *
     * @param element element that contains this view body
     * @return enemy current state
     */
    private ElementView.CharacterState getState(ElementBody element) {

        if (currentState == CharacterState.DEAD)
            return ElementView.CharacterState.DEAD;
        else if(currentState == CharacterState.ATTACKING)
            return CharacterState.ATTACKING;
        else if (element.getBody().getLinearVelocity().x != 0)
            return ElementView.CharacterState.WALKING;
        else
            return ElementView.CharacterState.STANDING;
    }

    /**
     * Updates enemy region position and rotation
     * @param delta time since last renders in seconds
     */
    private void updateEnemyView(float delta) {

        if (currentState == ElementView.CharacterState.RUNNING) {
            if (walkingRight)
                setPosition(getElement().getBody().getPosition().x - getWidth() / 2, getElement().getBody().getPosition().y - getHeight() / 2);
            else
                setPosition(getElement().getBody().getPosition().x - getWidth() / 2, getElement().getBody().getPosition().y - getHeight() / 2);
        } else {
            if (walkingRight)
                setPosition(getElement().getBody().getPosition().x - getWidth() / 2, getElement().getBody().getPosition().y - getHeight() / 2);
            else
                setPosition(getElement().getBody().getPosition().x - getWidth() / 2, getElement().getBody().getPosition().y - getHeight() / 2);
        }
        setRotation(getElement().getAngle());
        setRegion(getFrame(delta));

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
        float x = element.getX() - (CHARACTER_WIDTH/2) / PlayGameScreen.PIXEL_TO_METER;


        if (this.currentState == CharacterState.DEAD)
            element.getBody().setLinearVelocity(0, 0);
        else
            ((EnemyBody)element).updateEnemyPosition(x);


        updateEnemyView(delta);
    }


    @Override
    public void setCurrentState(ElementView.CharacterState state) {
        this.currentState = state;
    }

}
