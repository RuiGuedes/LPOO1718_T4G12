package com.ubros.game.Model.Elements;

public abstract class ElementModel {

    /**
     * Game model possible model types
     */
    public enum ModelType {LIMIT, HERO, ACID, MECHANISM, PLATFORM, OBJECTIVE, PORTAL, OBJECT, EXITDOOR, BULLET, ENEMY}

    /**
     * Game possible physics type
     */
    public enum PhysicsType {STATIC, DYNAMIC, KINEMATIC}

    /**
     * Tilemap used tile width
     */
    public final static float TILE_WIDTH = 32;

    /**
     * Tilemap used tile height
     */
    public final static float TILE_HEIGHT = 30;

    /**
     * Character body radius
     */
    int CHARACTER_RADIUS = 34;

    /**
     * The x-coordinate of this model in meters.
     */
    private float x;

    /**
     * The y-coordinate of this model in meters.
     */
    private float y;

    /**
     * The current rotation of this model in radians.
     */
    private float rotation;

    /**
     * Has this model been flagged for removal?
     */
    private boolean flaggedForRemoval = false;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public ElementModel(float x, float y, float rotation) {
        this.x = x;
        this.y = y;
        this.rotation = rotation;
    }

    /**
     * Returns the x-coordinate of this entity.
     *
     * @return The x-coordinate of this entity in meters.
     */
    public float getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of this entity.
     *
     * @return The y-coordinate of this entity in meters.
     */
    public float getY() {
        return y;
    }

    /**
     * Returns the rotation of this entity.
     *
     * @return The rotation of this entity in radians.
     */
    public float getRotation() {
        return rotation;
    }

    /**
     * Sets the position of this entity.
     *
     * @param x The x-coordinate of this entity in meters.
     * @param y The y-coordinate of this entity in meters.
     */
    void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the rotation of this entity.
     *
     * @param rotation The current rotation of this entity in radians.
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    /**
     * Returns if this entity has been flagged for removal
     *
     * @return true if it needs to be removed, false otherwise
     */
    public boolean isFlaggedToBeRemoved() {
        return flaggedForRemoval;
    }

    /**
     * Makes this model flagged for removal on next step
     */
    public void setFlaggedForRemoval(boolean flaggedForRemoval) {
        this.flaggedForRemoval = flaggedForRemoval;
    }

    /**
     * Abstract function responsible to return model type
     * @return model type
     */
    public abstract ModelType getType();

    /**
     * Abstract function responsible to return model physics type
     * @return model physics type
     */
    public abstract PhysicsType getPhysicsType();

}
