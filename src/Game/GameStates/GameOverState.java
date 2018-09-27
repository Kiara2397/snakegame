package Game.GameStates;

import Main.Handler;
import Resources.Images;
import UI.UIManager;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameOverState extends State {

    private UIManager uiManager;

    public GameOverState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);


    }

    @Override
    public void tick() {
	  handler.getMouseManager().setUimanager(uiManager);
      uiManager.tick();


    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.SnakeGameOver,0,0,840,840,null);
        uiManager.Render(g);

    }
}
