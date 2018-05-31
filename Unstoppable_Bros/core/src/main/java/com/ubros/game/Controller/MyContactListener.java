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
import com.ubros.game.View.Elements.NinjaView;
import com.ubros.game.View.Elements.RobotView;
import com.ubros.game.View.GameView;

public class MyContactListener implements ContactListener {

    /**
     * Checks collisions affecting every game element.
     *
     * @param contact Contact object that contains fixtures about the bodies in contact
     */
    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals("RobotBounds") || fixB.getUserData().equals("RobotBounds")) {
            Body bodyA = fixA.getUserData().equals("RobotBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();
            Fixture object = bodyA == fixA.getBody() ? fixB : fixA;

            robotBeginContact(bodyA, bodyB, object);
        }

        if (fixA.getUserData().equals("NinjaBounds") || fixB.getUserData().equals("NinjaBounds")) {
            Body bodyA = fixA.getUserData().equals("NinjaBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();
            Fixture object = bodyA == fixA.getBody() ? fixB : fixA;

            ninjaBeginContact(bodyA, bodyB, object);
        }

        if (fixA.getUserData().equals("Bullet") || fixB.getUserData().equals("Bullet")) {
            Body bodyA = fixA.getUserData().equals("Bullet") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            bulletCollision(bodyA, bodyB);
        }

    }

    /**
     * In case one of the body in contact is the robot, the remaining objects act accordingly
     *
     * @param robot  Robot body
     * @param object Other object body that is going to be determined
     * @param fix    Fixture associated to the object
     */
    private void robotBeginContact(Body robot, Body object, Fixture fix) {

        if ((object.getUserData()) instanceof LimitModel)
            ((RobotView) GameView.getInstance(null).getRobot()).setJumping(false);

        if ((object.getUserData()) instanceof DangerZoneModel)
            dangerZoneCollision(GameController.getInstance(null).getRobot().getBody(), GameModel.getInstance(null).getRobot().getElementView());

        if ((object.getUserData()) instanceof MechanismModel)
            mechanismCollision(object);

        if ((object.getUserData()) instanceof PlatformModel) {
            if (fix.getUserData().equals("Platform")) {
                platformCollision(robot, object);
                if (platformCollision(robot, object))
                    GameController.getInstance(null).getRobot().getBody().setLinearVelocity(0, 0);
            }
        }

        if (((object.getUserData()) instanceof ObjectiveModel) && (((ObjectiveModel) object.getUserData()).getData().equals("R")))
            objectiveCollision(object);

        if (((object.getUserData()) instanceof ExitDoorModel) && (((ExitDoorModel) object.getUserData()).getData().equals("RobotExitDoor")))
            exitDoorCollision(object, true);

        if (((object.getUserData()) instanceof EnemyModel) && !((EnemyModel) object.getUserData()).isDead())
            characterEnemyCollision(GameController.getInstance(null).getRobot().getBody(), GameModel.getInstance(null).getRobot().getElementView(), ((EnemyModel) object.getUserData()).getView());


    }

    /**
     * In case one of the body in contact is the ninja, the remaining objects act accordingly
     *
     * @param ninja  Robot body
     * @param object Other object body that is going to be determined
     * @param fix    Fixture associated to the object
     */
    private void ninjaBeginContact(Body ninja, Body object, Fixture fix) {

        if ((object.getUserData()) instanceof LimitModel)
            ((NinjaView) GameView.getInstance(null).getNinja()).setJumping(false);

        if ((object.getUserData()) instanceof DangerZoneModel)
            dangerZoneCollision(GameController.getInstance(null).getNinja().getBody(), GameModel.getInstance(null).getNinja().getElementView());

        if ((object.getUserData()) instanceof MechanismModel)
            mechanismCollision(object);

        if ((object.getUserData()) instanceof PlatformModel) {
            if (fix.getUserData().equals("Platform")) {
                platformCollision(ninja, object);
                if (platformCollision(ninja, object))
                    GameController.getInstance(null).getNinja().getBody().setLinearVelocity(0, 0);
            }
        }

        if (((object.getUserData()) instanceof ObjectiveModel) && (((ObjectiveModel) object.getUserData()).getData().equals("N")))
            objectiveCollision(object);

        if ((object.getUserData()) instanceof PortalModel)
            portalCollision(object);

        if (((object.getUserData()) instanceof ExitDoorModel) && (((ExitDoorModel) object.getUserData()).getData().equals("NinjaExitDoor")))
            exitDoorCollision(object, true);

        if (((object.getUserData()) instanceof EnemyModel) && !((EnemyModel) object.getUserData()).isDead())
            characterEnemyCollision(GameController.getInstance(null).getNinja().getBody(), GameModel.getInstance(null).getNinja().getElementView(), ((EnemyModel) object.getUserData()).getView());
    }

    /**
     * Checks the end of collisions affecting every game element.
     *
     * @param contact Contact object that contains fixtures about the bodies ending contact
     */
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

    /**
     * Both for robot and ninja the contact with this objects, their actions are the same
     *
     * @param characterBody ninja or robot body
     * @param object        object that character was in contact with
     */
    private void characterEndContact(Body characterBody, Body object) {

        if ((object.getUserData()) instanceof LimitModel)
            ((CharacterModel) characterBody.getUserData()).onGround = false;

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

    /**
     * Collision between a character and a danger zone
     *
     * @param characterBody character body that is colliding
     * @param characterView character view
     */
    private void dangerZoneCollision(Body characterBody, ElementView characterView) {
        characterView.setCurrentState(ElementView.CharacterState.DEAD);
        characterBody.setLinearVelocity(0f, 0f);
    }

    /**
     * Collision between a character and an enemy
     *
     * @param characterBody character body that is colliding
     * @param characterView character view
     * @param enemy         enemy view
     */
    private void characterEnemyCollision(Body characterBody, ElementView characterView, EnemyView enemy) {
        characterView.setCurrentState(ElementView.CharacterState.DEAD);
        characterBody.setLinearVelocity(0f, 0f);
        enemy.setCurrentState(ElementView.CharacterState.ATTACKING);
    }

    /**
     * Collision between a character and a mechanism. Same action for both characters
     *
     * @param mechanismBody mechanism body
     */
    private void mechanismCollision(Body mechanismBody) {
        MechanismModel mechanismModel = (MechanismModel) mechanismBody.getUserData();

        if (mechanismModel.isActive())
            mechanismModel.setActive(false);
        else
            mechanismModel.setActive(true);
    }

    /**
     * Collision between a character and a platform
     *
     * @param character character body that is colliding
     * @param platform  platform body that character is colliding with
     * @return true if it's a barrel, false otherwise
     */
    private boolean platformCollision(Body character, Body platform) {
        ((CharacterModel) character.getUserData()).setOnPlatform(true);
        return (((PlatformModel) platform.getUserData()).getPlatformView().equals("barrel.png"));
    }

    /**
     * Collision between a character and a portal
     *
     * @param portalBody portal body that character is colliding with
     */
    private void portalCollision(Body portalBody) {
        PortalModel portalModel = (PortalModel) portalBody.getUserData();

        GameController.getInstance(null).getNinja().newPosition = portalModel.getCharacterPosition();
        GameController.getInstance(null).getNinja().setTransformFlag = true;
    }

    /**
     * Collision between a character and an objective
     *
     * @param objectiveBody objective body that character is colliding with
     */
    private void objectiveCollision(Body objectiveBody) {
        ObjectiveModel objectiveModel = (ObjectiveModel) objectiveBody.getUserData();

        if (!objectiveModel.isCatched()) {
            if (SettingsScreen.soundActive)
                SettingsScreen.pickObjectiveSound.play();
            objectiveModel.setCatched();
            GameController.getInstance(null).setRemainingObjectives();
        }
    }

    /**
     * Collision between a character and an exit door
     *
     * @param exitDoorBody exit door that character is colliding with
     * @param status       door new status
     */
    private void exitDoorCollision(Body exitDoorBody, boolean status) {
        ((ExitDoorModel) exitDoorBody.getUserData()).setCharacterContact(status);
    }

    /**
     * Collision between a character and an exit door
     *
     * @param bullet bullet that is colliding with object
     * @param object object that bullet is colliding with
     */
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

    /**
     * Collision between an enemy and bullet
     *
     * @param bullet bullet that is colliding with enemy
     * @param enemy  enemy that bullet is colliding with
     */
    private void enemyBulletCollision(Body bullet, Body enemy) {

        if (!((EnemyModel) enemy.getUserData()).isDead()) {
            ((BulletModel) bullet.getUserData()).setFlaggedForRemoval(true);
            ((EnemyModel) enemy.getUserData()).decreaseHealth();

            if (((EnemyModel) enemy.getUserData()).getHealth() <= 0)
                ((EnemyModel) enemy.getUserData()).getView().setCurrentState(ElementView.CharacterState.DEAD);
        }
    }

}
