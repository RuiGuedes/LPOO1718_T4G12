package com.ubros.game.Controller.Elements;

import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Model.Elements.ElementModel;

public class LimitBody extends ElementBody {

    /**
     * Constructs a body representing a model in a certain world.
     *  @param world The world this body lives on.
     * @param model The model representing the body.
     */
    public LimitBody(World world, ElementModel model, float[] vertexSet) {
        super(world, model);

        createFixture(vertexSet);
    }

}
