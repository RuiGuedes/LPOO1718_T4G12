package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;

public abstract class ElementBody {

    /**
     * Character character width
     */
    int CHARACTER_WIDTH = 48;

    /**
     * Character character height
     */
    int CHARACTER_HEIGHT = 70;

    /**
     * Character object radius
     */
    int CHARACTER_RADIUS = (Math.max(CHARACTER_WIDTH / 2, CHARACTER_HEIGHT / 2) - 1);

    /**
     * Tiled map associated width
     */
    final float TILE_WIDTH = 32 / PlayGameScreen.PIXEL_TO_METER;

    /**
     * The Box2D body that supports this body.
     */
    private final Body body;

    /**
     * Model associated to the respective model
     */
    private final ElementModel model;

    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     */
    public ElementBody(World world, ElementModel model) {

        this.model = model;
        BodyDef bodyDef = new BodyDef();

        if (model.getPhysicsType() == ElementModel.PhysicsType.STATIC)
            bodyDef.type = BodyDef.BodyType.StaticBody;
        else if (model.getPhysicsType() == ElementModel.PhysicsType.DYNAMIC)
            bodyDef.type = BodyDef.BodyType.DynamicBody;
        else
            bodyDef.type = BodyDef.BodyType.KinematicBody;

        bodyDef.position.set(model.getX(), model.getY());
        bodyDef.angle = model.getRotation();

        body = world.createBody(bodyDef);
        body.setUserData(model);
    }

    /**
     * Creates fixture and associates them to a certain body
     *
     * @param body        The body that fixture will be attached
     * @param vertexes    The body shape vertexes
     * @param width       The body shape width
     * @param height      The body shape height
     * @param density     The body density
     * @param friction    The body friction
     * @param restitution The body restitution
     * @param category    The body category
     * @param mask        The body mask
     */
    public abstract void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask);

    /**
     * Wraps the getX method from the Box2D body class.
     *
     * @return the x-coordinate of this body.
     */
    public float getX() {
        return body.getPosition().x;
    }

    /**
     * Wraps the getY method from the Box2D body class.
     *
     * @return the y-coordinate of this body.
     */
    public float getY() {
        return body.getPosition().y;
    }

    /**
     * Wraps the getAngle method from the Box2D body class.
     *
     * @return the angle of rotation of this body.
     */
    public float getAngle() {
        return body.getAngle();
    }

    /**
     * Wraps the setTransform method from the Box2D body class.
     *
     * @param x     the new x-coordinate for this body
     * @param y     the new y-coordinate for this body
     * @param angle the new rotation angle for this body
     */
    public void setTransform(float x, float y, float angle) {
        body.setTransform(x, y, angle);
    }

    /**
     * Returns the world belonging body
     * @return world belonging body
     */
    public Body getBody() {
        return body;
    }

    /**
     * Get's the model associated to this body
     * @return model associated to this body
     */
    public ElementModel getModel() {
        return model;
    }
}
