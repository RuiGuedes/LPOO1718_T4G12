package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.UbrosGame;

public class ObjectView extends ElementView {

    /**
     * Object view texture
     */
    private Texture view;

    /**
     * Object width
     */
    private float width;

    /**
     * Object height
     */
    private float height;

    /**
     * Object type
     */
    private String type;

    /**
     * Creates a view belonging to a game.
     * @param game    the game this view belongs to. Needed to access the asset manager to get textures.
     * @param atlas   textures atlas that contains all enemy animations
     * @param element body associated to this view
     * @param objectView name of object texture to be loaded
     */
    public ObjectView(UbrosGame game, TextureAtlas atlas, ElementBody element, String objectView) {
        super(game, atlas, element);

        loadObjectView(objectView);

        this.view = game.getAssetManager().get(objectView, Texture.class);
        this.width = this.height = 64 / PlayGameScreen.PIXEL_TO_METER;
        this.type = objectView;

        if(objectView.contains("ball"))
            this.width = this.height = 32 / PlayGameScreen.PIXEL_TO_METER;

    }

    /**
     * Loads object respective texture
     * @param objectView name of object texture to be loaded
     */
    private void loadObjectView(String objectView) {
        super.getGame().getAssetManager().load(objectView,Texture.class);
        super.getGame().getAssetManager().finishLoading();
    }

    @Override
    public void draw(float delta) {
        this.update(delta);

        if(type.contains("box"))
            getGame().getBatch().draw(view, getElement().getX(), getElement().getY(), width, height);
        else
            getGame().getBatch().draw(view, getElement().getX() - width/2, getElement().getY() - height/2, width, height);
    }

    @Override
    public void setCurrentState(CharacterState state) {
    }

    @Override
    public void update(float delta) {
    }
}
