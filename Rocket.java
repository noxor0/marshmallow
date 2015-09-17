package marshmallow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Rocket extends GameObject {

	private Handler handler;
	
	private int oriX = Game.WIDTH / 2;
	private int oriY = Game.HEIGHT / 2; 
	private int size = 8;
	
	public Rocket(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = ((oriX - (float)x)) / 75;
		velY = ((oriY - (float)y)) / 75;
	}

	public void tick(){
		x += velX;
		y += velY;
		handler.addObject(new Trail((int)x, (int)y, ID.Particle, Color.orange, size, size, 0.05f, handler));
		if(hp <= 0){
			handler.addObject(new Explosion((int)x, (int)y, ID.Particle, Color.yellow, 24, 24, 0.02f, handler));
			handler.objectList.remove(this);
		}
	}
	
	public void render(Graphics g){
		g.setColor(Color.orange);
		g.fillRect((int)x, (int)y, size, size);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int) y, size, size);
	}

}