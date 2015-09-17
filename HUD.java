package marshmallow;


import java.awt.Color;
import java.awt.Graphics;
public class HUD {

	public static int Health = 1000;
	public int maxHealth = 1000;
	public int percentHp;
	
	public static int Force = 50;
	public int maxForce = 50;
	public int percentForce;
	
	private int score = 0;
	private int money = 2000;
	private int level = 15;
	
	private int gunner = 0;
	private int fixer = 100;
	
	private int fixCount = 0;
	
	public void tick(){
		score++;
		//FIXER------------
		fixCount += fixer;
		if(fixCount >= 100){
			fixCount = 0;
			if(Health < maxHealth){
				Health++;				
			}
		}
	}
	
	public void render(Graphics g){
		drawHud(g);
	}

	public void drawHud(Graphics g){
		//health bar
		percentHp = (int) ((float)Health/(float)maxHealth * 100);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
		g.setColor(Color.red);
		g.fillRect(16, 16, 199, 31);
		g.setColor(new Color(75, 255, 0));
		g.fillRect((int)16, (int)16, percentHp*2-1, 31);
		//TODO;force field bar
	//		percentForce = (int) ((float)Force/(float)maxForce *100);
	//		g.setColor(Color.blue);
	//		g.fillRect(15, 38, 200, 10);
	//		g.setColor(Color.cyan);
	//		g.drawString("test",290, 32);
		//info 
		g.setColor(Color.white);
		g.drawString(""+getHp()+"/"+getMaxHp(), 230, 32);
		g.drawString("Level: "+ level, 15, 64);
		g.drawString("Money: "+ money, 15, 78);
		g.drawString("Gunners: "+ gunner, 230, 46);
		g.drawString("Fixers: "+ fixer, 230, 60);
}

	public void setScore(int score){
		this.score = score;
	}
	public int getScore(){
		return score;
	}
	public void addLevel(int level){
		this.level += level;
	}
	public int getLevel(){
		return level;
	}
	public void setMoney(int money){
		this.money = money;
	}
	public void addMoney(int money){
		this.money += money;
	}
	public void spendMoney(int money){
		this.money -= money;
	}
	public int getMoney(){
		return money;
	}
	//---------------------
	public void setHp(int hpSet){
		Health = hpSet;
	}
	public void addHp(int hpAdd){
		Health += hpAdd;
	}
	public void removeHp(int hpSub){
		Health += hpSub;
	}
	public int getHp(){
		return Health;
	}
	public void addMaxHp(int hpMaxAdd){
		this.maxHealth += hpMaxAdd;
	}
	public int getMaxHp(){
		return this.maxHealth;
	}
	//---------------------
	public void setF(int fSet){
		Force = fSet;
	}
	public void addF(int fAdd){
		Force += fAdd;
	}
	public void removeF(int fSub){
		Force += fSub;
	}
	public int getF(){
		return Force;
	}
	public void addMaxF(int fMaxAdd){
		this.maxForce += fMaxAdd;
	}
	public int getMaxF(){
		return this.maxForce;
	}
	//---------------------
	public void setGun(int var){
		this.gunner = var;
	}
	public void addGun(){
		this.gunner++;
	}
	public int getGun(){
		return this.gunner;
	}
	public void setFix(int var){
		this.fixer = var;
	}
	public void addFix(){
		this.fixer++;
	}
	public int getFix(){
		return this.fixer;
	}
	
}
	
