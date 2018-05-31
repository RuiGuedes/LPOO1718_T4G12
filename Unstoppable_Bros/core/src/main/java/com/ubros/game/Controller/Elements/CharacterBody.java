package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;

public class CharacterBody extends ElementBody {

    /**
     * Body user data used to check contact with other bodies
     */
    private String userData;

    /**
     * New teleport position
     */
    public Vector3 newPosition;

    /**
     * Used to teleport characters
     */
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

    @Override
    public void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        float halfWidth = CHARACTER_WIDTH/2;
        float halfHeight = CHARACTER_HEIGHT/2;
        float radius = 10;
        String bodyUserData = "RobotBody";

        FixtureDef fdef = new FixtureDef();
        CircleShape circle = new CircleShape();
        circle.setRadius(radius/ PlayGameScreen.PIXEL_TO_METER);
        circle.setPosition(new Vector2(-(halfWidth - radius - 6)/PlayGameScreen.PIXEL_TO_METER,-(halfHeight - radius)/PlayGameScreen.PIXEL_TO_METER));

        fdef.shape = circle;
        body.createFixture(fdef).setUserData(bodyUserData);

        circle.setRadius(radius/ PlayGameScreen.PIXEL_TO_METER);
        circle.setPosition(new Vector2((halfWidth - radius - 6)/PlayGameScreen.PIXEL_TO_METER,-(halfHeight - radius)/PlayGameScreen.PIXEL_TO_METER));

        fdef.shape = circle;
        body.createFixture(fdef).setUserData(bodyUserData);

        EdgeShape side = new EdgeShape();

        side.set(new Vector2(-halfWidth/PlayGameScreen.PIXEL_TO_METER, -(halfHeight - radius)/PlayGameScreen.PIXEL_TO_METER), new Vector2(-halfWidth/PlayGameScreen.PIXEL_TO_METER, halfHeight/PlayGameScreen.PIXEL_TO_METER));
        fdef.shape = side;
        body.createFixture(fdef).setUserData(bodyUserData);

        side.set(new Vector2(halfWidth/PlayGameScreen.PIXEL_TO_METER, -(halfHeight - radius)/PlayGameScreen.PIXEL_TO_METER), new Vector2(halfWidth/PlayGameScreen.PIXEL_TO_METER, halfHeight/PlayGameScreen.PIXEL_TO_METER));
        fdef.shape = side;
        body.createFixture(fdef).setUserData(bodyUserData);

        side.set(new Vector2(-halfWidth/PlayGameScreen.PIXEL_TO_METER, halfHeight/PlayGameScreen.PIXEL_TO_METER), new Vector2(halfWidth/PlayGameScreen.PIXEL_TO_METER, halfHeight/PlayGameScreen.PIXEL_TO_METER));
        fdef.shape = side;
        body.createFixture(fdef).setUserData(bodyUserData);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(halfWidth/ PlayGameScreen.PIXEL_TO_METER, halfHeight/PlayGameScreen.PIXEL_TO_METER);

        fdef.shape = shape;
        fdef.isSensor = true;
        body.createFixture(fdef).setUserData(userData);

    }
}
