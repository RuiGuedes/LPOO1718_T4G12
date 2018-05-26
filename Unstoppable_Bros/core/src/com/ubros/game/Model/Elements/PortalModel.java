package com.ubros.game.Model.Elements;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector3;
import com.ubros.game.Controller.Elements.PortalBody;
import com.ubros.game.Gui.PlayGameScreen;

import java.util.StringTokenizer;

public class PortalModel extends ElementModel {

    private float CHARACTER_SIZE =  40/ PlayGameScreen.PIXEL_TO_METER;

    private Polygon shape;

    private String name;

    private String direction;

    private PortalBody portalDestiny;

    /**
     * Constructs a model with a position and a rotation.
     *
     * @param x        The x-coordinate of this entity in meters.
     * @param y        The y-coordinate of this entity in meters.
     * @param rotation The current rotation of this entity in radians.
     */
    public PortalModel(float x, float y, float rotation, Polygon shape, String data) {
        super(x, y, rotation);
        this.shape = shape;
        initializePortalData(data);
    }

    private void initializePortalData(String data) {

        StringTokenizer tokenizer = new StringTokenizer(data, "-");

        this.name = tokenizer.nextToken();
        this.direction = tokenizer.nextToken();
    }

    public String getName() {
        return name;
    }

    public Polygon getShape() {
        return shape;
    }

    public PortalBody getPortalDestiny() {
        return portalDestiny;
    }

    public void setPortalDestiny(PortalBody portalDestiny) {
        this.portalDestiny = portalDestiny;
    }

    public String getDirection() {
        return direction;
    }

    public Vector3 getCharacterPosition() {

        String dir = ((PortalModel)portalDestiny.getModel()).getDirection();
        float xPos = portalDestiny.getX();
        float yPos = portalDestiny.getY();
        float linearVelocityDirection = 2;

        if(dir.equals("L")) {
            xPos -= CHARACTER_SIZE;
            linearVelocityDirection = 0;
        }
        else if(dir.equals("R")) {
            xPos += CHARACTER_SIZE;
            linearVelocityDirection = 1;
        }
        else if(dir.equals("D"))
            yPos -= CHARACTER_SIZE;
        else
            yPos += CHARACTER_SIZE;

        return new Vector3(xPos,yPos,linearVelocityDirection);
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
