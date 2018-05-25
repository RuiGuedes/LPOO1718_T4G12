package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.View.Elements.ObjectiveView;

import java.util.StringTokenizer;

public class ObjectiveModel extends ElementModel {

    /**
     * Mechanism body shape
     */
    private Polygon shape;

    /**
     *
     */
    private ObjectiveView view;

    private String userData;

    private String objectiveView;

    private float objectiveWidth;

    private float objectiveHeight;

    private boolean catched;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public ObjectiveModel(float x, float y, float rotation, Polygon shape, String userData) {
        super(x, y, rotation);
        this.shape = shape;
        this.catched = false;
        initializeObjectiveData(userData);
    }

    private void initializeObjectiveData(String data) {

        StringTokenizer tokenizer = new StringTokenizer(data, "-");

        this.userData = tokenizer.nextToken();
        this.objectiveWidth = (Integer.parseInt(tokenizer.nextToken())*TILE_WIDTH)/PlayGameScreen.PIXEL_TO_METER;
        this.objectiveHeight = (Integer.parseInt(tokenizer.nextToken())*TILE_HEIGHT)/PlayGameScreen.PIXEL_TO_METER;
        this.objectiveView = tokenizer.nextToken();
    }

    public String getData() {
        return userData;
    }

    public String getObjectiveView() {
        return objectiveView;
    }

    public ObjectiveView getView() {
        return view;
    }

    public void setView(ObjectiveView view) {
        this.view = view;
    }

    public float getObjectiveWidth() {
        return objectiveWidth;
    }

    public float getObjectiveHeight() {
        return objectiveHeight;
    }

    public boolean isCatched() {
        return catched;
    }

    public void setCatched() {
        this.catched = true;
    }

    public Polygon getShape() {
        return shape;
    }

    @Override
    public ModelType getType() {
        return null;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return null;
    }
}
