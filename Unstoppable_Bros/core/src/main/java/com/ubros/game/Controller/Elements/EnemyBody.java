package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;
import com.ubros.game.Model.Elements.EnemyModel;


public class EnemyBody extends ElementBody {

    /**
     * Enemy speed
     */
    private float SPEED = 0.5f;

    /**
     * Constructs a body representing a model in a certain world.
     *
     * @param world The world this body lives on.
     * @param model The model representing the body.
     */
    public EnemyBody(World world, ElementModel model) {
        super(world, model);
        createFixture(getBody(),null,0,0,0f,0f,0f, (short)0, (short)0);
    }

    /**
     * Updates enemy position accordingly to is current position and state
     * @param x enemy current position
     */
    public void updateEnemyPosition(float x) {

        if (((EnemyModel) getModel()).isMovementDir()) {

            if ((x > ((EnemyModel) getModel()).getLeftX()) && (getBody().getLinearVelocity().x >= -SPEED*3))
                getBody().setLinearVelocity(-SPEED, getBody().getLinearVelocity().y);
            else if (x <= ((EnemyModel) getModel()).getLeftX())
                ((EnemyModel) getModel()).setMovementDir(false);
        } else {

            if ((((EnemyModel) getModel()).getRightX() > x) && (getBody().getLinearVelocity().x <= SPEED*3))
                getBody().setLinearVelocity(SPEED, getBody().getLinearVelocity().y);
            else if (x >= ((EnemyModel) getModel()).getRightX())
                ((EnemyModel) getModel()).setMovementDir(true);
        }
    }

    @Override
    public void createFixture(Body body, float[] vertexes, int width, int height, float density, float friction, float restitution, short category, short mask) {

        FixtureDef fdef = new FixtureDef();
        CircleShape shapeMov = new CircleShape();
        shapeMov.setRadius(CHARACTER_RADIUS/ PlayGameScreen.PIXEL_TO_METER);

        fdef.shape = shapeMov;
        body.createFixture(fdef).setUserData("Enemy");
    }

}
