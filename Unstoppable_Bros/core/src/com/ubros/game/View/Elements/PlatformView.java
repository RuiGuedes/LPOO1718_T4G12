package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.UbrosGame;

public class PlatformView extends ElementView {

    private Texture view;

    private float width;

    private float height;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the
     *                asset manager to get textures.
     * @param atlas
     * @param element
     */
    public PlatformView(UbrosGame game, TextureAtlas atlas, ElementBody element, Texture view) {
        super(game, atlas, element);
        this.view = view;

        if(((PlatformModel)getElement().getModel()).movementDir) {
            width = 4*0.32f;
            height = 0.30f;
        }
        else {
            width = 0.32f;
            height = 0.30f*3;
        }
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        getGame().getBatch().draw(view, getElement().getX(), getElement().getY(), width, height);
    }

    @Override
    public void update(float delta) {

        PlatformModel model = ((PlatformModel)getElement().getModel());
        PlatformBody body = ((PlatformBody)getElement());

        if(model.movementDir) {
            if( (body.getX() >= model.originX) || (body.getX() <= model.destinyX))
                body.getBody().setLinearVelocity(0,0);
        }
        else {
            if((body.getY() >= model.destinyY) || (body.getY() <= model.originY))
                body.getBody().setLinearVelocity(0,0);
        }
    }
}
