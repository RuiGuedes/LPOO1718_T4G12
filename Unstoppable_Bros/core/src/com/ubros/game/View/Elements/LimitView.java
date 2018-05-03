package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.ubros.game.UbrosGame;

public class LimitView extends ElementView {

    public LimitView(UbrosGame game) {
        super(game);
    }

    @Override
    public Sprite createSprite(UbrosGame game) {
        Texture texture = game.getAssetManager().get("ground.png");

        return new Sprite(texture, texture.getWidth(), texture.getHeight());
    }

}
