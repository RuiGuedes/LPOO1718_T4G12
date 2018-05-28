package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.View.Elements.ObjectView;

import java.util.StringTokenizer;

public class ObjectModel extends ElementModel {

    /**
     * Object shape
     */
    private Polygon shape;

    /**
     * Information associated to the object itself
     */
    private String data;

    /**
     * Radius for circle shape. 0 if it's not a circle shape
     */
    private int radius;

    /**
     * Object model view associated
     */
    private ObjectView view;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public ObjectModel(float x, float y, float rotation, Polygon shape, String data) {
        super(x, y, rotation);
        this.shape = shape;
        initializeData(data);
    }

    /**
     * Initializes all object model information
     * @param data information about the object
     */
    private void initializeData(String data) {

        StringTokenizer tokenizer = new StringTokenizer(data,"-");

        this.data = tokenizer.nextToken();
        this.radius = Integer.parseInt(tokenizer.nextToken());
    }

    /**
     * Get's object model shape
     * @return object shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * Get's object model data information
     * @return data information
     */
    public String getData() {
        return data;
    }

    /**
     * Get's object model radius
     * @return return object model radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * Get's object model associated view
     * @return object model view
     */
    public ObjectView getView() {
        return view;
    }

    /**
     * Set's object model new view
     * @param view new object model view
     */
    public void setView(ObjectView view) {
        this.view = view;
    }

    @Override
    public ModelType getType() {
        return ModelType.OBJECT;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.DYNAMIC;
    }
}
