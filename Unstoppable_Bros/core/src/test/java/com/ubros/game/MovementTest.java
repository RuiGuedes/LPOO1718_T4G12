package com.ubros.game;


import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Model.Elements.CharacterModel;

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
        Assert.assertEquals(x,robotBody.getX(),delta);
    }
}
