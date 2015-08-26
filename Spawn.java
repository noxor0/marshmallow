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
	private int spawnChance = 11;

	public Spawn(Game game,Handler handler, HUD hud) {
		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}

	public void tick(){
		scoreKeep -= hud.getLevel();
		if(scoreKeep <= 0){
			scoreKeep = prevKeep - 6;
			prevKeep = scoreKeep;
			spawnMultiple();
		}
		waveKeep++;
		if(waveKeep >= 3100){
			game.gameState = STATE.Store;
			hud.addLevel(1);
			waveKeep = 0;
		}
		if(hud.getLevel() > 6){
			if(spawnChance >= 5){
					spawnChance--;					
			}
		}
	}
	
	public void spawnMultiple(){
		switch(r.nextInt(6)){
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
		}
	}
	
	public void spawnRandom(){
		switch(r.nextInt(4)){
		case 0: spawnR();
		case 1: spawnL();
		case 2: spawnU();
		case 3:	spawnD();
		}
	}
	public void spawnR(){
		if(handler.objectList.size()<300){
			handler.addObject(new BasicEnemy(Game.WIDTH, r.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
			if(r.nextInt(spawnChance) <= 0){
				if(hud.getLevel() >= 5){
					handler.addObject(new RocketEnemy(Game.WIDTH, r.nextInt(Game.HEIGHT), ID.RocketEnemy,handler));
				}
			}
		}
	}
	public void spawnL(){
		if(handler.objectList.size()<300){
			handler.addObject(new BasicEnemy(0, r.nextInt(Game.HEIGHT), ID.BasicEnemy,handler));
			if(r.nextInt(spawnChance) <= 0){
				if(hud.getLevel() >= 5){
					handler.addObject(new RocketEnemy(0, r.nextInt(Game.HEIGHT), ID.RocketEnemy,handler));
				}
			}
		}
	}
	public void spawnU(){
		if(handler.objectList.size()<300){
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), 0, ID.BasicEnemy, handler));
			if(r.nextInt(spawnChance) <= 0 ){
				if(hud.getLevel() >= 5){
					handler.addObject(new RocketEnemy(r.nextInt(Game.WIDTH), 0, ID.RocketEnemy,handler));
				}
			}
		}
	}
	public void spawnD(){
		if(handler.objectList.size()<300){
			handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), Game.HEIGHT, ID.BasicEnemy,handler));
			if(r.nextInt(spawnChance) <= 0 ){
				if(hud.getLevel() >= 5){
					handler.addObject(new RocketEnemy(r.nextInt(Game.WIDTH),Game.HEIGHT, ID.RocketEnemy,handler));
				}
			}
		}
	}
	public void reset(){
		scoreKeep = 400;
		waveKeep = 0;
		spawnChance = 9;
	}
}

//IGNORE --------------------------
//	public void tick() {
//		scoreKeep++;
//		if (scoreKeep >= 300) {
//			scoreKeep = 0;
//			hud.setWave(hud.getWave() + 1);
//			if(hud.getWave()<9){
//				spawnMultiple();
//			}else{
//				game.gameState = STATE.Store;
//				hud.addLevel(1);
//				hud.setWave(0);
//			}
//		}
//	}
//1000 - currentLevel | 0: spawn 
//1000 - 100. in 10 ticks = 0 and spawn
//1000 - 1 in 100 tick = 0 and spawnfor(int i=0; i < hud.getLevel(); i++){


//	public void spawnMultipleOld(){
//		for(int i=0; i < hud.getLevel(); i++){
//			switch(r.nextInt(6)){
//			case 0: spawnRandom();
//					break;
//			case 1: spawnR();
//					spawnL();
//					break;
//			case 2: spawnR();
//					spawnD();
//					break;
//			case 3: spawnR();
//					spawnU();
//					break;
//			case 4: spawnL();
//					spawnU();
//					break;
//			case 5: spawnL();
//					spawnD();
//					break;
//			case 6: spawnD();
//					spawnU();
//					break;
//			}
//		}
//	}