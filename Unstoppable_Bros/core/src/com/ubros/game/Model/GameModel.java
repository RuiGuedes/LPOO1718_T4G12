package com.ubros.game.Model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.DangerZoneModel;
import com.ubros.game.Model.Elements.ExitDoorModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.Model.Elements.PortalModel;
import com.ubros.game.UbrosGame;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    /**
     * Private values used to determine objects from tiled map
     */
    private int GROUND_BODY = 3;
    private int DANGER_ZONE_BODY = 4;
    private int MECHANISM_BODY = 5;
    private int PLATFORM_BODY = 6;
    private int OBJECTIVE_BODY = 7;
    private int PORTAL_BODY = 8;
    private int EXIT_DOOR_BODY = 9;

    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    /**
     * Game object
     */
    private final UbrosGame game;

    /**
     *
     */
    private CharacterModel robot;

    /**
     *
     */
    private CharacterModel ninja;

    /**
     * The asteroids roaming around in this game.
     */
    private List<LimitModel> limits = new ArrayList<LimitModel>();

    /**
     * The asteroids roaming around in this game.
     */
    private List<DangerZoneModel> dangerZones = new ArrayList<DangerZoneModel>();

    /**
     * The asteroids roaming around in this game.
     */
    private List<MechanismModel> mechanisms = new ArrayList<MechanismModel>();

    /**
     * The asteroids roaming around in this game.
     */
    private List<PlatformModel> platforms = new ArrayList<PlatformModel>();

    /**
     *
     */
    private List<ObjectiveModel> objectives = new ArrayList<ObjectiveModel>();

    /**
     *
     */
    private List<PortalModel> portals = new ArrayList<PortalModel>();

    /**
     *
     */
    private List<ExitDoorModel> exitDoors = new ArrayList<ExitDoorModel>();

    /**
     * Constructs a game with a.space ship in the middle of the
     * arena and a certain number of asteroids in different sizes.
     */
    private GameModel(UbrosGame game) {
        this.game = game;
        initializeElementsModel();
    }

    /**
     * Initializes all game possible models
     */
    private void initializeElementsModel() {
        createCharacters();
        createLimits();
        createDangerZone();
        createMechanisms();
        createPlatforms();
        createObjectives();
        createPortals();
        createExitDoors();
    }


    ////////////////////
    // CREATE METHODS //
    ////////////////////

    /**
     * Creates both robot and ninja models
     */
    private void createCharacters() {
        this.robot = new CharacterModel(800 / PlayGameScreen.PIXEL_TO_METER, 400 / PlayGameScreen.PIXEL_TO_METER, 0);
        this.ninja = new CharacterModel(1600 / PlayGameScreen.PIXEL_TO_METER, 1000 / PlayGameScreen.PIXEL_TO_METER, 0);
    }

    /**
     * Creates all limit models
     */
    private void createLimits() {
        for (MapObject object : UbrosGame.map.getLayers().get(GROUND_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            limits.add(new LimitModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }
    }

    /**
     * Creates all possible danger zone models
     */
    private void createDangerZone() {
        for (MapObject object : UbrosGame.map.getLayers().get(DANGER_ZONE_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            dangerZones.add(new DangerZoneModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }
    }

    /**
     * Creates all mechanism models
     */
    private void createMechanisms() {
        for (MapObject object : UbrosGame.map.getLayers().get(MECHANISM_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            mechanisms.add(new MechanismModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }
    }

    /**
     * Creates all platform models
     */
    private void createPlatforms() {
        for (MapObject object : UbrosGame.map.getLayers().get(PLATFORM_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            platforms.add(new PlatformModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon, object.getName()));
        }
    }

    /**
     * Creates all objective models
     */
    private void createObjectives() {
        for (MapObject object : UbrosGame.map.getLayers().get(OBJECTIVE_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            objectives.add(new ObjectiveModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon, object.getName()));
        }
    }

    /**
     * Creates all portal models
     */
    private void createPortals() {
        for (MapObject object : UbrosGame.map.getLayers().get(PORTAL_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            portals.add(new PortalModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon, object.getName()));
        }
    }

    /**
     * Creates all exit door models and associate respective character
     */
    private void createExitDoors() {
        for (MapObject object : UbrosGame.map.getLayers().get(EXIT_DOOR_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            if(object.getName().equals("R"))
                exitDoors.add(new ExitDoorModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon, "RobotExitDoor"));
            else
                exitDoors.add(new ExitDoorModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon, "NinjaExitDoor"));

        }
    }


    /////////////////
    // GET METHODS //
    /////////////////

    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance(UbrosGame game) {
        if (instance == null)
            instance = new GameModel(game);
        return instance;
    }

    /**
     * Function responsible to retrieve robot model
     *
     * @return robot model
     */
    public CharacterModel getRobot() {
        return robot;
    }

    /**
     * Function responsible to retrieve ninja model
     *
     * @return ninja model
     */
    public CharacterModel getNinja() {
        return ninja;
    }

    /**
     * Function responsible to retrieve list of limit models
     *
     * @return list of limits model
     */
    public List<LimitModel> getLimits() {
        return limits;
    }

    /**
     * Function responsible to retrieve list of danger zone models
     *
     * @return list of danger zone models
     */
    public List<DangerZoneModel> getDangerZones() {
        return dangerZones;
    }

    /**
     * Function responsible to retrieve list of mechanism models
     *
     * @return list of mechanism models
     */
    public List<MechanismModel> getMechanisms() {
        return mechanisms;
    }

    /**
     * Function responsible to retrieve list of platform models
     *
     * @return list of platform models
     */
    public List<PlatformModel> getPlatforms() {
        return platforms;
    }

    /**
     * Function responsible to retrieve list of objective models
     *
     * @return list of objective models
     */
    public List<ObjectiveModel> getObjectives() {
        return objectives;
    }

    /**
     * Function responsible to retrieve list of portal models
     *
     * @return list of portal models
     */
    public List<PortalModel> getPortals() {
        return portals;
    }

    /**
     * Function responsible to retrieve list of exit door models
     * @return list of exit door models
     */
    public List<ExitDoorModel> getExitDoors() {
        return exitDoors;
    }
}
