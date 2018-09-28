package Game.GameStates;

import Main.GameSetUp;
import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;
import java.awt.event.KeyEvent;

import javax.sound.sampled.Clip;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class PauseState extends State {

    private int count = 0;
    private UIManager uiManager;

    public PauseState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);

        //Resume Button
        uiManager.addObjects(new UIImageButton(260, 360, 300, 150, Images.Resume, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
            GameSetUp.getAudioClip().start();
            GameSetUp.getAudioClip().loop(Clip.LOOP_CONTINUOUSLY);
        }));
        
        //Quit To Menu Button
        uiManager.addObjects(new UIImageButton(230, 360+(150), 350, 150, Images.Quit, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
            GameSetUp.getAudioClip().start();
            GameSetUp.getAudioClip().loop(Clip.LOOP_CONTINUOUSLY);
        }));


    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        GameSetUp.getAudioClip().stop();
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pbutt && count>=30){
            count=0;

            State.setState(handler.getGame().gameState);
        }

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
       	GameSetUp.getAudioClip().start();
       	GameSetUp.getAudioClip().loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.SnakePause,0,0,840,840,null);
        uiManager.Render(g);

    }
}
