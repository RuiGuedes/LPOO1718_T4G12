package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.View.Elements.ExitDoorView;

public class ExitDoorModel extends ElementModel {

    private Polygon shape;

    private boolean characterContact;

    private ExitDoorView view;

    private String data;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public ExitDoorModel(float x, float y, float rotation, Polygon shape, String data) {
        super(x, y, rotation);
        this.shape = shape;
        this.data = data;
        this.characterContact = false;
    }

    public boolean isCharacterContact() {
        return characterContact;
    }

    public void setCharacterContact(boolean characterContact) {
        this.characterContact = characterContact;
    }

    public ExitDoorView getView() {
        return view;
    }

    public void setView(ExitDoorView view) {
        this.view = view;
    }

    public String getData() {
        return data;
    }

    public Polygon getShape() {
        return shape;
    }

    @Override
    public ModelType getType() {
        return ModelType.EXITDOOR;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.STATIC;
    }
}
