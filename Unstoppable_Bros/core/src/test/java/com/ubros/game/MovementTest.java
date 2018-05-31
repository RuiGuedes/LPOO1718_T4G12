package com.ubros.game;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.Elements.BulletBody;
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.Elements.EnemyBody;
import com.ubros.game.Controller.Elements.LimitBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.BulletModel;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.Model.Elements.EnemyModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.PlatformModel;

import org.junit.Assert;
import org.junit.Test;

public class MovementTest {

    @Test
    public void characterMove(){
        float x = 4, y = 1, delta = .1f;

        World world = new World(new Vector2(0, 0), true);
        CharacterModel robotModel = new CharacterModel(x,y,0);
        CharacterBody robotBody = new CharacterBody(world,robotModel,"R");

        robotBody.getBody().applyLinearImpulse(new Vector2(-GameController.PLAYER_SPEED*3, 0), robotBody.getBody().getWorldCenter(), true);
        world.step(1, 6, 2);

        Assert.assertNotEquals(x,robotBody.getX(),delta);
        Assert.assertEquals(y,robotBody.getY(),delta);
    }

    @Test
    public void EnemyMove(){
        float x = 10, y = 5, delta = .1f;
        float[] vertex = {0, -0, 0, -1, 2000f, -1, 2000, 0, 0, 0 };

        World world = new World(new Vector2(0, -0.1f), true);
        EnemyModel model = new EnemyModel(x,y,0,new Polygon(),"D-6-1");
        EnemyBody body = new EnemyBody(world,model);
        LimitModel limitModel = new LimitModel(0,0,0,new Polygon(vertex));
        new LimitBody(world, limitModel, vertex);

        body.updateEnemyPosition(2);
        world.step(1 / 60f, 6, 2);

        Assert.assertNotEquals(x/PlayGameScreen.PIXEL_TO_METER,body.getX(),delta);
        Assert.assertEquals(0,body.getBody().getLinearVelocity().y,delta);
    }

    @Test
    public void BulletMove(){
        float x = 10, y = 0, delta = .1f;

        World world = new World(new Vector2(0, -10), true);
        BulletModel model = new BulletModel(x,y,0);
        BulletBody body = new BulletBody(world, model,true);

        world.step(1 / 60f, 6, 2);

        Assert.assertNotEquals(x,body.getX(),delta);
        Assert.assertEquals(y,body.getY(),delta);
    }

    @Test
    public void PlatformMove(){
        float x = 10, y = 0, delta = .1f;
        float[] vertex = {0, -0, 1.28f, -0, 1.28f, 0.3f, 0, 0.3f ,0, -0};

        World world = new World(new Vector2(0, -10), true);
        PlatformModel model = new PlatformModel(x,y,0,new Polygon(),"U-5-barrel.png-3");
        PlatformBody body = new PlatformBody(world, model,vertex);

        body.setLinearVelocity(true);
        world.step(1, 6, 2);

        Assert.assertEquals(x,body.getX(),delta);
        Assert.assertNotEquals(y,body.getY(),delta);
    }
}
