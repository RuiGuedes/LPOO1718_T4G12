package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
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

    int CHARACTER_RADIUS = 34;

    /**
     * The Box2D body that supports this body.
     */
    private final Body body;

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

        if(model.getPhysicsType() == ElementModel.PhysicsType.STATIC)
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
     * @param x the new x-coordinate for this body
     * @param y the new y-coordinate for this body
     * @param angle the new rotation angle for this body
     */
    public void setTransform(float x, float y, float angle) {
        body.setTransform(x, y, angle);
    }

    /**
     * Sets the angular velocity of this object in the direction it is rotated.
     *
     * @param velocity the new linear velocity angle for this body
     */
    //public void setLinearVelocity(float velocity) {
      //  body.setLinearVelocity((float)(velocity * -Math.sin(getAngle())), (float) (velocity * Math.cos(getAngle())));
    //}

    /**
     * Wraps the setAngularVelocity method from the Box2D body class.
     *
     * @param omega the new angular velocity angle for this body
     */
    public void setAngularVelocity(float omega) {
        body.setAngularVelocity(omega);
    }

    /**
     * Wraps the applyForceToCenter method from the Box2D body class.
     *
     * @param forceX the x-component of the force to be applied
     * @param forceY the y-component of the force to be applied
     * @param awake should the body be awaken
     */
    public void applyForceToCenter(float forceX, float forceY, boolean awake) {
        body.applyForceToCenter(forceX, forceY, awake);
    }

    public Body getBody() {
        return body;
    }

    public ElementModel getModel() {
        return model;
    }

    /**
     * Wraps the getUserData method from the Box2D body class.
     *
     * @return the user data
     */
    public Object getUserData() {
        return body.getUserData();
    }
}
