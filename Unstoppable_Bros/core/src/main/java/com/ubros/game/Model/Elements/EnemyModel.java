package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.View.Elements.EnemyView;

import java.util.Random;
import java.util.StringTokenizer;

public class EnemyModel extends ElementModel {

    /**
     * Enemy shape
     */
    private Polygon shape;

    /**
     * Max left position which enemy can be
     */
    private float leftX;

    /**
     * Max right position which enemy can be
     */
    private float rightX;

    /**
     * Indicates whether enemy is static or dynamic. True if it's static. False if it's dynamic
     */
    private boolean type;

    /**
     * Indicates direction in which enemy is moving. True for left direction, false to right direction
     */
    private boolean movementDir;

    /**
     * Enemy associated view
     */
    private EnemyView view;

    /**
     * Enemy health
     */
    private int health;

    /**
     * Determines whether enemy is dead or not
     */
    private boolean dead = false;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     * @param shape    The model shape
     * @param data     Information about the model
     */
    public EnemyModel(float x, float y, float rotation, Polygon shape, String data) {
        super(x, y, rotation);

        this.shape = shape;
        this.movementDir = true;
        this.leftX = this.rightX = x;

        Random random = new Random();
        this.health = random.nextInt(10 - 5 + 1) + 5;

        initializeRemainingVariables(data);

        super.setPosition(x,y + 35/PlayGameScreen.PIXEL_TO_METER );
    }

    /**
     * Initializes all enemy variables decomposing data string
     * @param data information about the model
     */
    private void initializeRemainingVariables(String data) {

        StringTokenizer tokenizer = new StringTokenizer(data, "-");

        type = tokenizer.nextToken().equals("S");
        this.leftX -= (Integer.parseInt(tokenizer.nextToken()) * TILE_WIDTH)/ PlayGameScreen.PIXEL_TO_METER - (CHARACTER_RADIUS/2)/PlayGameScreen.PIXEL_TO_METER;
        this.rightX += (Integer.parseInt(tokenizer.nextToken()) * TILE_WIDTH)/ PlayGameScreen.PIXEL_TO_METER - (CHARACTER_RADIUS/2)/PlayGameScreen.PIXEL_TO_METER;

    }

    /**
     * Get enemy body shape
     * @return enemy body shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * Get enemy max left position
     * @return enemy max left position
     */
    public float getLeftX() {
        return leftX;
    }

    /**
     * Get enemy max right position
     * @return enemy max right position
     */
    public float getRightX() {
        return rightX;
    }

    /**
     * Get enemy moving direction
     * @return enemy direction
     */
    public boolean isMovementDir() {
        return movementDir;
    }

    /**
     * Sets enemy movement direction
     * @param movementDir new enemy movement direction
     */
    public void setMovementDir(boolean movementDir) {
        this.movementDir = movementDir;
    }

    /**
     * Get enemy associated view
     * @return enemy associated view
     */
    public EnemyView getView() {
        return view;
    }

    /**
     * Set enemy view
     * @param view new enemy view
     */
    public void setView(EnemyView view) {
        this.view = view;
    }

    /**
     * Checks if enemy is dead
     * @return enemy state
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Set enemy to dead state
     */
    public void setDead() {
        this.dead = true;
    }

    /**
     * Get enemy health
     * @return enemy health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Decreases enemy health
     */
    public void decreaseHealth() {
        this.health --;
    }

    @Override
    public ModelType getType() {
        return ModelType.ENEMY;
    }

    @Override
    public PhysicsType getPhysicsType() {
        if(type)
            return PhysicsType.STATIC;
        else
            return PhysicsType.DYNAMIC;
    }
}
