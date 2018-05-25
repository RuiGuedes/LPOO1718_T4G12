package com.ubros.game.Model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.AcidModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.UbrosGame;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    /**
     * Private values used to determine objects from tiled map
     */
    int GROUND_BODY = 3; int ACID_BODY = 4; int MECHANISM_BODY = 5; int PLATFORM_BODY = 6; int OBJECTIVE_BODY = 7;

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
    private List<AcidModel> acidRegions = new ArrayList<AcidModel>();

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
    private  List<ObjectiveModel> objectives = new ArrayList<ObjectiveModel>();

    /**
     * Constructs a game with a.space ship in the middle of the
     * arena and a certain number of asteroids in different sizes.
     */
    private GameModel(UbrosGame game) {
        this.game = game;
        initializeElementsModel();
    }

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

    private void initializeElementsModel() {
        createCharacters();
        createGround();
        createDangerZone();
        createMechanisms();
        createPlatforms();
        createObjectives();
    }

    private void createCharacters() {
        this.robot = new CharacterModel(800/ PlayGameScreen.PIXEL_TO_METER,400/ PlayGameScreen.PIXEL_TO_METER,0);
        this.ninja = new CharacterModel(1600/ PlayGameScreen.PIXEL_TO_METER,900/ PlayGameScreen.PIXEL_TO_METER,0);
    }

    public CharacterModel getRobot() {
        return robot;
    }

    public CharacterModel getNinja() {
        return ninja;
    }

    private void createGround() {
        for(MapObject object : UbrosGame.map.getLayers().get(GROUND_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            limits.add(new LimitModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }
    }

    public List<LimitModel> getLimits() {
        return limits;
    }

    private void createDangerZone() {
        for(MapObject object : UbrosGame.map.getLayers().get(ACID_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            acidRegions.add(new AcidModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }
    }

    public List<AcidModel> getAcidRegions() {
        return acidRegions;
    }

    private void createMechanisms() {
        for(MapObject object : UbrosGame.map.getLayers().get(MECHANISM_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            mechanisms.add(new MechanismModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }
    }

    public List<MechanismModel> getMechanisms() {
        return mechanisms;
    }


    private void createPlatforms() {
        for(MapObject object : UbrosGame.map.getLayers().get(PLATFORM_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            platforms.add(new PlatformModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon, object.getName()));
        }
    }

    public List<PlatformModel> getPlatforms() {
        return platforms;
    }

    private void createObjectives() {
        for(MapObject object : UbrosGame.map.getLayers().get(OBJECTIVE_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            objectives.add(new ObjectiveModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon, object.getName()));
        }
    }

    public List<ObjectiveModel> getObjectives() {
        return objectives;
    }
}
