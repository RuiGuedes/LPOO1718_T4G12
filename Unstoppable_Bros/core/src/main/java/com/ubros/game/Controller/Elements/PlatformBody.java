package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;
import com.ubros.game.Model.Elements.PlatformModel;

public class PlatformBody extends ElementBody {

    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     * @param vertexSet The model shape vertex's
     */
    public PlatformBody(World world, ElementModel model, float[] vertexSet) {
        super(world, model);
        createFixture(getBody(),vertexSet,0,0,0f,0f,0f, (short)0, (short)0);
    }

    @Override
    public void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        for(int i = 0; i < vertexes.length; i++)
            vertexes[i] = vertexes[i]/ PlayGameScreen.PIXEL_TO_METER;

        shape.set(vertexes);
        fdef.shape = shape;
        fdef.friction = 10;
        body.createFixture(fdef).setUserData("PlatformBody");

        if(((PlatformModel)getModel()).getPlatformView().equals("barrel.png")) {

            EdgeShape upSide = new EdgeShape();

            float xPos = ((PlatformModel)getModel()).getPlatformWidth();
            float yPos = ((PlatformModel)getModel()).getPlatformHeight()*1.1f;

            upSide.set(new Vector2(xPos*0.1f,yPos), new Vector2(xPos*0.9f,yPos));

            fdef.shape = upSide;
            fdef.isSensor = true;
            body.createFixture(fdef).setUserData("Platform");

        }

    }

    /**
     * Applies a linear velocity to the platform accordingly to the respective direction
     * @param direction true for horizontal movement, false for vertical movement
     */
    public void setLinearVelocity(boolean direction) {

        float multiplier = direction ? -1 : 1;

        if(((PlatformModel)getModel()).isMovementDir())
            super.getBody().setLinearVelocity(multiplier*1.5f,0f);
        else
            super.getBody().setLinearVelocity(0f,multiplier*-1.5f);

    }

}
