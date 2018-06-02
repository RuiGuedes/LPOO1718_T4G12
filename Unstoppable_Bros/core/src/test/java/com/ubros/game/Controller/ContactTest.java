package com.ubros.game.Controller;


import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.Elements.ObjectBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Gui.MainMenuScreen;
import com.ubros.game.Model.Elements.EnemyModel;
import com.ubros.game.Model.Elements.ExitDoorModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.UbrosGame;
import com.ubros.game.View.GameView;

import org.junit.Assert;
import org.junit.Test;

public class ContactTest extends GameTest{

    @Test
    public void RobotContactDangerTest(){
        UbrosGame game = new UbrosGame();
        game.setAssetManager(new AssetManager());
        UbrosGame.mainMenu = new MainMenuScreen(game);
        TmxMapLoader mapLoader = new TmxMapLoader();
        UbrosGame.map = mapLoader.load("UbrosMap/UbrosMapTest.tmx");
        GameController.getInstance(game);
        GameView.getInstance(game);
        CharacterBody robotBody = GameController.getInstance(game).getRobot();

        robotBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED, 0);
        GameController.getInstance(game).getWorld().step(1 / 60f, 6, 2);

        while (((EnemyModel)GameController.getInstance(game).getEnemyBodies().get(0).getModel()).getHealth() > 0) {
            GameModel.getInstance(game).createBullet(GameView.getInstance(game).getRobot().getElement().getX(), GameView.getInstance(game).getRobot().getElement().getY(), false);
            GameController.getInstance(game).getWorld().step(1 / 60f, 6, 2);
        }

        Assert.assertEquals(0,((EnemyModel)GameController.getInstance(game).getEnemyBodies().get(0).getModel()).getHealth());

        while(GameController.getInstance(game).getRobot().getBody().getLinearVelocity().x != 0){
            robotBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED*5, 0);
            GameController.getInstance(game).getWorld().step(1 / 60f, 6, 2);
        }

        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().x, .0001f);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().y, .0001f);
    }

    @Test
    public void CharacterContactEnemyTest(){
        UbrosGame game = new UbrosGame();
        game.setAssetManager(new AssetManager());
        UbrosGame.mainMenu = new MainMenuScreen(game);
        TmxMapLoader mapLoader = new TmxMapLoader();
        UbrosGame.map = mapLoader.load("UbrosMap/UbrosMapTest.tmx");
        GameController.getInstance(game);
        GameView.getInstance(game);
        CharacterBody robotBody = GameController.getInstance(game).getRobot();

        robotBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED * 5, 0);

        while(robotBody.getBody().getLinearVelocity().x != 0) {
            robotBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED * 5, 0);
            GameController.getInstance(game).getWorld().step(1 / 60f, 6, 2);
        }

        Assert.assertEquals(GameController.getInstance(game).getEnemyBodies().get(0).getX(),robotBody.getX(),0.6f);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().x, 0.0001f);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().y, 0.0001f);
    }

    @Test
    public void CharacterContactExitDoorTest(){
        UbrosGame game = new UbrosGame();
        game.setAssetManager(new AssetManager());
        UbrosGame.mainMenu = new MainMenuScreen(game);
        TmxMapLoader mapLoader = new TmxMapLoader();
        UbrosGame.map = mapLoader.load("UbrosMap/UbrosMapTest.tmx");
        GameController.getInstance(game);
        GameView.getInstance(game);
        CharacterBody robotBody = GameController.getInstance(game).getRobot();
        CharacterBody ninjaBody = GameController.getInstance(game).getNinja();

        while (!((ExitDoorModel)GameController.getInstance(game).getExitDoors().get(0).getModel()).isCharacterContact()){
            robotBody.getBody().setLinearVelocity(GameController.PLAYER_SPEED*3, 0);
            GameController.getInstance(game).getWorld().step(1/60f, 6, 2);
        }

        Assert.assertEquals(GameController.getInstance(game).getExitDoors().get(0).getX(),robotBody.getX(),0.6f);
        Assert.assertEquals(GameController.getInstance(game).getExitDoors().get(0).getY(),robotBody.getY(),0.5f);

        while (!((ExitDoorModel)GameController.getInstance(game).getExitDoors().get(1).getModel()).isCharacterContact()){
            ninjaBody.getBody().setLinearVelocity(GameController.PLAYER_SPEED*3, 0);
            GameController.getInstance(game).getWorld().step(1/60f, 6, 2);
        }

        Assert.assertEquals(GameController.getInstance(game).getExitDoors().get(1).getX(),ninjaBody.getX(),0.6f);
        Assert.assertEquals(GameController.getInstance(game).getExitDoors().get(1).getY(),ninjaBody.getY(),0.5f);
    }

    @Test
    public void CharacterContactMechanismAndPortalTest(){
        UbrosGame game = new UbrosGame();
        game.setAssetManager(new AssetManager());
        UbrosGame.mainMenu = new MainMenuScreen(game);
        TmxMapLoader mapLoader = new TmxMapLoader();
        UbrosGame.map = mapLoader.load("UbrosMap/UbrosMapTest.tmx");
        GameController.getInstance(game);
        GameView.getInstance(game);
        CharacterBody robotBody = GameController.getInstance(game).getRobot();
        CharacterBody ninjaBody = GameController.getInstance(game).getNinja();

        PlatformBody platformBody= GameController.getInstance(game).getPlatforms().get(0);
        float platformX = platformBody.getX();
        float platformY = platformBody.getY();

        while (!((MechanismModel)GameController.getInstance(game).getMechanisms().get(0).getModel()).isActive()){
            robotBody.getBody().setLinearVelocity(GameController.PLAYER_SPEED, 0);
            GameController.getInstance(game).getWorld().step(1/60f, 6, 2);
        }
        robotBody.getBody().setLinearVelocity(0, 0);
        for (int i=0; i<20; i++)
            GameController.getInstance(game).getWorld().step(1/60f, 6, 2);

        Assert.assertTrue(((MechanismModel)GameController.getInstance(game).getMechanisms().get(0).getModel()).isActive());

        Assert.assertEquals(platformX,platformBody.getX(), 0.001f);
        Assert.assertNotEquals(platformY,platformBody.getY(), 2f);

        while (!ninjaBody.setTransformFlag){
            ninjaBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED*10, 0);
            GameController.getInstance(game).getWorld().step(1/60f, 6, 2);
        }

        Assert.assertEquals(robotBody.getY(), ninjaBody.newPosition.y, 0.1f);
    }

    @Test
    public void CharacterContactObjectiveTest(){
        UbrosGame game = new UbrosGame();
        game.setAssetManager(new AssetManager());
        UbrosGame.mainMenu = new MainMenuScreen(game);
        TmxMapLoader mapLoader = new TmxMapLoader();
        UbrosGame.map = mapLoader.load("UbrosMap/UbrosMapTest.tmx");
        GameController.getInstance(game);
        GameView.getInstance(game);
        CharacterBody robotBody = GameController.getInstance(game).getRobot();
        CharacterBody ninjaBody = GameController.getInstance(game).getNinja();

        robotBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED * 5, 0);
        ninjaBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED * 5, 0);

        while(robotBody.getBody().getLinearVelocity().x != 0 || ninjaBody.getBody().getLinearVelocity().x != 0) {
            robotBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED * 5, 0);
            ninjaBody.getBody().setLinearVelocity(-GameController.PLAYER_SPEED * 5, 0);
            GameController.getInstance(game).getWorld().step(1 / 60f, 6, 2);
        }

        Assert.assertEquals(0,GameController.getInstance(game).getRemainingObjectives());
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().x, 0.0001f);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().y, 0.0001f);
        Assert.assertEquals(0,ninjaBody.getBody().getLinearVelocity().x, 0.0001f);
        Assert.assertEquals(0,ninjaBody.getBody().getLinearVelocity().y, 0.0001f);
    }

    @Test
    public void CharacterContactObjectTest(){
        UbrosGame game = new UbrosGame();
        game.setAssetManager(new AssetManager());
        UbrosGame.mainMenu = new MainMenuScreen(game);
        TmxMapLoader mapLoader = new TmxMapLoader();
        UbrosGame.map = mapLoader.load("UbrosMap/UbrosMapTest.tmx");
        GameController.getInstance(game);
        GameView.getInstance(game);
        CharacterBody ninjaBody = GameController.getInstance(game).getNinja();

        ObjectBody objectBody = GameController.getInstance(game).getObjects().get(0);

        while(objectBody.getBody().getLinearVelocity().x != 0) {
            ninjaBody.getBody().setLinearVelocity(GameController.PLAYER_SPEED * 5, 0);
            GameController.getInstance(game).getWorld().step(1 / 60f, 6, 2);
        }

        Assert.assertNotEquals(0, objectBody.getBody().getLinearVelocity().x);
    }
}
