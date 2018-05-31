package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.UbrosGame;

public class ObjectiveView extends ElementView {

    /**
     * Texture scale factor
     */
    private final float SCALE = 0.85f;

    /**
     * Texture increment y position
     */
    private final float Y_POS = 0.1f;

    /**
     * Objective texture view
     */
    private Texture view;

    /**
     * Object texture width
     */
    private float width;

    /**
     * Object texture height
     */
    private float height;

    /**
     * Draws only if not catched
     */
    private boolean drawable;

    /**
     * Creates a view belonging to a game.
     *
     * @param game          the game this view belongs to. Needed to access the asset manager to get textures.
     * @param atlas         textures atlas that contains all enemy animations
     * @param element       body associated to this view
     * @param objectiveView name of objective texture to be loaded
     */
    public ObjectiveView(UbrosGame game, TextureAtlas atlas, ElementBody element, String objectiveView) {
        super(game, atlas, element);

        ObjectiveModel model = ((ObjectiveModel) getElement().getModel());
        loadObjectiveView(objectiveView);

        this.view = game.getAssetManager().get(objectiveView, Texture.class);
        this.width = model.getObjectiveWidth();
        this.height = model.getObjectiveHeight();
        this.drawable = true;
    }

    /**
     * Load's objective view texture
     *
     * @param objectiveView objective texture name
     */
    private void loadObjectiveView(String objectiveView) {
        super.getGame().getAssetManager().load(objectiveView, Texture.class);
        super.getGame().getAssetManager().finishLoading();
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        if (drawable)
            getGame().getBatch().draw(view, getElement().getX(), getElement().getY() + height * Y_POS, width * SCALE, height * SCALE);
    }

    @Override
    public void setCurrentState(CharacterState state) {
    }

    @Override
    public void update(float delta) {
        ObjectiveModel model = ((ObjectiveModel) getElement().getModel());
        drawable = !model.isCatched();
    }
}
