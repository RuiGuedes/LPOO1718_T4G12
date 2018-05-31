package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;

public class DangerZoneBody extends ElementBody {

    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world     The world this body lives on.
     * @param model     The model representing the body.
     * @param vertexSet The model shape vertexes
     */
    public DangerZoneBody(World world, ElementModel model, float[] vertexSet) {
        super(world, model);
        createFixture(getBody(), vertexSet, 0, 0, 0f, 0f, 0f, (short) 0, (short) 0);
    }

    @Override
    public void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        for (int i = 0; i < vertexes.length; i++)
            vertexes[i] = vertexes[i] / PlayGameScreen.PIXEL_TO_METER;

        shape.set(vertexes);
        fdef.shape = shape;
        body.createFixture(fdef).setUserData("DangerZone");
    }
}
