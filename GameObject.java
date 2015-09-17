package marshmallow;


import java.awt.Graphics;
import java.awt.Rectangle;


public abstract class GameObject {

	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected int hp;
	
	public GameObject(int x, int y, ID id){
		this.x = x;
		this.y = y;
		this.id = id;
		if(id == ID.BasicEnemy) hp = 10;
		if(id == ID.RocketEnemy) hp = 25;
		if(id == ID.Rocket) hp = 1;
		if(id == ID.FastEnemy) hp = 5;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g); 
	public abstract Rectangle getBounds(); 
	
	public float getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public int getHp(){
		return hp;
	}
	public void removeHp(int dmg){
		hp -= dmg;
	}
	public float getVelX() {
		return velX;
	}
	public void setVelX(int velX) {
		this.velX = velX;
	}
	public float getVelY() {
		return velY;
	}
	public void setVelY(int velY) {
		this.velY = velY;
	}
}
