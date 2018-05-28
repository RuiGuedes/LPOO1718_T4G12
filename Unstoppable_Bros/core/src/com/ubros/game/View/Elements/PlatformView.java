package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Controller.Elements.PlatformBody;
import com.ubros.game.Model.Elements.PlatformModel;
import com.ubros.game.UbrosGame;

public class PlatformView extends ElementView {

    /**
     * Platform view texture
     */
    private Texture view;

    /**
     * Platform width
     */
    private float width;

    /**
     * Platform height
     */
    private float height;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the
     *                asset manager to get textures.
     * @param atlas
     * @param element
     */
    public PlatformView(UbrosGame game, TextureAtlas atlas, ElementBody element, String platformView) {
        super(game, atlas, element);

        PlatformModel model = ((PlatformModel)getElement().getModel());
        loadPlatformView(platformView);

        this.view = game.getAssetManager().get(platformView, Texture.class);
        this.width =  model.getPlatformWidth();
        this.height = model.getPlatformHeight();
    }

    public void loadPlatformView(String platformView) {
        super.getGame().getAssetManager().load(platformView,Texture.class);
        super.getGame().getAssetManager().finishLoading();
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        getGame().getBatch().draw(view, getElement().getX(), getElement().getY(), width, height);
    }

    @Override
    public void setCurrentState(CharacterState state) {
    }

    @Override
    public void update(float delta) {

        PlatformModel model = ((PlatformModel)getElement().getModel());
        PlatformBody body = ((PlatformBody)getElement());

        if(model.isMovementDir()) {
            if( (body.getX() >= model.getOriginX()) || (body.getX() <= model.getDestinyX()))
                body.getBody().setLinearVelocity(0,0);
        }
        else {
            if((body.getY() >= model.getDestinyY()) || (body.getY() <= model.getOriginY()))
                body.getBody().setLinearVelocity(0,0);
        }
    }
}
