package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;

public class HeroBody extends ElementBody {

    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     */
    public HeroBody(World world, ElementModel model) {
        super(world, model);

       createFixture(getBody(),null,0,0,0f,0f,0f, (short)0, (short)0);
    }

    public void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(34/ PlayGameScreen.PIXEL_TO_METER);

        fdef.shape = shape;
        body.createFixture(fdef).setUserData("HeroBounds");

        PolygonShape bounds = new PolygonShape();
        bounds.setAsBox(24/PlayGameScreen.PIXEL_TO_METER, 35/PlayGameScreen.PIXEL_TO_METER);
        fdef.shape = bounds;
        fdef.isSensor = true;

        body.createFixture(fdef);
    }

}
