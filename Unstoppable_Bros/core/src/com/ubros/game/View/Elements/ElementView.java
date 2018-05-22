package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.UbrosGame;

public abstract class ElementView extends Sprite {

    public enum State {FALLING, JUMPING, STANDING, RUNNING}

    public  State currentState;
    public  State previousState;

    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> heroRun;
    private com.badlogic.gdx.graphics.g2d.Animation<TextureRegion> heroJump;
    private float stateTimer;
    private boolean runningRight;

    /**
     * The sprite representing this entity view.
     */
    Sprite sprite;

    TextureRegion heroUP;

    TextureAtlas atlas;

    private int HERO_WIDTH = 80;

    private int HERO_HEIGHT = 80;
    /**
     * Creates a view belonging to a game.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     */
    ElementView(UbrosGame game, TextureAtlas atlas) {

        this.atlas = atlas;
        currentState = State.STANDING;
        previousState = State.STANDING;
        stateTimer = 0;
        runningRight = true;

        Array<TextureRegion> frames = new Array<TextureRegion>();

        for(int i = 1; i <= 8; i++)
            frames.add(new TextureRegion(atlas.findRegion("Run (" + i + ")").getTexture(), 0, 0, HERO_WIDTH, HERO_HEIGHT));

        heroRun = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(0.1f,frames);
        frames.clear();

        for(int i = 1; i <= 9; i++)
           frames.add(new TextureRegion(atlas.findRegion("Jump (" + i + ")").getTexture(), 0,0,HERO_WIDTH,HERO_HEIGHT));

        heroJump = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(0.05f,frames);
        frames.clear();

        heroUP = new TextureRegion(atlas.findRegion("Idle (1)"), 0,0, HERO_WIDTH,HERO_HEIGHT);

        setBounds(0,0,HERO_WIDTH/ PlayGameScreen.PIXEL_TO_METER,HERO_HEIGHT/PlayGameScreen.PIXEL_TO_METER);

        setRegion(heroUP);

    }

    /**
     * Draws the sprite from this view using a sprite batch.
     *
     * @param batch The sprite batch to be used for drawing.
     */
    public void draw(Batch batch) {
        super.draw(batch);

    }

    /**
     * Abstract method that creates the view sprite. Concrete
     * implementation should extend this method to create their
     * own sprites.
     *
     * @param game the game this view belongs to. Needed to access the
     *             asset manager to get textures.
     * @return the sprite representing this view.
     */
    public abstract Sprite createSprite(UbrosGame game);

    /**
     * Updates this view based on a certain model.
     *
     * @param body the model used to update this view
     */
    public void update(float delta, ElementBody body) {
        //setPosition(body.body.getPosition().x - getWidth()/3, body.body.getPosition().y - getHeight()/5);
        if(currentState == State.RUNNING) {
            if (runningRight)
                setPosition(body.body.getPosition().x - getWidth() / 2, body.body.getPosition().y);
            else
                setPosition(body.body.getPosition().x - getWidth() / 2, body.body.getPosition().y - getHeight() / 2);
        }
        else {
            if (runningRight)
                setPosition(body.body.getPosition().x - getWidth() / 2, body.body.getPosition().y - getHeight() / 2);
            else
                setPosition(body.body.getPosition().x - getWidth() / 2, body.body.getPosition().y - getHeight() / 2);
        }
        setRotation(body.getAngle());
        setRegion(getFrame(delta, body));
    }

    public TextureRegion getFrame(float delta, ElementBody body) {

        currentState = getState(body);

        System.out.println(currentState);

        TextureRegion region = null;
        switch (currentState) {
            case RUNNING:
                System.out.println("RUNNING : " + stateTimer);
                region = heroRun.getKeyFrame(stateTimer, true);
                break;
            case STANDING:
                region = heroUP;
                break;
            case JUMPING:
                region = heroJump.getKeyFrame(stateTimer);
                break;
            case FALLING:
                region = heroUP;
                break;
        }

        if((body.body.getLinearVelocity().x < 0 || !runningRight) && !region.isFlipX()) {
            region.flip(true,false);
            runningRight = false;
        }
        else if((body.body.getLinearVelocity().x > 0 || runningRight) && region.isFlipX()) {
            region.flip(true,false);
            runningRight = true;
        }

        if(currentState == previousState)
            stateTimer += delta;
        else {
            stateTimer = 0;
        }

        previousState = currentState;

        return region;
    }

    public State getState(ElementBody body) {
        if(body.body.getLinearVelocity().y > 0 || body.body.getLinearVelocity().y < 0 && previousState == State.JUMPING)
            return State.JUMPING;
        else if(body.body.getLinearVelocity().y < 0)
            return State.FALLING;
        else if(body.body.getLinearVelocity().x != 0)
            return State.RUNNING;
        else
            return State.STANDING;
    }
}
