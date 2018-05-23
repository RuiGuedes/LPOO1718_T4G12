package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;

public class MechanismModel extends ElementModel {

    /**
     *  Mechanism body shape
     */
    private Polygon shape;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public MechanismModel(float x, float y, float rotation, Polygon shape) {
        super(x, y, rotation);
        this.shape = shape;
    }

    public Polygon getShape() {
        return shape;
    }

    @Override
    public ModelType getType() {
        return ModelType.MECHANISM;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.STATIC;
    }
}
