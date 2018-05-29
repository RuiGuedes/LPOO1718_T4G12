package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.ubros.game.Controller.Elements.PortalBody;
import com.ubros.game.Gui.PlayGameScreen;

import java.util.StringTokenizer;

public class PortalModel extends ElementModel {

    /**
     * Character size (width)
     */
    private float CHARACTER_SIZE = 40 / PlayGameScreen.PIXEL_TO_METER;

    /**
     * Portal shape
     */
    private Polygon shape;

    /**
     * Portal associated name
     */
    private String name;

    /**
     * Direction to output character to. Up(U), Down(D), Left(L), Right(R)
     */
    private String direction;

    /**
     * Associated portal destiny
     */
    private PortalBody portalDestiny;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     * @param shape    The shape of portal
     * @param data     Information associated to the portal
     */
    public PortalModel(float x, float y, float rotation, Polygon shape, String data) {
        super(x, y, rotation);
        this.shape = shape;
        initializePortalData(data);
    }

    /**
     * Initializes data about the portal
     * @param data compressed data about the portal
     */
    private void initializePortalData(String data) {

        StringTokenizer tokenizer = new StringTokenizer(data, "-");

        this.name = tokenizer.nextToken();
        this.direction = tokenizer.nextToken();
    }

    /**
     * Get's portal model shape
     * @return portal model shape
     */
    public Polygon getShape() {
        return shape;
    }

    /**
     * Get's portal name
     * @return porta's name
     */
    public String getName() {
        return name;
    }

    /**
     * Set's this portal, portal destiny
     * @param portalDestiny new portal destiny
     */
    public void setPortalDestiny(PortalBody portalDestiny) {
        this.portalDestiny = portalDestiny;
    }

    /**
     * Get's portal output direction
     * @return direction from which character will come out
     */
    private String getDirection() {
        return direction;
    }

    /**
     * Get's character position out of this portal
     * @return Tri-dimensional vector containing x and y position plus linearVelocity to be set in which direction
     */
    public Vector3 getCharacterPosition() {

        String dir = ((PortalModel) portalDestiny.getModel()).getDirection();
        float xPos = portalDestiny.getX();
        float yPos = portalDestiny.getY();
        float linearVelocityDirection = 2;

        if (dir.equals("L")) {
            xPos -= CHARACTER_SIZE;
            linearVelocityDirection = 0;
        } else if (dir.equals("R")) {
            xPos += CHARACTER_SIZE;
            linearVelocityDirection = 1;
        } else if (dir.equals("D"))
            yPos -= CHARACTER_SIZE;
        else
            yPos += CHARACTER_SIZE;

        return new Vector3(xPos, yPos, linearVelocityDirection);
    }

    @Override
    public ModelType getType() {
        return ModelType.PORTAL;
    }

    @Override
    public PhysicsType getPhysicsType() {
        return PhysicsType.STATIC;
    }
}
