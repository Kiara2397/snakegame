package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIImageButton;
import UI.UIManager;

import java.awt.*;

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

        uiManager.addObjects(new UIImageButton(300, 350, 300, 150, Images.Resume, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().gameState);
        }));
        
        uiManager.addObjects(new UIImageButton(270, 350+(90), 350, 150, Images.Quit, () -> {
            handler.getMouseManager().setUimanager(null);
            State.setState(handler.getGame().menuState);
        }));

//        uiManager.addObjects(new UIImageButton(380, 350+(64+16), 128, 64, Images.Options, () -> {
//            handler.getMouseManager().setUimanager(null);
//            State.setState(handler.getGame().menuState);
//        }));

//        uiManager.addObjects(new UIImageButton(380, (350+(64+16))+(64+16), 128, 64, Images.BTitle, () -> {
//            handler.getMouseManager().setUimanager(null);
//            State.setState(handler.getGame().menuState);
//        }));





    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        count++;
        if( count>=30){
            count=30;
        }
        if(handler.getKeyManager().pbutt && count>=30){
            count=0;

            State.setState(handler.getGame().gameState);
        }


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.SnakePause,0,0,900,900,null);
        uiManager.Render(g);

    }
}
