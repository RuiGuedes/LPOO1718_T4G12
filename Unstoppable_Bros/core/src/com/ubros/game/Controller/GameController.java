package com.ubros.game.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.Elements.HeroBody;
import com.ubros.game.Controller.Elements.LimitBody;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.UbrosGame;

import java.util.List;

public class GameController implements ContactListener {

    /**
     *
     */
    private float GRAVITY = -10;

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    /**
     * Game object
     */
    private final UbrosGame game;

    /**
     * The physics world controlled by this controller.
     */
    private final World world;

    /**
     * The physics box2d debug renderer
     */
    private Box2DDebugRenderer debugRenderer;

    /**
     *
     */
    private HeroBody hero;

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     *
     */
    public GameController(UbrosGame game) {

        this.game = game;

        this.world = new World(new Vector2(0,GRAVITY), true);

        this.debugRenderer = new Box2DDebugRenderer();

        hero = new HeroBody(this.world, GameModel.getInstance(this.game).getHero());

        List<LimitModel> limits = GameModel.getInstance(this.game).getLimits();
        for (LimitModel limit : limits)
            new LimitBody(this.world, limit, limit.getShape().getVertices());


        this.world.setContactListener(this);
    }

    /**
     * Returns a singleton instance of a game controller
     *
     * @return the singleton instance
     */
    public static GameController getInstance(UbrosGame game) {
        if (instance == null)
            instance = new GameController(game);
        return instance;
    }

    /**
     * Returns the world controlled by this controller. Needed for debugging purposes only.
     *
     * @return The world controlled by this controller.
     */
    public World getWorld() {
        return world;
    }

    public Box2DDebugRenderer getDebugRenderer() {
        return debugRenderer;
    }

    public HeroBody getHero() {
        return hero;
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void update(float delat) {

    }
}
