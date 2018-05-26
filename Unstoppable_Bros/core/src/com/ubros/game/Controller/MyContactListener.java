package com.ubros.game.Controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.Model.Elements.DangerZoneModel;
import com.ubros.game.Model.Elements.ExitDoorModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.Elements.PortalModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.View.Elements.ElementView;

public class MyContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals("RobotBounds") || fixB.getUserData().equals("RobotBounds")) {
            Body bodyA = fixA.getUserData().equals("RobotBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            robotBeginContact(bodyA, bodyB);
        }

        if (fixA.getUserData().equals("NinjaBounds") || fixB.getUserData().equals("NinjaBounds")) {
            Body bodyA = fixA.getUserData().equals("NinjaBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            ninjaBeginContact(bodyA, bodyB);
        }

    }

    private void robotBeginContact(Body robot, Body object) {

        if ((object.getUserData()) instanceof DangerZoneModel)
            dangerZoneCollision(GameController.getInstance(null).getRobot().getBody(), GameModel.getInstance(null).getRobot().getElementView());

        if ((object.getUserData()) instanceof MechanismModel)
            mechanismCollision(object);

        if ((object.getUserData()) instanceof PlatformModel)
            platformCollision(robot);

        if (((object.getUserData()) instanceof ObjectiveModel) && (((ObjectiveModel)object.getUserData()).getData().equals("R")))
            objectiveCollision(object);

        if (((object.getUserData()) instanceof ExitDoorModel) && (((ExitDoorModel)object.getUserData()).getData().equals("RobotExitDoor")))
            exitDoorCollision(object,true);

    }

    private void ninjaBeginContact(Body ninja, Body object) {

        if ((object.getUserData()) instanceof DangerZoneModel)
            dangerZoneCollision(GameController.getInstance(null).getNinja().getBody(), GameModel.getInstance(null).getNinja().getElementView());

        if ((object.getUserData()) instanceof MechanismModel)
            mechanismCollision(object);

        if ((object.getUserData()) instanceof PlatformModel)
            platformCollision(ninja);

        if (((object.getUserData()) instanceof ObjectiveModel) && (((ObjectiveModel)object.getUserData()).getData().equals("N")))
            objectiveCollision(object);

        if ((object.getUserData()) instanceof PortalModel)
            portalCollision(object);

        if (((object.getUserData()) instanceof ExitDoorModel) && (((ExitDoorModel)object.getUserData()).getData().equals("NinjaExitDoor")))
            exitDoorCollision(object,true);

    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals("RobotBounds") || fixB.getUserData().equals("RobotBounds")) {
            Body bodyA = fixA.getUserData().equals("RobotBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            characterEndContact(bodyA, bodyB);
        }

        if (fixA.getUserData().equals("NinjaBounds") || fixB.getUserData().equals("NinjaBounds")) {
            Body bodyA = fixA.getUserData().equals("NinjaBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            characterEndContact(bodyA, bodyB);
        }
    }

    private void characterEndContact(Body characterBody, Body object) {

        if ((object.getUserData()) instanceof MechanismModel)
            mechanismCollision(object);

        if ((object.getUserData()) instanceof PlatformModel)
            ((CharacterModel)characterBody.getUserData()).setOnPlatform(false);

        if (((object.getUserData()) instanceof ExitDoorModel))
            exitDoorCollision(object, false);

    }


    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {
    }

    private void dangerZoneCollision(Body characterBody, ElementView characterView) {
        characterView.setCurrentState(ElementView.CharacterState.DEAD);
        characterBody.setLinearVelocity(0f,0f);
        GameController.getInstance(null).setState(GameController.GameStatus.GAMEOVER);
    }

    private void mechanismCollision(Body mechanismBody) {
        MechanismModel mechanismModel = (MechanismModel)mechanismBody.getUserData();

        if(mechanismModel.isActive())
            mechanismModel.setActive(false);
        else
            mechanismModel.setActive(true);
    }

    private void platformCollision(Body character) {
        ((CharacterModel)character.getUserData()).setOnPlatform(true);
    }

    private void portalCollision(Body portalBody) {
        PortalModel portalModel = (PortalModel) portalBody.getUserData();

        GameController.getInstance(null).getNinja().newPosition = portalModel.getCharacterPosition();
        GameController.getInstance(null).getNinja().setTransformFlag = true;
    }

    private void objectiveCollision(Body objectiveBody) {
        ObjectiveModel objectiveModel = (ObjectiveModel)objectiveBody.getUserData();

        if(!objectiveModel.isCatched()) {
            objectiveModel.setCatched();
            GameController.getInstance(null).setRemainingObjectives();
        }
    }

    private void exitDoorCollision(Body exitDoorBody, boolean status) {
        ((ExitDoorModel)exitDoorBody.getUserData()).setCharacterContact(status);
    }

}
