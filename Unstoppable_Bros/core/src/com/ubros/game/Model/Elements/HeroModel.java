package com.ubros.game.Model.Elements;

public class HeroModel extends ElementModel {

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public HeroModel(float x, float y, float rotation) {
        super(x, y, rotation);
    }

    @Override
    public ModelType getType() {
        return ModelType.HERO;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.DYNAMIC;
    }
}
