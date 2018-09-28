package Game.Entities.Dynamic;

import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.GameStates.GameState;
import Game.GameStates.State;

/**
 * Created by AlexVR on 7/2/2018.
 */
public class Player {


    public int length;
	public int score;
	

    public boolean justAte;
    private Handler handler;

    public int xCoord;
    public int yCoord;

    public int moveCounter;
    private final int DEFAULT_SPEED = 3;
    public int speed = DEFAULT_SPEED;

    public String direction;//is your first name one?

    public Player(Handler handler){
        this.handler = handler;
        xCoord = 0;
        yCoord = 0;
        moveCounter = DEFAULT_SPEED;
        direction= "Right";
        justAte = false;
        length= 1;

    }

    public void tick(){
        moveCounter++;
        if(moveCounter>=speed) {
            checkCollisionAndMove();
            moveCounter = 0;
        }
        //Prevent Backtracking
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP) && !(direction.equals("Down")) &&
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))&& 
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT))&&
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT))){
            direction = "Up";
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN) && !(direction.equals("Up")) &&
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP))&& 
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT))&&
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT))){
            direction ="Down";
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT) && !(direction.equals("Right")) &&
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP))&& 
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))&&
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT))){
            direction ="Left";
        }
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_RIGHT) && !(direction.equals("Left")) &&
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_UP))&& 
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_DOWN))&&
        		!(handler.getKeyManager().keyJustPressed(KeyEvent.VK_LEFT))){
            direction ="Right";
        }
       //Increases Speed
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_EQUALS)) {
        	speed--;
        }
        //Decreases Speed
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_MINUS)){
        	speed++;
        }
        //Pauses Game
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_P)){
        	State.setState(handler.getGame().pauseState);;
        }
        //Adds Tails with key 'N'
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_N)){
        	length++;
        	handler.getWorld().body.addFirst(new Tail(xCoord,yCoord,handler));
       }
        //Resets Game with key 'R'
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)){
        	State.setState(new GameState(handler));
        }
    }

    public void checkCollisionAndMove(){
        handler.getWorld().playerLocation[xCoord][yCoord]=false;
        int x = xCoord;
        int y = yCoord;

        switch (direction){
            case "Left":
                if(xCoord==0){
                    kill();
                }else{
                    xCoord--;
                }
                break;
            case "Right":
                if(xCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    kill();
                }else{
                    xCoord++;
                }
                break;
            case "Up":
                if(yCoord==0){
                    kill();
                }else{
                    yCoord--;
                }
                break;
            case "Down":
                if(yCoord==handler.getWorld().GridWidthHeightPixelCount-1){
                    kill();
                }else{
                    yCoord++;
                }
                break;
        }
        //Self Collision
        if(handler.getWorld().playerLocation[xCoord][yCoord]==true){
        	kill();
        }
        handler.getWorld().playerLocation[xCoord][yCoord]=true;


        if(handler.getWorld().appleLocation[xCoord][yCoord]){
            Eat();
        }

        if(!handler.getWorld().body.isEmpty()) {
            handler.getWorld().playerLocation[handler.getWorld().body.getLast().x][handler.getWorld().body.getLast().y] = false;
            handler.getWorld().body.removeLast();
            handler.getWorld().body.addFirst(new Tail(x, y,handler));
        }

    }

    public void render(Graphics g,Boolean[][] playeLocation){
        Random r = new Random();
        
        //Display Score
        g.setColor(Color.WHITE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        g.drawString(Integer.toString(this.score), 400, 25);
        
        
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {
                
            	//Draw Green Snake
                if(playeLocation[i][j]){
                	g.setColor(Color.GREEN);
                    g.fillRect((i*handler.getWorld().GridPixelsize),
                            (j*handler.getWorld().GridPixelsize),
                            handler.getWorld().GridPixelsize,
                            handler.getWorld().GridPixelsize);
                }
                //Draw Apple Image
                if (handler.getWorld().appleLocation[i][j]){
                	g.drawImage(Images.Apple,i*handler.getWorld().GridPixelsize,
                			j*handler.getWorld().GridPixelsize,
                			handler.getWorld().GridPixelsize+1,
                			handler.getWorld().GridPixelsize+1,null);

                }

            }
        }


    }

    public void Eat(){

        length++;
    	score++;

        Tail tail= null;
        handler.getWorld().appleLocation[xCoord][yCoord]=false;
        handler.getWorld().appleOnBoard=false;
        switch (direction){
            case "Left":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail = new Tail(this.xCoord+1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail = new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail =new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler);
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler);

                        }
                    }

                }
                break;
            case "Right":
                if( handler.getWorld().body.isEmpty()){
                    if(this.xCoord!=0){
                        tail=new Tail(this.xCoord-1,this.yCoord,handler);
                    }else{
                        if(this.yCoord!=0){
                            tail=new Tail(this.xCoord,this.yCoord-1,handler);
                        }else{
                            tail=new Tail(this.xCoord,this.yCoord+1,handler);
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().x!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                    }else{
                        if(handler.getWorld().body.getLast().y!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                        }
                    }

                }
                break;
            case "Up":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(this.xCoord,this.yCoord+1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        }
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=handler.getWorld().GridWidthHeightPixelCount-1){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord+1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
            case "Down":
                if( handler.getWorld().body.isEmpty()){
                    if(this.yCoord!=0){
                        tail=(new Tail(this.xCoord,this.yCoord-1,handler));
                    }else{
                        if(this.xCoord!=0){
                            tail=(new Tail(this.xCoord-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(this.xCoord+1,this.yCoord,handler));
                        } System.out.println("Tu biscochito");
                    }
                }else{
                    if(handler.getWorld().body.getLast().y!=0){
                        tail=(new Tail(handler.getWorld().body.getLast().x,this.yCoord-1,handler));
                    }else{
                        if(handler.getWorld().body.getLast().x!=0){
                            tail=(new Tail(handler.getWorld().body.getLast().x-1,this.yCoord,handler));
                        }else{
                            tail=(new Tail(handler.getWorld().body.getLast().x+1,this.yCoord,handler));
                        }
                    }

                }
                break;
        }
        handler.getWorld().body.addLast(tail);
        handler.getWorld().playerLocation[tail.x][tail.y] = true;
    }

    public void kill(){
        length = 0;
        for (int i = 0; i < handler.getWorld().GridWidthHeightPixelCount; i++) {
            for (int j = 0; j < handler.getWorld().GridWidthHeightPixelCount; j++) {

                handler.getWorld().playerLocation[i][j]=false;

            }
        }
        State.setState(handler.getGame().gameOverState);
    }

    public boolean isJustAte() {
        return justAte;
    }

    public void setJustAte(boolean justAte) {
        this.justAte = justAte;
    }

  }