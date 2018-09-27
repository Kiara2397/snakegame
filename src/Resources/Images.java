package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {



	public static BufferedImage[] startBut;
	public static BufferedImage[] instrBut;
	public static BufferedImage Snaketitle;
	public static BufferedImage Pause;
	public static BufferedImage SnakePause;
	public static BufferedImage[] Resume;
	public static BufferedImage[] Quit;
	public static BufferedImage[] Options;
	public static BufferedImage[] Yes;
	public static BufferedImage[] No;
	public static ImageIcon icon;
	public static BufferedImage SnakeGameOver;
	public static BufferedImage Apple;


    public Images() {

    	startBut = new BufferedImage[3];
    	instrBut = new BufferedImage[3];
		Resume = new BufferedImage[2];
		Quit = new BufferedImage[2];
		Options = new BufferedImage[2];
		Yes = new BufferedImage[2];
		No = new BufferedImage[2];

		try {


			Snaketitle = ImageIO.read(getClass().getResourceAsStream("/Sheets/SnakeTitle.png"));
			SnakePause = ImageIO.read(getClass().getResourceAsStream("/Sheets/SnakePause.png"));
            SnakeGameOver = ImageIO.read(getClass().getResourceAsStream("/Sheets/SnakeGameOver2.png"));
            Apple = ImageIO.read(getClass().getResourceAsStream("/Sheets/apple-red-cartoon-hi.png"));
			icon =  new ImageIcon(ImageIO.read(getClass().getResourceAsStream("/Sheets/icon.png")));
			
			Resume[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/SnakeResume.png"));
			Resume[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/SnakeResumeP.png"));
			
			Quit[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/SnakeQuit.png"));
			Quit[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/SnakeQuitP.png"));
			
			Options[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Options.png"));
			Options[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/OptionsP.png"));
			
			Yes[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/Yes.png"));
			Yes[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/YesP.png"));
			
			No[0] = ImageIO.read(getClass().getResourceAsStream("/Buttons/No.png"));
			No[1] = ImageIO.read(getClass().getResourceAsStream("/Buttons/NoP.png"));

			startBut[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/StartNBut.png"));//normbut
			startBut[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/StartHBut.png"));//hoverbut
			startBut[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/StartCBut.png"));//clickbut
			
			instrBut[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/InstrNBut.png"));//normbut
			instrBut[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/InstrHBut.png"));//hoverbut
			instrBut[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/InstrCBut.png"));//clickbut


		}catch (IOException e) {
			e.printStackTrace();
		}


	}

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(Images.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

}
