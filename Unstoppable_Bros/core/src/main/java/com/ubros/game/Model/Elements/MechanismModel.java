package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.View.Elements.MechanismView;

public class MechanismModel extends ElementModel {

    /**
     * Mechanism body shape
     */
    private Polygon shape;

    /**
     * Boolean that represents whether mechanism is active or not
     */
    private boolean active;

    /**
     * Associated mechanism view
     */
    private MechanismView view;

    /**
     * Platform body associated to this mechanism
     */
    private PlatformBody platform;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     * @param shape    The mechanism body shape
     */
    public MechanismModel(float x, float y, float rotation, Polygon shape) {
        super(x, y, rotation);
        this.shape = shape;
        this.active = false;
    }

    /**
     * Get's mechanism body shape
     *
     * @return mechanism body shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * Checks if mechanism is active or not
     *
     * @return true if it is. Otherwise return false
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Changes mechanism activation status
     *
     * @param active new active status
     */
    public void setActive(boolean active) {
        platform.setLinearVelocity(this.active = active);
    }

    /**
     * Function responsible to retrieve mechanism view
     *
     * @return mechanism view
     */
    public MechanismView getView() {
        return view;
    }

    /**
     * Function responsible to set mechanism view
     *
     * @param view new view to be set
     */
    public void setView(MechanismView view) {
        this.view = view;
    }

    /**
     * Get's mechanism associated platform
     *
     * @return mechanism associated platform
     */
    public PlatformBody getPlatform() {
        return platform;
    }

    /**
     * Set's mechanism new associated platform
     *
     * @param platform mechanism new associated platform
     */
    public void setPlatform(PlatformBody platform) {
        this.platform = platform;
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
