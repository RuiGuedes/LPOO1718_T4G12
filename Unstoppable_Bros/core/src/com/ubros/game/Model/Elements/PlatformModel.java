package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.View.Elements.PlatformView;

public class PlatformModel extends ElementModel {

    /**
     * Mechanism body shape
     */
    private Polygon shape;

    /**
     * Associated mechanism view
     */
    private PlatformView view;

    public float originX;

    public float originY;

    public float destinyX;

    public float destinyY;

    public boolean movementDir;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public PlatformModel(float x, float y, float rotation, Polygon shape) {
        super(x, y, rotation);
        this.shape = shape;
    }

    /**
     * Function responsible to retrieve mechanism view
     *
     * @return mechanism view
     */
    public PlatformView getView() {
        return view;
    }

    /**
     * Function responsible to set mechanism view
     */
    public void setView(PlatformView view) {
        this.view = view;
    }

    public Polygon getShape() {
        return shape;
    }

    @Override
    public ModelType getType() {
        return ModelType.PLATFORM;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.KINEMATIC;
    }
}
