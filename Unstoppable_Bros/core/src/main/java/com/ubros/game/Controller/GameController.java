package com.ubros.game.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.Elements.DangerZoneBody;
import com.ubros.game.Controller.Elements.EnemyBody;
import com.ubros.game.Controller.Elements.ExitDoorBody;
import com.ubros.game.Controller.Elements.LimitBody;
import com.ubros.game.Controller.Elements.MechanismBody;
import com.ubros.game.Controller.Elements.ObjectBody;
import com.ubros.game.Controller.Elements.ObjectiveBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Controller.Elements.PortalBody;
import com.ubros.game.Gui.SettingsScreen;
import com.ubros.game.Model.Elements.DangerZoneModel;
import com.ubros.game.Model.Elements.ElementModel;
import com.ubros.game.Model.Elements.EnemyModel;
import com.ubros.game.Model.Elements.ExitDoorModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.ObjectModel;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.Elements.PortalModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.UbrosGame;
import com.ubros.game.View.Elements.RobotView;
import com.ubros.game.View.GameView;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    /**
     * Possible game status
     */
    public enum GameStatus {
        PLAYING, PAUSE, GAMEOVER, VICTORY
    }

    /**
     * Actual game status
     */
    private GameStatus state;

    /**
     * Player movement speed
     */
    public final static float PLAYER_SPEED = 0.3f;

    /**
     * World considered gravity value
     */
    private float GRAVITY = -10;

    /**
     * Time before robot takes it's next shot
     */
    private float TIME_FOR_NEXT_SHOOT = 0.2f;

    /**
     * Time that both characters have to wait before exit
     */
    private float TIME_BEFORE_EXIT = 1f;

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * Game object
     */
    private UbrosGame game;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The physics box2d debug renderer
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     * Number of objectives still waiting to be catched
     */
    private int remainingObjectives;

    /**
     * Variable use to control robot shooting time
     */
    private float timeToNextShoot;

    /**
     * Variable use to control exit time
     */
    private float timeToExit;

    /**
     * Robot body
     */
    private CharacterBody robot;

    /**
     * Ninja body
     */
    private CharacterBody ninja;

    /**
     * List of all mechanism bodies
     */
    private List<MechanismBody> mechanisms = new ArrayList<MechanismBody>();

    /**
     * List of all platform bodies
     */
    private List<PlatformBody> platforms = new ArrayList<PlatformBody>();

    /**
     * List of all objective bodies
     */
    private List<ObjectiveBody> objectives = new ArrayList<ObjectiveBody>();

    /**
     * List of all portal bodies
     */
    private List<PortalBody> portals = new ArrayList<PortalBody>();

    /**
     * List of all exit door bodies
     */
    private List<ExitDoorBody> exitDoors = new ArrayList<ExitDoorBody>();

    /**
     * List of all enemy bodies
     */
    private List<EnemyBody> enemyBodies = new ArrayList<EnemyBody>();

    /**
     * List of all object bodies
     */
    private List<ObjectBody> objects = new ArrayList<ObjectBody>();

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    private GameController(UbrosGame game) {

        this.game = game;
        this.state = GameStatus.PLAYING;
        this.world = new World(new Vector2(0, GRAVITY), true);
        this.debugRenderer = new Box2DDebugRenderer();

        createBodies();

        this.world.setContactListener(new MyContactListener());
    }

    ////////////////////
    // CREATE METHODS //
    ////////////////////

    /**
     * Creates all world bodies
     */
    private void createBodies() {
        createCharacters();
        createGround();
        createDangerZones();
        createMechanisms();
        createObjectives();
        createPortals();
        createExitDoors();
        createObjects();
        createEnemys();
    }

    /**
     * Create character bodies
     */
    private void createCharacters() {
        robot = new CharacterBody(this.world, GameModel.getInstance(this.game).getRobot(), "RobotBounds");
        ninja = new CharacterBody(this.world, GameModel.getInstance(this.game).getNinja(), "NinjaBounds");
    }

    /**
     * Create ground bodies
     */
    private void createGround() {
        List<LimitModel> limits = GameModel.getInstance(this.game).getLimits();
        for (LimitModel limit : limits)
            new LimitBody(this.world, limit, limit.getShape().getVertices());
    }

    /**
     * Create danger zone bodies
     */
    private void createDangerZones() {
        List<DangerZoneModel> acidRegions = GameModel.getInstance(this.game).getDangerZones();
        for (DangerZoneModel acid : acidRegions)
            new DangerZoneBody(this.world, acid, acid.getShape().getVertices());
    }

    /**
     * Create mechanism bodies
     */
    private void createMechanisms() {

        int pointer = 0;
        createPlatforms();

        List<MechanismModel> mechanisms = GameModel.getInstance(this.game).getMechanisms();
        for (MechanismModel mechanism : mechanisms) {
            this.mechanisms.add(new MechanismBody(this.world, mechanism, mechanism.getShape().getVertices()));
            mechanism.setPlatform(platforms.get(pointer++));
        }
    }

    /**
     * Create platform bodies
     */
    private void createPlatforms() {
        List<PlatformModel> platforms = GameModel.getInstance(this.game).getPlatforms();
        for (PlatformModel platform : platforms)
            this.platforms.add(new PlatformBody(this.world, platform, platform.getShape().getVertices()));
    }

    /**
     * Create objective bodies
     */
    private void createObjectives() {
        List<ObjectiveModel> objectives = GameModel.getInstance(this.game).getObjectives();
        for (ObjectiveModel objective : objectives)
            this.objectives.add(new ObjectiveBody(this.world, objective, objective.getShape().getVertices()));

        this.remainingObjectives = objectives.size();
    }

    /**
     * Create portal bodies
     */
    private void createPortals() {
        List<PortalModel> portalModels = GameModel.getInstance(this.game).getPortals();
        for (PortalModel portal : portalModels)
            portals.add(new PortalBody(this.world, portal, portal.getShape().getVertices()));

        for (PortalBody portalBody : portals) {
            for (PortalModel portalModel : portalModels) {
                if ((portalModel.getName().charAt(0) == ((PortalModel) portalBody.getModel()).getName().charAt(0)) &&
                        (portalModel.getName().charAt(1) != ((PortalModel) portalBody.getModel()).getName().charAt(1)))
                    portalModel.setPortalDestiny(portalBody);
            }
        }
    }

    /**
     * Create exit door bodies
     */
    private void createExitDoors() {
        List<ExitDoorModel> exitDoorModels = GameModel.getInstance(this.game).getExitDoors();
        for (ExitDoorModel exitDoor : exitDoorModels)
            exitDoors.add(new ExitDoorBody(this.world, exitDoor, exitDoor.getShape().getVertices()));
    }

    /**
     * Create object bodies
     */
    private void createObjects() {
        List<ObjectModel> objectModels = GameModel.getInstance(this.game).getObjects();
        for (ObjectModel object : objectModels)
            objects.add(new ObjectBody(this.world, object, object.getShape().getVertices()));
    }

    /**
     * Create enemy bodies
     */
    private void createEnemys() {
        List<EnemyModel> enemyModels = GameModel.getInstance(this.game).getEnemys();
        for (EnemyModel enemy : enemyModels)
            enemyBodies.add(new EnemyBody(this.world, enemy));
    }

    /////////////////
    // GET METHODS //
    /////////////////

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static GameController getInstance(UbrosGame game) {
        if (instance == null)
            instance = new GameController(game);
        return instance;
    }

    /**
     * Returns game state
     *
     * @return game state
     */
    public GameStatus getState() {
        return state;
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Returns world associated debug renderer
     *
     * @return world associated debug renderer
     */
    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    /**
     * Get's robot body
     *
     * @return robot body
     */
    public CharacterBody getRobot() {
        return robot;
    }

    /**
     * Get's ninja body
     *
     * @return ninja
     */
    public CharacterBody getNinja() {
        return ninja;
    }

    /**
     * Returns the number of remaining objectives
     *
     * @return number of remaining objectives
     */
    public int getRemainingObjectives() {
        return remainingObjectives;
    }

    /**
     * Get's list of mechanism bodies
     *
     * @return mechanism bodies
     */
    public List<MechanismBody> getMechanisms() {
        return mechanisms;
    }

    /**
     * Get's list of platform bodies
     *
     * @return platform bodies
     */
    public List<PlatformBody> getPlatforms() {
        return platforms;
    }

    /**
     * Get's list of objectives bodies
     *
     * @return objectives bodies
     */
    public List<ObjectiveBody> getObjectives() {
        return objectives;
    }

    /**
     * Get's list of enemy bodies
     *
     * @return enemy bodies
     */
    public List<EnemyBody> getEnemyBodies() {
        return enemyBodies;
    }

    /**
     * Get's list of exit door bodies
     *
     * @return exit door bodies
     */
    public List<ExitDoorBody> getExitDoors() {
        return exitDoors;
    }

    /**
     * Get's list of objects bodies
     *
     * @return mechanism bodies
     */
    public List<ObjectBody> getObjects() {
        return objects;
    }

    /**
     * Returns game instance
     *
     * @return game instance
     */
    public UbrosGame getGame() {
        return game;
    }

    ////////////
    // OTHERS //
    ////////////

    /**
     * Set's a new state
     *
     * @param state new state
     */
    public void setState(GameStatus state) {
        this.state = state;
    }

    /**
     * Set's new instance of game
     *
     * @param game new game
     */
    public void setGame(UbrosGame game) {
        this.game = game;
    }

    /**
     * Decrements the number of remaining objectives
     */
    public void setRemainingObjectives() {
        this.remainingObjectives--;
    }

    /**
     * Updates this instance world bodies
     */
    public void update(float delta) {

        timeToNextShoot -= delta;

        if (GameController.getInstance(this.game).getNinja().setTransformFlag) {
            GameController.getInstance(this.game).getNinja().setTransform(GameController.getInstance(this.game).getNinja().newPosition.x, GameController.getInstance(this.game).getNinja().newPosition.y, 0);
            GameController.getInstance(this.game).getNinja().setTransformFlag = false;

            if (GameController.getInstance(this.game).getNinja().newPosition.z == 0)
                GameController.getInstance(this.game).getNinja().getBody().applyLinearImpulse(new Vector2(-0.5f, 0), GameController.getInstance(this.game).getNinja().getBody().getWorldCenter(), true);
            else if (GameController.getInstance(this.game).getNinja().newPosition.z == 1)
                GameController.getInstance(this.game).getNinja().getBody().applyLinearImpulse(new Vector2(0.5f, 0), GameController.getInstance(this.game).getNinja().getBody().getWorldCenter(), true);

        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            if (((ElementModel) body.getUserData()).isFlaggedToBeRemoved()) {
                GameModel.getInstance(null).remove((ElementModel) body.getUserData());
                world.destroyBody(body);
            }
        }

        updateExitVariables(delta);
    }

    /**
     * Check's and updates exit possible status
     */
    private void updateExitVariables(float delta) {

        for (ExitDoorModel doorModel : GameModel.getInstance(null).getExitDoors()) {
            if (!doorModel.isCharacterContact()) {
                timeToExit = TIME_BEFORE_EXIT;
                return;
            }
        }

        if (timeToExit <= 0)
            this.state = GameStatus.VICTORY;
        else
            timeToExit -= delta;
    }

    /**
     * Robot shoot action
     */
    public void robotShoot() {

        if (GameController.getInstance(this.game).timeToNextShoot < 0) {
            if (SettingsScreen.soundActive)
                SettingsScreen.shootSound.play();

            GameModel.getInstance(this.game).createBullet(GameView.getInstance(this.game).getRobot().getElement().getX(), GameView.getInstance(this.game).getRobot().getElement().getY(), ((RobotView) GameView.getInstance(this.game).getRobot()).isRunningRight());
            ((RobotView) GameView.getInstance(this.game).getRobot()).setShoot(true);
            GameController.getInstance(this.game).timeToNextShoot = TIME_FOR_NEXT_SHOOT;
        }
    }

    /**
     * Disables a particular body
     *
     * @param body body to disabled
     */
    public void disablesBody(Body body) {
        body.setType(BodyDef.BodyType.StaticBody);
        body.getFixtureList().get(0).setSensor(true);
    }

    /**
     * Dispose all world created bodies
     */
    public void dispose() {

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);
        for (Body body : bodies) {
            GameModel.getInstance(null).remove((ElementModel) body.getUserData());
            world.destroyBody(body);
        }

        GameModel.getInstance(this.game).setInstance(null);
        instance = null;
    }

}
