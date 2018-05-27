package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;

public class CharacterBody extends ElementBody {

    /**
     * Body user data used to check contact with other bodies
     */
    private String userData;

    public Vector3 newPosition;

    public boolean setTransformFlag = false;

    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     * @param userData The body user data
     */
    public CharacterBody(World world, ElementModel model, String userData) {
        super(world, model);

        this.userData = userData;
        createFixture(getBody(),null,0,0,0f,0f,0f, (short)0, (short)0);
    }

    /**
     * Function responsible to create fixture to a certain body
     * @param body
     * @param vertexes
     * @param width
     * @param height
     * @param density
     * @param friction
     * @param restitution
     * @param category
     * @param mask
     */
    public void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(34/ PlayGameScreen.PIXEL_TO_METER);

        fdef.shape = shape;
        body.createFixture(fdef).setUserData(userData);

    }
}