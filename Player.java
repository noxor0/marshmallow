package marshmallow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

//import java.awt.Graphics2D;

public class Player extends GameObject { //tester
	
	private boolean ffPresent = true;

	private Handler handler;
//	private ForceField ff = new ForceField(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.ForceField);
	
	public static int PlayerLevel = 1;
	private BufferedImage img = null;
	
	public Player(int x, int y, ID id, Handler handler){
		super(x, y, id);
		this.handler = handler;
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x+7, (int)y+7, 50, 50);
	}
	
	public void tick() {
		collision();
	}
	
	public void render(Graphics g){
		drawBase(g);
//		if(ffPresent == true){
//			ff.render(g);
//		}
		
//		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.PINK);
//		g2d.draw(getBounds());
	}
	
	private void collision(){
		for(int i = 0; i < handler.objectList.size(); i++){
			GameObject tempObject = handler.objectList.get(i);
			if(tempObject.getId() == ID.BasicEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.Health -= 8;
					tempObject.hp =- 8;
				}
			}
			if(tempObject.getId() == ID.FastEnemy){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.Health -= 12;
					tempObject.hp =- 5;				
				}
			}
			if(tempObject.getId() == ID.Rocket){
				if(getBounds().intersects(tempObject.getBounds())){
					HUD.Health -= 22;
					tempObject.hp =- 1;		
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
					img = ImageIO.read(getClass().getResourceAsStream("models/baseOne.png"));
					g.drawImage(img, (int)x, (int)y, 64, 64, null);
				}catch(Exception e){
					g.setColor(new Color(0, 153, 0));
					g.fillOval((int)x, (int)y, 64, 64);
				}
				break;
		case 2: try{
					img = ImageIO.read(getClass().getResourceAsStream("models/baseTwo.png"));
					g.drawImage(img, (int)x, (int)y, 64, 64, null);
				}catch(Exception e){
					g.setColor(Color.gray);
					g.fillOval((int)x, (int)y, 64, 64);
				}
				break;
		case 3: try{
					img = ImageIO.read(getClass().getResourceAsStream("models/baseThree.png"));
					g.drawImage(img, (int)x, (int)y, 64, 64, null);
				}catch(Exception e){
					g.setColor(Color.pink);
					g.fillOval((int)x, (int)y, 64, 64);
					break;
				}
		}	
	}

	public boolean isffUp() {
		return ffPresent;
	}

	public void ffUp(boolean ffPresent) {
		this.ffPresent = ffPresent;
	}

}
