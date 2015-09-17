package marshmallow;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ForceField extends GameObject {
	public boolean isOn = false;
	
	public ForceField(int x, int y, ID id) {
		super(x, y, id);
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x-4, (int)y-4, 70, 70);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(.1f, .3f, .7f, .3f));
		g2d.fillOval((int)x-12, (int)y-11, 85, 85);
		g2d.setColor(new Color(0,124,219));
		g2d.setStroke(new BasicStroke(3));
		g2d.drawOval((int)x-11, (int)y-11, 85, 85);	
	}
	
	public void turnOn(){
		isOn = true;
	}
	
	public void turnOff(){
		isOn = false;
	}
	
	public boolean isOn(){
		if(isOn == true) return true;
		else return false;
	}
}
