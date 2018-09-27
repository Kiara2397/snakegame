package Game.GameStates;


import Main.Handler;
import Resources.Images;
import UI.ClickListlener;
import UI.UIImageButton;
import UI.UIManager;
import javafx.scene.paint.Color;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Display.DisplayScreen;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class MenuState extends State {

	private UIManager uiManager;

	public MenuState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);

		//Start Button
		uiManager.addObjects(new UIImageButton(460,400, 160, 90, Images.startBut, new ClickListlener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUimanager(null);
				handler.getGame().reStart();
				State.setState(handler.getGame().gameState);
			}
		}));

		uiManager.addObjects(new UIImageButton(405,400+(90+50), 280, 90, Images.instrBut, new ClickListlener() {
			@Override
			public void onClick() {
				handler.getMouseManager().setUimanager(null);

				//Instructions Frame
				String instructions =  "Press <M> key to Mute/Unmute \n"
						+ "Press <P> to Pause Game \n" + "Press <R> key to Reset Game \n" 
						+ "Press <+/-> to control speed \n" + "Press <N> Key to add tails";
				JOptionPane instrFrame = new JOptionPane();
				JOptionPane.showMessageDialog(null,instructions,"Instructions",JOptionPane.INFORMATION_MESSAGE);
				DisplayScreen.getFrame().add(instrFrame);
				int result = 0;
				if (result == JOptionPane.OK_OPTION) {
					DisplayScreen.getFrame().remove(instrFrame);;
					}
				}
		}));

	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();

	}

	@Override
	public void render(Graphics g) {
		g.fillRect(0,0,handler.getWidth(),handler.getHeight());
		g.drawImage(Images.Snaketitle,0,0,handler.getWidth(),handler.getHeight(),null);
		uiManager.Render(g);

	}


}
