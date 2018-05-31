package com.ubros.game.Model.Elements;

import com.ubros.game.View.Elements.BulletView;

public class BulletModel extends ElementModel {

    /**
     * Bullet associated view
     */
    private BulletView view;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public BulletModel(float x , float y, float rotation) {
        super(x, y, rotation);
    }

    /**
     * Get's bullet view
     * @return bullet view
     */
    public BulletView getView() {
        return view;
    }

    /**
     * Set's bullet new view
     * @param view new bullet view
     */
    public void setView(BulletView view) {
        this.view = view;
    }

    @Override
    public ModelType getType() {
        return ModelType.BULLET;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.DYNAMIC;
    }
}
