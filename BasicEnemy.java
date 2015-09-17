package marshmallow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import javax.imageio.ImageIO;

public class BasicEnemy extends GameObject {

	private Handler handler;
	private Random r = new Random();
	
	private int oriX = Game.WIDTH / 2;
	private int oriY = Game.HEIGHT / 2; 
//	private BufferedImage img = null;
	
	
	public BasicEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		switch(r.nextInt(4)){
		case 0: velX = ((oriX - (float)x) / 310);
				velY = ((oriY - (float)y) / 310);
				break;
		case 1: velX = ((oriX - (float)x) / 290);
				velY = ((oriY - (float)y) / 290);
				break;
		case 2: velX = ((oriX - (float)x) / 270);
				velY = ((oriY - (float)y) / 270);
				break;
		case 3: velX = ((oriX - (float)x) / 250);
				velY = ((oriY - (float)y) / 250);
				break;
		case 4: velX = ((oriX - (float)x) / 230);
				velY = ((oriY - (float)y) / 230);
				break;
		}
	}

	public void tick(){
		x += velX;
		y += velY;
		handler.addObject(new Trail((int)x, (int)y, ID.Particle, Color.red, 16, 16, 0.05f, handler));
		if(hp <= 0){
			handler.objectList.remove(this);
		}
	}
	
	
	public void render(Graphics g){
//		try{
//			img = ImageIO.read(getClass().getResourceAsStream("models/enemy.png"));
//			img = ImageIO.read(new File("/home/noxor/workspace/marshmallow/src/marshmallow/models/enemy.png"));
//			g.drawImage(img, (int)x, (int)y, 16, 16, null);
//		}catch(Exception e){
			g.setColor(Color.red);
			g.fillRect((int)x, (int)y, 16, 16);			
//		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}
