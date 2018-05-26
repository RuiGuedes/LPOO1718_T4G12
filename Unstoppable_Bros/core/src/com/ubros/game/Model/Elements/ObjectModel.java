package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;

import java.util.StringTokenizer;

public class ObjectModel extends ElementModel {

    private Polygon shape;

    private String data;

    private int radius;

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

    private void initializeData(String data) {

        StringTokenizer tokenizer = new StringTokenizer(data,"-");

        this.data = tokenizer.nextToken();
        this.radius = Integer.parseInt(tokenizer.nextToken());
    }

    public Polygon getShape() {
        return shape;
    }

    public String getData() {
        return data;
    }

    public int getRadius() {
        return radius;
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
