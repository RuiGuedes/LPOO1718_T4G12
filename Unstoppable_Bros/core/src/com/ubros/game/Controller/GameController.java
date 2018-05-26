package com.ubros.game.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.Elements.AcidBody;
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.Elements.LimitBody;
import com.ubros.game.Controller.Elements.MechanismBody;
import com.ubros.game.Controller.Elements.ObjectiveBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Controller.Elements.PortalBody;
import com.ubros.game.Model.Elements.AcidModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.Elements.PortalModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.UbrosGame;

import java.util.ArrayList;
import java.util.List;

public class GameController {

    public static enum GameStatus {PLAYING, PAUSE, GAMEOVER, VICTORY}

    private GameStatus state;

    /**
     * World considered gravity value
     */
    private float GRAVITY = -10;

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * Game object
     */
    private final UbrosGame game;

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

    private CharacterBody robot;

    private CharacterBody ninja;

    private List<MechanismBody> mechanismBodies = new ArrayList<MechanismBody>();

    private List<PlatformBody> platformBodies = new ArrayList<PlatformBody>();

    private List<ObjectiveBody> objectiveBodies = new ArrayList<ObjectiveBody>();

    private List<PortalBody> portalBodies = new ArrayList<PortalBody>();

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
        List<AcidModel> acidRegions = GameModel.getInstance(this.game).getAcidRegions();
        for (AcidModel acid : acidRegions)
            new AcidBody(this.world, acid, acid.getShape().getVertices());
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

    public void createPortals() {
        List<PortalModel> portalModels = GameModel.getInstance(this.game).getPortals();
        for (PortalModel portal : portalModels)
            portalBodies.add(new PortalBody(this.world, portal, portal.getShape().getVertices()));

        for(PortalBody portalBody : portalBodies) {
            for(PortalModel portalModel : portalModels) {
                if((portalModel.getName().charAt(0) == ((PortalModel)portalBody.getModel()).getName().charAt(0)) &&
                   (portalModel.getName().charAt(1) != ((PortalModel)portalBody.getModel()).getName().charAt(1)))
                    portalModel.setPortalDestiny(portalBody);
            }
        }
    }


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

    public UbrosGame getGame() {
        return game;
    }

    public void update(float delta) {

        if(GameController.getInstance(this.game).getNinja().setTransformFlag) {
            GameController.getInstance(this.game).getNinja().setTransform(GameController.getInstance(this.game).getNinja().newPosition.x, GameController.getInstance(this.game).getNinja().newPosition.y, 0);
            GameController.getInstance(this.game).getNinja().setTransformFlag = false;

            if(GameController.getInstance(this.game).getNinja().newPosition.z == 0)
                GameController.getInstance(this.game).getNinja().getBody().applyLinearImpulse(new Vector2(-0.5f,0),GameController.getInstance(this.game).getNinja().getBody().getWorldCenter(), true);
            else if(GameController.getInstance(this.game).getNinja().newPosition.z == 1)
                GameController.getInstance(this.game).getNinja().getBody().applyLinearImpulse(new Vector2(0.5f,0),GameController.getInstance(this.game).getNinja().getBody().getWorldCenter(), true);

        }

    }

}
