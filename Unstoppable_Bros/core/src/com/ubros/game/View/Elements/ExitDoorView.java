package com.ubros.game.View.Elements;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.Controller.Elements.ElementBody;
import com.ubros.game.Controller.GameController;
import com.ubros.game.Model.Elements.ExitDoorModel;
import com.ubros.game.UbrosGame;

public class ExitDoorView extends ElementView {

    private Texture exitDoor;

    /**
     * Creates a view belonging to a game.
     *
     * @param game    the game this view belongs to. Needed to access the
     *                asset manager to get textures.
     * @param atlas
     * @param element
     */
    public ExitDoorView(UbrosGame game, TextureAtlas atlas, ElementBody element) {
        super(game, atlas, element);
    }

    @Override
    public void draw(float delta) {
        this.update(delta);
        getGame().getBatch().draw(exitDoor,  getElement().getX(), getElement().getY(), 0.64f, 0.9f);
    }

    @Override
    public void setCurrentState(CharacterState state) {
    }

    @Override
    public void update(float delta) {

        int remaingObjectives = GameController.getInstance(null).getRemainingObjectives();

        if(remaingObjectives != 0)
            this.exitDoor = getGame().getAssetManager().get("DoorLocked.png", Texture.class);
        else if(!((ExitDoorModel)getElement().getModel()).isCharacterContact())
            this.exitDoor = getGame().getAssetManager().get("DoorUnlocked.png", Texture.class);
        else
            this.exitDoor = getGame().getAssetManager().get("DoorOpen.png", Texture.class);
    }
}
