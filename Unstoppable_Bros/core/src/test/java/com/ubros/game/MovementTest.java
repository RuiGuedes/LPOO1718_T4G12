package com.ubros.game;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.Elements.BulletBody;
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.Elements.EnemyBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Model.Elements.BulletModel;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.Model.Elements.EnemyModel;
import com.ubros.game.Model.Elements.PlatformModel;

import org.junit.Assert;
import org.junit.Test;

public class MovementTest {

    @Test
    public void characterMove(){
        float x = 4, y = 1, delta = .1f;

        World world = new World(new Vector2(0, -10), true);
        CharacterModel robotModel = new CharacterModel(x,y,0);
        CharacterBody robotBody = new CharacterBody(world,robotModel,"R");

        robotBody.getBody().applyLinearImpulse(new Vector2(-GameController.PLAYER_SPEED, 0), robotBody.getBody().getWorldCenter(), true);
        robotBody.setTransform(x+(robotBody.getBody().getLinearVelocity().x*1),y+(robotBody.getBody().getLinearVelocity().y*1),0);

        Assert.assertEquals(x+(-GameController.PLAYER_SPEED),robotBody.getX(),delta);
        Assert.assertEquals(y,robotBody.getY(),delta);
    }

    @Test
    public void EnemyMove(){
        float x = 10, y = 0, delta = .1f;

        World world = new World(new Vector2(0, -10), true);
        EnemyModel model = new EnemyModel(x,y,0,new Polygon(),"D-6-1");
        EnemyBody body = new EnemyBody(world,model);

        body.getBody().setLinearVelocity(-0.5f, body.getBody().getLinearVelocity().y);
        body.updateEnemyPosition(2); // NÃO FUNCIONA !!!!!!!!!

        Assert.assertNotEquals(0,body.getBody().getLinearVelocity().x,delta);
        Assert.assertEquals(0,body.getBody().getLinearVelocity().y,delta);
    }

    @Test
    public void BulletMove(){
        float x = 10, y = 0, delta = .1f;

        World world = new World(new Vector2(0, -10), true);
        BulletModel model = new BulletModel(x,y,0);
        BulletBody body = new BulletBody(world, model,true);

        Assert.assertNotEquals(0,body.getBody().getLinearVelocity().x,delta);
        Assert.assertEquals(0,body.getBody().getLinearVelocity().y,delta);
    }

    @Test
    public void PlatformMove(){
        float x = 10, y = 0, delta = .1f;
        float[] vertex = {0, -0, 1.28f, -0, 1.28f, 0.3f, 0, 0.3f ,0, -0};

        World world = new World(new Vector2(0, -10), true);
        PlatformModel model = new PlatformModel(x,y,0,new Polygon(),"U-5-barrel.png-3");
        PlatformBody body = new PlatformBody(world, model,vertex);

        body.setLinearVelocity(true);

        Assert.assertEquals(0,body.getBody().getLinearVelocity().x,delta);
        Assert.assertNotEquals(0,body.getBody().getLinearVelocity().y,delta);
    }
}
