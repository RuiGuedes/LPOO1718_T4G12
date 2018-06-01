package com.ubros.game.Controller;

import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.Elements.DangerZoneBody;
import com.ubros.game.Controller.Elements.EnemyBody;
import com.ubros.game.Controller.Elements.ExitDoorBody;
import com.ubros.game.Controller.Elements.LimitBody;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.Model.Elements.DangerZoneModel;
import com.ubros.game.Model.Elements.EnemyModel;
import com.ubros.game.Model.Elements.ExitDoorModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.UbrosGame;

import org.junit.Assert;
import org.junit.Test;

public class ContactTest {

    @Test
    public void RobotContactDangerTest(){
        float x = 4, y = 1, delta = .00001f;
        float[] dangerVertex = {0, -0, 1.28f, -0, 1.28f, 0.5f, 0, 0.5f ,0, -0};
        float[] limitVertex = {0, -0, 0, -1, 2000, -1, 2000, 0, 0, 0 };

<<<<<<< Updated upstream
       // TmxMapLoader mapLoader = new TmxMapLoader();
       // UbrosGame.map = mapLoader.load("UbrosMap/UbrosMap.tmx");
       // UbrosGame.map = new TmxMapLoader(new ExternalFileHandleResolver()).load("UbrosMap/UbrosMap.tmx");
       // UbrosGame game = new UbrosGame();
=======
UbrosGame.map = new TmxMapLoader().load("assets/UbrosMap/UbrosMap.tmx");
>>>>>>> Stashed changes

        World world = new World(new Vector2(0, -10), true);
        CharacterModel robotModel = new CharacterModel(x,y,0);
        CharacterBody robotBody = new CharacterBody(world,robotModel,"RobotBounds");
        DangerZoneModel dangerModel = new DangerZoneModel(x, y,0,new Polygon());
        new DangerZoneBody(world, dangerModel, dangerVertex);
        LimitModel limitModel = new LimitModel(0,0,0,new Polygon(limitVertex));
        new LimitBody(world, limitModel, limitVertex);

        world.setContactListener(new MyContactListener());
        robotBody.getBody().applyLinearImpulse(new Vector2(-GameController.PLAYER_SPEED*3, 0), robotBody.getBody().getWorldCenter(), true);
        world.step(1/60f, 6, 2);

        Assert.assertNotEquals(x,robotBody.getX(), delta);
        Assert.assertNotEquals(y,robotBody.getY(), delta);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().x, delta);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().y, delta);
    }

    @Test
    public void CharacterContactEnemyTest(){
        float x = 4, y = 1, delta = .1f;
        float[] limitVertex = {0, -0, 0, -1, 2000, -1, 2000, 0, 0, 0 };

        World world = new World(new Vector2(0, -10), true);
        CharacterModel robotModel = new CharacterModel(x,y,0);
        CharacterBody robotBody = new CharacterBody(world,robotModel,"RobotBounds");
        EnemyModel enemyModel = new EnemyModel(x+1,y,0,new Polygon(),"D-6-3");
        new EnemyBody(world,enemyModel);
        LimitModel limitModel = new LimitModel(0,0,0,new Polygon(limitVertex));
        new LimitBody(world, limitModel, limitVertex);

        world.setContactListener(new MyContactListener());
        robotBody.getBody().applyLinearImpulse(new Vector2(GameController.PLAYER_SPEED*5, 0), robotBody.getBody().getWorldCenter(), true);
        world.step(1/60f, 6, 2);

        //Assert.assertEquals(x,robotBody.getX(),delta);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().x, delta);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().y, delta);
    }

    @Test
    public void CharacterContactExitDoorTest(){
        float x = 4, y = 1, delta = .1f;
        float[] limitVertex = {0, -0, 0, -1, 2000, -1, 2000, 0, 0, 0 };
        float[] doorVertex = {0, 0, 200, 0, 200, 300, 0, 300, 0, 0};

        World world = new World(new Vector2(0, -10), true);
        CharacterModel robotModel = new CharacterModel(x,y,0);
        CharacterBody robotBody = new CharacterBody(world,robotModel,"RobotBounds");
        ExitDoorModel doorModel = new ExitDoorModel(x+2,0,0,new Polygon(),"RobotExitDoor");
        ExitDoorBody doorBody= new ExitDoorBody(world,doorModel, doorVertex);
        LimitModel limitModel = new LimitModel(0,0,0,new Polygon(limitVertex));
        new LimitBody(world, limitModel, limitVertex);

        world.setContactListener(new MyContactListener());
        System.out.println(robotBody.getX() + "  ,   " + robotBody.getY());
       // while (!doorModel.isCharacterContact()){
            robotBody.getBody().applyLinearImpulse(new Vector2(GameController.PLAYER_SPEED*3, 0), robotBody.getBody().getWorldCenter(), true);
            world.step(1/60f, 6, 2);
            System.out.println(robotBody.getX() + "  ,   " + robotBody.getY());
       // }
System.out.println(doorModel.isCharacterContact());
        /*robotBody.getBody().applyLinearImpulse(new Vector2(GameController.PLAYER_SPEED*30, 0), robotBody.getBody().getWorldCenter(), true);
        world.step(1, 6, 2);
System.out.println(robotBody.getX() + "  ,   " + robotBody.getY());
        System.out.println(robotBody.getBody().getLinearVelocity().x + "  ,   " + robotBody.getBody().getLinearVelocity().y);
        robotBody.getBody().applyLinearImpulse(new Vector2(GameController.PLAYER_SPEED*8, 0), robotBody.getBody().getWorldCenter(), true);
        world.step(1, 6, 2);
    System.out.println(robotBody.getX() + "  ,   " + robotBody.getY());
        System.out.println(robotBody.getBody().getLinearVelocity().x + "  ,   " + robotBody.getBody().getLinearVelocity().y);*/
        Assert.assertTrue(doorModel.isCharacterContact());

        //robotBody.getBody().applyLinearImpulse(new Vector2(GameController.PLAYER_SPEED*3, 0), robotBody.getBody().getWorldCenter(), true);
        world.step(1, 6, 2);
        System.out.println(robotBody.getX() + "  ,   " + robotBody.getY());
        Assert.assertFalse(doorModel.isCharacterContact());
    }

    @Test
    public void CharacterContactMechanismTest(){

    }
}
