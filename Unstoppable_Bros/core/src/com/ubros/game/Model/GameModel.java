package com.ubros.game.Model;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.math.Polygon;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.UbrosGame;

import java.util.ArrayList;
import java.util.List;

public class GameModel {

    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;

    /**
     * Game object
     */
    private final UbrosGame game;


    /**
     * The asteroids roaming around in this game.
     */
    private List<LimitModel> limits;

    /**
     * Constructs a game with a.space ship in the middle of the
     * arena and a certain number of asteroids in different sizes.
     */
    private GameModel(UbrosGame game) {

        this.game = game;
        this.limits = new ArrayList<LimitModel>();

        for(MapObject object : UbrosGame.map.getLayers().get(2).getObjects().getByType(PolygonMapObject.class)) {

            Polygon polygon = ((PolygonMapObject) object).getPolygon();
            limits.add(new LimitModel(polygon.getX(), polygon.getY(), 0, polygon));
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

    public List<LimitModel> getLimits() {
        return limits;
    }
}
