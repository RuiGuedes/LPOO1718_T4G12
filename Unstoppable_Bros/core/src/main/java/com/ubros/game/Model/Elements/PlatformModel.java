package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.View.Elements.PlatformView;

import java.util.StringTokenizer;

public class PlatformModel extends ElementModel {

    /**
     * Platform body shape
     */
    private Polygon shape;

    /**
     * Associated platform view
     */
    private PlatformView view;

    /**
     * Origin X model position
     */
    private float originX;

    /**
     * Origin Y model position
     */
    private float originY;

    /**
     * Destiny X model position
     */
    private float destinyX;

    /**
     * Destiny Y model position
     */
    private float destinyY;

    /**
     * Boolean responsible to determine type of movement
     * True for horizontal movement and False for vertical movement
     */
    private boolean movementDir;

    /**
     * Name of platform view texture
     */
    private String platformView;

    /**
     * Platform model width
     */
    private float platformWidth;

    /**
     * Platform model height
     */
    private float platformHeight;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     * @param shape    The model associated shape
     * @param data     Information about the platform model
     */
    public PlatformModel(float x, float y, float rotation, Polygon shape, String data) {
        super(x, y, rotation);

        this.shape = shape;
        this.originX = this.destinyX = x;
        this.originY = this.destinyY = y;

        initializeRemainingVariables(data);
    }

    /**
     * Initializes all remaining variables related to the model
     *
     * @param data Information about the platform model
     */
    private void initializeRemainingVariables(String data) {

        float movement;
        StringTokenizer tokenizer = new StringTokenizer(data, "-");

        this.movementDir = tokenizer.nextToken().equals("L");

        movement = Integer.parseInt(tokenizer.nextToken());
        this.platformView = tokenizer.nextToken();

        if (movementDir) {
            this.destinyX -= (movement * TILE_WIDTH) / PlayGameScreen.PIXEL_TO_METER;
            this.platformWidth = (Integer.parseInt(tokenizer.nextToken()) * TILE_WIDTH) / PlayGameScreen.PIXEL_TO_METER;
            this.platformHeight = TILE_HEIGHT / PlayGameScreen.PIXEL_TO_METER;
        } else {
            this.destinyY += (movement * TILE_HEIGHT) / PlayGameScreen.PIXEL_TO_METER;
            this.platformWidth = TILE_WIDTH / PlayGameScreen.PIXEL_TO_METER;
            this.platformHeight = (Integer.parseInt(tokenizer.nextToken()) * TILE_HEIGHT) / PlayGameScreen.PIXEL_TO_METER;
        }
    }

    /**
     * Function responsible to retrieve model shape
     *
     * @return this object's shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * Function responsible to retrieve platform associated view
     *
     * @return platform view
     */
    public PlatformView getView() {
        return view;
    }

    /**
     * Function responsible to set platform view
     *
     * @param view new view to be set
     */
    public void setView(PlatformView view) {
        this.view = view;
    }

    /**
     * Platform origin X position
     *
     * @return x origin position
     */
    public float getOriginX() {
        return originX;
    }

    /**
     * Platform origin Y position
     *
     * @return y origin position
     */
    public float getOriginY() {
        return originY;
    }

    /**
     * Platform destiny X position
     *
     * @return x origin position
     */
    public float getDestinyX() {
        return destinyX;
    }

    /**
     * Platform destiny Y position
     *
     * @return y destiny position
     */
    public float getDestinyY() {
        return destinyY;
    }

    /**
     * Checks type of platform movement
     *
     * @return true for horizontal movement, false for vertical movement
     */
    public boolean isMovementDir() {
        return movementDir;
    }

    /**
     * Get's platform model texture name
     *
     * @return texture name of the present object
     */
    public String getPlatformView() {
        return platformView;
    }

    /**
     * Get's platform model width
     *
     * @return model width
     */
    public float getPlatformWidth() {
        return platformWidth;
    }

    /**
     * Get's platform model height
     *
     * @return model height
     */
    public float getPlatformHeight() {
        return platformHeight;
    }

    @Override
    public ModelType getType() {
        return ModelType.PLATFORM;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.KINEMATIC;
    }
}