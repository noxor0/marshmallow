package marshmallow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player extends GameObject { //tester

	Handler handler;
	public static int PlayerLevel = 1;
	private BufferedImage img = null;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x+7, (int)y+7, 50, 50);
		//TODO: Add more hit boxes
	}
	
	public void tick() {
		collision();
	}
	
	public void render(Graphics g) {		
		drawBase(g);
	}
	
	//TODO: Add enemy/player interactions
	private void collision(){
		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);
			if(tempObject.getId() == ID.BasicEnemy){
				//add how different enemies damage base
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.Health -= 10;
					tempObject.hp =-10;
				}
			}
			if(tempObject.getId() == ID.Rocket){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.Health -= 25;
					tempObject.hp =- 1;
//					handler.objectList.remove(i);					
				}
			}
		}
	}
	
	public static void addPlayerLevel(){
		PlayerLevel++;
	}
	public void drawBase(Graphics g){
		
		
		switch(PlayerLevel){
		case 1: try{
					img = ImageIO.read(new File("/home/noxor/workspace/marshmallow/"
							+ "src/marshmallow/models//playBase1.png"));
					g.drawImage(img, (int)x, (int)y, 64, 64, null);
				}catch(IOException e){
					System.out.println("nope, chunk testa");
				}
				break;
//		case 1: g.setColor(new Color(0, 153, 0));
//				g.fillOval((int)x, (int)y, 64, 64);
//				g.setColor(Color.green);
//				break;
		case 2: g.setColor(Color.gray);
				g.fillOval((int)x, (int)y, 64, 64);
				break;
		case 3: g.setColor(Color.pink);
				g.fillOval((int)x, (int)y, 64, 64);
				break;
		}	
	}

}
