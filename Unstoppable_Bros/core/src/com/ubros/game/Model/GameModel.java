package com.ubros.game.Model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.AcidModel;
import com.ubros.game.Model.Elements.HeroModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.UbrosGame;

import java.util.ArrayList;
import java.util.List;

public class GameModel {


    private int GROUND_BODY = 3;
    private int ACID_BODY = 4;
    private int MECHANISM_BODY = 5;
    private int PLATFORM_BODY = 6;

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
    private HeroModel hero;

    /**
     * The asteroids roaming around in this game.
     */
    private List<LimitModel> limits;

    /**
     * The asteroids roaming around in this game.
     */
    private List<AcidModel> acidRegions;

    /**
     * The asteroids roaming around in this game.
     */
    private List<MechanismModel> mechanisms;

    private List<PlatformModel> platforms = new ArrayList<PlatformModel>();

    /**
     * Constructs a game with a.space ship in the middle of the
     * arena and a certain number of asteroids in different sizes.
     */
    private GameModel(UbrosGame game) {

        this.game = game;
        this.limits = new ArrayList<LimitModel>();
        this.acidRegions = new ArrayList<AcidModel>();
        this.mechanisms = new ArrayList<MechanismModel>();

        this.hero = new HeroModel(1600/ PlayGameScreen.PIXEL_TO_METER,1000/ PlayGameScreen.PIXEL_TO_METER,0);

        //Initialize ground body´s
        for(MapObject object : UbrosGame.map.getLayers().get(GROUND_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            limits.add(new LimitModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }

        //Initialize acid body´s
        for(MapObject object : UbrosGame.map.getLayers().get(ACID_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            acidRegions.add(new AcidModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }

        //Initialize mechanism body´s
        for(MapObject object : UbrosGame.map.getLayers().get(MECHANISM_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            mechanisms.add(new MechanismModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }

        //Initialize mechanism body´s
        for(MapObject object : UbrosGame.map.getLayers().get(PLATFORM_BODY).getObjects().getByType(PolygonMapObject.class)) {
            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            platforms.add(new PlatformModel(polygon.getX() / PlayGameScreen.PIXEL_TO_METER, polygon.getY() / PlayGameScreen.PIXEL_TO_METER, 0, polygon));
        }

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

    public HeroModel getHero() {
        return hero;
    }

    public List<LimitModel> getLimits() {
        return limits;
    }

    public List<AcidModel> getAcidRegions() {
        return acidRegions;
    }

    public List<MechanismModel> getMechanisms() {
        return mechanisms;
    }

    public List<PlatformModel> getPlatforms() {
        return platforms;
    }
}
