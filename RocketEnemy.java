package marshmallow;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class RocketEnemy extends GameObject {

	private Handler handler;
	private Random r = new Random();
	
	private int oriX = Game.WIDTH / 2;
	private int oriY = Game.HEIGHT / 2; 
	private int count = 0;
	private int rocketCount = 0;
	private boolean isStopped = false;
	
	public RocketEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
		velX = ((oriX - (float)x) / 400);
		velY = ((oriY - (float)y) / 400);
	}

	public void tick(){
		count += r.nextInt(5);
		if(count <= 275){
			x += velX;
			y += velY;
			isStopped = true;
		}
		handler.addObject(new Trail((int)x, (int)y, ID.Particle, Color.cyan, 20, 20, 0.05f, handler));
		if(hp <= 0){
			handler.objectList.remove(this);
		}
		if(isStopped){
			rocketCount++;
			if(rocketCount >= 200){
				rocketCount = 0;
				handler.addObject(new Rocket((int)this.x+5, (int)this.y+5, ID.Rocket, handler));
			}
		}
	}
	
	
	public void render(Graphics g){
		g.setColor(Color.cyan);
		g.fillRect((int)x, (int)y, 20, 20);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x,(int) y, 16, 16);
	}

}