package com.ubros.game.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.ubros.game.Controller.Elements.AcidBody;
import com.ubros.game.Controller.Elements.HeroBody;
import com.ubros.game.Controller.Elements.LimitBody;
import com.ubros.game.Controller.Elements.MechanismBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Model.Elements.AcidModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.UbrosGame;

import java.util.ArrayList;
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

    private List<MechanismBody> mechanismBodies = new ArrayList<MechanismBody>();

    private List<PlatformBody> platformBodies = new ArrayList<PlatformBody>();

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    public GameController(UbrosGame game) {

        this.game = game;
        this.world = new World(new Vector2(0, GRAVITY), true);
        this.debugRenderer = new Box2DDebugRenderer();

        createHero();
        createGround();
        createAcid();
        createMechanisms();

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

    public void createHero() {
        hero = new HeroBody(this.world, GameModel.getInstance(this.game).getHero());
    }

    public void createGround() {
        List<LimitModel> limits = GameModel.getInstance(this.game).getLimits();
        for (LimitModel limit : limits)
            new LimitBody(this.world, limit, limit.getShape().getVertices());
    }

    public void createAcid() {
        List<AcidModel> acidRegions = GameModel.getInstance(this.game).getAcidRegions();
        for (AcidModel acid : acidRegions)
            new AcidBody(this.world, acid, acid.getShape().getVertices());
    }

    public void createMechanisms() {
        int pointer = 0;

        createPlatforms();

        List<MechanismModel> mechanisms = GameModel.getInstance(this.game).getMechanisms();
        for (MechanismModel mechanism : mechanisms) {
            mechanismBodies.add(new MechanismBody(this.world, mechanism, mechanism.getShape().getVertices()));
            mechanism.setPlatform(platformBodies.get(pointer++));
        }
    }

    public void createPlatforms() {
        int pointer = 0;

        List<PlatformModel> platforms = GameModel.getInstance(this.game).getPlatforms();
        for (PlatformModel platform : platforms) {
            platformBodies.add(new PlatformBody(this.world, platform, platform.getShape().getVertices()));
            if(pointer == 0) {
                System.out.println(" ORIGIN X = " + platform.originX + "    - DEST _ " + ((float)(32 * 15) / 100));
                initializeModelVariables(platform, platform.originX - ((float)(32 * 15) / 100), platform.originY, true);
            }
            else if(pointer == 1)
                initializeModelVariables(platform, platform.originX - ((float)(32*4)/100), platform.originY, true);
            else if(pointer == 2)
                initializeModelVariables(platform, platform.originX, platform.originY + ((float)(30*3)/100), false);

            pointer++;
        }
    }

    private void initializeModelVariables(PlatformModel platform, float destinyX, float destinyY, boolean direction) {
        platform.destinyX = destinyX;
        platform.destinyY = destinyY;
        platform.movementDir = direction;
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

    public List<MechanismBody> getMechanismBodies() {
        return mechanismBodies;
    }

    public List<PlatformBody> getPlatformBodies() {
        return platformBodies;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals("RobotBounds") || fixB.getUserData().equals("RobotBounds")) {
            Body bodyA = fixA.getUserData().equals("RobotBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            if ((bodyB.getUserData()) instanceof AcidModel) {
                System.out.print("HERO + MECH \n");
             //   GameController.getInstance(this.game).getHero().getRobotView().setCurrentState(ElementView.CharacterState.DEAD);
               // GameController.getInstance(this.game).getHero().getBody().setLinearVelocity(0, 0);
            }

            if ((bodyB.getUserData()) instanceof MechanismModel)
                ((MechanismModel) bodyB.getUserData()).setActive(true);
        }


    }

    @Override
    public void endContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals("RobotBounds") || fixB.getUserData().equals("RobotBounds")) {
            Body bodyA = fixA.getUserData().equals("RobotBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            if ((bodyB.getUserData()) instanceof AcidModel) {
                System.out.print("END HERO + MECH \n");
                // GameController.getInstance(this.game).getHero().getRobotView().setCurrentState(ElementView.CharacterState.DEAD);
               // GameController.getInstance(this.game).getHero().getBody().setLinearVelocity(0, 0);
            }

            if ((bodyB.getUserData()) instanceof MechanismModel)
                ((MechanismModel) bodyB.getUserData()).setActive(false);
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    public void characterCollision(Body bodyA, Body bodyB) {

    }

    public void update(float delta) {

    }

}
