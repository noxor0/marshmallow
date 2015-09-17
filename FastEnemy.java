package marshmallow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class FastEnemy extends GameObject {

	private Handler handler;
	private Random r = new Random();
	
	private int oriX = Game.WIDTH / 2;
	private int oriY = Game.HEIGHT / 2; 
	private int size = 14;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		switch(r.nextInt(3)){
		case 0: velX = ((oriX - (float)x) / 275);
				velY = ((oriY - (float)y) / 275);
				break;
		case 1: velX = ((oriX - (float)x) / 255);
				velY = ((oriY - (float)y) / 255);
				break;
		case 2: velX = ((oriX - (float)x) / 235);
				velY = ((oriY - (float)y) / 235);
				break;
		case 3: velX = ((oriX - (float)x) / 215);
				velY = ((oriY - (float)y) / 215);
				break;
		}
	}

	public void tick(){
		x += velX;
		y += velY;
		handler.addObject(new Trail((int)x, (int)y, ID.Particle, Color.pink, size, size, 0.05f, handler));
		if(hp <= 0){
			handler.objectList.remove(this);
		}
	}
	
	
	public void render(Graphics g){
		g.setColor(Color.pink);
		g.fillRect((int)x, (int)y, size, size);		
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int) y, size, size);
	}

}
