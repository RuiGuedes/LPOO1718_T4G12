package com.ubros.game.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ubros.game.Controller.Elements.BulletBody;
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

    public enum GameStatus {PLAYING, PAUSE, GAMEOVER, VICTORY}

    public final static float PLAYER_SPEED = 0.3f;

    private GameStatus state;

    /**
     * World considered gravity value
     */
    private float GRAVITY = -10;

    /**
     * World considered gravity value
     */
    private float TIME_FOR_NEXT_SHOOT = 0.2f;

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * Game object
     */
    private UbrosGame game;

    public void setGame(UbrosGame game) {
        this.game = game;
    }

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The physics box2d debug renderer
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     *
     */
    private int remainingObjectives;

    private float timeToNextShoot;

    private CharacterBody robot;

    private CharacterBody ninja;

    private List<MechanismBody> mechanismBodies = new ArrayList<MechanismBody>();

    private List<PlatformBody> platformBodies = new ArrayList<PlatformBody>();

    private List<ObjectiveBody> objectiveBodies = new ArrayList<ObjectiveBody>();

    private List<PortalBody> portalBodies = new ArrayList<PortalBody>();

    private List<ExitDoorBody> exitDoorBodies = new ArrayList<ExitDoorBody>();

    private List<EnemyBody> enemyBodies = new ArrayList<EnemyBody>();

    private  List<ObjectBody> objectBodies = new ArrayList<ObjectBody>();

    public List<BulletBody> bulletBodies = new ArrayList<BulletBody>();

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    private GameController(UbrosGame game) {

        this.game = game;
        this.state = GameStatus.PLAYING;
        this.world = new World(new Vector2(0, GRAVITY), true);
        this.debugRenderer = new Box2DDebugRenderer();

        createCharacters();
        createGround();
        createAcid();
        createMechanisms();
        createObjectives();
        createPortals();
        createExitDoors();
        createObjects();
        createEnemys();

        this.world.setContactListener(new MyContactListener());
    }

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

    public GameStatus getState() {
        return state;
    }

    public void setState(GameStatus state) {
        this.state = state;
    }

    private void createCharacters() {
        robot = new CharacterBody(this.world, GameModel.getInstance(this.game).getRobot(), "RobotBounds");
        ninja = new CharacterBody(this.world, GameModel.getInstance(this.game).getNinja(), "NinjaBounds");
    }

    private void createGround() {
        List<LimitModel> limits = GameModel.getInstance(this.game).getLimits();
        for (LimitModel limit : limits)
            new LimitBody(this.world, limit, limit.getShape().getVertices());
    }

    private void createAcid() {
        List<DangerZoneModel> acidRegions = GameModel.getInstance(this.game).getDangerZones();
        for (DangerZoneModel acid : acidRegions)
            new DangerZoneBody(this.world, acid, acid.getShape().getVertices());
    }

    private void createMechanisms() {

        int pointer = 0;
        createPlatforms();

        List<MechanismModel> mechanisms = GameModel.getInstance(this.game).getMechanisms();
        for (MechanismModel mechanism : mechanisms) {
            mechanismBodies.add(new MechanismBody(this.world, mechanism, mechanism.getShape().getVertices()));
            mechanism.setPlatform(platformBodies.get(pointer++));
        }
    }

    private void createPlatforms() {
        List<PlatformModel> platforms = GameModel.getInstance(this.game).getPlatforms();
        for (PlatformModel platform : platforms)
            platformBodies.add(new PlatformBody(this.world, platform, platform.getShape().getVertices()));
    }

    private void createObjectives() {
        List<ObjectiveModel> objectives = GameModel.getInstance(this.game).getObjectives();
        for (ObjectiveModel objective : objectives)
            objectiveBodies.add(new ObjectiveBody(this.world, objective, objective.getShape().getVertices()));

        this.remainingObjectives = objectives.size();
    }

    private void createPortals() {
        List<PortalModel> portalModels = GameModel.getInstance(this.game).getPortals();
        for (PortalModel portal : portalModels)
            portalBodies.add(new PortalBody(this.world, portal, portal.getShape().getVertices()));

        for (PortalBody portalBody : portalBodies) {
            for (PortalModel portalModel : portalModels) {
                if ((portalModel.getName().charAt(0) == ((PortalModel) portalBody.getModel()).getName().charAt(0)) &&
                        (portalModel.getName().charAt(1) != ((PortalModel) portalBody.getModel()).getName().charAt(1)))
                    portalModel.setPortalDestiny(portalBody);
            }
        }
    }

    private void createExitDoors() {
        List<ExitDoorModel> exitDoorModels = GameModel.getInstance(this.game).getExitDoors();
        for (ExitDoorModel exitDoor : exitDoorModels)
            exitDoorBodies.add(new ExitDoorBody(this.world, exitDoor, exitDoor.getShape().getVertices()));
    }

    private void createObjects() {
        List<ObjectModel> objectModels = GameModel.getInstance(this.game).getObjects();
        for (ObjectModel object : objectModels)
            objectBodies.add(new ObjectBody(this.world, object, object.getShape().getVertices()));
    }

    private void createEnemys() {
        List<EnemyModel> enemyModels = GameModel.getInstance(this.game).getEnemys();
        for (EnemyModel enemy : enemyModels)
            enemyBodies.add(new EnemyBody(this.world, enemy));
    }

    /////////////////
    // GET METHODS //
    /////////////////

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public CharacterBody getRobot() {
        return robot;
    }

    public int getRemainingObjectives() {
        return remainingObjectives;
    }

    public void setRemainingObjectives() {
        this.remainingObjectives--;
    }

    public CharacterBody getNinja() {
        return ninja;
    }

    public List<MechanismBody> getMechanismBodies() {
        return mechanismBodies;
    }

    public List<PlatformBody> getPlatformBodies() {
        return platformBodies;
    }

    public List<ObjectiveBody> getObjectiveBodies() {
        return objectiveBodies;
    }

    public List<EnemyBody> getEnemyBodies() {
        return enemyBodies;
    }

    public UbrosGame getGame() {
        return game;
    }

    public List<ExitDoorBody> getExitDoorBodies() {
        return exitDoorBodies;
    }

    public List<ObjectBody> getObjectBodies() {
        return objectBodies;
    }

    ////////////
    // OTHERS //
    ////////////

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
    }

    /**
     * Robot shoot action
     */
    public void robotShoot() {

        if (GameController.getInstance(this.game).timeToNextShoot < 0) {
            if(SettingsScreen.soundActive)
                SettingsScreen.shootSound.play();

            GameModel.getInstance(this.game).createBullet(GameView.getInstance(this.game).getRobot().getElement().getX(), GameView.getInstance(this.game).getRobot().getElement().getY(), ((RobotView) GameView.getInstance(this.game).getRobot()).isRunningRight());
            ((RobotView) GameView.getInstance(this.game).getRobot()).shoot = true;
            GameController.getInstance(this.game).timeToNextShoot = TIME_FOR_NEXT_SHOOT;
        }
    }

    /**
     * Disables a particular body
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
