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
		//TODO: Level up gun, do more dmg
		int mx = e.getX();
		int my = e.getY();
		
		if(game.gameState == STATE.Game){
			for(int i = 0; i < handler.objectList.size(); i++){
				GameObject tempObject = handler.objectList.get(i);
				if(tempObject.id == ID.BasicEnemy){
					if(mouseOver(mx, my, (int)tempObject.x-5, (int)tempObject.y-5, 30, 30)){
						tempObject.removeHp(5);
						handler.addObject(new Explosion(mx, my, ID.Particle, Explosion.boom(), 14, 14, 0.04f, handler));
						if(tempObject.getHp()<=0){
							hud.addMoney(5);
						}
					}	
				}
				if(tempObject.id == ID.RocketEnemy){
					if(mouseOver(mx, my, (int)tempObject.x-5, (int)tempObject.y-5, 30, 30)){
						tempObject.removeHp(5);
						handler.addObject(new Explosion(mx, my, ID.Particle, Explosion.boom(), 14, 14, 0.04f, handler));
						if(tempObject.getHp()<=0){
							hud.addMoney(30);
						}
					}	
				}
			}
		}
	}
	
	public void tick(){
		//add animation for guns shooting?
		int gunCount = hud.getGun();
		count += gunCount;
		if(count >= 700){ 
			//TODO: balance this shit
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
						hud.addMoney(20);
					}
					return;
				}
			}	
		}
		//fixers are in HUD
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
