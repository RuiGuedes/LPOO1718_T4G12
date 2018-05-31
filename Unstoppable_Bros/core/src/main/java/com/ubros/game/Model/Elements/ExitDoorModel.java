package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.View.Elements.ExitDoorView;

public class ExitDoorModel extends ElementModel {

    /**
     * Exit door body shape
     */
    private Polygon shape;

    /**
     * If respective character is in contact with exit door (true) or not (false)
     */
    private boolean characterContact;

    /**
     * Exit door associated view
     */
    private ExitDoorView view;

    /**
     * Compressed information about exit door
     */
    private String data;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     * @param shape    Exit door shape
     * @param data     Information about exit door
     */
    public ExitDoorModel(float x, float y, float rotation, Polygon shape, String data) {
        super(x, y, rotation);
        this.shape = shape;
        this.data = data;
        this.characterContact = false;
    }

    /**
     * Get's exit door body shape
     * @return exit door body shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * Verifies if character is or is not in contact with exit door
     * @return true if it is, false otherwise
     */
    public boolean isCharacterContact() {
        return characterContact;
    }

    /**
     * Set's character with new value
     * @param characterContact new value to be set
     */
    public void setCharacterContact(boolean characterContact) {
        this.characterContact = characterContact;
    }

    /**
     * Get's exit door associated view
     * @return exit door associated view
     */
    public ExitDoorView getView() {
        return view;
    }

    /**
     * Set's exit door new view
     * @param view new view to be set
     */
    public void setView(ExitDoorView view) {
        this.view = view;
    }

    /**
     * Body user data to be check on possible collisions
     * @return body user data
     */
    public String getData() {
        return data;
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
