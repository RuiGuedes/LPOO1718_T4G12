package com.ubros.game.Model.Elements;

import com.ubros.game.View.Elements.ElementView;

public class CharacterModel extends ElementModel {

    private ElementView elementView;

    private boolean onPlatform;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public CharacterModel(float x, float y, float rotation) {
        super(x, y, rotation);
        this.onPlatform = false;
    }

    public boolean isOnPlatform() {
        return onPlatform;
    }

    public void setOnPlatform(boolean onPlatform) {
        this.onPlatform = onPlatform;
    }

    public ElementView getElementView() {
        return elementView;
    }

    public void setElementView(ElementView elementView) {
        this.elementView = elementView;
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
