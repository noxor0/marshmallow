package marshmallow;


import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 960, HEIGHT = WIDTH / 12 * 9; 
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Gun gun;
		
	public STATE gameState = STATE.Menu; 
	
	public Game(){
		new Window(WIDTH, HEIGHT, "Marshmellow", this);
		handler = new Handler(); // add objects under this	
		hud = new HUD();
		menu = new Menu(this, handler, hud);
		gun = new Gun(this, handler, hud);
		spawner = new Spawn(this, handler, hud);
		
		this.addKeyListener(new KeyInput(handler)); // just used for esc
		
		if(gameState != STATE.Game){
			this.addMouseListener(menu);
		}
		this.addMouseListener(gun);
	}
	
	public synchronized void start(){ 
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop(){ 
		try{
			thread.join();
			running = false;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		 this.requestFocus();
         long lastTime = System.nanoTime();
         double amountOfTicks = 60.0;
         double ns = 1000000000 / amountOfTicks;
         double delta = 0;
         long timer = System.currentTimeMillis();
         int frames = 0;
         while(running){
                 long now = System.nanoTime();
                 delta += (now - lastTime) / ns;
                 lastTime = now;
                 while(delta >= 1){
                         tick();
                         delta--;
                 }
                 if(running)
                         render();
                 frames++;
                 if(System.currentTimeMillis() - timer > 1000){
                         timer += 1000;
                         System.out.println("FPS: " + frames);
                         frames = 0;
                 }
         }
         stop();
	}
	
	private void tick(){
		handler.tick();
		if(gameState == STATE.Game){
			hud.tick();
			spawner.tick();	
			gun.tick();
			//game over
			if(hud.getHp() <= 0){
				hud.setHp(100);
				spawner.reset();
				gameState = STATE.Menu;
				handler.clear();
			}
		}else {
			menu.tick();
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		drawBackground(g); //TODO: fix it up? Background Images
		handler.render(g);
		if(gameState == STATE.Game){
			hud.render(g);	
			gun.render(g);
		}else if (gameState != STATE.Game){
			handler.clear();
			menu.render(g);
		}
		g.dispose();
		bs.show();
	}
	
	public static int clamp(float x, int min, int max){
		if(x >= max)
			return (int) (x = max);
		else if(x <= min)
			return (int) (x = min);
		else
			return (int) x;
	}
	
	public static void main(String args[]){
		new Game();
	}
	
	public void drawBackground(Graphics g){
		try{
			BufferedImage img = ImageIO.read(getClass().getResourceAsStream("models"
					+ "/none.png"));			
			g.drawImage(img, 0, 0, WIDTH, HEIGHT, null);
		}catch(Exception e){
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);	
		}
	}
//	public static BufferedImage convert(BufferedImage image){
//		BufferedImage newImage = new BufferedImage(
//		        image.getWidth(), image.getHeight(),
//		        BufferedImage.TYPE_INT_ARGB);
//		    Graphics2D g = newImage.createGraphics();
//		    g.drawImage(image, 0, 0, null);
//		    g.dispose();
//		    return newImage;
//	}
}
