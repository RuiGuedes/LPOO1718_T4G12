package com.ubros.game.View;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.EnemyBody;
import com.ubros.game.Controller.Elements.ExitDoorBody;
import com.ubros.game.Controller.Elements.MechanismBody;
import com.ubros.game.Controller.Elements.ObjectBody;
import com.ubros.game.Controller.Elements.ObjectiveBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Model.Elements.BulletModel;
import com.ubros.game.Model.Elements.EnemyModel;
import com.ubros.game.Model.Elements.ExitDoorModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.ObjectModel;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.UbrosGame;
import com.ubros.game.View.Elements.ElementView;
import com.ubros.game.View.Elements.EnemyView;
import com.ubros.game.View.Elements.ExitDoorView;
import com.ubros.game.View.Elements.MechanismView;
import com.ubros.game.View.Elements.NinjaView;
import com.ubros.game.View.Elements.ObjectView;
import com.ubros.game.View.Elements.ObjectiveView;
import com.ubros.game.View.Elements.PlatformView;
import com.ubros.game.View.Elements.RobotView;

import java.util.List;

public class GameView {

    /**
     * The singleton instance of the game view
     */
    private static GameView instance;

    /**
     * Game object
     */
    private UbrosGame game;

    /**
     * Robot character element view
     */
    private ElementView robot;

    /**
     * Ninja character element view
     */
    private ElementView ninja;


    private GameView(UbrosGame game) {
        this.game = game;

        initializeGraphics();
    }

    ////////////////////
    // CREATE METHODS //
    ////////////////////

    /**
     * Creates both robot and ninja views
     */
    private void createCharactersView() {

        this.robot = new RobotView(this.game, (TextureAtlas) this.game.getAssetManager().get("Robot/Robot.pack"));
        this.ninja = new NinjaView(this.game, (TextureAtlas) this.game.getAssetManager().get("Ninja/Ninja.pack"));

        GameModel.getInstance(this.game).getRobot().setElementView(robot);
        GameModel.getInstance(this.game).getNinja().setElementView(ninja);
    }

    /**
     * Creates all mechanisms views
     */
    private void createMechanismView() {
        List<MechanismBody> mechanisms = GameController.getInstance(this.game).getMechanisms();
        for (MechanismBody mechanism : mechanisms) {
            ((MechanismModel) mechanism.getModel()).setView(new MechanismView(this.game, null, mechanism));
        }
    }

    /**
     * Creates all platform views
     */
    private void createPlatformsView() {
        List<PlatformBody> platforms = GameController.getInstance(this.game).getPlatforms();
        for (PlatformBody platformBody : platforms) {
            ((PlatformModel) platformBody.getModel()).setView(new PlatformView(this.game, null, platformBody, ((PlatformModel) platformBody.getModel()).getPlatformView()));
        }
    }

    /**
     * Creates all objects view
     */
    private void createObjectsView() {
        List<ObjectBody> objects = GameController.getInstance(this.game).getObjects();
        for (ObjectBody objectBody : objects)
            ((ObjectModel) objectBody.getModel()).setView(new ObjectView(this.game, null, objectBody, ((ObjectModel) objectBody.getModel()).getData()));
    }

    /**
     * Creates all objectives views
     */
    private void createObjectivesView() {
        List<ObjectiveBody> objectives = GameController.getInstance(this.game).getObjectives();
        for (ObjectiveBody objective : objectives) {
            ((ObjectiveModel) objective.getModel()).setView(new ObjectiveView(this.game, null, objective, ((ObjectiveModel) objective.getModel()).getObjectiveView()));
        }
    }

    /**
     * Creates all exit door views
     */
    private void createExitDoorsView() {
        List<ExitDoorBody> exitDoorBodies = GameController.getInstance(this.game).getExitDoors();
        for (ExitDoorBody exitDoor : exitDoorBodies) {
            ((ExitDoorModel) exitDoor.getModel()).setView(new ExitDoorView(this.game, null, exitDoor));
        }
    }

    /**
     * Create all enemies view
     */
    private void createEnemysView() {

        for (EnemyBody enemyBody : GameController.getInstance(this.game).getEnemyBodies())
            ((EnemyModel) enemyBody.getModel()).setView(new EnemyView(this.game, (TextureAtlas) this.game.getAssetManager().get("Enemy/Enemy.pack"), enemyBody));

    }

    /////////////////
    // GET METHODS //
    /////////////////

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameView getInstance(UbrosGame game) {
        if (instance == null)
            instance = new GameView(game);
        return instance;
    }

    public ElementView getRobot() {
        return robot;
    }

    public ElementView getNinja() {
        return ninja;
    }

    ////////////
    // OTHERS //
    ////////////

    /**
     * Initializes all game elements view
     */
    private void initializeGraphics() {

        createCharactersView();
        createMechanismView();
        createPlatformsView();
        createObjectivesView();
        createExitDoorsView();
        createEnemysView();
        createObjectsView();
    }

    /**
     * Draws all elements previously defined
     *
     * @param delta time since last renders in seconds.
     */
    public void drawElements(float delta) {

        for (MechanismBody mechanism : GameController.getInstance(this.game).getMechanisms())
            ((MechanismModel) mechanism.getModel()).getView().draw(delta);

        for (PlatformBody platformBody : GameController.getInstance(this.game).getPlatforms())
            ((PlatformModel) platformBody.getModel()).getView().draw(delta);

        for (ObjectBody objectBody : GameController.getInstance(this.game).getObjects())
            ((ObjectModel) objectBody.getModel()).getView().draw(delta);

        for (ObjectiveBody objectiveBody : GameController.getInstance(this.game).getObjectives())
            ((ObjectiveModel) objectiveBody.getModel()).getView().draw(delta);

        for (ExitDoorBody exitDoorBody : GameController.getInstance(this.game).getExitDoors())
            ((ExitDoorModel) exitDoorBody.getModel()).getView().draw(delta);

        for (EnemyBody enemyBody : GameController.getInstance(this.game).getEnemyBodies())
            ((EnemyModel) enemyBody.getModel()).getView().draw(delta);

        for (BulletModel bulletModel : GameModel.getInstance(this.game).bullets)
            bulletModel.getView().draw(delta);


        robot.draw(delta);
        ninja.draw(delta);
    }

    /**
     * Dispose game view
     */
    public void dispose() {
        instance = null;
    }

}
