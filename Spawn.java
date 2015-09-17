package marshmallow;

import java.util.Random;

public class Spawn {
	private Handler handler;
	private HUD hud;
	private Game game;
	private Random r = new Random();
	private int scoreKeep = 600;
	private int prevKeep = 600;
	private int waveKeep = 0;
	private int spawnChanceFE= 15;
	private int spawnChanceRE= 25;
	private boolean spawnDecFE = false;
	private boolean spawnDecRE = false;

	public Spawn(Game game,Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void tick(){
		scoreKeep -= hud.getLevel();
		if(scoreKeep <= 0){
			scoreKeep = prevKeep - 3;
			prevKeep = scoreKeep;
			spawnMultiple();
		}
		waveKeep++;
		if(waveKeep >= 3100){
			game.gameState = STATE.Store;
			hud.addLevel(1);
			waveKeep = 0;
			spawnDecRE = false; spawnDecFE = false;
		}
		if(spawnDecFE == false && hud.getLevel() > 3 ){
				int minusFE = hud.getLevel() - 3;
				if (minusFE < 5){
					spawnChanceFE -= minusFE;
					spawnDecFE = true;					
				}if(minusFE > 5){
					spawnChanceFE -= 5;
					spawnDecFE = true;
				}
		}
		if(spawnDecRE == false && hud.getLevel() > 7 ){
			int minusRE = hud.getLevel() - 7;
			if (minusRE < 5){
				spawnChanceRE -= minusRE;
				System.out.println("aoeu");
				spawnDecRE = true;					
			}if(minusRE > 5){
				System.out.println("1234");
				spawnChanceRE -= 5;
				spawnDecRE = true;
			}
		}	
	}
	
	public void spawnMultiple(){
		switch(r.nextInt(7)){
		case 0: spawnRandom();
				break;
		case 1: spawnR();
				spawnL();
				break;
		case 2: spawnR();
				spawnD();
				break;
		case 3: spawnR();
				spawnU();
				break;
		case 4: spawnL();
				spawnU();
				break;
		case 5: spawnL();
				spawnD();
				break;
		case 6: spawnD();
				spawnU();
				break;
		case 7: spawnRandom();
				break;
		}
	}
	
	public void spawnRandom(){
		switch(r.nextInt(4)){
		case 0: spawnR();
				break;
		case 1: spawnL();
				break;
		case 2: spawnU();
				break;
		case 3:	spawnD();
				break;
		}
	}
	public void spawnR(){
		if(handler.objectList.size()<400){
			
			handler.addObject(new BasicEnemy(Game.WIDTH, r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			if(r.nextInt(spawnChanceFE) <= 5){
				if(hud.getLevel() >= 4){
					handler.addObject(new FastEnemy(Game.WIDTH, r.nextInt(Game.HEIGHT), ID.FastEnemy,handler));
				}
			}
			if(r.nextInt(spawnChanceRE) == 3){
					if(hud.getLevel() >= 7){
					handler.addObject(new RocketEnemy(Game.WIDTH, r.nextInt(Game.HEIGHT), ID.RocketEnemy,handler));
				}
			}
			
		}		spawnChanceRE = 11;
		spawnChanceFE = 11;
	}
	public void spawnL(){
		if(handler.objectList.size()<400){
			handler.addObject(new BasicEnemy(0, r.nextInt(Game.HEIGHT), ID.BasicEnemy,handler));
			if(r.nextInt(spawnChanceFE) <= 5){
				if(hud.getLevel() >= 4){
					handler.addObject(new FastEnemy(0, r.nextInt(Game.HEIGHT), ID.FastEnemy,handler));
				}
			}
			if(r.nextInt(spawnChanceRE) == 3){ 
				if(hud.getLevel() >= 7){
					handler.addObject(new RocketEnemy(0, r.nextInt(Game.HEIGHT), ID.RocketEnemy,handler));
				}
			}
		}
	}
	public void spawnU(){
		if(handler.objectList.size()<400){
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), 0, ID.BasicEnemy, handler));
			if(r.nextInt(spawnChanceFE) <= 5){
				if(hud.getLevel() >= 4){
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), 0, ID.FastEnemy,handler));
				}
			}
			if(r.nextInt(spawnChanceRE) == 3){
				if(hud.getLevel() >= 7){
					handler.addObject(new RocketEnemy(r.nextInt(Game.WIDTH), 0, ID.RocketEnemy,handler));
				}
			}
		}
	}
	public void spawnD(){
		if(handler.objectList.size()<400){
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), Game.HEIGHT, ID.BasicEnemy,handler));
			if(r.nextInt(spawnChanceFE) <= 2){
				if(hud.getLevel() >= 4){
					handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH), Game.HEIGHT, ID.FastEnemy,handler));
				}
			}
			if(r.nextInt(spawnChanceRE) == 3){
				if(hud.getLevel() >= 7){
					handler.addObject(new RocketEnemy(r.nextInt(Game.WIDTH),Game.HEIGHT, ID.RocketEnemy,handler));
				}
			}
		}
	}
	public void reset(){
		scoreKeep = 400;
		waveKeep = 0;
	}
}