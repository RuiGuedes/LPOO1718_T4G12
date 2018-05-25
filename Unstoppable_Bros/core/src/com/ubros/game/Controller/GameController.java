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
import com.ubros.game.Controller.Elements.CharacterBody;
import com.ubros.game.Controller.Elements.LimitBody;
import com.ubros.game.Controller.Elements.MechanismBody;
import com.ubros.game.Controller.Elements.ObjectiveBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Model.Elements.AcidModel;
import com.ubros.game.Model.Elements.CharacterModel;
import com.ubros.game.Model.Elements.LimitModel;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.Model.GameModel;
import com.ubros.game.UbrosGame;
import com.ubros.game.View.Elements.ElementView;
import com.ubros.game.View.Elements.NinjaView;
import com.ubros.game.View.Elements.RobotView;

import java.util.ArrayList;
import java.util.List;

public class GameController implements ContactListener {

    /**
     * World considered gravity value
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
    private int remainingObjectives;

    private CharacterBody robot;

    private CharacterBody ninja;

    private List<MechanismBody> mechanismBodies = new ArrayList<MechanismBody>();

    private List<PlatformBody> platformBodies = new ArrayList<PlatformBody>();

    private List<ObjectiveBody> objectiveBodies = new ArrayList<ObjectiveBody>();

    /**
     * Creates a new GameController that controls the physics of a certain GameModel.
     */
    private GameController(UbrosGame game) {

        this.game = game;
        this.world = new World(new Vector2(0, GRAVITY), true);
        this.debugRenderer = new Box2DDebugRenderer();

        createCharacters();
        createGround();
        createAcid();
        createMechanisms();
        createObjectives();

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

    private void createCharacters() {
        robot = new CharacterBody(this.world, GameModel.getInstance(this.game).getRobot(), "RobotBounds");
        ninja = new CharacterBody(this.world, GameModel.getInstance(this.game).getNinja(), "NinjaBounds");
    }

    private void createGround() {
        List<LimitModel> limits = GameModel.getInstance(this.game).getLimits();
        for (LimitModel limit : limits)
            new LimitBody(this.world, limit, limit.getShape().getVertices());
    }

    private void createAcid() {
        List<AcidModel> acidRegions = GameModel.getInstance(this.game).getAcidRegions();
        for (AcidModel acid : acidRegions)
            new AcidBody(this.world, acid, acid.getShape().getVertices());
    }

    private void createMechanisms() {

        int pointer = 0;
        createPlatforms();

        List<MechanismModel> mechanisms = GameModel.getInstance(this.game).getMechanisms();
        for (MechanismModel mechanism : mechanisms) {
            mechanismBodies.add(new MechanismBody(this.world, mechanism, mechanism.getShape().getVertices()));
            mechanism.setPlatform(platformBodies.get(pointer++));
        }
    }

    private void createPlatforms() {
        List<PlatformModel> platforms = GameModel.getInstance(this.game).getPlatforms();
        for (PlatformModel platform : platforms)
            platformBodies.add(new PlatformBody(this.world, platform, platform.getShape().getVertices()));
    }

    public void createObjectives() {
        List<ObjectiveModel> objectives = GameModel.getInstance(this.game).getObjectives();
        for (ObjectiveModel objective : objectives)
            objectiveBodies.add(new ObjectiveBody(this.world, objective, objective.getShape().getVertices()));

        this.remainingObjectives = objectives.size();
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

    public CharacterBody getRobot() {
        return robot;
    }

    public CharacterBody getNinja() {
        return ninja;
    }

    public List<MechanismBody> getMechanismBodies() {
        return mechanismBodies;
    }

    public List<PlatformBody> getPlatformBodies() {
        return platformBodies;
    }

    public List<ObjectiveBody> getObjectiveBodies() {
        return objectiveBodies;
    }

    public UbrosGame getGame() {
        return game;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();

        if (fixA.getUserData().equals("RobotBounds") || fixB.getUserData().equals("RobotBounds")) {
            Body bodyA = fixA.getUserData().equals("RobotBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            if ((bodyB.getUserData()) instanceof AcidModel) {
                System.out.print("ROBOT + MECH \n");
             //   GameController.getInstance(this.game).getHero().getRobotView().setCurrentState(ElementView.CharacterState.DEAD);
               // GameController.getInstance(this.game).getHero().getBody().setLinearVelocity(0, 0);
            }

            if ((bodyB.getUserData()) instanceof MechanismModel)
                ((MechanismModel) bodyB.getUserData()).setActive(true);

            if ((bodyB.getUserData()) instanceof PlatformModel)
                ((PlatformModel)(bodyB.getUserData())).robotContact = true;

            if (((bodyB.getUserData()) instanceof ObjectiveModel)) {
                if(((ObjectiveModel)bodyB.getUserData()).getData().equals("R")) {
                    if(!((ObjectiveModel)bodyB.getUserData()).isCatched()) {
                        ((ObjectiveModel) bodyB.getUserData()).setCatched();
                        this.remainingObjectives--;
                    }
                }
            }
        }

        if (fixA.getUserData().equals("NinjaBounds") || fixB.getUserData().equals("NinjaBounds")) {
            Body bodyA = fixA.getUserData().equals("NinjaBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            if ((bodyB.getUserData()) instanceof AcidModel) {
                System.out.print("NINJA + MECH \n");
                ((NinjaView)(GameModel.getInstance(this.game).getNinja().getElementView())).setCurrentState(ElementView.CharacterState.DEAD);
                GameController.getInstance(this.game).getNinja().getBody().setLinearVelocity(0, 0);
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

            if ((bodyB.getUserData()) instanceof PlatformModel) {
                ((PlatformModel) (bodyB.getUserData())).robotContact = false;
                ((RobotView)((CharacterModel)bodyA.getUserData()).getElementView()).onPlatform = false;
            }
        }

        if (fixA.getUserData().equals("NinjaBounds") || fixB.getUserData().equals("NinjaBounds")) {
            Body bodyA = fixA.getUserData().equals("NinjaBounds") ? fixA.getBody() : fixB.getBody();
            Body bodyB = bodyA == fixA.getBody() ? fixB.getBody() : fixA.getBody();

            if ((bodyB.getUserData()) instanceof AcidModel) {
                //System.out.print("END HERO + MECH \n");
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

    public void update(float delta) {

        /*
        for(PlatformBody platform : this.getPlatformBodies()) {
            PlatformModel model = ((PlatformModel)platform.getModel());

            if(model.movementDir) {
                if( (platform.getX() >= model.originX) || (platform.getX() <= model.destinyX))
                    platform.getBody().setLinearVelocity(0,0);
            }
            else {
                if((platform.getY() >= model.destinyY) || (platform.getY() <= model.originY))
                   platform.getBody().setLinearVelocity(0,0);
            }
        }*/

    }

}
