package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Model.Elements.MechanismModel;
import com.ubros.game.UbrosGame;

public class MechanismView extends ElementView {

    private Texture mechanism;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the
     *                asset manager to get textures.
     * @param atlas
     * @param element
     */
    public MechanismView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        super(game, atlas, element);

    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        getGame().getBatch().draw(mechanism,  getElement().getX(), getElement().getY(), 0.32f, 0.9f);
    }

    @Override
    public void setCurrentState(CharacterState state) {
    }

    @Override
    public void update(float delta) {
        if(((MechanismModel)super.getElement().getModel()).isActive())
            this.mechanism = getGame().getAssetManager().get("mechanismOn.png", Texture.class);
        else
            this.mechanism = getGame().getAssetManager().get("mechanismOff.png", Texture.class);
    }
}
