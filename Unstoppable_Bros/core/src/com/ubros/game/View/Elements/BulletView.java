package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Gui.PlayGameScreen;
import com.ubros.game.UbrosGame;

public class BulletView extends ElementView {

    private Texture bullet;

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
    public BulletView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        super(game, atlas, element);
        this.bullet = game.getAssetManager().get("bullet.png",Texture.class);
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        super.getGame().getBatch().draw(bullet, getElement().getX() - (32 *0.6f)/ PlayGameScreen.PIXEL_TO_METER, getElement().getY() - (30 * 0.2f)/ PlayGameScreen.PIXEL_TO_METER, 32f/ PlayGameScreen.PIXEL_TO_METER, (30*0.4f)/ PlayGameScreen.PIXEL_TO_METER);
    }

    @Override
    public void setCurrentState(CharacterState state) {
    }

    @Override
    public void update(float delta) {
    }
}
