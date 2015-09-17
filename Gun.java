package marshmallow;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gun extends MouseAdapter{
	
	private Game game;
	private Handler handler;
	private HUD hud;
	
	private int count = 0;
	
	public Gun(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Game){
			for(int i = 0; i < handler.objectList.size(); i++){
				GameObject tempObject = handler.objectList.get(i);
				shoot(tempObject, mx, my);		
			}
		}
	}
	
	public void shoot(GameObject tempObject, int mx, int my){
		ID id = tempObject.getId();
		if(id!= ID.Player && id!= ID.Particle && id!=ID.Rocket){
			if(mouseOver(mx, my, (int)tempObject.x-5, (int)tempObject.y-5, 30, 30)){
				tempObject.removeHp(5);
				handler.addObject(new Explosion(mx-7, my-7, ID.Particle, Explosion.boom(), 14, 14, 0.04f, handler));
				if(tempObject.getHp()<=0){
					if(id == ID.BasicEnemy)hud.addMoney(5);
					if(id == ID.FastEnemy) hud.addMoney(10);
					if(id == ID.RocketEnemy) hud.addMoney(30);
				}
			}
		}
	}
	
	public void tick(){
		//add animation for guns shooting?
		//fixers are in HUD
		int gunCount = hud.getGun();
		count += gunCount;
		if(count >= 700){ 
			count = 0;
			for(int i = 0; i < handler.objectList.size(); i++){
				if(handler.objectList.get(i).id == ID.BasicEnemy){
					handler.objectList.get(i).removeHp(10);
					if(handler.objectList.get(i).hp <= 0){
						hud.addMoney(4);
					}
					return;
				}if(handler.objectList.get(i).id == ID.RocketEnemy){
					handler.objectList.get(i).removeHp(10);
					if(handler.objectList.get(i).hp <= 0){
						hud.addMoney(22);
					}
					return;
				}if(handler.objectList.get(i).id == ID.FastEnemy){
					handler.objectList.get(i).removeHp(10);
					if(handler.objectList.get(i).hp <= 0){
						hud.addMoney(8);
					}
					return;
				}
			}	
		}
	}
	
	public void render(Graphics g){
	}

	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}
}
