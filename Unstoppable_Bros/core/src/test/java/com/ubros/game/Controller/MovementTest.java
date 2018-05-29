package com.ubros.game.Controller;

import org.junit.Assert;
import org.junit.Test;


public class MovementTest {

    GameController controller;

    @Test
    public void characterMove(){
        controller = GameController.getInstance(null);

        float x = controller.getNinja().getX();
        float y = controller.getNinja().getY();

        controller.getNinja().getModel().setPosition(x, y+5);

        Assert.assertEquals(x,controller.getNinja().getX());
        Assert.assertEquals(y+5,controller.getNinja().getY());
    }
}
