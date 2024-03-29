package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;

public class BulletBody extends ElementBody {

    /**
     * Bullet width
     */
    private final float BULLET_WIDTH = (ElementModel.TILE_WIDTH * 0.4f) / PlayGameScreen.PIXEL_TO_METER;

    /**
     * Bullet height
     */
    private final float BULLET_HEIGHT = (ElementModel.TILE_HEIGHT * 0.2f) / PlayGameScreen.PIXEL_TO_METER;

    /**
     * Bullet speed
     */
    private final float BULLET_SPEED = 15f;

    /**
     * Indicates whether direction is to left (false) or to right (true)
     */
    private float multiplier;

    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world     The world this body lives on.
     * @param model     The model representing the body.
     * @param direction Bullet direction
     */
    public BulletBody(World world, ElementModel model, boolean direction) {
        super(world, model);
        multiplier = direction ? 1 : -1;
        createFixture(getBody(), null, 0, 0, 0f, 0f, 0f, (short) 0, (short) 0);
    }

    @Override
    public void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        shape.setAsBox(BULLET_WIDTH, BULLET_HEIGHT);
        fdef.shape = shape;

        body.setGravityScale(0);
        body.createFixture(fdef).setUserData("Bullet");
        body.setLinearVelocity(multiplier * BULLET_SPEED, 0);

    }
}
