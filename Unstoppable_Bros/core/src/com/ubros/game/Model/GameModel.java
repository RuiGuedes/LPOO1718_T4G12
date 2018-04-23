package com.ubros.game.Model;

public class GameModel {

    /**
     * The singleton instance of the game model
     */
    private static GameModel instance;


    /**
     * Returns a singleton instance of the game model
     *
     * @return the singleton instance
     */
    public static GameModel getInstance() {
        if (instance == null)
            instance = new GameModel();
        return instance;
    }


}
