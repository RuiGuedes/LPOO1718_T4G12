package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.View.Elements.ObjectiveView;

import java.util.StringTokenizer;

public class ObjectiveModel extends ElementModel {

    /**
     * Objective body shape
     */
    private Polygon shape;

    /**
     * Objective associated view
     */
    private ObjectiveView view;

    /**
     * Information about objective object
     */
    private String userData;

    /**
     * Objective texture filename
     */
    private String objectiveView;

    /**
     * Objective texture width
     */
    private float objectiveWidth;

    /**
     * Objective texture height
     */
    private float objectiveHeight;

    /**
     * Hold information about objective state: True for catched. False otherwise
     */
    private boolean catched;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     * @param shape    The objective shape
     * @param userData Information about the objective
     */
    public ObjectiveModel(float x, float y, float rotation, Polygon shape, String userData) {
        super(x, y, rotation);
        this.shape = shape;
        this.catched = false;
        initializeObjectiveData(userData);
    }

    /**
     * Initializes objective information
     * @param data information about the objective
     */
    private void initializeObjectiveData(String data) {

        StringTokenizer tokenizer = new StringTokenizer(data, "-");

        this.userData = tokenizer.nextToken();
        this.objectiveWidth = (Integer.parseInt(tokenizer.nextToken())*TILE_WIDTH)/PlayGameScreen.PIXEL_TO_METER;
        this.objectiveHeight = (Integer.parseInt(tokenizer.nextToken())*TILE_HEIGHT)/PlayGameScreen.PIXEL_TO_METER;
        this.objectiveView = tokenizer.nextToken();
    }

    /**
     * Get's objective shape
     * @return objective shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * Get's objective view
     * @return objective view
     */
    public ObjectiveView getView() {
        return view;
    }

    /**
     * Set's objective new view
     * @param view objective new view
     */
    public void setView(ObjectiveView view) {
        this.view = view;
    }

    /**
     * Get's objective userData to check collisions
     * @return objective userData
     */
    public String getData() {
        return userData;
    }

    /**
     * Get's objective texture name
     * @return objective texture name
     */
    public String getObjectiveView() {
        return objectiveView;
    }

    /**
     * Get's objective texture width
     * @return objective texture width
     */
    public float getObjectiveWidth() {
        return objectiveWidth;
    }

    /**
     * Get's objective texture height
     * @return objective texture height
     */
    public float getObjectiveHeight() {
        return objectiveHeight;
    }

    /**
     * Check's if objective is or isn't catched already
     * @return True if it's catched. False otherwise
     */
    public boolean isCatched() {
        return catched;
    }

    /**
     * Catches objective
     */
    public void setCatched() {
        this.catched = true;
    }

    @Override
    public ModelType getType() {
        return ModelType.OBJECTIVE;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.STATIC;
    }
}
