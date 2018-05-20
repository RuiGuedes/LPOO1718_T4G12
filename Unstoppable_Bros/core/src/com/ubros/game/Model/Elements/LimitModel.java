package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;

public class LimitModel extends ElementModel {

    /**
     *  Limit body shape
     */
    private Polygon shape;

    /**
     *
     * @param x
     * @param y
     * @param rotation
     * @param shape
     */
    public LimitModel(float x, float y, float rotation, Polygon shape) {
        super(x, y, rotation);
        this.shape = shape;
    }

    public Polygon getShape() {
        return shape;
    }

    @Override
    public ModelType getType() {
        return ModelType.LIMIT;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.STATIC;
    }
}
