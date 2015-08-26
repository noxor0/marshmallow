package marshmallow;


import java.awt.Color;
import java.awt.Graphics;

//TODO: maybe give it its own section on top of screen STH style?
//TODO: Time till end of wave?
public class HUD {

	public static int Health = 100;
	public int maxHealth = 100;
	public int percentHp;
	
	private int score = 0;
	private int money = 29;
	private int level = 5;
	
	private int gunner = 10;
	private int fixer = 1;
	
	private int fixCount = 0;
	
	public void tick(){
		score++;
		//HERE ARE FIXERS, YOU BLIND SHIT
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
		g.setColor(Color.red);
		g.fillRect(15, 15, 200, 32);
		g.setColor(new Color(75, 255, 0));
		g.fillRect((int)15, (int)15, percentHp*2, 32);
		g.setColor(Color.white);
		g.drawRect(15, 15, 200, 32);
	
		//info 
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
	
