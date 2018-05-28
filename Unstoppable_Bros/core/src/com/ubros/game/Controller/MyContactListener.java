package com.ubros.game.Controller;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.ubros.game.Gui.SettingsScreen;
import com.ubros.game.Model.Elements.BulletModel;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.Model.Elements.DangerZoneModel;
import com.ubros.game.Model.Elements.EnemyModel;
import com.ubros.game.Model.Elements.ExitDoorModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.ObjectModel;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.Elements.PortalModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.View.Elements.ElementView;
import com.ubros.game.View.Elements.EnemyView;

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

        if (fixA.getUserData().equals("Bullet") || fixB.getUserData().equals("Bullet")) {
            Body bodyA = fixA.getUserData().equals("Bullet") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            bulletCollision(bodyA, bodyB);
        }

    }

    private void robotBeginContact(Body robot, Body object) {

        if ((object.getUserData()) instanceof DangerZoneModel)
            dangerZoneCollision(GameController.getInstance(null).getRobot().getBody(), GameModel.getInstance(null).getRobot().getElementView());

        if ((object.getUserData()) instanceof MechanismModel)
            mechanismCollision(object);

        if ((object.getUserData()) instanceof PlatformModel) {
            if (platformCollision(robot, object))
                GameController.getInstance(null).getRobot().getBody().setLinearVelocity(0, 0);
        }

        if (((object.getUserData()) instanceof ObjectiveModel) && (((ObjectiveModel) object.getUserData()).getData().equals("R")))
            objectiveCollision(object);

        if (((object.getUserData()) instanceof ExitDoorModel) && (((ExitDoorModel) object.getUserData()).getData().equals("RobotExitDoor")))
            exitDoorCollision(object, true);

        if(((object.getUserData()) instanceof EnemyModel) && !((EnemyModel)object.getUserData()).isDead())
            characterEnemyCollision(GameController.getInstance(null).getRobot().getBody(), GameModel.getInstance(null).getRobot().getElementView(), ((EnemyModel)object.getUserData()).getView());


    }

    private void ninjaBeginContact(Body ninja, Body object) {

        if ((object.getUserData()) instanceof DangerZoneModel)
            dangerZoneCollision(GameController.getInstance(null).getNinja().getBody(), GameModel.getInstance(null).getNinja().getElementView());

        if ((object.getUserData()) instanceof MechanismModel)
            mechanismCollision(object);

        if ((object.getUserData()) instanceof PlatformModel)
            platformCollision(ninja, object);

        if (((object.getUserData()) instanceof ObjectiveModel) && (((ObjectiveModel) object.getUserData()).getData().equals("N")))
            objectiveCollision(object);

        if ((object.getUserData()) instanceof PortalModel)
            portalCollision(object);

        if (((object.getUserData()) instanceof ExitDoorModel) && (((ExitDoorModel) object.getUserData()).getData().equals("NinjaExitDoor")))
            exitDoorCollision(object, true);

        if(((object.getUserData()) instanceof EnemyModel) && !((EnemyModel)object.getUserData()).isDead())
            characterEnemyCollision(GameController.getInstance(null).getNinja().getBody(), GameModel.getInstance(null).getNinja().getElementView(), ((EnemyModel)object.getUserData()).getView());
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
            ((CharacterModel) characterBody.getUserData()).setOnPlatform(false);

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
        characterBody.setLinearVelocity(0f, 0f);
    }

    private void characterEnemyCollision(Body characterBody, ElementView characterView, EnemyView enemy) {
        characterView.setCurrentState(ElementView.CharacterState.DEAD);
        characterBody.setLinearVelocity(0f, 0f);
        enemy.setCurrentState(ElementView.CharacterState.ATTACKING);
    }

    private void mechanismCollision(Body mechanismBody) {
        MechanismModel mechanismModel = (MechanismModel) mechanismBody.getUserData();

        if (mechanismModel.isActive())
            mechanismModel.setActive(false);
        else
            mechanismModel.setActive(true);
    }

    private boolean platformCollision(Body character, Body platform) {
        ((CharacterModel) character.getUserData()).setOnPlatform(true);
        return (((PlatformModel) platform.getUserData()).getPlatformView().equals("barrel.png"));
    }

    private void portalCollision(Body portalBody) {
        PortalModel portalModel = (PortalModel) portalBody.getUserData();

        GameController.getInstance(null).getNinja().newPosition = portalModel.getCharacterPosition();
        GameController.getInstance(null).getNinja().setTransformFlag = true;
    }

    private void objectiveCollision(Body objectiveBody) {
        ObjectiveModel objectiveModel = (ObjectiveModel) objectiveBody.getUserData();

        if (!objectiveModel.isCatched()) {
            SettingsScreen.pickObjectiveSound.play();
            objectiveModel.setCatched();
            GameController.getInstance(null).setRemainingObjectives();
        }
    }

    private void exitDoorCollision(Body exitDoorBody, boolean status) {
        ((ExitDoorModel) exitDoorBody.getUserData()).setCharacterContact(status);
    }

    private void bulletCollision(Body bullet, Body object) {

        if (object.getUserData() instanceof LimitModel)
            ((BulletModel) bullet.getUserData()).setFlaggedForRemoval(true);
        else if (object.getUserData() instanceof PlatformModel)
            ((BulletModel) bullet.getUserData()).setFlaggedForRemoval(true);
        else if (object.getUserData() instanceof PortalModel)
            ((BulletModel) bullet.getUserData()).setFlaggedForRemoval(true);
        else if (object.getUserData() instanceof ObjectModel)
            ((BulletModel) bullet.getUserData()).setFlaggedForRemoval(true);
        else if (object.getUserData() instanceof EnemyModel)
            enemyBulletCollision(bullet, object);

    }

    private void enemyBulletCollision(Body bullet, Body enemy) {

        if (!((EnemyModel) enemy.getUserData()).isDead()) {
            ((BulletModel) bullet.getUserData()).setFlaggedForRemoval(true);
            ((EnemyModel) enemy.getUserData()).decreaseHealth();

            if (((EnemyModel) enemy.getUserData()).getHealth() <= 0)
                ((EnemyModel) enemy.getUserData()).getView().setCurrentState(ElementView.CharacterState.DEAD);
        }
    }

}
