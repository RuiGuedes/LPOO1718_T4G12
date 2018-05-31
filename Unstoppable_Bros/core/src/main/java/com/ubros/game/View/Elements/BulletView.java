package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.Model.Elements.ElementModel;
import com.ubros.game.UbrosGame;

public class BulletView extends ElementView {

    /**
     * Bullet associated texture
     */
    private Texture bullet;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the
     *                asset manager to get textures.
     * @param atlas   the texture atlas containing bullet possible animations
     * @param element the bullet body
     */
    public BulletView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        super(game, atlas, element);
        this.bullet = game.getAssetManager().get("bullet.png",Texture.class);
    }

    @Override
    public void draw(float delta) {
        this.update(delta);

        float xPos = getElement().getX() - (ElementModel.TILE_WIDTH *0.6f)/ PlayGameScreen.PIXEL_TO_METER;
        float yPos  = getElement().getY() - (ElementModel.TILE_HEIGHT * 0.2f)/ PlayGameScreen.PIXEL_TO_METER;
        float width = ElementModel.TILE_WIDTH / PlayGameScreen.PIXEL_TO_METER;
        float height = (ElementModel.TILE_HEIGHT * 0.4f) / PlayGameScreen.PIXEL_TO_METER;

        super.getGame().getBatch().draw(bullet, xPos, yPos, width, height);
    }

    @Override
    public void setCurrentState(CharacterState state) {
    }

    @Override
    public void update(float delta) {
    }
}
