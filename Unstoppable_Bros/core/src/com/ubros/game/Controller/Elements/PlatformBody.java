package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.physics.box2d.Body;
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
     */
    public PlatformBody(World world, ElementModel model, float[] vertexSet) {
        super(world, model);
        createFixture(getBody(),vertexSet,0,0,0f,0f,0f, (short)0, (short)0);
        ((PlatformModel)getModel()).originX = getBody().getPosition().x;
        ((PlatformModel)getModel()).originY = getBody().getPosition().y;
    }


    @Override
    public void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        for(int i = 0; i < vertexes.length; i++)
            vertexes[i] = vertexes[i]/ PlayGameScreen.PIXEL_TO_METER;

        shape.set(vertexes);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("Platform");
    }

    public void setLinearVelocity(boolean direction) {

        float multiplier = direction ? -1 : 1;

        if(((PlatformModel)getModel()).movementDir)
            super.getBody().setLinearVelocity(multiplier*1.5f,0f);
        else
            super.getBody().setLinearVelocity(0f,multiplier*-1.5f);

    }
}
