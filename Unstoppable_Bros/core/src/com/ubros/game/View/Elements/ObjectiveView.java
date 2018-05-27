package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Model.Elements.ObjectiveModel;
import com.ubros.game.UbrosGame;

public class ObjectiveView extends ElementView {

    private final float SCALE = 0.85f;

    private final float Y_POS = 0.1f;

    private Texture view;

    private float width;

    private float height;

    private boolean drawable;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the
     *                asset manager to get textures.
     * @param atlas
     * @param element
     */
    public ObjectiveView(UbrosGame game, TextureAtlas atlas, ElementBody element, String objectiveView) {
        super(game, atlas, element);

        ObjectiveModel model = ((ObjectiveModel)getElement().getModel());
        loadPlatformView(objectiveView);

        this.view = game.getAssetManager().get(objectiveView, Texture.class);
        this.width =  model.getObjectiveWidth();
        this.height = model.getObjectiveHeight();
        this.drawable = true;
    }

    private void loadPlatformView(String objectiveView) {
        super.getGame().getAssetManager().load(objectiveView,Texture.class);
        super.getGame().getAssetManager().finishLoading();
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        if(drawable)
            getGame().getBatch().draw(view, getElement().getX(), getElement().getY() + height*Y_POS, width*SCALE, height*SCALE);
    }

    @Override
    public void setCurrentState(CharacterState state) {
    }

    @Override
    public void update(float delta) {

        ObjectiveModel model = ((ObjectiveModel)getElement().getModel());
        drawable = !model.isCatched();
    }
}