package com.ubros.game.Controller;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.Elements.DangerZoneBody;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.Model.Elements.DangerZoneModel;

import org.junit.Assert;
import org.junit.Test;

public class ContactTest {

    @Test
    public void RobotContactDangerTest(){
        float x = 4, y = 1, delta = .1f;
        float[] vertex = {0, -0, 1.28f, -0, 1.28f, 0.3f, 0, 0.3f ,0, -0};

        World world = new World(new Vector2(0, -10), true);
        CharacterModel robotModel = new CharacterModel(x,y,0);
        CharacterBody robotBody = new CharacterBody(world,robotModel,"R");
        DangerZoneModel dangerModel = new DangerZoneModel(x, y,0,new Polygon());
        DangerZoneBody dangerBody = new DangerZoneBody(world, dangerModel, vertex);
        world.setContactListener(new MyContactListener());

        robotBody.getBody().setLinearVelocity(0f,0f);

        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().x, delta);
        Assert.assertEquals(0,robotBody.getBody().getLinearVelocity().y, delta);
    }

    @Test
    public void CharacterContactEnemyTest(){

    }

    @Test
    public void CharacterContactExitDoorTest(){

    }

    @Test
    public void CharacterContactLimitTest(){

    }
}
