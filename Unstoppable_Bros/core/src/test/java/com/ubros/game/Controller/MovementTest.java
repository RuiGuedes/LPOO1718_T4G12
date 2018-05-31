package com.ubros.game.Controller;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.ubros.game.UbrosGame;
import com.ubros.game.View.GameView;

import org.junit.Test;


public class MovementTest {

    GameController controller;
    UbrosGame game;

    @Test
    public void characterMove(){

        this.game = new UbrosGame();

        GameController.getInstance(null).dispose();
        GameController.getInstance(this.game).setState(GameController.GameStatus.PLAYING);
        GameView.getInstance(this.game).dispose();
    }

    private void loadAssets() {
        this.game.getAssetManager().load("background.jpg", Texture.class);

        this.game.getAssetManager().load("MainMenuExitButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuPlayButtonOff.png",Texture.class);
        this.game.getAssetManager().load("MainMenuExitButtonOn.png",Texture.class);
        this.game.getAssetManager().load("MainMenuSettingsButtonOn.png",Texture.class);
        this.game.getAssetManager().load("MainMenuPlayButtonOn.png",Texture.class);
        this.game.getAssetManager().load("gameTitle.png",Texture.class);
        this.game.getAssetManager().load("victory.png",Texture.class);
        this.game.getAssetManager().load("gameOver.png",Texture.class);
        this.game.getAssetManager().load("retryOff.png",Texture.class);
        this.game.getAssetManager().load("retryOn.png",Texture.class);
        this.game.getAssetManager().load("backLoseFocus.png",Texture.class);
        this.game.getAssetManager().load("pauseOff.png",Texture.class);
        this.game.getAssetManager().load("pauseOn.png",Texture.class);

        this.game.getAssetManager().load("audio/music/BullyWalkingTheme.mp3", Music.class);
        this.game.getAssetManager().load("audio/music/BullyMainTheme.mp3", Music.class);
        this.game.getAssetManager().load("audio/sounds/pickSound.wav", Sound.class);
        this.game.getAssetManager().load("audio/sounds/bulletSound.wav", Sound.class);

        this.game.getAssetManager().load("sound.png", Texture.class);
        this.game.getAssetManager().load("soundOff.png", Texture.class);
        this.game.getAssetManager().load("soundOn.png", Texture.class);
        this.game.getAssetManager().load("tutorialOff.png", Texture.class);
        this.game.getAssetManager().load("tutorialOn.png", Texture.class);
        this.game.getAssetManager().load("returnButtonOff.png", Texture.class);
        this.game.getAssetManager().load("returnButtonOn.png", Texture.class);


        this.game.getAssetManager().load("robotHand.png", Texture.class);
        this.game.getAssetManager().load("ninjaHand.png", Texture.class);
        this.game.getAssetManager().load("moveLeftButtonOff.png", Texture.class);
        this.game.getAssetManager().load("moveLeftButtonOn.png", Texture.class);
        this.game.getAssetManager().load("moveRightButtonOff.png", Texture.class);
        this.game.getAssetManager().load("moveRightButtonOn.png", Texture.class);
        this.game.getAssetManager().load("jumpButtonOff.png", Texture.class);
        this.game.getAssetManager().load("jumpButtonOn.png", Texture.class);
        this.game.getAssetManager().load("bulletButtonOff.png", Texture.class);
        this.game.getAssetManager().load("bulletButtonOn.png", Texture.class);
        this.game.getAssetManager().load("mechanismOff.png", Texture.class);
        this.game.getAssetManager().load("mechanismOn.png", Texture.class);
        this.game.getAssetManager().load("DoorLocked.png", Texture.class);
        this.game.getAssetManager().load("DoorUnlocked.png", Texture.class);
        this.game.getAssetManager().load("DoorOpen.png", Texture.class);
        this.game.getAssetManager().load("bullet.png", Texture.class);

        this.game.getAssetManager().load("Robot/Robot.pack", TextureAtlas.class);
        this.game.getAssetManager().load("Ninja/Ninja.pack", TextureAtlas.class);
        this.game.getAssetManager().load("Enemy/Enemy.pack", TextureAtlas.class);


        this.game.getAssetManager().finishLoading();
    }

}
