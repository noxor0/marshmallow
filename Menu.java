package marshmallow;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
	
	private int halfW = Game.WIDTH / 2;
	
	private Game game;
	private Handler handler;
	private HUD hud;
	
	public Menu(Game game, Handler handler, HUD hud){
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();

		//main menu
		if(game.gameState == STATE.Menu){ // 'play button'
			if(mouseOver(mx, my, halfW - 100, 100, 200, 64)){
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); ;
			}			
		}
		
		if(game.gameState == STATE.Menu){ // 'help button'
			if(mouseOver(mx, my,halfW - 100, 200, 200, 64)){
				game.gameState = STATE.Help;
			}			
		}
		
		if(game.gameState == STATE.Menu){ // 'quit button'
			if(mouseOver(mx, my,halfW - 100, 300, 200, 64)){
				System.exit(1);
			}			
		}
		//help menu
		if(game.gameState == STATE.Help){ // 'back button'
			if(mouseOver(mx, my,halfW - 100, 600, 200, 64)){
				game.gameState = STATE.Menu;
				return;
			}
		}
		//store menu 
		//TODO: Add force field? 
		if(game.gameState == STATE.Store){ // 'shooter button'
			if(mouseOver(mx, my,halfW - 100, 100, 200, 64)){
				if(hud.getMoney()>=50){
					hud.addGun();
					hud.spendMoney(50);
				}
			}			
		}
		if(game.gameState == STATE.Store){ // 'Fixer button'
			if(mouseOver(mx, my,halfW - 100, 200, 200, 64)){
				if(hud.getMoney() >= 150){
					hud.addFix();
					hud.spendMoney(150);
				}
			}			
		}
		if(game.gameState == STATE.Store){ // 'Heal button'
			if(mouseOver(mx, my,halfW - 100, 300, 200, 64)){
				if(hud.getMoney()>= 15){
					if(hud.getHp()+20 >= hud.getMaxHp()){
						hud.addHp(hud.getMaxHp() - hud.getHp());
						hud.spendMoney(15);
					}else{
					hud.addHp(20);					
					hud.spendMoney(15);
					}
				}
			}		
		}
		if(game.gameState == STATE.Store){ // 'Base up button'
			if(mouseOver(mx, my,halfW - 100, 400, 200, 64)){
				if(Player.PlayerLevel < 3){
					if(hud.getMoney() >= 1000){
						hud.spendMoney(1000);
						hud.addMaxHp(650);
						hud.addHp(650);
						Player.addPlayerLevel();
					}	
				}
			}			
		}
		if(game.gameState == STATE.Store){ // 'Next wave button'
			if(mouseOver(mx, my,halfW - 100, 500, 200, 64)){
				game.gameState = STATE.Game;
				handler.addObject(new Player(Game.WIDTH/2-32, Game.HEIGHT/2-32, ID.Player, handler)); 
			}			
		}
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	//TODO: Make pretty starting screen
	public void render(Graphics g){
		if(game.gameState == STATE.Menu){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 20);
			
			g.setColor(Color.white);
			g.setFont(fnt);
			g.drawString("Menu", Game.WIDTH / 2 - 65, 50);
			
			g.setFont(fnt2);
			g.drawRect(halfW - 100, 100, 200, 64);
			g.drawString("Play", Game.WIDTH / 2 - 85, 142);
			
			g.drawRect(halfW - 100, 200, 200, 64);
			g.drawString("Help", Game.WIDTH / 2 - 85, 242);
			
			g.drawRect(halfW - 100, 300, 200, 64);
			g.drawString("Quit", Game.WIDTH / 2 - 85, 342);
		}if(game.gameState == STATE.Help){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 30);
			
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", halfW - 50, 100);
			
			g.setFont(fnt2);
			g.drawRect(halfW - 100, 600, 200, 64);
			g.drawString("Back", halfW - 60, 645);
			
			g.drawString("Shoot shit", 20 , 300);	
		}if(game.gameState == STATE.Store){
			Font fnt = new Font("arial", 1, 50);
			Font fnt2 = new Font("arial", 1, 20);
			hud.drawHud(g);
			
			//drawing the store
			g.setFont(fnt);
			g.drawString("Store", Game.WIDTH / 2 - 65, 50);
			
			g.setFont(fnt2);
			g.drawRect(halfW - 100, 100, 200, 64);
			g.drawString("Shooter: 50", Game.WIDTH / 2 - 85, 142);
			
			g.drawRect(halfW - 100, 200, 200, 64);
			g.drawString("Fixer: 150", Game.WIDTH / 2 - 85, 242);
			
			g.drawRect(halfW - 100, 300, 200, 64);
			g.drawString("Heal: 15", Game.WIDTH / 2 - 85, 342);
			
			g.drawRect(halfW - 100, 400, 200, 64);
			g.drawString("Upgrade Base: 1000", Game.WIDTH / 2 - 85, 442);
			
			g.drawRect(halfW - 100, 500, 200, 64);
			g.drawString("Next Wave", Game.WIDTH / 2 - 85, 542);
		}
		
		//TODO: A (?) Game over
	}
	
	public void tick(){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
		if(mx > x && mx < x + width){
			if(my > y && my < y + height){
				return true;
			}else return false;
		}else return false;
	}

}